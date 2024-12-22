package genuary._2025;

import brush.Brush;
import processing.core.PApplet;
import processing.core.PVector;

import static genuary._2025.parameters.Parameters.*;
import static genuary._2025.save.SaveUtil.saveSketch;

public class Genuary01 extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Genuary01.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.green());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        for (int y = floor(MARGIN); y <= HEIGHT - MARGIN; y += ceil(2f * BRUSH_SIZE)) {
            float startX = WIDTH / 2f - sqrt(sq(WIDTH / 2f - MARGIN) - sq(y - HEIGHT / 2f));
            Brush brush = new Brush(BRUSH_SIZE,
                    BRISTLE_DENSITY,
                    random(MINIMUM_INK_AMOUNT, MAXIMUM_INK_AMOUNT)
                            + map(abs(y - HEIGHT / 2f),
                            0, WIDTH / 2f - MARGIN,
                            MINIMUM_INK_AMOUNT, 0), this);
            brush.line(new PVector(startX, y), new PVector(WIDTH - MARGIN, y));
        }

        saveSketch(this);
    }
}
