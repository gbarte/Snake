package utils;

public enum Sizes {

    DEFAULT_MINIMUM_MAP_TILES(50);

    private int number;

    Sizes(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
