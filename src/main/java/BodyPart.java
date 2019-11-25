import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import org.w3c.dom.Text;

public class BodyPart {
    private int headX, headY;
    private int x, y;
    //add body part image or rectangle
    Texture texture;

    //have to add width height according to mapsize
    public BodyPart(Texture texture, int headX, int headY) {
        this.texture = texture;
        this.headX = headX;
        this.headY = headY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updateBodyPartPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawBody(Batch batch) {
        if(x != headX || y != headY) {
            batch.draw(texture, x, y);
        }
    }

}
