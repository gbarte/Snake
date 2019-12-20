package snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import gamelogic.Coordinate;

import java.util.LinkedList;

public class SnakeBody {
    public static final int CELL_SIZE = 50;
    private static final int INITIAL_LENGTH = 2;

    private Coordinate headCoord;
    private LinkedList<BodyPart> bodyParts;
    private Direction currDir;

    public enum Direction { LEFT, RIGHT, UP, DOWN }

    /**
     * Constructs a snake with INITIAL_LENGTH amount of bodyparts.
     *
     * @param headX - X coordinate of head
     * @param headY - Y coordinate of head
     */
    public SnakeBody(int headX, int headY) {
        this.headCoord = new Coordinate(headX / 2, headY / 2);
        this.currDir = Direction.RIGHT;
        this.bodyParts = new LinkedList<BodyPart>();
        growSnake(INITIAL_LENGTH);
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

    public Coordinate getHeadCoord() {
        return headCoord;
    }

    public void setHeadCoord(Coordinate headCoord) {
        this.headCoord = headCoord;
    }

    /**
     * Grows the snake body by one body part.
     */
    public void growSnake() {
        if (bodyParts.size() == 0) {
            bodyParts.add(new BodyPart(headCoord.getCoordinateX(), headCoord.getCoordinateY()));
        } else if (bodyParts.size() > 0) {
            int tailID = bodyParts.size() - 1;
            BodyPart tail = bodyParts.get(tailID);
            Coordinate tailCoord = tail.getCoordinate();
            bodyParts.add(new BodyPart(tailCoord.getCoordinateX(), tailCoord.getCoordinateY()));
        }
    }

    /**
     * Grows the snake body by a specified number of body parts.
     *
     * @param length - by how many body parts the snake will be grown
     */
    public void growSnake(int length) {
        for (int i = 0; i < length; i++) {
            this.growSnake();
        }
    }

    /**
     * First renders the head of the snake as a rectangle.
     * Then loops through the bodyparts and renders those.
     *
     * @param shapeRenderer - ShapeRenderer object
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void renderSnake(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(Color.GREEN));
        int x = this.headCoord.getCoordinateX();
        int y = this.headCoord.getCoordinateY();
        shapeRenderer.rect(x, y, CELL_SIZE, CELL_SIZE);
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                Coordinate bodyCoord = bp.getCoordinate();
                shapeRenderer.rect(bodyCoord.getCoordinateX(), bodyCoord.getCoordinateY(),
                        CELL_SIZE, CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Updates currDir to the given direction.
     *
     * @param snakeDirection - Updates currDir to this direction
     */
    public void moveSnake(Direction snakeDirection) {
        switch (snakeDirection) {
            case RIGHT:
                updateBodyPartsPosition(headCoord);
                headCoord.addToX(CELL_SIZE);
                break;
            case LEFT:
                updateBodyPartsPosition(headCoord);
                headCoord.subtractFromX(CELL_SIZE);
                break;
            case UP:
                updateBodyPartsPosition(headCoord);
                headCoord.addToY(CELL_SIZE);
                break;
            case DOWN:
                updateBodyPartsPosition(headCoord);
                headCoord.subtractFromY(CELL_SIZE);
                break;
            default:
                // will not execute
        }
    }

    /**
     * Updates the position of each body part.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void updateBodyPartsPosition(Coordinate coordinate) {
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                int currX = bp.getCoordinate().getCoordinateX();
                int currY = bp.getCoordinate().getCoordinateY();
                bp.updateBodyPartPos(coordinate);
                coordinate = new Coordinate(currX, currY);
            }
        }
    }

}