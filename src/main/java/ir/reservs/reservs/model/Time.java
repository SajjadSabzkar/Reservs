package ir.reservs.reservs.model;

public class Time {
    private String start;
    private String end;
    private String price;

    Time(String start,
         String end,
         String price) {

    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
