package Sem1;

import java.nio.file.Path;

public interface Serializer {
    String Serialize (Animal animal);
    Animal Deserialize (String path);
}
