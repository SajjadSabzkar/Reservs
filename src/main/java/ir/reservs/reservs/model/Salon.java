package ir.reservs.reservs.model;


/**
 * Created by mhrezai on 03/10/2018.
 */
public class Salon {
    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private int week;
    private int minPrice;
    private int maxPrice;
    private String cityName;

    public Salon() {
    }

    public Salon(int id, String title, String description, String thumbnail, int week, int minPrice,
                 int maxPrice, String cityName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.week = week;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.cityName = cityName;
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

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public String getCityName() {
        return cityName;
    }
}
