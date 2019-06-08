package ir.reservs.reservs.model;

public class ReserveHistory {
    private String name;
    private String location;
    private String date;
    private String start_time;
    private String end_time;

    public ReserveHistory(String name, String location, String date, String start_time, String end_time) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return start_time;
    }

    public String getEndTime() {
        return end_time;
    }

}
