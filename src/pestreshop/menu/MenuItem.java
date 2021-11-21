package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;

public abstract class MenuItem { 
  
  public Pestreshop sketch;

  float position;
  protected float len;
  
  boolean hovered;
  Menu menu;
  
  public MenuItem(Pestreshop sk, float pos, Menu m){
    sketch = sk;
    position = pos;
    hovered = false;
    menu = m;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
  }
  
  abstract void mousePressed();
  
  abstract void mouseReleased();
  
  abstract void mouseDragged();
  
  public void show(){
    if(sketch.mouseX<sketch.width/4 && sketch.mouseY>position && sketch.mouseY<position+len){
      hovered = true;
    } else{
      hovered = false;
    }
    sketch.pushStyle();
    if(hovered){
      sketch.fill(30,75);
    }else{
      sketch.fill(51,75);
    }
    sketch.rect(0, position, sketch.width/4, len);
    sketch.popStyle();
  }
}
