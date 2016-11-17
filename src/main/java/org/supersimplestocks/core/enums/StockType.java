package org.supersimplestocks.core.enums;

import org.supersimplestocks.core.exceptions.SSSException;

/**
 *
 * @author kurt
 */
public enum StockType {

    COMMON("common"),
    PREFERRED("preferred");

    private final String value;

    StockType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StockType getType(String value) throws SSSException {
        value = value.toLowerCase();
        for (StockType type : StockType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new SSSException("StockType " + value + " not defined in enum");
    }
}
