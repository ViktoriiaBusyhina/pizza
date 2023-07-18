package com.example.pizza.enam;
/**
 * The PizzaSize enum represents the sizes of different pizza options.
 */
public enum PizzaSize {

    KIDS("24 cm"),
    MEDIUM("26 cm"),
    NORMAL("30 cm"),
    BIG("33 cm"),
    XXL("40 cm");

    private final String description;

    /**
     * Constructs a PizzaSize enum with the specified description.
     *
     * @param description the description of the pizza size
     */
    PizzaSize(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the pizza size.
     *
     * @return the description of the pizza size
     */
    public String getDescription() {
        return description;
    }
}
