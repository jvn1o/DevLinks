package com.jvn1o.devlinks.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceType {
    FREE("Free"),
    PAID("Paid"),
    FREE_AND_PAID("Free + Paid");

    private final String label;

    PriceType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}