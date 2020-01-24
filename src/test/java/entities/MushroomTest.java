package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import world.GameMap;


public class MushroomTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new MushroomPowerUp();
    }

    @Test
    void mushroomActionTest() {
        GameMap fakeMap = mock(GameMap.class, CALLS_REAL_METHODS);
        MushroomPowerUp powerUp = new MushroomPowerUp();
        when(fakeMap.getMoveTime()).thenReturn(GameMap.DEFAULT_MOVE_TIME);

        powerUp.action(fakeMap);

        verify(fakeMap).setMoveTime(1.5f * GameMap.DEFAULT_MOVE_TIME);
    }

}
