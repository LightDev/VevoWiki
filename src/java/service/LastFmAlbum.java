package service;

import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class LastFmAlbum {

    public LastFmAlbum(String name, String description, String releaseYear) {
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }
    private String name;
    private String description;
    private String releaseYear;
    private ArrayList<VevoVideo> songs;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
}
