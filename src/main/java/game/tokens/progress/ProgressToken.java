package game.tokens.progress;

public abstract class ProgressToken {
    private final String name;
    private final String description;

    public ProgressToken(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String GetName() {
        return name;
    }

    public String GetDescription() {
        return description;
    }


}
