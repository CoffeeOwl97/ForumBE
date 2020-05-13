package com.example.ForumBE.service.exception;

public class InvalidTopicException extends Exception {

    public InvalidTopicException(String topic) {
        super("Topic does not exist: " + topic);
    }
}
