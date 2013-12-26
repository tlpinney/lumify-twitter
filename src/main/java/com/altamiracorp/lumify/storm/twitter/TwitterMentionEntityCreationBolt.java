/*
 * Copyright 2013 Altamira Corporation.
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

package com.altamiracorp.lumify.storm.twitter;

import static com.altamiracorp.lumify.storm.twitter.TwitterConstants.*;

/**
 * This bolt creates entities from user mentions found in a tweet.
 */
public class TwitterMentionEntityCreationBolt extends BaseTwitterEntityCreationBolt {
    /**
     * The regular expression.
     */
    private static final String REGEX = "(@(\\w+))";
    
    @Override
    protected String getConceptName() {
        return TWITTER_MENTION_CONCEPT;
    }

    @Override
    protected String getTermRegex() {
        return REGEX;
    }

    @Override
    protected String getRelationshipLabel() {
        return TWEET_MENTION_RELATIONSHIP;
    }
}