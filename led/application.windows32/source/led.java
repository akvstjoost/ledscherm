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

public class led extends PApplet {


/**
 * Load and Display 
 * 
 * Images can be loaded and displayed to the screen at their actual size
 * or any other size. 
 */

PImage in, out;  // Declare variable "a" of type PImage
int filenr=-1;
String[] filenames;

public void setup() {

  String path = sketchPath();

  println("Listing all filenames in a directory: ");
  filenames = listFileNames(path);
  printArray (filenames);
  loadNext();

  
  //size(640, 640);

  frameRate(0.5f);
}

public void draw() {
  loadNext();
  image(out, 0, 0);
}

public void inToOut() {

  for (int frame = 0; frame < 3; frame++) {
    for (int x=0; x<40; x++) {
      for (int y=0; y<32; y++)
        out.set(
          x+(frame*40), 
          y, 
          in.get((39+(abs(frame-2)*48))-x, (31-y)));
    }
  }
}


public String[] listFileNames(String dir) {
  File file = new File(dir);
  if (file.isDirectory()) {
    String names[] = file.list();
    return names;
  } else {
    // If it's not a director
    return null;
  }
}

public void loadNext() {
    filenr++;
    if (filenr >= filenames.length) filenr = 0;
    if (filenames[filenr].contains(".gif")) {
    in = loadImage(filenames[filenr]);  // Load the image into the program  
  out = createImage(120, 32, RGB);
  inToOut();    

  } else {

    loadNext();
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "led" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
