package main;

public class Plot {
    public int numCrops;

    public String type;

    public String title;

    public Plot() {
        numCrops = 0;
        type = "None";
        title = "";
    }

    public Plot(int numCrops, String type, int title) {
        this.numCrops = numCrops;
        this.type = type;
        this.title = "Plot " + title;
    }
}
