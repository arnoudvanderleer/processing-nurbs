import com.tempestasludi.processing.nurbs.*;

NurbsCurve n;

void setup() {
  size(640, 480);
  n = new NurbsCurve(
    new PVector[]{
      new PVector(300, 200),
      new PVector(300, 300),
      new PVector(200, 300),
      new PVector(100, 300),
      new PVector(100, 200),
      new PVector(100, 100),
      new PVector(200, 100),
      new PVector(300, 100),
      new PVector(300, 200)
    },
    new float[] {
      2, sqrt(2), 2, sqrt(2), 2, sqrt(2), 2, sqrt(2), 2
    },
    new float[] {
      0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4
    }
  );
}

void draw() {
  clear();

  n.draw(g, max(mouseX / 25, 4));
}