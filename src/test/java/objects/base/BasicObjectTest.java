package objects.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class BasicObjectTest {

    @Mock
    private transient Texture texture;

    @Test
    void constructorSimpleTest() {
        BasicObject obj = new BasicObject(texture) {
        };

        assertTrue(obj.getIsCollidable(), "The object should be collidable by default");
    }

    @Test
    void constructorCollidableTest() {
        BasicObject obj = new BasicObject(texture, false) {
        };

        assertFalse(
                obj.getIsCollidable(),
                "The object should be not collidable if specified in the constructor"
        );
    }

    @Test
    void changeSpriteWithTextureTest() {
        BasicObject obj = new BasicObject(texture) {
        };
        Texture texture2 = Mockito.mock(Texture.class);

        Mockito.when(texture2.getHeight()).thenReturn(5);
        obj.setSprite(texture2);

        assertEquals(obj.getSpriteRectangle().height, 5);

    }

    @Test
    void changeCollidablePropertyTest() {
        BasicObject obj = new BasicObject(texture) {
        };

        assertTrue(obj.getIsCollidable());
        obj.setIsCollidable(false);
        assertFalse(obj.getIsCollidable(), "The setter did not change the collidable property");
    }

    @Test
    void changeSpritePositionTest() {
        BasicObject obj = new BasicObject(texture) {
        };

        Sprite sprite = Mockito.mock(Sprite.class);

        obj.setSprite(sprite);
        obj.spritePos(1, 2);

        Mockito.verify(sprite).setPosition(1, 2);
        assertEquals(obj.getSpriteRectangle(), sprite.getBoundingRectangle());
    }

    @Test
    void isCollidingPositiveTest() {

        // Setup
        Sprite sprite1 = Mockito.mock(Sprite.class);
        BasicObject obj1 = new BasicObject(texture) {
        };
        obj1.setSprite(sprite1);

        Sprite sprite2 = Mockito.mock(Sprite.class);
        BasicObject obj2 = new BasicObject(texture) {
        };
        obj2.setSprite(sprite2);


        Mockito.when(sprite1.getBoundingRectangle()).thenReturn(new Rectangle(1, 5, 2, 2));
        Mockito.when(sprite2.getBoundingRectangle()).thenReturn(new Rectangle(1, 5, 2, 2));

        // Calling tested method
        boolean outcome = obj1.isColliding(obj2);

        // Verifying outcome
        assertTrue(outcome);

    }

    @Test
    void isCollidingNegativeTest() {

        // Setup
        Sprite sprite1 = Mockito.mock(Sprite.class);
        BasicObject obj1 = new BasicObject(texture) {
        };
        obj1.setSprite(sprite1);

        Sprite sprite2 = Mockito.mock(Sprite.class);
        BasicObject obj2 = new BasicObject(texture) {
        };
        obj2.setSprite(sprite2);


        Mockito.when(sprite1.getBoundingRectangle()).thenReturn(new Rectangle(1, 10, 2, 2));
        Mockito.when(sprite2.getBoundingRectangle()).thenReturn(new Rectangle(1, 5, 2, 2));

        // Calling tested method
        boolean outcome = obj1.isColliding(obj2);

        // Verifying outcome
        assertFalse(outcome);

    }

    @Test
    void drawTest() {

        Sprite sprite1 = Mockito.mock(Sprite.class);
        BasicObject obj1 = new BasicObject(texture) {
        };
        obj1.setSprite(sprite1);

        Batch batch = Mockito.mock(Batch.class);
        obj1.draw(batch, 1.0f);

        Mockito.verify(sprite1).draw(batch);

    }

}
