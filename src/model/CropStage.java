package model;

public enum CropStage {
    SEED("Seed"), IMMATURE("Immature"), MATURE("Mature"),
    DIRT("Dirt"), DEAD("Dead");

    private final String name;

    private CropStage(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
