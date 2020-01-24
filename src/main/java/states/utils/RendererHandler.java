package states.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Utility class that helps to use rendering.
 * Extracted, since multiple states are using it.
 */
public class RendererHandler {


    /**
     * Initialize the title object of the game.
     * @param stage of the screen.
     * @return Label object of the title.
     */
    public static Label initTitle(Stage stage) {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, 255, 0, 1));
        Label title = new Label("Lil' Snake", labelStyle);
        title.setSize(600, 120);
        title.setPosition(100,550);
        title.setFontScale(3);
        title.setAlignment(Align.center);
        stage.addActor(title);
        return title;
    }

    /**
     * Initalizes default screen.
     * @param batch of the screen.
     * @param stage of the screen.
     * @param bg background texture for the screen.
     */
    public static void render(Batch batch, Stage stage, Texture bg) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(bg, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
        batch.end();
    }

}
