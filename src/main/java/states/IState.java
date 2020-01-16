package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * State interface.
 * There are all the necessary methods that all states should implement.
 */
interface IState {

    void handleInput();

    void update(float dt);

    void render(SpriteBatch batch);

    void dispose();

}
