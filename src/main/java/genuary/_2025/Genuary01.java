package genuary._2025;

import processing.core.PApplet;

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
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.green());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        strokeWeight(STROKE_WEIGHT);
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        for (int y = 0; y < HEIGHT; y += 2 * STROKE_WEIGHT) {
            drawLine(y);
        }
        saveSketch(this);
    }

    private void drawLine(float y) {
        float threshold = .35f;

        Float start = null;
        for (int x = 0; x < width; x += 2 * STROKE_WEIGHT) {
            float noise = noise(x * NOISE_SCALE, y * NOISE_SCALE);
            if (abs(noise - threshold) > .05 && start == null) {
                start = (float) x;
            }
            if (abs(noise - threshold) < .05 && start != null) {
                line(start, y, x, y);
                start = null;
            }
        }
        if (start != null) {
            Color color = PALETTE.get(floor(random(PALETTE.size())));
            stroke(color.red(), color.green(), color.blue(), color.alpha());
            line(start, y, WIDTH, y);
        }
    }
}
