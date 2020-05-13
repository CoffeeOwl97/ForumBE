package com.example.ForumBE.service.exception;

public class TopicTakenException extends Exception {

    public TopicTakenException(String topic) {
        super("Topic already exists: " + topic);
    }
}
