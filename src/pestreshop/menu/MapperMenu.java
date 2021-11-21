package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.effects.Mapper;

public class MapperMenu extends MenuItem{
    
  Mapper mapper;
  
  public MapperMenu(Pestreshop sk, float pos, Menu m, Mapper map){
    super(sk, pos, m);
    mapper = map;
    len = 100;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
  }
  
  @Override
  public void mousePressed(){
    if(sketch.mouseX < 90 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      mapper.toggleHSB();
    }
    if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
      mapper.comp1++;
      mapper.comp1 = mapper.comp1%3;
    }
    if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+65 && sketch.mouseY<position+80){
      mapper.comp2++;
      mapper.comp2 = mapper.comp2%3;
    }
    if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+80 && sketch.mouseY<position+95){
      mapper.comp3++;
      mapper.comp3 = mapper.comp3%3;
    }
    if(sketch.mouseX > sketch.width/4 - 20 && sketch.mouseX < sketch.width/4 && sketch.mouseY > position && sketch.mouseY < position+20){
      menu.effectsChain.removeEffect(mapper);
      menu.removeMenuItem(this);
    }
  }

  @Override
  public void mouseReleased() {}
  
  @Override
  public void mouseDragged() {}
  
  public void show(){
    sketch.pushStyle();
    sketch.colorMode(sketch.RGB);
    super.show();
    sketch.fill(255);
    sketch.textSize(19);
    sketch.text("Mapper", 0, position+19);
    sketch.text("X", sketch.width/4 - 20, position+19);
    sketch.textSize(15);
    if(sketch.mouseX < 90 && sketch.mouseY > position+20 && sketch.mouseY < position+35){
      sketch.fill(0,255,0);
    }
    if(mapper.hsbMap){
      sketch.text("RGB -> HSB", 0, position+37);
    }else{
      sketch.text("HSB -> RGB", 0, position+37);
    }
    sketch.fill(255);

    String comp1Mapper = "";
    String comp2Mapper = ""; 
    String comp3Mapper = "";
    if(mapper.hsbMap){
      switch(mapper.comp1){
        case 0:
          comp1Mapper = "red";
          break;
        case 1:
          comp1Mapper = "green";
          break;
        case 2:
          comp1Mapper = "blue";
          break;
      }
      switch(mapper.comp2){
        case 0:
          comp2Mapper = "red";
          break;
        case 1:
          comp2Mapper = "green";
          break;
        case 2:
          comp2Mapper = "blue";
          break;
      }
      switch(mapper.comp3){
        case 0:
          comp3Mapper = "red";
          break;
        case 1:
          comp3Mapper = "green";
          break;
        case 2:
          comp3Mapper = "blue";
          break;
      }
            
      sketch.text("Hue:", 0, position+65);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
        sketch.fill(0,255,0);
      }
      sketch.text(comp1Mapper, 90, position+65);
      sketch.fill(255);
      sketch.text("Saturation:", 0, position+80);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+65 && sketch.mouseY<position+80){
        sketch.fill(0,255,0);
      }
      sketch.text(comp2Mapper, 90, position+80);
      sketch.fill(255);
      sketch.text("Brightness:", 0, position+95);
      if(sketch.mouseX > 90 && sketch.mouseX<130 && sketch.mouseY>position+80 && sketch.mouseY<position+95){
        sketch.fill(0,255,0);
      }
      sketch.text(comp3Mapper, 90, position+95);
      sketch.fill(255);
    }else{
      switch(mapper.comp1){
        case 0:
          comp1Mapper = "hue";
          break;
        case 1:
          comp1Mapper = "saturation";
          break;
        case 2:
          comp1Mapper = "brightness";
          break;
      }
      switch(mapper.comp2){
        case 0:
          comp2Mapper = "hue";
          break;
        case 1:
          comp2Mapper = "saturation";
          break;
        case 2:
          comp2Mapper = "brightness";
          break;
      }
      switch(mapper.comp3){
        case 0:
          comp3Mapper = "hue";
          break;
        case 1:
          comp3Mapper = "saturation";
          break;
        case 2:
          comp3Mapper = "brightness";
          break;
      }
      sketch.text("Red:", 0, position+65);
      if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+55 && sketch.mouseY<position+65){
        sketch.fill(0,255,0);
      }
      sketch.text(comp1Mapper, 90, position+65);
      sketch.fill(255);
      sketch.text("Green:", 0, position+80);
      if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+65 && sketch.mouseY<position+80){
        sketch.fill(0,255,0);
      }
      sketch.text(comp2Mapper, 90, position+80);
      sketch.fill(255);
      sketch.text("Blue:", 0, position+95);
      if(sketch.mouseX > 90 && sketch.mouseX<165 && sketch.mouseY>position+80 && sketch.mouseY<position+95){
        sketch.fill(0,255,0);
      }
      sketch.text(comp3Mapper, 90, position+95);
      sketch.fill(255);
    }
    sketch.popStyle();
  }
}
