import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;

public class SnakeBody {
    protected static final float CELL_SIZE = 50;
    private static final int INITIAL_LENGTH = 2;
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
        growSnake(INITIAL_LENGTH);
    }
//
//    public Texture getHeadTexture() {
//        return headTexture;
//    }
//
//    public void setHeadTexture(Texture headTexture) {
//        this.headTexture = headTexture;
//    }
//    public float getCELL_SIZE() {
//        return CELL_SIZE;
//    }
//
//    public int getINITIAL_LENGTH() {
//        return INITIAL_LENGTH;
//    }

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

    /**
     * Grows the snake body by one body part
     */
    public void growSnake() {
        int snakeSize = bodyParts.size() + 1;
        bodyParts.add(new BodyPart(this.headX - (snakeSize * CELL_SIZE), this.headY));
    }

    /**
     * Grows the snake body by a specified number of body parts
     * @param length - by how many body parts the snake will be grown
     */
    public void growSnake(int length) {
        for(int i = 0; i < length; i++)
            this.growSnake();
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
        shapeRenderer.setColor(new Color(Color.GREEN));
//        shapeRenderer.rect(this.headX, this.getHeadY(), 16, 16);
        shapeRenderer.rect(this.headX, this.getHeadY(), CELL_SIZE, CELL_SIZE);
//        growSnake();
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                shapeRenderer.rect(bp.getX(), bp.getY(), CELL_SIZE, CELL_SIZE);
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
                headX += CELL_SIZE;
                break;
            case LEFT:
                updateBodyPartsPosition(headX, headY);
                headX -= CELL_SIZE;
                break;
            case UP:
                updateBodyPartsPosition(headX, headY);
                headY += CELL_SIZE;
                break;
            case DOWN:
                updateBodyPartsPosition(headX, headY);
                headY -= CELL_SIZE;
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
