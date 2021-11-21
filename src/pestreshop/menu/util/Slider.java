package pestreshop.menu.util;

import processing.core.*;

import pestreshop.Pestreshop;

public class Slider {

  public Pestreshop sketch;

  float lowerBound;
  float higherBound;
  float increment;
  
  float value;
  
  public PVector position;
  float length;
  
  boolean selected;
  
  public Slider(Pestreshop sk, float low, float high, float inc, PVector pos, float l){
    sketch = sk;
    lowerBound = low;
    higherBound = high;
    
    value = (high-low)/2;
    increment = inc;
    position = pos;
    length = l;
    
    selected = false;
  }
  
  public float getValue(){
    return value;
  }
  
  public void mousePressed(){
    PVector mouse = new PVector(sketch.mouseX, sketch.mouseY);
    
    float t = value/(higherBound-lowerBound);
    PVector pos = new PVector(position.x+t*length, position.y);
    
    if(mouse.dist(pos)<=5)
      selected = true;
  }
  
  public void mouseReleased(){
    selected = false;
  }
  
  public void mouseDragged(){
    int diff = sketch.mouseX - sketch.pmouseX;
    if(selected){
      if(diff<0 && value > lowerBound){
        value-=increment;
      }
      if(diff>0 && value < higherBound){
        value+=increment;
      }
    }
  }
  
  public void show(){
    sketch.pushStyle();
    sketch.strokeWeight(2);
    sketch.stroke(0);
    sketch.line(position.x, position.y, position.x+length, position.y);
    sketch.strokeWeight(1);
    float t = value/(higherBound-lowerBound);
    sketch.fill(255);
    sketch.ellipse(position.x+t*length, position.y, 10, 10);
    sketch.textSize(8);
    sketch.text(value, position.x+t*length, position.y);
    sketch.popStyle();
  }
}
