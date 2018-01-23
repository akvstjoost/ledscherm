PImage in, out;  // Declare variable "a" of type PImage
int scrollX = 0;
int scrollWidth;
void setup() {
  in = loadImage("scroll.gif");  // Load the image into the program 
  scrollWidth = in.width;
  fullScreen();
}

void draw() {
  out = createImage(120, 32, RGB);
  scrollX++;
  if (scrollX > scrollWidth) scrollX = -200;
  inToOut();
  image(out, 0, 0);
}

void inToOut() {

  for (int frame = 0; frame < 3; frame++) {
    for (int x=0; x<40; x++) {
      for (int y=0; y<32; y++) {
        int inX = (39+(abs(frame-2)*48))-x;
        int inY = (31-y);
          out.set(
          x+(frame*40), 
          y, 
          in.get((inX+max(scrollX,0))%scrollWidth, inY));
      }
    }
  }
}