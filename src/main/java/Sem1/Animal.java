package Sem1;

import java.io.Serializable;
import java.util.List;

public class Animal implements Serializable {
    private final AnimalType type;
    private final String name;
    private final int age;

    private List<Food> canEat;

    public Animal(AnimalType type, String name, int age, List<Food> canEat) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.canEat = canEat;
    }


    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String Eats() {
        String result = "";
        for (Food food : getCanEat()) {
            result += food.getName() + ", ";
        }
        return  result.substring(0, result.length() - 2);
    }

    public List<Food> getCanEat() {
        return canEat;
    }

}
