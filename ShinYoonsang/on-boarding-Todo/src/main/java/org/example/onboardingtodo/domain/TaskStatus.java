package org.example.onboardingtodo.domain;

public enum TaskStatus {
    TODO, IN_PROGRESS, ON_HOLD, COMPLETED, CANCELLED;

    public static TaskStatus fromString(String status) {
        try {
            return TaskStatus.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid TaskStatus: " + status);
        }
    }
}
