import java.time.LocalDateTime;

public class Food {
    private int id;
    private String category;
    private String subcategory;
    private double value;
    private LocalDateTime date;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() { return id; }
    public String getCategory() { return category;}
    public String getSubcategory() { return subcategory;}
    public double getValue() { return value;}
    public LocalDateTime getDate() { return date;}
}

