import com.tempestasludi.processing.nurbs.*;

// The surface.
NurbsSurface n;

// The weight of the center point.
float w = 5;

void setup() {
  size(1600, 900, P3D);

  // Create a grid of points and give every point weight 1.
  PVector[][] points = new PVector[5][5];
  float[][] weights = new float[5][5];
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      points[i][j] = new PVector((i - 2) * 100, (j - 2) * 100, 0);
      weights[i][j] = 1;
    }
  }

  // Create the surface. The current knot vectors (the last two arguments) make it a degree 2 surface in both parameters.
  // Using one of the commented knot vectors (or both) instead of the currently used one(s) will change it to a degree 4 surface in one or both parameters.
  n = new NurbsSurface(
    points,
    weights,
    new float[] {0, 0, 0, 0.3, .7, 1, 1, 1},
    new float[] {0, 0, 0, 0.3, .7, 1, 1, 1}
    //new float[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
    //new float[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}
  );
}

void draw() {
  // The height of the points.
  float h = 100 * cos(millis() / 1000.0);

  // The moving (center) point.
  PVector point = new PVector(100 * cos(millis() / 200.0), 100 * sin(millis() / 200.0), h);

  // Move the points in alternating directions, for a wavy effect.
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      n.setPoint(i, j, new PVector((i - 2) * 100, (j - 2) * 100, h * (1 - (i + j) % 2 * 2)));
    }
  }

  // Set the center point and its weight.
  n.setPoint(2, 2, point);
  n.setWeight(2, 2, w);

  // Set the stage.
  background(0);
  camera(mouseX - width / 2, 0, (height/2) / tan(PI/6), 0, 0, 0, 0, 1, 0);
  directionalLight(255, 255, 255, 1, 1, -1);
  directionalLight(50, 50, 50, -1, -1, -1);

  // Draw the centerpoint.
  noStroke();
  fill(127);
  pushMatrix();
  translate(point.x, point.y, point.z);
  sphere(5);
  popMatrix();

  // Draw the surface.
  noStroke();
  fill(127);
  n.draw(g, 20);

  // Draw the red reference square.
  stroke(0xffff0000);
  noFill();
  beginShape(QUADS);
  vertex(-200, -200, 0);
  vertex(200, -200, 0);
  vertex(200, 200, 0);
  vertex(-200, 200, 0);
  endShape();
}

// Change the weight of the center point if the up or down arrow keys are pressed.
void keyPressed() {
  if (keyCode == UP) {
    w *= 1.2;
  }
  if (keyCode == DOWN) {
    w /= 1.2;
  }
}