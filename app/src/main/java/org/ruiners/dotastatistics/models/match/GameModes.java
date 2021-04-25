package org.ruiners.dotastatistics.models.match;

import java.util.HashMap;
import java.util.Map;

public class GameModes {
    private final Map<Integer, String> modes;
    private static GameModes sInstance;

    private GameModes() {
        modes = new HashMap<Integer, String>();
        modes.put(1, "All Pick");
        modes.put(2, "Captains Mode");
        modes.put(3, "Random Draft");
        modes.put(4, "Single Draft");
        modes.put(5, "All Random");
        modes.put(6, "Intro");
        modes.put(7, "Diretide");
        modes.put(8, "Captains Draft");
        modes.put(9, "Greeveling");
        modes.put(10, "Tutorial");
        modes.put(11, "Mid Only");
        modes.put(12, "Least Played");
        modes.put(13, "Limited Heroes");
        modes.put(14, "Matchmaking");
        modes.put(15, "Custom");
        modes.put(16, "Captains Draft");
        modes.put(17, "Draft");
        modes.put(18, "Ability Draft");
        modes.put(19, "Event");
        modes.put(20, "All Random Death Match");
        modes.put(21, "1v1 Mid");
        modes.put(22, "All Draft");
        modes.put(23, "Turbo");
        modes.put(24, "Mutation");
    }

    public String getData(Integer i) {
        return modes.get(i);
    }

    public synchronized static GameModes getInstance() {
        if (sInstance == null) {
            sInstance = new GameModes();
        }
        return sInstance;
    }
}
