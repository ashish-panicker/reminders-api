package org.example.remindersapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Priority {

    LOW, MEDIUM, HIGH, CRITICAL;

    /**
     * Creates a {@link Priority} enum instance from the given string value.
     * <p>
     * This factory method is used during JSON deserialization. It converts the
     * provided value to upper case before resolving it to a corresponding
     * {@code Priority} constant.
     *
     * @param value the input string representing a priority level
     * @return the matching {@code Priority} enum constant
     * @throws IllegalArgumentException if the value does not match any enum constant
     */
    @JsonCreator
    public static Priority from(String value) {
        return Priority.valueOf(value.toUpperCase());
    }

}
