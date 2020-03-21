package Sem1;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnimalSaver {
    private final Serializer serializer;
    public AnimalSaver(Serializer serializer) {
        this.serializer = serializer;
    }

    public List<String> Save(List<Animal> animals) {
        List<String> paths = new ArrayList<>();
        for (Animal animal : animals) {
            String path = UUID.randomUUID().toString();
            serializer.Serialize(animal, path);
            paths.add(path);
        }
        return  paths;
    }

    public List<Animal> Load(List<String> paths) {
        List<Animal> animals = new ArrayList<>();
        for (String path : paths) {
            animals.add(serializer.Deserialize(path));
        }
        return  animals;
    }


}
