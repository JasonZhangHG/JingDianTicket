package com.education.myoschinatest.bean;

/**
 * Created by Json on 2017/5/16.
 */

public class MediaUpdateEvent {

    private String mediaUpdateEvent;

    public MediaUpdateEvent() {
    }

    public MediaUpdateEvent(String mediaUpdateEvent) {
        this.mediaUpdateEvent = mediaUpdateEvent;
    }


    public String getMediaUpdateEvent() {
        return mediaUpdateEvent;
    }

    public void setMediaUpdateEvent(String mediaUpdateEvent) {
        this.mediaUpdateEvent = mediaUpdateEvent;
    }

    @Override
    public String toString() {
        return "MediaUpdateEvent{" +
                "mediaUpdateEvent='" + mediaUpdateEvent + '\'' +
                '}';
    }
}
