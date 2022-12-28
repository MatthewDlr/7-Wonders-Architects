package errorsCenter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DataChecking {
    
    public static void checkIfFileIsCorrect(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Error in DataChecking Files Check : Failed to load " + path);
        }
        
        if (!Files.isReadable(Paths.get(path))) {
            throw new IllegalArgumentException("Error in DataChecking Files Check : " + path + " is not readable");
        }
        
    }
    
}
