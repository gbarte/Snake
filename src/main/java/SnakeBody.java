import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;

public class SnakeBody {
    private float headX;
    private float headY;
    private LinkedList<BodyPart> bodyParts;
//    private Texture headTexture;
    private Direction currDir;
    private float edgeSize;

    public enum Direction {LEFT, RIGHT, UP, DOWN};

    public SnakeBody() {
        this.headX = Gdx.graphics.getWidth()/2;
        this.headY = Gdx.graphics.getHeight()/2;
        this.currDir = Direction.UP;
        this.bodyParts = new LinkedList<BodyPart>();
        this.edgeSize = Gdx.graphics.getHeight()/20;
    }
//
//    public Texture getHeadTexture() {
//        return headTexture;
//    }
//
//    public void setHeadTexture(Texture headTexture) {
//        this.headTexture = headTexture;
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

    public float getEdgeSize() {
        return edgeSize;
    }

    public void setEdgeSize(float size) {
        this.edgeSize = size;
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
        shapeRenderer.rect(this.headX, this.getHeadY(), edgeSize, edgeSize);
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                System.out.println("lichaamsdeel");
                shapeRenderer.rect(bp.getX(), bp.getHeadY(), edgeSize, edgeSize);
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
                headX += edgeSize;
                updateBodyPartsPosition();
                break;
            case LEFT:
                headX -= edgeSize;
                updateBodyPartsPosition();
                break;
            case UP:
                headY += edgeSize;
                updateBodyPartsPosition();
                break;
            case DOWN:
                headY -= edgeSize;
                updateBodyPartsPosition();
                break;
        }
    }

    /**
     * Updates the position of each body part
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void updateBodyPartsPosition() {
        float x = getHeadX();
        float y = getHeadY();
        if(bodyParts.size() > 0) {
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
