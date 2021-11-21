package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.menu.util.Slider;
import pestreshop.effects.Noise;

public class NoiseMenu extends MenuItem{
  
  boolean advanced;
    
  Slider redFactor;
  Slider greenFactor;
  Slider blueFactor;
  
  Noise noise;
  
  public NoiseMenu(Pestreshop sk, float pos, Menu m, Noise n){
    super(sk, pos, m);
    noise = n;
    len = 80;
    advanced = false;
    redFactor = new Slider(sketch, 0f, 1f, 0.005f, new PVector(50, pos+45), sketch.width/8);
    greenFactor = new Slider(sketch, 0f, 1f, 0.005f, new PVector(50, pos+55), sketch.width/8);
    blueFactor = new Slider(sketch, 0f, 1f, 0.005f, new PVector(50, pos+65), sketch.width/8);
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    redFactor.position.y = position+45;
    greenFactor.position.y = position+55;
    blueFactor.position.y = position+65;
  }
  
  @Override
  public void mousePressed(){
    if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      advanced = !advanced;
    }
    if(sketch.mouseX > sketch.width/4 - 20 && sketch.mouseX < sketch.width/4 && sketch.mouseY > position && sketch.mouseY < position+20){
      menu.effectsChain.removeEffect(noise);
      menu.removeMenuItem(this);
    }
    redFactor.mousePressed();
    greenFactor.mousePressed();
    blueFactor.mousePressed();
  }
  
  @Override
  public void mouseReleased(){
    redFactor.mouseReleased();
    greenFactor.mouseReleased();
    blueFactor.mouseReleased();
  }
  
  @Override
  public void mouseDragged(){
    redFactor.mouseDragged();
    greenFactor.mouseDragged();
    blueFactor.mouseDragged();
  }
  
  public void show(){
    sketch.pushStyle();
    sketch.colorMode(sketch.RGB);
    super.show();
    sketch.fill(255);
    sketch.textSize(19);
    sketch.text("Noise", 0, position+19);
    sketch.text("X", sketch.width/4 - 20, position+19);
    sketch.textSize(15);
    //if(filter.highPass){
    //  text("High Pass", width/8, position+37);
    //}else{
    //  text("Low Pass", width/8, position+37);
    //}
    if(advanced){
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Advanced", 0, position+37);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Red", 0, position+45);
      redFactor.show();
      sketch.text("Green", 0, position+55);
      greenFactor.show();
      sketch.text("Blue", 0, position+65);
      blueFactor.show();
      
      noise.setRedFactor(redFactor.getValue());
      noise.setGreenFactor(greenFactor.getValue());
      noise.setBlueFactor(blueFactor.getValue());
    }else{
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Simple", 0, position+20+15);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Factor", 0, position+45);
      redFactor.show();
      
      noise.setFactor(redFactor.getValue());
    }
    sketch.popStyle();
  }
}
