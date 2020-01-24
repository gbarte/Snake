package states.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Utility class that helps to create a games rule dialog.
 * Extracted, since multiple states are using it.
 */
public class GameRulesDialog {

    /**
     * Action to display the rules on the screen.
     */
    public static void display(Stage stage) {
        Skin uiSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        Dialog dialog = new Dialog("Rules", uiSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Use 'WASD' to move the snake.\n"
                + "Eat food to grow your snake.\n"
                + "Game will end when you either hit yourself or the wall.\n"
                + "Press p to pause the game.\n"
                + "Press q to quit the game.\n"
                + "Enjoy :) ");
        dialog.button("OK", true);
        dialog.show(stage);
    }

}
