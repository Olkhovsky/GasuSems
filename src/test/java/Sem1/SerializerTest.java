package Sem1;

import org.graalvm.compiler.lir.LIRFrameState;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SerializerTest {

    @Test
    public void ObjectSerializerTest() {
        ObjectSerializer serializer = new ObjectSerializer();
        CatBoris(serializer);
        CatAndBird(serializer);
        Empty(serializer);
        InvertCatAndBird(serializer);
    }

    @Test
    public void DataSerializerTest() {
        DataSerializer serializer = new DataSerializer();
        CatBoris(serializer);
        CatAndBird(serializer);
        Empty(serializer);
        InvertCatAndBird(serializer);
    }

    public  void Empty(Serializer serializer) {
        List<Animal> animals = new ArrayList<>();

        AnimalSaver saver = new AnimalSaver(serializer);
        List<String> paths = saver.Save(animals);
        List<Animal> newList = saver.Load(paths);
        Assert.assertEquals(animals, newList);
    }

    public void CatAndBird(Serializer serializer) {
        Food milk = new Food("milk", 5);
        Food apple = new Food("red apple", 10);
        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(apple);
        String name = "Boris";
        int age = 1;
        AnimalType type = AnimalType.Walking;
        Animal cat = new Animal(type, name, age, list);


        List<Food> list2 = new ArrayList<>();
        Food worm = new Food("worm", 4);
        list2.add(milk);
        list2.add(apple);
        list2.add(worm);
        String name2 = "Owlzzz";
        int age2 = 2;
        AnimalType type2 = AnimalType.Flying;
        Animal bird = new Animal(type2, name2, age2, list2);

        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(bird);

        AnimalSaver saver = new AnimalSaver(serializer);
        List<String> paths = saver.Save(animals);
        List<Animal> newList = saver.Load(paths);
        Assert.assertEquals(animals, newList);
    }
    public void InvertCatAndBird(Serializer serializer) {
        Food milk = new Food("milk", 5);
        Food apple = new Food("red apple", 10);
        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(apple);
        String name = "Boris";
        int age = 1;
        AnimalType type = AnimalType.Walking;
        Animal cat = new Animal(type, name, age, list);


        List<Food> list2 = new ArrayList<>();
        Food worm = new Food("worm", 4);
        list2.add(milk);
        list2.add(apple);
        list2.add(worm);
        String name2 = "Owlzzz";
        int age2 = 2;
        AnimalType type2 = AnimalType.Flying;
        Animal bird = new Animal(type2, name2, age2, list2);

        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(bird);

        AnimalSaver saver = new AnimalSaver(serializer);
        List<String> paths = saver.Save(animals);
        Collections.reverse(paths);
        List<Animal> newList = saver.Load(paths);
        Assert.assertFalse(animals.equals(newList));
    }



    private void CatBoris(Serializer serializer) {
        Food milk = new Food("milk", 5);
        Food apple = new Food("red apple", 10);
        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(apple);
        String name = "Boris";
        int age = 1;
        AnimalType type = AnimalType.Walking;
        Animal cat = new Animal(type, name, age, list);

        String path = serializer.Serialize(cat, "BoxForBoris");
        Animal newCat = serializer.Deserialize(path);

        Assert.assertEquals(cat, newCat);
    }
}