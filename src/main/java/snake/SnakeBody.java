package snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import gamelogic.Coordinates;

import java.util.LinkedList;

public class SnakeBody {
    public static final int CELL_SIZE = 50;
    private static final int INITIAL_LENGTH = 2;

    private Coordinates headCoordinates;
    private LinkedList<BodyPart> bodyParts;
    private Direction currDir;

    public enum Direction { LEFT, RIGHT, UP, DOWN }

    /**
     * Constructs a snake with INITIAL_LENGTH amount of bodyparts.
     * @param headX - X coordinate of head
     * @param headY - Y coordinate of head
     */
    public SnakeBody(int headX, int headY) {
        this.headCoordinates = new Coordinates(headX / 2, headY / 2);
        this.currDir = Direction.UP;
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

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public void setHeadCoordinates(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
    }

    /**
     * Grows the snake body by one body part.
     */
    public void growSnake() {
        int snakeSize = bodyParts.size() + 1;
        bodyParts.add(new BodyPart(headCoordinates.getCoordinateX() - (snakeSize * CELL_SIZE), headCoordinates.getCoordinateY()));
    }

    /**
     * Grows the snake body by a specified number of body parts.
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
     * @param shapeRenderer - ShapeRenderer object
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void renderSnake(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(Color.GREEN));
        shapeRenderer.rect(this.headCoordinates.getCoordinateX(), this.headCoordinates.getCoordinateY(), CELL_SIZE, CELL_SIZE);
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                shapeRenderer.rect(bp.getCoordinates().getCoordinateX(), bp.getCoordinates().getCoordinateY(), CELL_SIZE, CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Updates currDir to the given direction.
     * @param snakeDirection - Updates currDir to this direction
     */
    public void moveSnake(Direction snakeDirection) {
        switch (snakeDirection) {
            case RIGHT:
                updateBodyPartsPosition(headCoordinates);
                headCoordinates.addToX(CELL_SIZE);
                break;
            case LEFT:
                updateBodyPartsPosition(headCoordinates);

                headCoordinates.subtractFromX(CELL_SIZE);
                break;
            case UP:
                updateBodyPartsPosition(headCoordinates);
                headCoordinates.addToY(CELL_SIZE);
                break;
            case DOWN:
                updateBodyPartsPosition(headCoordinates);
                headCoordinates.subtractFromY(CELL_SIZE);
                break;
            default:
                // will not execute
        }
    }

    /**
     * Updates the position of each body part.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void updateBodyPartsPosition(Coordinates coordinates) {
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                int currX = bp.getCoordinates().getCoordinateX();
                int currY = bp.getCoordinates().getCoordinateY();
                bp.updateBodyPartPos(coordinates);
                coordinates = new Coordinates(currX, currY);
            }
        }
    }

}