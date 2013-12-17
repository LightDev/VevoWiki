/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

public class VevoVideo {

    private String title;
    private String description;
    private String url;

    public VevoVideo(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        //fuckin conversion get set must be aware of
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return title;
    }
}
