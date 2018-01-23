import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class scroll extends PApplet {

PImage in, out;  // Declare variable "a" of type PImage
int scrollX = 0;
int scrollWidth;
public void setup() {
  in = loadImage("scroll.gif");  // Load the image into the program 
  scrollWidth = in.width;
  
}

public void draw() {
  out = createImage(120, 32, RGB);
  scrollX++;
  if (scrollX > scrollWidth) scrollX = -200;
  inToOut();
  image(out, 0, 0);
}

public void inToOut() {

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
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "scroll" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
