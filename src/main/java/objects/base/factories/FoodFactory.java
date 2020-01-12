package objects.base.factories;

import game.SnakeGame;
import gamelogic.Coordinate;
import objects.base.Food;
import snake.SnakeBody;

import java.util.Random;

public abstract class FoodFactory {

    public abstract Food createFood();

    public Coordinate randomCoordinates() {
        Random r = new Random();
        int minX = 0;
        int minY = 0;
        int maxX = SnakeGame.WIDTH / SnakeBody.CELL_SIZE;
        int maxY = SnakeGame.HEIGHT / SnakeBody.CELL_SIZE;

        int x = r.nextInt(maxX - minX) + minX;
        int y = r.nextInt(maxY - minY) + minY;

        int coordinateX = x * SnakeBody.CELL_SIZE;
        int coordinateY = y * SnakeBody.CELL_SIZE;

        return new Coordinate(coordinateX, coordinateY);
    }

}
