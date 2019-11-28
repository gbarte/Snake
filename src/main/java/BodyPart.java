public class BodyPart {
//    private float headX, headY;
    private float x, y;
    //add body part image or rectangle
//    Texture texture;

    public BodyPart(float x, float y) {
//        this.texture = texture;
        this.x = x;
        this.y = y;
    }

//    public float getHeadX() {
//        return headX;
//    }
//
//    public void setHeadX(float headX) {
//        this.headX = headX;
//    }
//
//    public float getHeadY() {
//        return headY;
//    }
//
//    public void setHeadY(float headY) {
//        this.headY = headY;
//    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Moves body part to new coordinates
     * @param x - New x coordinate of position of bodypart.
     * @param y - New y coordinate of position of bodypart.
     */
    public void updateBodyPartPos(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
