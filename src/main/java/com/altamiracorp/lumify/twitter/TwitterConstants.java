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

package com.altamiracorp.lumify.twitter;

import com.altamiracorp.lumify.core.json.IntegerJsonProperty;
import com.altamiracorp.lumify.core.json.JSONObjectJsonProperty;
import com.altamiracorp.lumify.core.json.StringJsonProperty;
import com.altamiracorp.lumify.core.model.properties.DateLumifyProperty;
import com.altamiracorp.lumify.core.model.properties.IdentityLumifyProperty;
import com.altamiracorp.lumify.core.model.properties.TextLumifyProperty;
import java.nio.charset.Charset;

/**
 * Constants used by the Twitter processing spouts and bolts.
 */
public interface TwitterConstants {
    /**
     * The Tweet Concept name.
     */
    String CONCEPT_TWEET = "tweet";

    /**
     * The Twitter Handle (user) concept name.
     */
    String CONCEPT_TWITTER_HANDLE = "twitterHandle";

    /**
     * The Twitter Profile Image concept name.
     */
    String CONCEPT_TWITTER_PROFILE_IMAGE = "tweeterProfileImage";

    /**
     * The Twitter Mention concept name.
     */
    String CONCEPT_TWITTER_MENTION = CONCEPT_TWITTER_HANDLE;

    /**
     * The Twitter Hashtag concept name.
     */
    String CONCEPT_TWITTER_HASHTAG = "hashtag";

    /**
     * The Twitter URL concept name.
     */
    String CONCEPT_TWITTER_URL = "url";

    /**
     * The Tweeted relationship label.
     */
    String TWEETED_RELATIONSHIP = "twitterHandleTweetedTweet";

    /**
     * The Tweet Mention relationship label.
     */
    String TWEET_MENTION_RELATIONSHIP = "tweetMentionedHandle";

    /**
     * The Tweet Hashtag relationship label.
     */
    String TWEET_HASHTAG_RELATIONSHIP = "tweetHasHashtag";

    /**
     * The Tweet URL relationship label.
     */
    String TWEET_URL_RELATIONSHIP = "tweetHasURL";

    /**
     * The Twitter Character Set.
     */
    Charset TWITTER_CHARSET = Charset.forName("UTF-8");

    /**
     * The Twitter Queue Name.
     */
    String TWITTER_QUEUE_NAME = "twitterStream";

    /**
     * The Twitter text property.
     */
    StringJsonProperty JSON_TEXT_PROPERTY = new StringJsonProperty("text");

    /**
     * The Twitter created at property.
     */
    TwitterDateJsonProperty JSON_CREATED_AT_PROPERTY = new TwitterDateJsonProperty("created_at");

    /**
     * The Twitter user property.
     */
    JSONObjectJsonProperty JSON_USER_PROPERTY = new JSONObjectJsonProperty("user");

    /**
     * The Twitter coordinates property.
     */
    TwitterGeoPointJsonProperty JSON_COORDINATES_PROPERTY = new TwitterGeoPointJsonProperty("coordinates");

    /**
     * The Twitter favorite count property.
     */
    IntegerJsonProperty JSON_FAVORITE_COUNT_PROPERTY = new IntegerJsonProperty("favorite_count");

    /**
     * The Twitter retweet count property.
     */
    IntegerJsonProperty JSON_RETWEET_COUNT_PROPERTY = new IntegerJsonProperty("retweet_count");

    /**
     * The Twitter user screen name property.
     */
    StringJsonProperty JSON_SCREEN_NAME_PROPERTY = new StringJsonProperty("screen_name");

    /**
     * The Twitter user display name property.
     */
    StringJsonProperty JSON_DISPLAY_NAME_PROPERTY = new StringJsonProperty("name");

    /**
     * The Twitter status count property.
     */
    IntegerJsonProperty JSON_STATUS_COUNT_PROPERTY = new IntegerJsonProperty("statuses_count");

    /**
     * The Twitter followers count property.
     */
    IntegerJsonProperty JSON_FOLLOWERS_COUNT_PROPERTY = new IntegerJsonProperty("followers_count");

    /**
     * The Twitter friends count property.
     */
    IntegerJsonProperty JSON_FRIENDS_COUNT_PROPERTY = new IntegerJsonProperty("friends_count");

    /**
     * The Twitter user description property.
     */
    StringJsonProperty JSON_DESCRIPTION_PROPERTY = new StringJsonProperty("description");

    /**
     * The Twitter profile image URL property.
     */
    StringJsonProperty JSON_PROFILE_IMAGE_URL_PROPERTY = new StringJsonProperty("profile_image_url");

    /**
     * The Lumify favorite count property.
     */
    IdentityLumifyProperty<Integer> LUMIFY_FAVORITE_COUNT_PROPERTY = new IdentityLumifyProperty<Integer>("favoriteCount");

    /**
     * The Lumify retweet count property.
     */
    IdentityLumifyProperty<Integer> LUMIFY_RETWEET_COUNT_PROPERTY = new IdentityLumifyProperty<Integer>("retweetCount");

    /**
     * The Lumify status count property.
     */
    IdentityLumifyProperty<Integer> LUMIFY_STATUS_COUNT_PROPERTY = new IdentityLumifyProperty<Integer>("statusCount");

    /**
     * The Lumify follower count property.
     */
    IdentityLumifyProperty<Integer> LUMIFY_FOLLOWER_COUNT_PROPERTY = new IdentityLumifyProperty<Integer>("followerCount");

    /**
     * The Lumify following count property.
     */
    IdentityLumifyProperty<Integer> LUMIFY_FOLLOWING_COUNT_PROPERTY = new IdentityLumifyProperty<Integer>("followingCount");

    /**
     * The Lumify creation date property.
     */
    DateLumifyProperty LUMIFY_CREATION_DATE_PROPERTY = new DateLumifyProperty("creationDate");

    /**
     * The Lumify description property.
     */
    TextLumifyProperty LUMIFY_DESCRIPTION_PROPERTY = TextLumifyProperty.all("description");

    String ENTITY_HAS_IMAGE_HANDLE_PHOTO = "entityHasImageTweeterProfileImage";

    String HANDLE_CONTAINS_IMAGE_OF = "tweeterProfileImageContainsImageOfEntity";
}
