
PImage ref;
PImage img;

Menu m;
EffectsChain effectsChain;

boolean pause;

int frameCounter;
boolean print;

void setup(){
  size(840,680);
  surface.setResizable(true);
  pause = true;
  //ref = loadImage("jape.jpg");
  selectInput("Select input image", "fileSelected");
  frameCounter = 0;
  print = false;
}

void fileSelected(File selection){
  if(selection == null){
    selectInput("Select input image", "fileSelected");
    return;
  }
  ref = loadImage(selection.getAbsolutePath());
  float resizeFactor = 1;
  if(ref.width >= ref.height)
    resizeFactor = (float) width / (float) ref.width;
  else
    resizeFactor = (float) height / (float) ref.height;
  ref.resize((int) (ref.width*resizeFactor),(int) (ref.height*resizeFactor));
  effectsChain = new EffectsChain(ref);
  m = new Menu(effectsChain);
  pause = false;
}

void imageSelected(File selection){
  if(selection == null){
    pause = false;
    return;
  }
  ref = loadImage(selection.getAbsolutePath());
  float resizeFactor = 1;
  if(ref.width >= ref.height)
    resizeFactor = (float) width / (float) ref.width;
  else
    resizeFactor = (float) height / (float) ref.height;
  ref.resize((int) (ref.width*resizeFactor),(int) (ref.height*resizeFactor));  
  effectsChain.redefineInputImage(ref);
  pause = false;
}

void keyPressed(){
  if(key == 'p' || key == 'P'){
    print  = true;
  }
}

void mousePressed(){
  m.mousePressed();
}

void mouseReleased(){
  m.mouseReleased();
}

void mouseDragged(){
  m.mouseDragged();
}

void draw(){
  background(255);
  if(!pause){
    img = effectsChain.apply();
    //if(!effects.isEmpty())
      //img = effects.get(effects.size()-1).outputImage;
    image(img, (width-img.width)/2, (height-img.height)/2);
    if(print){
      img.save("saves/save"+year()+"-"+month()+"-"+day()+"-"+hour()+"-"+minute()+"-"+second()+".png");
      print = false;
    }
    m.show();
    //saveFrame("out/video-#######.png");
  }
}
