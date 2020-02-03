package Sem1;

import java.io.Serializable;

public class Food implements Serializable {
    private final String name;
    private final int nutritious;

    public Food(String name, int nutritious) {
        this.name = name;
        this.nutritious = nutritious;
    }

    public String getName() {
        return name;
    }

    public int getNutritious() {
        return nutritious;
    }

}
