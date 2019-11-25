import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class SnakeBody {
    private int headX;
    private int headY;
    //head image or separate head rectangle or add to the array.
    private ShapeRenderer sr;
    private Array<BodyPart> bodyParts;
    private Direction currDir;

    public enum Direction {LEFT, RIGHT, UP, DOWN};

    public SnakeBody() {
//        headX = insert starting pos
//        headY = insert starting pos
        bodyParts = new Array<BodyPart>();
    }

//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }

    public ShapeRenderer getSr() {
        return sr;
    }

    public void setSr(ShapeRenderer sr) {
        this.sr = sr;
    }

    public Direction getCurrDir() {
        return currDir;
    }

    public void setCurrDir(Direction currDir) {
        this.currDir = currDir;
    }

    public int getHeadX() {
        return headX;
    }

    public void setHeadX(int headX) {
        this.headX = headX;
    }

    public int getHeadY() {
        return headY;
    }

    public void setHeadY(int headY) {
        this.headY = headY;
    }

    public Array<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(Array<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public void moveSnake(Direction snakeDirection) {
        switch (snakeDirection) {
            case RIGHT: {
                updateBodyPartsPosition();
                headX += 1;
            }
            case LEFT: {
                updateBodyPartsPosition();
                headX -= 1;
            }
            case UP: {
                updateBodyPartsPosition();
                headY += 1;
            }
            case DOWN: {
                updateBodyPartsPosition();
                headX += 1;
            }
        }
    }

    public void updateBodyPartsPosition() {
        int x = getHeadX();
        int y = getHeadY();
        if(bodyParts.size > 0) {
            for (BodyPart bp : bodyParts) {
                int currX = bp.getX();
                int currY = bp.getY();
                bp.updateBodyPartPos(x, y);
                x = currX;
                y = currY;
            }
        }
    }

    public void renderSnake(SpriteBatch batch) {
        for (BodyPart bp : bodyParts) {
            bp.drawBody(batch);
        }
    }

}
