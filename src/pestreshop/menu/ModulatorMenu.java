package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.menu.util.Slider;
import pestreshop.effects.Modulator;

public class ModulatorMenu extends MenuItem{
  
  boolean advanced;
    
  Slider modulation;
  
  Modulator modulator;
  int moder;
  
  public ModulatorMenu(Pestreshop sk, float pos, Menu m, Modulator mod){
    super(sk, pos, m);
    modulator = mod;
    len = 100;
    advanced = false;
    modulation = new Slider(sketch, 0f, 1f, 0.008f, new PVector(50, pos+45), sketch.width/8);
    moder = 1;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    modulation.position.y = position+45;
  }
  
  @Override
  public void mousePressed(){
    if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      advanced = !advanced;
    }
    if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
      if(!advanced){
        moder++;
        moder = moder%5;
      }else{
        modulator.redModulator++;
        modulator.redModulator = modulator.redModulator%4;
      }
    }
    if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+65 && sketch.mouseY<position+80){
        modulator.greenModulator++;
        modulator.greenModulator = modulator.greenModulator%4;
    }
    if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+80 && sketch.mouseY<position+95){
        modulator.blueModulator++;
        modulator.blueModulator = modulator.blueModulator%4;
    }
    if(sketch.mouseX > sketch.width/4 - 20 && sketch.mouseX < sketch.width/4 && sketch.mouseY > position && sketch.mouseY < position+20){
      menu.effectsChain.removeEffect(modulator);
      menu.removeMenuItem(this);
    }
    modulation.mousePressed();
  }
  
  @Override
  public void mouseReleased(){
    modulation.mouseReleased();
  }
  
  @Override
  public void mouseDragged(){
    modulation.mouseDragged();
  }
  
  public void show(){
    sketch.pushStyle();
    sketch.colorMode(sketch.RGB);
    super.show();
    sketch.fill(255);
    sketch.textSize(19);
    sketch.text("Modulator", 0, position+19);
    sketch.text("X", sketch.width/4 - 20, position+19);
    sketch.textSize(15);
    if(advanced){
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Advanced", 0, position+37);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Mod", 0, position+50);
      modulation.show();
      sketch.textSize(15);
      String redModer = "";
      String greenModer = ""; 
      String blueModer = "";
      switch(modulator.redModulator){
        case 0:
          redModer = "null";
          break;
        case 1:
          redModer = "red";
          break;
        case 2:
          redModer = "green";
          break;
        case 3:
          redModer = "blue";
          break;
      }
      switch(modulator.greenModulator){
        case 0:
          greenModer = "null";
          break;
        case 1:
          greenModer = "red";
          break;
        case 2:
          greenModer = "green";
          break;
        case 3:
          greenModer = "blue";
          break;
      }
      switch(modulator.blueModulator){
        case 0:
          blueModer = "null";
          break;
        case 1:
          blueModer = "red";
          break;
        case 2:
          blueModer = "green";
          break;
        case 3:
          blueModer = "blue";
          break;
      }
      sketch.text("Red:", 0, position+65);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
        sketch.fill(0,255,0);
      }
      sketch.text(redModer, 90, position+65);
      sketch.fill(255);
      sketch.text("Green:", 0, position+80);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+65 && sketch.mouseY<position+80){
        sketch.fill(0,255,0);
      }
      sketch.text(greenModer, 90, position+80);
      sketch.fill(255);
      sketch.text("Blue:", 0, position+95);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+80 && sketch.mouseY<position+95){
        sketch.fill(0,255,0);
      }
      sketch.text(blueModer, 90, position+95);
      sketch.fill(255);
      
      modulator.setModulation(modulation.getValue());
    }else{
      if(sketch.mouseX < 75 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
        sketch.fill(0,255,0);
      }
      sketch.text("Simple", 0, position+20+15);
      sketch.fill(255);
      sketch.textSize(10);
      sketch.text("Mod", 0, position+50);
      modulation.show();
      sketch.textSize(15);
      String modString = "";
      switch(moder){
        case 0:
          modString = "null";
          break;
        case 1:
          modString = "red";
          break;
        case 2:
          modString = "green";
          break;
        case 3:
          modString = "blue";
          break;
        case 4:
          modString = "self";
          break;
      }
      sketch.text("Modulator:",0,position+65);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
        sketch.fill(0,255,0);
      }
      sketch.text(modString, 90, position+65);
      sketch.fill(255);
      
      modulator.setModulation(modulation.getValue());
      if(moder==4){
        modulator.setSelfModulation();
      }else{
        modulator.setModulator(moder);
      }
    }
    sketch.popStyle();
  }
}
