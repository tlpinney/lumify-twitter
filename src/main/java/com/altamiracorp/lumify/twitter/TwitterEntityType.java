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

package com.altamiracorp.lumify.twitter;

import java.util.regex.Pattern;

import static com.altamiracorp.lumify.twitter.TwitterConstants.*;

/**
 * An Entity that can be extracted from a Tweet.
 */
public enum TwitterEntityType {
    MENTION(CONCEPT_TWITTER_MENTION, "(@(\\w+))", TWEET_MENTION_RELATIONSHIP, "user_mentions", "screen_name"),
    HASHTAG(CONCEPT_TWITTER_HASHTAG, "(#(\\w+))", TWEET_HASHTAG_RELATIONSHIP, "hashtags", "text"),
    URL(CONCEPT_TWITTER_URL, "((https?://[^\\s]+))", TWEET_URL_RELATIONSHIP, "urls", "url");

    /**
     * The Concept name for this Entity.
     */
    private final String conceptName;

    /**
     * The regular expression used to find this Entity in a Tweet.
     */
    private final Pattern termRegex;

    /**
     * The label of the relationship between this Entity and its source Tweet.
     */
    private final String relationshipLabel;

    private final String jsonKey;
    private final String signKey;

    /**
     * Create a new TwitterEntityType.
     *
     * @param concept      the name of the Concept for this Entity
     * @param regex        the regex used to identify these Entities
     * @param relationship the label for this Entity's relationship with its source Tweet
     */
    private TwitterEntityType(final String concept, final String regex, final String relationship, final String jsonKey, final String signKey) {
        this.conceptName = concept;
        this.termRegex = Pattern.compile(regex);
        this.relationshipLabel = relationship;
        this.jsonKey = jsonKey;
        this.signKey = signKey;
    }

    public String getConceptName() {
        return conceptName;
    }

    public Pattern getTermRegex() {
        return termRegex;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public String getSignKey() {
        return signKey;
    }

    public String getRelationshipLabel() {
        return relationshipLabel;
    }
}
