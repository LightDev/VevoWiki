package service;

import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class Band {

    public Band(String name, String description) {
        this.name = name;
        this.description = description;
    }
    private String name;
    private String description;
    private ArrayList<LastFmAlbum> albums;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
