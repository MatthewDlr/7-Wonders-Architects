package game.tokens.progress;

import errorsCenter.DataChecking;

public abstract class ProgressToken {
    
    private final String name;
    private final String description;
    private final String progressTokenPath;
    private final String progressTokenBackPath;
    
    public ProgressToken(String name, String description) {
        this.name = name;
        this.description = description;
        progressTokenPath = "src/main/resources/game/progressTokens/" + name + ".png";
        progressTokenBackPath = "src/main/resources/game/progressTokens/Back.png";
        
        DataChecking.checkIfFileIsCorrect(progressTokenPath);
        DataChecking.checkIfFileIsCorrect(progressTokenBackPath);
        
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    
}
