package brush;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.map;
import static processing.core.PApplet.sq;
import static processing.core.PConstants.PI;
import static processing.core.PConstants.TWO_PI;

public class Brush {

    private final PApplet pApplet;
    private final List<Bristle> bristles;

    public Brush(float size, float bristleDensity, float inkAmount, PApplet pApplet) {
        this.pApplet = pApplet;

        bristles = new ArrayList<>();

        for (int k = 0; k < PI * sq(size) * bristleDensity; k++) {
            PVector bristlePosition = PVector.fromAngle(pApplet.random(TWO_PI)).mult(pApplet.random(size));
            float bristleInkQuantity =
                    inkAmount * (map(bristlePosition.mag(), 0, size, 1, .75f)
                            + .1f * pApplet.randomGaussian());
            bristles.add(new Bristle(bristleInkQuantity, bristlePosition));
        }
    }

    public void point(PVector point) {
        bristles.forEach(bristle -> bristle.point(point, pApplet));
    }

    public void line(PVector start, PVector end) {
        float length = PVector.sub(end, start).mag();
        for (float t = 0; t <= 1; t += 1f / (2 * length)) {
            PVector brushPosition = PVector.add(start, PVector.sub(end, start).mult(t));
            point(brushPosition);
        }
    }
}
