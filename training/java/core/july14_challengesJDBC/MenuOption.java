package util;

public enum MenuOption {
    INSERT_ORDER(1),
    UPDATE_QUANTITY(2),
    DELETE_ORDER(3),
    VIEW_ORDERS(4),
    EXIT(0);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOption fromInt(int input) {
        for (MenuOption option : values()) {
            if (option.value == input) return option;
        }
        return null;
    }
}
