package objects.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Abstract class for future use and decoupling of the map objects.
 */
public abstract class BasicObject extends Actor {

    /**
     * Sprite of the object.
     */
    private transient Sprite sprite;

    /**
     * Defines whether or not the object
     * has a collision property (solid).
     */
    private boolean isCollidable = true;

    /**
     * Setter for the specific sprite.
     * @param sprite of the object.
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Setter for the sprite through texture.
     * @param texture of the object that converts to sprite.
     */
    public void setSprite(Texture texture) {
        sprite = new Sprite(texture);
    }


    /**
     * Setter for the collidable property.
     * @param isCollidable as a new state of the property.
     */
    public void setIsCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    /**
     * Getter for the collidable property.
     * @return True if the object is collidable.
     */
    public boolean getIsCollidable() {
        return this.isCollidable;
    }

    /**
     * Constructor for the object.
     * @param texture of the object that converts to sprite.
     */
    public BasicObject(Texture texture) {
        sprite = new Sprite(texture);
        spritePos(sprite.getX(), sprite.getY());
        setTouchable(Touchable.enabled);

    }

    /**
     * Constructor of the object.
     * @param texture of the object that converts to sprite.
     * @param isCollidable defines if the object is a solid object.
     */
    public BasicObject(Texture texture, boolean isCollidable) {
        this(texture);
        this.isCollidable = isCollidable;
    }

    /**
     * Sets position of the object.
     * @param x - position along X-axis of the window.
     * @param y - position along Y-axis of the window.
     */
    public void spritePos(float x, float y) {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public Rectangle getSpriteRectangle() {
        return sprite.getBoundingRectangle();
    }

    /**
     * Checks if the object is colliding with another object.
     * @param other object to check with.
     * @return True if two objects collide.
     */
    public boolean isColliding(BasicObject other) {
        Rectangle rectThis = this.sprite.getBoundingRectangle();
        Rectangle rectOther = other.sprite.getBoundingRectangle();
        return rectThis.overlaps(rectOther);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
