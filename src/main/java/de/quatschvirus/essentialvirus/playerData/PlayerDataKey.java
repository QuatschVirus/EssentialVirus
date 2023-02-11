package de.quatschvirus.essentialvirus.playerData;

import java.util.function.Function;

public enum PlayerDataKey {

    ELYTRA_SAVING(false);

    private final Object defaultValue;
    private final Function<Object, Object> onChange;


    PlayerDataKey(Object defaultValue, Function<Object, Object> onChange) {
        this.defaultValue = defaultValue;
        this.onChange = onChange;
    }

    PlayerDataKey(Object defaultValue) {
        this.defaultValue = defaultValue;
        this.onChange = (Object o) -> 0;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Object change(Object o) {
        return onChange.apply(o);
    }
}
