package se.olofkarlsson.java.platformer.domain;

/**
 * A class that represents a playable level.
 *
 * This class is not even remotely done. I'm thinking there could
 * be a bool for storing if the level has been visited or not, for example.
 *
 * @author Olof Karlsson
 */
public class Level {

    private final int id;
    private String name;

    public Level(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
