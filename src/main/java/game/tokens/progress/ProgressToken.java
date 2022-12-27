package game.tokens.progress;

import java.io.File;

public abstract class ProgressToken {
    private final String name;
    private final String description;
    private final String progressTokenPath;
    private final String progressTokenBackPath;

    public ProgressToken(String name, String description) {
        this.name = name;
        this.description = description;
        this.progressTokenPath = "src/main/resources/game/progressTokens/" + name + ".png";
        this.progressTokenBackPath = "src/main/resources/game/progressTokens/Back.png";
        CheckIfFileExist(progressTokenPath);
        CheckIfFileExist(progressTokenBackPath);

    }
    private void CheckIfFileExist(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Error in ProgressToken Files Check : Failed to load " + path);
        }
    }

    public String GetName() {
        return name;
    }

    public String GetDescription() {
        return description;
    }


}
