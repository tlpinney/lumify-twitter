package com.altamiracorp.lumify.storm.twitter;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.Scheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import com.altamiracorp.lumify.core.cmdline.CommandLineBase;
import com.altamiracorp.lumify.model.KafkaJsonEncoder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.kafka.KafkaConfig;
import storm.kafka.SpoutConfig;

public class StormRunner extends CommandLineBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(StormRunner.class);

    private static final String CMD_OPT_LOCAL = "local";
    private static final String TOPOLOGY_NAME = "lumify-twitter";

    public static void main(String[] args) throws Exception {
        int res = new StormRunner().run(args);
        if (res != 0) {
            System.exit(res);
        }
    }

    public StormRunner() {
        initFramework = true;
    }

    @Override
    protected Options getOptions() {
        Options opts = super.getOptions();

        opts.addOption(
                OptionBuilder
                        .withLongOpt(CMD_OPT_LOCAL)
                        .withDescription("Run local")
                        .create()
        );

        return opts;
    }

    @Override
    protected int run(CommandLine cmd) throws Exception {
        boolean isLocal = cmd.hasOption(CMD_OPT_LOCAL);

        Config conf = new Config();
        conf.put("topology.kryo.factory", "com.altamiracorp.lumify.storm.DefaultKryoFactory");
        for (String key : getConfiguration().getKeys()) {
            conf.put(key, getConfiguration().get(key));
        }
        conf.setDebug(false);
        conf.setNumWorkers(2);

        StormTopology topology = createTopology();
        LOGGER.info("Created topology layout: " + topology);
        LOGGER.info(String.format("Submitting topology '%s'", TOPOLOGY_NAME));

        if (isLocal) {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(TOPOLOGY_NAME, conf, topology);

            while (!willExit()) {
                Utils.sleep(100);
            }

            cluster.killTopology(TOPOLOGY_NAME);
            cluster.shutdown();
        } else {
            StormSubmitter.submitTopology(TOPOLOGY_NAME, conf, topology);
        }

        return 0;
    }

    public StormTopology createTopology() {
        TopologyBuilder builder = new TopologyBuilder();
        createTwitterStreamTopology(builder);
        return builder.createTopology();
    }

    private void createTwitterStreamTopology(TopologyBuilder builder) {
        String spoutName = "twitterStreamSpout";
        builder.setSpout(spoutName, new TwitterStreamSpout(/*"/tweets"*/), 1);
        builder.setBolt(spoutName + "-bolt", new TwitterStreamingBolt(), 1)
                .shuffleGrouping(spoutName); //.shuffleGrouping("twitterStreamSpout");
    }
}