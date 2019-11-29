package objects.base;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.badlogic.gdx.graphics.Texture;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InteractableObjectTest {

    @Mock
    private transient Function<Void, Void> action;

    @Mock
    private transient Texture texture;

    @Test
    void constructorCollidingTest() {

        InteractableObject obj1 = new InteractableObject(texture, false, action) {
        };

        assertFalse(obj1.getIsCollidable());

    }

    @Test
    void actionWorkflowTest() {

        InteractableObject obj1 = new InteractableObject(texture, action) {
        };

        obj1.interact();

        Mockito.verify(action).apply(null);

    }


}
