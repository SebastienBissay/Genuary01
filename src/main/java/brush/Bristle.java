package brush;

import processing.core.PApplet;
import processing.core.PVector;

import static genuary._2025.parameters.Parameters.INK_ABSORPTION;
import static genuary._2025.parameters.Parameters.STROKE_COLOR;

public class Bristle {

    private float inkQuantity;
    private final PVector position;

    public Bristle(float inkQuantity, PVector position) {
        this.inkQuantity = inkQuantity;
        this.position = position;
    }

    public void point(PVector point, PApplet pApplet) {
        pApplet.stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), 127 * inkQuantity);
        pApplet.point(point.x + position.x, point.y + position.y);
        inkQuantity -= pApplet.random(INK_ABSORPTION);
    }
}
