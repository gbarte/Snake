import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class GameMain extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    GameMap gameMap;
    OrthographicCamera orthographicCamera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("assets/pkm.jpg");

        orthographicCamera = new OrthographicCamera();
        //false cz u wanna draw from bottom left ipv top left
        System.out.println("width is " + Gdx.graphics.getWidth());
        System.out.println("height is " + Gdx.graphics.getHeight());
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
        gameMap.render(orthographicCamera);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
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
