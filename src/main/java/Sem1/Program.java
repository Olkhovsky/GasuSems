package Sem1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Food milk = new Food("milk", 5);
        Food apple = new Food("red apple", 10);
        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(apple);
        Animal cat = new Animal(AnimalType.Walking, "Boris", 1, list);

        DataSerializer serializer = new DataSerializer();
        String path = serializer.Serialize(cat);
        cat = serializer.Deserialize(path);
        System.out.println(cat.Eats());
    }


}
