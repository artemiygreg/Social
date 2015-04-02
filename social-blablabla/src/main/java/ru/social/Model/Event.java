package ru.social.Model;

/**
 * Created by Admin on 25.03.15.
 */
public class Event {
    private long id;
    private String title;
    private String body;
    private String image;
    private String date;

    public Event(){

    }
    public Event(long id, String title, String body, String image, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.image = image;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
