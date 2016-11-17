package org.supersimplestocks.core.enums;

import org.supersimplestocks.core.exceptions.SSSException;

/**
 *
 * @author kurt
 */
public enum Action {
    BUY("buy"),
    SELL("sell");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Action getType(String value) throws SSSException {
        value = value.toLowerCase();
        for (Action type : Action.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new SSSException("Action " + value + " not defined in enum");
    }
}
