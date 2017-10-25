
/**
 * Load and Display 
 * 
 * Images can be loaded and displayed to the screen at their actual size
 * or any other size. 
 */

PImage in, out;  // Declare variable "a" of type PImage
int filenr=-1;
String[] filenames;

void setup() {

  String path = "D:/";

  println("Listing all filenames in a directory: ");
  filenames = listFileNames(path);
  printArray (filenames);
  loadNext();

  fullScreen();
  //size(640, 640);

  frameRate(0.5);
}

void draw() {
  loadNext();
  image(out, 0, 0);
}

void inToOut() {

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


String[] listFileNames(String dir) {
  File file = new File(dir);
  if (file.isDirectory()) {
    String names[] = file.list();
    return names;
  } else {
    // If it's not a director
    return null;
  }
}

void loadNext() {
    filenr++;
    if (filenr >= filenames.length) filenr = 0;
    if (filenames[filenr].contains(".gif")) {
    in = loadImage("D:/"+filenames[filenr]);  // Load the image into the program  
  out = createImage(120, 32, RGB);
  inToOut();    

  } else {

    loadNext();
  }
}