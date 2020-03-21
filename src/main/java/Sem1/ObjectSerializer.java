package Sem1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectSerializer implements Serializer {
    @Override
    public String Serialize(Animal animal, String path) {
        Path _path = Paths.get(path);
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(_path))) {
            outputStream.writeObject(animal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path.toString();
    }

    @Override
    public Animal Deserialize(String path) {
        Animal newAnimal = null;
        Path _path = Paths.get(path);
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(_path))) {
            newAnimal = (Animal) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newAnimal;
    }
}
