import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.LinkedList;

public class SnakeBody {
    private float headX;
    private float headY;
    //head image or separate head rectangle or add to the array.
    private LinkedList<BodyPart> bodyParts;
//    private Texture headTexture;
    private Direction currDir;

    public enum Direction {LEFT, RIGHT, UP, DOWN};

    public SnakeBody() {
        this.headX = Gdx.graphics.getWidth()/2;
        this.headY = Gdx.graphics.getHeight()/2;
        this.currDir = Direction.DOWN;
        this.bodyParts = new LinkedList<BodyPart>();
    }
//
//    public Texture getHeadTexture() {
//        return headTexture;
//    }
//
//    public void setHeadTexture(Texture headTexture) {
//        this.headTexture = headTexture;
//    }


    public float getHeadX() {
        return headX;
    }

    public void setHeadX(float headX) {
        this.headX = headX;
    }

    public float getHeadY() {
        return headY;
    }

    public void setHeadY(float headY) {
        this.headY = headY;
    }

    public LinkedList<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(LinkedList<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public Direction getCurrDir() {
        return currDir;
    }

    public void setCurrDir(Direction currDir) {
        this.currDir = currDir;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void renderSnake(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(Color.GREEN));
        shapeRenderer.rect(this.headX, this.getHeadY(), 16, 16);
        if (bodyParts.size() > 0) {
            for (BodyPart bp : bodyParts) {
                System.out.println("lichaamsdeel");
                shapeRenderer.rect(bp.getX(), bp.getHeadY(), 16, 16);
            }
        }
        shapeRenderer.end();

    }

}
