package snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;

public class SnakeBody {
    private final float edgeSize = 50;
    private float headX;
    private float headY;
    private LinkedList<BodyPart> bodyParts;
//    private Texture headTexture;
    private Direction currDir;

    public enum Direction {LEFT, RIGHT, UP, DOWN};

    public SnakeBody(float headX, float headY) {
        this.headX = headX/2;
        this.headY = headY/2;
        this.currDir = Direction.UP;
        this.bodyParts = new LinkedList<BodyPart>();
        bodyParts.add(new BodyPart(this.headX - edgeSize, this.headY));
        bodyParts.add(new BodyPart(this.headX - (2 * edgeSize), this.headY));
    }

    public float getHeadX() {
        return headX;
    }

    public void setHeadX(float headX) {
        this.headX = headX;
    }

    public float getHeadY() {
        return headY;
    }

    public void setHeadY(float headY) {
        this.headY = headY;
    }

    public LinkedList<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(LinkedList<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public Direction getCurrDir() {
        return currDir;
    }

    public void setCurrDir(Direction currDir) {
        this.currDir = currDir;
    }

    public float getEdgeSize() {
        return edgeSize;
    }


    /**
     * First renders the head of the snake as a rectangle,
     * then loops through the bodyparts and renders those.
     *
     * @param shapeRenderer
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void renderSnake(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(Color.RED));
        shapeRenderer.rect(this.headX, this.getHeadY(), edgeSize, edgeSize);
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                shapeRenderer.rect(bp.getX(), bp.getY(), edgeSize, edgeSize);
            }
        }
        shapeRenderer.end();

    }

    /**
     * Updates currDir to the given direction
     * @param snakeDirection - Updates currDir to this direction
     */
    public void moveSnake(Direction snakeDirection) {
        switch (snakeDirection) {
            case RIGHT:
                updateBodyPartsPosition(headX, headY);
                headX += edgeSize;
                break;
            case LEFT:
                updateBodyPartsPosition(headX, headY);
                headX -= edgeSize;
                break;
            case UP:
                updateBodyPartsPosition(headX, headY);
                headY += edgeSize;
                break;
            case DOWN:
                updateBodyPartsPosition(headX, headY);
                headY -= edgeSize;
                break;
        }
    }

    /**
     * Updates the position of each body part
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void updateBodyPartsPosition(float x, float y) {
        if (bodyParts.size() > 0) {
                for (BodyPart bp : bodyParts) {
                    float currX = bp.getX();
                    float currY = bp.getY();
                    bp.updateBodyPartPos(x, y);
                    x = currX;
                    y = currY;
                }
            }
    }

}
