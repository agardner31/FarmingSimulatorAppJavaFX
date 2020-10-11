package model;

public class Plot {

    private Crop crop;

    private String type;

    private String title;

    public Plot() {
        type = "None";
        title = "";
    }

    public Plot(Crop crop, int title) {
        this.crop = crop;
        this.type = crop.getType();
        this.title = "Plot " + title;
    }

    public Crop getCrop() {
        return crop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
