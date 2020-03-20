package Sem1;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataSerializer implements Serializer {
    @Override
    public String Serialize(Animal animal) {
        String path = "data.bin";
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(path)))
        {
            dos.writeUTF(animal.getName());
            dos.writeInt(animal.getAge());
            dos.writeUTF(animal.getType().toString());
            for (Food food : animal.getCanEat()) {
               dos.writeInt(food.getNutritious());
               dos.writeUTF(food.getName());
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return path;
    }

    @Override
    public Animal Deserialize(String path) {
        Animal animal = null;
        try(DataInputStream dos = new DataInputStream(new FileInputStream(path)))
        {
            String name = dos.readUTF();
            int age = dos.readInt();
            AnimalType type = AnimalType.valueOf(dos.readUTF());
            List<Food> list = new ArrayList<>();
            try {
                while (true) {
                    Integer nutritious = dos.readInt();
                    String fName = dos.readUTF();
                    list.add(new Food(fName, nutritious));
                }
            }
            catch (Exception e) {}
            animal = new Animal(type, name, age, list);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return animal;
    }
}
