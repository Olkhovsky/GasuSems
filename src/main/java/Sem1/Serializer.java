package Sem1;

import java.nio.file.Path;

public interface Serializer {
    String Serialize (Animal animal, String path);
    Animal Deserialize (String path);
}
