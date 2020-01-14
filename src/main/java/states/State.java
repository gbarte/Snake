package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class state.
 */
interface State {

    void handleInput();

    void update(float dt);

    void render(SpriteBatch batch);

    void dispose();

}
