/*
 * Copyright 2014 Altamira Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.altamiracorp.lumify.twitter.storm;

import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.altamiracorp.lumify.twitter.TwitterEntityType;
import com.altamiracorp.securegraph.Vertex;
import org.json.JSONObject;

/**
 * This Bolt accepts an input Tuple containing a JSON-serialized Twitter
 * <a href="https://dev.twitter.com/docs/platform-objects/tweets">Tweet</a>
 * object in the <code>json</code> field and the Lumify GraphVertex representing
 * the Tweet artifact in the <code>tweetVertex</code> field.  It identifies entities
 * found in the Tweet text and creates or updates them in the Lumify system, linking
 * them to the Tweet.
 * 
 * <h2>Input Tuple:</h2>
 * <table>
 * <tr><th>Field</th><th>Type</th><th>Value</th></tr>
 * <tr><td>json</td><td>String</td><td>serialized Tweet JSON object</td></tr>
 * <tr><td>tweetVertex</td><td>GraphVertex</td><td>the Lumify GraphVertex for the Tweet artifact</td></tr>
 * </table>
 */
public class TweetEntityExtractionBolt extends BaseTwitterBolt {
    /**
     * The entity type this bolt is targeting.
     */
    private final TwitterEntityType entityType;

    /**
     * Create a new TweetEntityExtractionBolt.
     * @param type the entity type to extract
     */
    public TweetEntityExtractionBolt(final TwitterEntityType type) {
        assert type != null;
        this.entityType = type;
    }
    
    @Override
    protected void processJson(final JSONObject json, final Tuple input) throws Exception {
        Vertex tweetVertex = (Vertex) input.getValueByField(TwitterStormConstants.TWEET_VERTEX_FIELD);
        getTwitterProcessor().extractEntities(getProcessId(), json, tweetVertex, entityType);
    }

    @Override
    public void declareOutputFields(final OutputFieldsDeclarer ofd) {
    }
}
