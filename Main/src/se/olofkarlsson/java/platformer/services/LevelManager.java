package se.olofkarlsson.java.platformer.services;

import se.olofkarlsson.java.platformer.domain.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private final List<Level> levelList;

    public LevelManager() {
        Level testLevel = new Level(1000);
        testLevel.setName("Test Level");

        levelList = new ArrayList<Level>();
        levelList.add(testLevel);
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public Level findLevelById(int id) {
        if (id < 0 || id >= levelList.size()) {
            return null;
        } else {
            return levelList.get(id);
        }
    }

}
