package com.jvn1o.devlinks.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    ENGLISH("English"),
    KOREAN("Korean");

    private final String label;

    Language(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}