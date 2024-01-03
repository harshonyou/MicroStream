package com.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagEngagementEventDTOTest {
    @Test
    public void testSetAndGetUserId() {
        TagEngagementEventDTO tagEvent = new TagEngagementEventDTO();
        String testUserId = "User123";

        tagEvent.setUserId(testUserId);
        assertEquals(testUserId, tagEvent.getUserId(), "setUserId or getUserId does not work correctly");
    }

    @Test
    public void testSetAndGetTag() {
        TagEngagementEventDTO tagEvent = new TagEngagementEventDTO();
        String testTag = "Technology";

        tagEvent.setTag(testTag);
        assertEquals(testTag, tagEvent.getTag(), "setTag or getTag does not work correctly");
    }

    @Test
    public void testSetAndGetSubscriptionStatus() {
        TagEngagementEventDTO tagEvent = new TagEngagementEventDTO();
        boolean testStatus = true;

        tagEvent.setSubscriptionStatus(testStatus);
        assertEquals(testStatus, tagEvent.isSubscriptionStatus(), "setSubscriptionStatus or isSubscriptionStatus does not work correctly");
    }

    @Test
    public void testAllArgsConstructor() {
        String testUserId = "User123";
        String testTag = "Technology";
        boolean testStatus = true;

        TagEngagementEventDTO tagEvent = new TagEngagementEventDTO(testUserId, testTag, testStatus);

        assertEquals(testUserId, tagEvent.getUserId(), "Constructor does not set userId correctly");
        assertEquals(testTag, tagEvent.getTag(), "Constructor does not set tag correctly");
        assertEquals(testStatus, tagEvent.isSubscriptionStatus(), "Constructor does not set subscriptionStatus correctly");
    }
}