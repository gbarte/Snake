import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class GameMain extends ApplicationAdapter {

    GameMap gameMap;
    OrthographicCamera orthographicCamera;

    @Override
    public void create() {
        orthographicCamera = new OrthographicCamera();
        //false cz u wanna draw from bottom left ipv top left
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();
        gameMap = new TiledGameMap();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1); //this changes the background color, number between 0-1
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //uncomment to be able to move the map
        /*if (Gdx.input.isTouched()) {
            orthographicCamera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            orthographicCamera.update();
        } */

        if (Gdx.input.justTouched()) {
            Vector3 position = orthographicCamera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            //might have to change the layer to 1!!!
            TileType type = getGameMap().getTileTypeByLocation(0, position.x, position.y);

            if (type != null) {
                System.out.println("Id is " + type.getId() + " name is " + type.getName());
            }
        }
        gameMap.render(orthographicCamera);
    }

    @Override
    public void dispose() {
        gameMap.dispose(orthographicCamera);
    }


    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }
}
