package com.example;

import java.util.Set;

public class Tags {
    private Set<String> tags;
    private boolean likeStatus;

    public Tags(Set<String> tags, boolean likeStatus) {
        this.tags = tags;
        this.likeStatus = likeStatus;
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tags=" + tags +
                ", likeStatus=" + likeStatus +
                '}';
    }
}
