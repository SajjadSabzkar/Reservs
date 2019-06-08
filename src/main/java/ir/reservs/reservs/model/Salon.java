package ir.reservs.reservs.model;


/**
 * Created by mhrezai on 03/10/2018.
 */
public class Salon {
    int id;
    String title;
    String description;
    String thumbnail;
    int week;

    public Salon() {
    }

    public Salon(int id, String title, String description, String thumbnail, int week) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.week = week;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getWeek() {
        return week;
    }

}
