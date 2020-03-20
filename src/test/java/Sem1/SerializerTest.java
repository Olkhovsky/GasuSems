package Sem1;

import org.graalvm.compiler.lir.LIRFrameState;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SerializerTest {

    @Test
    public void ObjectSerializerTest() {
        ObjectSerializer serializer = new ObjectSerializer();
        CatBoris(serializer);
    }

    @Test
    public void DataSerializerTest() {
        DataSerializer serializer = new DataSerializer();
        CatBoris(serializer);
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

        String path = serializer.Serialize(cat);
        cat = serializer.Deserialize(path);

        Assert.assertEquals(name, cat.getName());
        Assert.assertEquals(age, cat.getAge());
        Assert.assertEquals(type, cat.getType());
        Assert.assertTrue(list.equals(cat.getCanEat()));
    }
}