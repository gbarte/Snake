package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.GridPoint2;
import utils.Direction;
import world.GameMap;

public class Player extends Sprite {

    private GridPoint2 headPosition;
    protected Direction currDir;
    protected GameMap gameMap;
    private Texture texture;
    private String texturePath;

    /**
     * Creates an uninitialized sprite.
     * The sprite will need a texture region and bounds set before it can be drawn.
     * This constructor only takes in a gameMap and uses default values for the rest.
     * @param gameMap GameMap on which to render snake.
     */
    public Player(GameMap gameMap) {
        //Texture texture = new Texture("assets/pixel.png");
        //GridPoint2 pos = new GridPoint2(gameMap.getHeight() / 2, gameMap.getWidth() / 2);
        //Direction currDir = Direction.RIGHT;
        this("assets/pixel.png",
                new GridPoint2((gameMap.getHeight() / 2), (gameMap.getWidth() / 2)),
                Direction.RIGHT,
                gameMap);
    }

    /**
     * Creates an uninitialized sprite.
     * The sprite will need a texture region and bounds set before it can be drawn.
     * @param texturePath The file path for the image of the texture.
     * @param headPosition The position of the snake's head.
     * @param currDir The current direction of the snake.
     * @param gameMap GameMap on which to render snake.
     */
    public Player(String texturePath, GridPoint2 headPosition, Direction currDir, GameMap gameMap) {
        this(new Texture(texturePath), headPosition, currDir, gameMap);
        this.texturePath = texturePath;
    }

    /**
     * Creates a sprite with width, height, and texture region equal to the size of the texture.
     *
     * @param texture Give this sprite a texture.
     */
    public Player(Texture texture, GridPoint2 headPosition, Direction currDir, GameMap gameMap) {
        super(texture);
        this.headPosition = headPosition;
        this.currDir = currDir;
        this.gameMap = gameMap;
        this.texture = texture;
    }

    protected void move(Direction direction) {
        switch (direction) {
            case RIGHT:
                setHeadPosition(new GridPoint2(getHeadX() + 1, getHeadY()));
                break;
            case LEFT:
                setHeadPosition(new GridPoint2(getHeadX() - 1, getHeadY()));
                break;
            case UP:
                setHeadPosition(new GridPoint2(getHeadX(), getHeadY() + 1));
                break;
            case DOWN:
                setHeadPosition(new GridPoint2(getHeadX(), getHeadY() - 1));
                break;
            default:
                // will not execute
        }

    }

    public GridPoint2 getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(GridPoint2 headPosition) {
        this.headPosition = headPosition;
    }

    public Direction getCurrDir() {
        return currDir;
    }

    public void setCurrDir(Direction currDir) {
        this.currDir = currDir;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public int getHeadX() {
        return headPosition.x;
    }

    public int getHeadY() {
        return headPosition.y;
    }
}
