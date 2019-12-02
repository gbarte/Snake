package objects.base;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        Coordinates that = (Coordinates) obj;
        if(this.x == that.x && this.y == that.y) {
            return true;
        }
        return false;
    }
}
