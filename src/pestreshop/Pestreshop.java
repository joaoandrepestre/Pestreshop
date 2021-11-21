package pestreshop;

import processing.core.*;
import java.io.File;

import pestreshop.menu.Menu;
import pestreshop.effects.EffectsChain;

public class Pestreshop extends PApplet {

  public PImage ref;
  public PImage img;

  public Menu m;
  public EffectsChain effectsChain;

  public boolean pause;

  public int frameCounter;
  public boolean print;

  public void settings(){
    size(840,680);
  }

  public void setup() {
    surface.setResizable(true);
    pause = true;
    //ref = loadImage("jape.jpg");
    selectInput("Select input image", "fileSelected");
    frameCounter = 0;
    print = false;
  }

  public void fileSelected(File selection){
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
    effectsChain = new EffectsChain(this, ref);
    m = new Menu(this, effectsChain);
    pause = false;
  }

  public void imageSelected(File selection){
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

  public void outputFileSelected(File selection) {
    if(selection == null){
      pause = false;
      return;
    }
    img.save(selection.getAbsolutePath());
    pause = false;
  }

  public void keyPressed(){
    if(key == 'p' || key == 'P'){
      print  = true;
    }
  }

  public void mousePressed(){
    m.mousePressed();
  }

  public void mouseReleased(){
    m.mouseReleased();
  }

  public void mouseDragged(){
    m.mouseDragged();
  }

  public void draw(){
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
}
