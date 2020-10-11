package model;

public class Plot {
    private int numCrops;

    private Crop[] crops;

    private String type;

    private String title;

    public Plot() {
        numCrops = 0;
        type = "None";
        title = "";
    }

    public Plot(int numCrops, String type, int title, String difficulty) {
        this.numCrops = numCrops;
        this.crops = new Crop[numCrops * 2];
        for (int i = 0; i < numCrops; i++) {
            this.crops[i] = new Crop(type, difficulty);
        }
        this.type = type;
        this.title = "Plot " + title;
    }

    public int getNumCrops() {
        return numCrops;
    }

    public void setNumCrops(int numCrops) {
        this.numCrops = numCrops;
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
