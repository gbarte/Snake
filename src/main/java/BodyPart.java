import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import org.w3c.dom.Text;

import java.util.LinkedList;

public class BodyPart {
    private float headX, headY;
    private float x, y;
    ShapeRenderer shapeRenderer;
    ShapeRenderer.ShapeType bp;
    //add body part image or rectangle
//    Texture texture;

    //have to add width height according to mapsize
//    public BodyPart(Texture texture, int headX, int headY) {
    public BodyPart(int headX, int headY) {
//        this.texture = texture;
        this.headX = headX;
        this.headY = headY;
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.rect(headX, headY, 1, 1);
//        shapeRenderer.end();
    }

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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public ShapeRenderer.ShapeType getBp() {
        return bp;
    }

    public void setBp(ShapeRenderer.ShapeType bp) {
        this.bp = bp;
    }

//    public void drawBody() {
//        if(x != headX || y != headY) {
////            batch.draw(texture, x, y);
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//            shapeRenderer.setColor(Color.GREEN);
//            shapeRenderer.rect(headX, headY, 1, 1);
//            shapeRenderer.end();
//        }
//    }

}
