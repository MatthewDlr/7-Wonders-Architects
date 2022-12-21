package game.tokens.progress;

import java.util.ArrayList;

public abstract class ProgressToken {
    private String name;
    private String description;

    public ProgressToken(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ArrayList<ProgressToken> CreateInstanceOfAllTokens() {
        ArrayList<ProgressToken> listOfProgressTokens = new ArrayList<>();
        listOfProgressTokens.add(new Architecture());
        listOfProgressTokens.add(new Artisanat());
        listOfProgressTokens.add(new Culture());
        listOfProgressTokens.add(new Culture());
        listOfProgressTokens.add(new Décoration());
        listOfProgressTokens.add(new Economie());
        listOfProgressTokens.add(new Education());
        listOfProgressTokens.add(new Ingénierie());
        listOfProgressTokens.add(new Joaillerie());
        listOfProgressTokens.add(new Politique());
        listOfProgressTokens.add(new Propagande());
        listOfProgressTokens.add(new Science());
        listOfProgressTokens.add(new Stratégie());
        listOfProgressTokens.add(new Tactique());
        listOfProgressTokens.add(new Urbanisme());
        return listOfProgressTokens;
    }
    public String GetName() {
        return name;
    }

    public String GetDescription() {
        return description;
    }



}
