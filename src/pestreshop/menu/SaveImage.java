package pestreshop.menu;

import processing.core.*;
import java.io.File;

import pestreshop.Pestreshop;

public class SaveImage extends MenuItem{
  
  public SaveImage(Pestreshop sk, float pos, Menu m){
    super(sk, pos, m);
    len = 25;
  }
  
  public void mousePressed(){
    if(hovered){
      sketch.pause = true;
      sketch.selectOutput("Save image as", "outputFileSelected");
    }
  }
  
  public void mouseReleased(){}
  
  public void mouseDragged(){}
  
  public void show(){
    sketch.pushStyle();
    super.show();
    sketch.textSize(len*0.75f);
    sketch.fill(255);
    sketch.stroke(0);
    sketch.text("Save image", 0f, position+len*0.75f);
    sketch.popStyle();
  }

}
