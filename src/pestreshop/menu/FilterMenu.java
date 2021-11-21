package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.menu.util.Slider;
import pestreshop.effects.Filter;

public class FilterMenu extends MenuItem{
  
  boolean advanced;
    
  Slider redTreshold;
  Slider greenTreshold;
  Slider blueTreshold;
  
  Filter filter;
  
  public FilterMenu(Pestreshop sk, float pos, Menu m, Filter f){
    super(sk, pos, m);
    filter = f;
    len = 80;
    advanced = false;
    redTreshold = new Slider(sketch, 0f, 1f, 0.008f, new PVector(50, pos+45), sketch.width/8);
    greenTreshold = new Slider(sketch, 0f, 1f, 0.008f, new PVector(50, pos+55), sketch.width/8);
    blueTreshold = new Slider(sketch, 0f, 1f, 0.008f, new PVector(50, pos+65), sketch.width/8);
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    redTreshold.position.y = position+45;
    greenTreshold.position.y = position+55;
    blueTreshold.position.y = position+65;
  }
  
  @Override
  public void mousePressed(){
    if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      advanced = !advanced;
    }
    if(sketch.mouseX > sketch.width/8 && sketch.mouseX < sketch.width/8 + 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      filter.highPass = !filter.highPass;
    }
    if(sketch.mouseX > sketch.width/4 - 20 && sketch.mouseX < sketch.width/4 && sketch.mouseY > position && sketch.mouseY < position+20){
      menu.effectsChain.removeEffect(filter);
      menu.removeMenuItem(this);
    }
    redTreshold.mousePressed();
    greenTreshold.mousePressed();
    blueTreshold.mousePressed();
  }
  
  @Override
  public void mouseReleased(){
    redTreshold.mouseReleased();
    greenTreshold.mouseReleased();
    blueTreshold.mouseReleased();
  }
  
  @Override
  public void mouseDragged(){
    redTreshold.mouseDragged();
    greenTreshold.mouseDragged();
    blueTreshold.mouseDragged();
  }
  
  public void show(){
    sketch.pushStyle();
    sketch.colorMode(sketch.RGB);
    super.show();
    sketch.fill(255);
    sketch.textSize(19);
    sketch.text("Filter", 0, position+19);
    sketch.text("X", sketch.width/4 - 20, position+19);
    sketch.textSize(15);
    if(sketch.mouseX > sketch.width/8 && sketch.mouseX < sketch.width/8 + 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      sketch.fill(0,255,0);
    }
    if(filter.highPass){
      sketch.text("High Pass", sketch.width/8, position+37);
    }else{
      sketch.text("Low Pass", sketch.width/8, position+37);
    }
    sketch.fill(255);
    if(advanced){
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Advanced", 0, position+37);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Red", 0, position+45);
      redTreshold.show();
      sketch.text("Green", 0, position+55);
      greenTreshold.show();
      sketch.text("Blue", 0, position+65);
      blueTreshold.show();
      
      filter.setRedTreshold(redTreshold.getValue());
      filter.setGreenTreshold(greenTreshold.getValue());
      filter.setBlueTreshold(blueTreshold.getValue());
    }else{
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Simple", 0, position+20+15);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Treshold", 0, position+45);
      redTreshold.show();
      
      filter.setTreshold(redTreshold.getValue());
    }
    sketch.popStyle();
  }
}
