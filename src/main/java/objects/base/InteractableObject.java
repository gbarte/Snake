package objects.base;

import com.badlogic.gdx.graphics.Texture;

import java.util.function.Function;

/**
 * Abstract class for future use and decoupling of the map objects.
 */
public abstract class InteractableObject extends BasicObject {

    /**
     * Action after interaction with the snake.
     */
    private transient Function<Void, Void> action;

    /**
     * Constructor for the interactable object.
     * @param texture of the object that converts to sprite.
     */
    public InteractableObject(Texture texture, Function<Void, Void> action) {
        super(texture);
        this.action = action;
    }

    /**
     * Constructor of the interactable object.
     * @param texture of the object that converts to sprite.
     * @param isCollidable defines if the object is a solid object.
     */
    public InteractableObject(Texture texture, boolean isCollidable, Function<Void, Void> action) {
        super(texture, isCollidable);
        this.action = action;
    }

    /**
     * The public method that invokes action of the interactable object.
     */
    public void interact() {
        action.apply(null);
    }


}
