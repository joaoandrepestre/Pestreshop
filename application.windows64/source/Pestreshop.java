import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pestreshop extends PApplet {


PImage ref;
PImage img;

Menu m;
EffectsChain effectsChain;

boolean pause;

int frameCounter;
boolean print;

public void setup(){
  
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
  effectsChain = new EffectsChain(ref);
  m = new Menu(effectsChain);
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
class AddFilterMenu extends MenuItem{
  
  public AddFilterMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Filter f = new Filter(true, 0.5f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      FilterMenu fMenu = new FilterMenu(p, menu, f);
      menu.effectsChain.addEffect(f);
      menu.menuItems.add(fMenu);
    }
  }
  
  public void mouseReleased(){
  }
  
  public void mouseDragged(){
  }
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("+ Add new filter", 0, position+len*0.75f);
    popStyle();
  }
}
class AddMapperMenu extends MenuItem{
  
  public AddMapperMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Mapper map = new Mapper();
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      MapperMenu mapMenu = new MapperMenu(p, menu, map);
      menu.effectsChain.addEffect(map);
      menu.menuItems.add(mapMenu);
    }
  }
  
  public void mouseReleased(){
  }
  
  public void mouseDragged(){
  }
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("+ Add new mapper", 0, position+len*0.75f);
    popStyle();
  }
}
class AddModulatorMenu extends MenuItem{
  
  public AddModulatorMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = m.creationMenu.get(m.creationMenu.size()-1).position + m.creationMenu.get(m.creationMenu.size()-1).len;
    if(hovered){
      Modulator mod = new Modulator(0.25f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      ModulatorMenu modMenu = new ModulatorMenu(p, menu, mod);
      menu.effectsChain.addEffect(mod);
      m.menuItems.add(modMenu);
    }
  }
  
  public void mouseReleased(){
  }
  
  public void mouseDragged(){
  }
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("+ Add new modulator", 0, position+len*0.75f);
    popStyle();
  }
}
class AddNoiseMenu extends MenuItem{
  
  public AddNoiseMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      //if(!effects.isEmpty())
        //reference = effects.get(effects.size()-1).getOutput();
      Noise n = new Noise(0.5f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      NoiseMenu nMenu = new NoiseMenu(p, menu, n);
      menu.effectsChain.addEffect(n);
      menu.menuItems.add(nMenu);
    }
  }
  
  public void mouseReleased(){
  }
  
  public void mouseDragged(){
  }
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("+ Add noise", 0, position+len*0.75f);
    popStyle();
  }
}
abstract class Effect{
  
  public abstract int apply(int pixel);
}
class EffectsChain{

  PImage inputImage;
  PImage outputImage;
  
  ArrayList<Effect> effects;
  
  public EffectsChain(PImage reference){
    inputImage = reference;
    outputImage = reference.copy();
    
    effects = new ArrayList<Effect>();
  }
  
  public void addEffect(Effect e){
    effects.add(e);
  }
  
  public void removeEffect(Effect e){
    effects.remove(e);
  }
  
  public void redefineInputImage(PImage reference){
    inputImage = reference;
    outputImage = reference.copy();
  }
  
  public PImage apply(){
    if(!pause){
      int pixel;
      for(int i=0;i<inputImage.pixels.length;i++){
        pixel = inputImage.pixels[i];
        for(Effect e: effects){
          pixel = e.apply(pixel);
        }
        outputImage.pixels[i] = pixel;
      }
      outputImage.updatePixels();
    }
    return outputImage;
  }
}
class Filter extends Effect{
  
  boolean highPass;
  
  float redTreshold;
  float greenTreshold;
  float blueTreshold;
  
  public Filter(boolean pass, float treshold){    
    highPass = pass;
    
    redTreshold = treshold;
    greenTreshold = treshold;
    blueTreshold = treshold;
  }
  
  public void toggleHighPass(){
    highPass = !highPass;
  }
  
  public void setTreshold(float treshold){
    redTreshold = treshold;
    greenTreshold = treshold;
    blueTreshold = treshold;
  }
  
  public void setRedTreshold(float treshold){
    redTreshold = treshold;
  }
  
  public void setGreenTreshold(float treshold){
    greenTreshold = treshold;
  }
  
  public void setBlueTreshold(float treshold){
    blueTreshold = treshold;
  }
  
  @Override
  public int apply(int pixel){
    float inputRed = red(pixel);
    float inputGreen = green(pixel);
    float inputBlue = blue(pixel);
  
    float outputRed = 0;
    float outputGreen = 0;
    float outputBlue = 0;
    if((highPass && inputRed >= 255*redTreshold) || (!highPass && inputRed <= 255*redTreshold)){
      outputRed = inputRed;
    }
    if((highPass && inputGreen >= 255*greenTreshold) || (!highPass && inputGreen <= 255*greenTreshold)){
      outputGreen = inputGreen;
    }
    if((highPass && inputBlue >= 255*blueTreshold) || (!highPass && inputBlue <= 255*blueTreshold)){
      outputBlue = inputBlue;
    }
  
    colorMode(RGB);
    return color(outputRed,
                  outputGreen,
                  outputBlue);
  
  }
}
class FilterMenu extends MenuItem{
  
  boolean advanced;
    
  Slider redTreshold;
  Slider greenTreshold;
  Slider blueTreshold;
  
  Filter filter;
  
  public FilterMenu(float pos, Menu m, Filter f){
    super(pos, m);
    filter = f;
    len = 80;
    advanced = false;
    redTreshold = new Slider(0, 1, 0.008f, new PVector(50, pos+45), width/8);
    greenTreshold = new Slider(0, 1, 0.008f, new PVector(50, pos+55), width/8);
    blueTreshold = new Slider(0, 1, 0.008f, new PVector(50, pos+65), width/8);
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    redTreshold.position.y = position+45;
    greenTreshold.position.y = position+55;
    blueTreshold.position.y = position+65;
  }
  
  @Override
  public void mousePressed(){
    if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
      advanced = !advanced;
    }
    if(mouseX > width/8 && mouseX < width/8 + 75 && mouseY > position+20 && mouseY < position+35){
      filter.highPass = !filter.highPass;
    }
    if(mouseX > width/4 - 20 && mouseX < width/4 && mouseY > position && mouseY < position+20){
      effectsChain.removeEffect(filter);
      m.removeMenuItem(this);
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
    pushStyle();
    colorMode(RGB);
    super.show();
    fill(255);
    textSize(19);
    text("Filter", 0, position+19);
    text("X", width/4 - 20, position+19);
    textSize(15);
    if(mouseX > width/8 && mouseX < width/8 + 75 && mouseY > position+20 && mouseY < position+35){
      fill(0,255,0);
    }
    if(filter.highPass){
      text("High Pass", width/8, position+37);
    }else{
      text("Low Pass", width/8, position+37);
    }
    fill(255);
    if(advanced){
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Advanced", 0, position+37);
      fill(255);
      textSize(10);
      text("Red", 0, position+45);
      redTreshold.show();
      text("Green", 0, position+55);
      greenTreshold.show();
      text("Blue", 0, position+65);
      blueTreshold.show();
      
      filter.setRedTreshold(redTreshold.value);
      filter.setGreenTreshold(greenTreshold.value);
      filter.setBlueTreshold(blueTreshold.value);
    }else{
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Simple", 0, position+20+15);
      fill(255);
      textSize(10);
      text("Treshold", 0, position+45);
      redTreshold.show();
      
      filter.setTreshold(redTreshold.value);
    }
    popStyle();
  }
}
class Mapper extends Effect{
  
  boolean hsbMap;
  
  int comp1, comp2, comp3;
  
  public Mapper(){
    hsbMap = true;
    
    comp1 = 0;
    comp2 = 1;
    comp3 = 2;
  }
  
  public void toggleHSB(){
    hsbMap = !hsbMap;
  }
  
  public void setComp1(int c1){
    comp1 = c1;
  }
  
  public void setComp2(int c2){
    comp2 = c2;
  }
  
  public void setComp3(int c3){
    comp3 = c3;
  }

  public int apply(int pixel){
    float inputComp1, inputComp2, inputComp3;
    float outputComp1 = 0;
    float outputComp2 = 0;
    float outputComp3 = 0;
    int outPixel;
    
    if(hsbMap){
      inputComp1 = red(pixel);
      inputComp2 = green(pixel);
      inputComp3 = blue(pixel);
      
      switch(comp1){
        case 0:
          outputComp1 = inputComp1;
          break;
        case 1:
          outputComp1 = inputComp2;
          break;
        case 2:
          outputComp1 = inputComp3;
          break;
      }
      
      switch(comp2){
        case 0:
          outputComp2 = inputComp1;
          break;
        case 1:
          outputComp2 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      switch(comp3){
        case 0:
          outputComp3 = inputComp1;
          break;
        case 1:
          outputComp3 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      colorMode(HSB);
      outPixel = color(outputComp1, outputComp2, outputComp3);
    }else{
      inputComp1 = hue(pixel);
      inputComp2 = saturation(pixel);
      inputComp3 = brightness(pixel);
      
      switch(comp1){
        case 0:
          outputComp1 = inputComp1;
          break;
        case 1:
          outputComp1 = inputComp2;
          break;
        case 2:
          outputComp1 = inputComp3;
          break;
      }
      
      switch(comp2){
        case 0:
          outputComp2 = inputComp1;
          break;
        case 1:
          outputComp2 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      switch(comp3){
        case 0:
          outputComp3 = inputComp1;
          break;
        case 1:
          outputComp3 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      colorMode(RGB);
      outPixel = color(outputComp1, outputComp2, outputComp3);
    }
    
    return outPixel;
  }
}
class MapperMenu extends MenuItem{
    
  Mapper mapper;
  
  public MapperMenu(float pos, Menu m, Mapper map){
    super(pos, m);
    mapper = map;
    len = 100;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
  }
  
  @Override
  public void mousePressed(){
    if(mouseX < 90 && mouseY > position+20 && mouseY < position+35){
      mapper.toggleHSB();
    }
    if(mouseX > 90 && mouseX<165 && mouseY>position+55 && mouseY<position+65){
      mapper.comp1++;
      mapper.comp1 = mapper.comp1%3;
    }
    if(mouseX > 90 && mouseX<165 && mouseY>position+65 && mouseY<position+80){
      mapper.comp2++;
      mapper.comp2 = mapper.comp2%3;
    }
    if(mouseX > 90 && mouseX<165 && mouseY>position+80 && mouseY<position+95){
      mapper.comp3++;
      mapper.comp3 = mapper.comp3%3;
    }
    if(mouseX > width/4 - 20 && mouseX < width/4 && mouseY > position && mouseY < position+20){
      effectsChain.removeEffect(mapper);
      m.removeMenuItem(this);
    }
    //modulation.mousePressed();
  }
  
  @Override
  public void mouseReleased(){
    //modulation.mouseReleased();
  }
  
  @Override
  public void mouseDragged(){
    //modulation.mouseDragged();
  }
  
  public void show(){
    pushStyle();
    colorMode(RGB);
    super.show();
    fill(255);
    textSize(19);
    text("Mapper", 0, position+19);
    text("X", width/4 - 20, position+19);
    textSize(15);
    if(mouseX < 90 && mouseY > position+20 && mouseY < position+35){
      fill(0,255,0);
    }
    if(mapper.hsbMap){
      text("RGB -> HSB", 0, position+37);
    }else{
      text("HSB -> RGB", 0, position+37);
    }
    fill(255);

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
            
      text("Hue:", 0, position+65);
      if(mouseX > 90 && mouseX<130 && mouseY>position+55 && mouseY<position+65){
        fill(0,255,0);
      }
      text(comp1Mapper, 90, position+65);
      fill(255);
      text("Saturation:", 0, position+80);
      if(mouseX > 90 && mouseX<130 && mouseY>position+65 && mouseY<position+80){
        fill(0,255,0);
      }
      text(comp2Mapper, 90, position+80);
      fill(255);
      text("Brightness:", 0, position+95);
      if(mouseX > 90 && mouseX<130 && mouseY>position+80 && mouseY<position+95){
        fill(0,255,0);
      }
      text(comp3Mapper, 90, position+95);
      fill(255);
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
      text("Red:", 0, position+65);
      if(mouseX > 90 && mouseX<165 && mouseY>position+55 && mouseY<position+65){
        fill(0,255,0);
      }
      text(comp1Mapper, 90, position+65);
      fill(255);
      text("Green:", 0, position+80);
      if(mouseX > 90 && mouseX<165 && mouseY>position+65 && mouseY<position+80){
        fill(0,255,0);
      }
      text(comp2Mapper, 90, position+80);
      fill(255);
      text("Blue:", 0, position+95);
      if(mouseX > 90 && mouseX<165 && mouseY>position+80 && mouseY<position+95){
        fill(0,255,0);
      }
      text(comp3Mapper, 90, position+95);
      fill(255);
    }
    popStyle();
  }
}
class Menu{
  
  ArrayList<MenuItem> creationMenu;
  ArrayList<MenuItem> menuItems;
  
  EffectsChain effectsChain;
  
  public Menu(EffectsChain ec){
    effectsChain = ec;
    creationMenu = new ArrayList<MenuItem>();
    menuItems = new ArrayList<MenuItem>();
    creationMenu.add(new OpenImage(0, this));
    creationMenu.add(new SaveImage(25,this));
    creationMenu.add(new AddFilterMenu(50, this));
    creationMenu.add(new AddNoiseMenu(75, this));
    creationMenu.add(new AddModulatorMenu(100, this));
    creationMenu.add(new AddMapperMenu(125, this));
  }
  
  public void removeMenuItem(MenuItem mi){
    int index = menuItems.indexOf(mi);
    menuItems.remove(mi);
    for(int i=index;i<menuItems.size();i++){
      menuItems.get(i).updatePosition(menuItems.get(i).position-mi.len);
      //if(i-1<0){
      //  effects.get(i).inputImage = ref;
      //}else{
      //  effects.get(i).inputImage = effects.get(i-1).getOutput();
      //}
    }
  }
  
  public void mousePressed(){
    for(int i=0;i<creationMenu.size();i++){
      creationMenu.get(i).mousePressed();
    }
    for(int i=0;i<menuItems.size();i++){
      menuItems.get(i).mousePressed();
    }
  }
  
  public void mouseReleased(){
    for(MenuItem mi: menuItems){
      mi.mouseReleased();
    }
  }
  
  public void mouseDragged(){
    for(MenuItem mi: menuItems){
      mi.mouseDragged();
    }
  }
  
  public void show(){
    pushStyle();
    noStroke();
    if(mouseX <= width/4){
      fill(51,75);
      rect(0,0,width/4,height);
      for(MenuItem mi: creationMenu){
        mi.show();
      }
      for(MenuItem mi: menuItems){
        mi.show();
      }
    }
    popStyle();
  }
}
abstract class MenuItem{
  
  float position;
  float len;
  
  boolean hovered;
  Menu menu;
  
  public MenuItem(float pos, Menu m){
    position = pos;
    hovered = false;
    menu = m;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
  }
  
  public abstract void mousePressed();
  
  public abstract void mouseReleased();
  
  public abstract void mouseDragged();
  
  public void show(){
    if(mouseX<width/4 && mouseY>position && mouseY<position+len){
      hovered = true;
    } else{
      hovered = false;
    }
    pushStyle();
    if(hovered){
      fill(30,75);
    }else{
      fill(51,75);
    }
    rect(0, position, width/4, len);
    popStyle();
  }
}
class Modulator extends Effect {
  
  float modulation;
  
  int redModulator, greenModulator, blueModulator;
  
  public Modulator(float mod){
    modulation = mod;
    
    redModulator = 1;
    greenModulator = 1;
    blueModulator = 1;
  }
  
  public void setModulation(float mod){
    modulation = mod;
  }
  
  public void setModulator(int mod){
    redModulator = mod;
    greenModulator = mod;
    blueModulator = mod;
  }
  
  public void setRedModulator(int redMod){
    redModulator = redMod;
  }
  
  public void setGreenModulator(int greenMod){
    greenModulator = greenMod;
  }
  
  public void setBlueModulator(int blueMod){
    blueModulator = blueMod;
  }
  
  public void setSelfModulation(){
    redModulator = 1;
    greenModulator = 2;
    blueModulator = 3;
  }
  
  public int apply(int pixel){
    float inputRed = red(pixel);
    float inputGreen = green(pixel);
    float inputBlue = blue(pixel);
    
    float outputRed = 0;
    float outputGreen = 0;
    float outputBlue = 0;
    
    switch(redModulator){
      case 0:
        outputRed = inputRed;
        break;
      case 1:
        outputRed = inputRed%(modulation*inputRed);
        break;
      case 2:
        outputRed = inputRed%(modulation*inputGreen);
        break;
      case 3:        
        outputRed = inputRed%(modulation*inputBlue);
        break;
    }
    
    switch(greenModulator){
      case 0:
        outputGreen = inputGreen;
        break;
      case 1:
        outputGreen = inputGreen%(modulation*inputRed);
        break;
      case 2:
        outputGreen = inputGreen%(modulation*inputGreen);
        break;
      case 3:        
        outputGreen = inputGreen%(modulation*inputBlue);
        break;
    }
    
    switch(blueModulator){
      case 0:
        outputBlue = inputBlue;
        break;
      case 1:
        outputBlue = inputBlue%(modulation*inputRed);
        break;
      case 2:
        outputBlue = inputBlue%(modulation*inputGreen);
        break;
      case 3:        
        outputBlue = inputBlue%(modulation*inputBlue);
        break;
    }
    
    colorMode(RGB);
    return color(outputRed, 
                 outputGreen, 
                 outputBlue);
  }
}
class ModulatorMenu extends MenuItem{
  
  boolean advanced;
    
  Slider modulation;
  
  Modulator modulator;
  int moder;
  
  public ModulatorMenu(float pos, Menu m, Modulator mod){
    super(pos, m);
    modulator = mod;
    len = 100;
    advanced = false;
    modulation = new Slider(0, 1, 0.008f, new PVector(50, pos+45), width/8);
    moder = 1;
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    modulation.position.y = position+45;
  }
  
  @Override
  public void mousePressed(){
    if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
      advanced = !advanced;
    }
    //if(mouseX > width/8 && mouseX < width/4 && mouseY > position+20 && mouseY < position+35){
      //filter.highPass = !filter.highPass;
    //}
    if(mouseX > 90 && mouseX<130 && mouseY>position+55 && mouseY<position+65){
      if(!advanced){
        moder++;
        moder = moder%5;
      }else{
        modulator.redModulator++;
        modulator.redModulator = modulator.redModulator%4;
      }
    }
    if(mouseX > 90 && mouseX<130 && mouseY>position+65 && mouseY<position+80){
        modulator.greenModulator++;
        modulator.greenModulator = modulator.greenModulator%4;
    }
    if(mouseX > 90 && mouseX<130 && mouseY>position+80 && mouseY<position+95){
        modulator.blueModulator++;
        modulator.blueModulator = modulator.blueModulator%4;
    }
    if(mouseX > width/4 - 20 && mouseX < width/4 && mouseY > position && mouseY < position+20){
      effectsChain.removeEffect(modulator);
      m.removeMenuItem(this);
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
    pushStyle();
    colorMode(RGB);
    super.show();
    fill(255);
    textSize(19);
    text("Modulator", 0, position+19);
    text("X", width/4 - 20, position+19);
    textSize(15);
    if(advanced){
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Advanced", 0, position+37);
      fill(255);
      textSize(10);
      text("Mod", 0, position+50);
      modulation.show();
      textSize(15);
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
      text("Red:", 0, position+65);
      if(mouseX > 90 && mouseX<130 && mouseY>position+55 && mouseY<position+65){
        fill(0,255,0);
      }
      text(redModer, 90, position+65);
      fill(255);
      text("Green:", 0, position+80);
      if(mouseX > 90 && mouseX<130 && mouseY>position+65 && mouseY<position+80){
        fill(0,255,0);
      }
      text(greenModer, 90, position+80);
      fill(255);
      text("Blue:", 0, position+95);
      if(mouseX > 90 && mouseX<130 && mouseY>position+80 && mouseY<position+95){
        fill(0,255,0);
      }
      text(blueModer, 90, position+95);
      fill(255);
      
      modulator.setModulation(modulation.value);
    }else{
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Simple", 0, position+20+15);
      fill(255);
      textSize(10);
      text("Mod", 0, position+50);
      modulation.show();
      textSize(15);
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
      text("Modulator:",0,position+65);
      if(mouseX > 90 && mouseX<130 && mouseY>position+55 && mouseY<position+65){
        fill(0,255,0);
      }
      text(modString, 90, position+65);
      fill(255);
      
      modulator.setModulation(modulation.value);
      if(moder==4){
        modulator.setSelfModulation();
      }else{
        modulator.setModulator(moder);
      }
    }
    popStyle();
  }
}
class Noise extends Effect{
  
  float redFactor, greenFactor, blueFactor;
  
  public Noise(float factor){    
    redFactor = factor;
    greenFactor = factor;
    blueFactor = factor;
  }
  
  public void setFactor(float factor){
    redFactor = factor;
    greenFactor = factor;
    blueFactor = factor;
  }
  
  public void setRedFactor(float factor){
    redFactor = factor;
  }
  
  public void setGreenFactor(float factor){
    greenFactor = factor;
  }
  
  public void setBlueFactor(float factor){
    blueFactor = factor;
  }
  
  public int apply(int pixel){
    float inputRed, inputGreen, inputBlue;
    float redNoise, greenNoise, blueNoise;
    inputRed = red(pixel);
    inputGreen = green(pixel);
    inputBlue = blue(pixel);
      
    redNoise = random(-inputRed*redFactor, inputRed*redFactor);
    greenNoise = random(-inputGreen*greenFactor, inputGreen*greenFactor);
    blueNoise = random(-inputBlue*blueFactor, inputBlue*blueFactor);
    
    colorMode(RGB);
    return color(inputRed+redNoise, inputGreen+greenNoise, inputBlue+blueNoise);
  }
}
class NoiseMenu extends MenuItem{
  
  boolean advanced;
    
  Slider redFactor;
  Slider greenFactor;
  Slider blueFactor;
  
  Noise noise;
  
  public NoiseMenu(float pos, Menu m, Noise n){
    super(pos, m);
    noise = n;
    len = 80;
    advanced = false;
    redFactor = new Slider(0, 1, 0.005f, new PVector(50, pos+45), width/8);
    greenFactor = new Slider(0, 1, 0.005f, new PVector(50, pos+55), width/8);
    blueFactor = new Slider(0, 1, 0.005f, new PVector(50, pos+65), width/8);
  }
  
  public void updatePosition(float newPos){
    position = newPos;
    redFactor.position.y = position+45;
    greenFactor.position.y = position+55;
    blueFactor.position.y = position+65;
  }
  
  @Override
  public void mousePressed(){
    if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
      advanced = !advanced;
    }
    if(mouseX > width/4 - 20 && mouseX < width/4 && mouseY > position && mouseY < position+20){
      effectsChain.removeEffect(noise);
      m.removeMenuItem(this);
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
    pushStyle();
    colorMode(RGB);
    super.show();
    fill(255);
    textSize(19);
    text("Noise", 0, position+19);
    text("X", width/4 - 20, position+19);
    textSize(15);
    //if(filter.highPass){
    //  text("High Pass", width/8, position+37);
    //}else{
    //  text("Low Pass", width/8, position+37);
    //}
    if(advanced){
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Advanced", 0, position+37);
      fill(255);
      textSize(10);
      text("Red", 0, position+45);
      redFactor.show();
      text("Green", 0, position+55);
      greenFactor.show();
      text("Blue", 0, position+65);
      blueFactor.show();
      
      noise.setRedFactor(redFactor.value);
      noise.setGreenFactor(greenFactor.value);
      noise.setBlueFactor(blueFactor.value);
    }else{
      if(mouseX < 75 && mouseY > position+20 && mouseY < position+35){
        fill(0,255,0);
      }
      text("Simple", 0, position+20+15);
      fill(255);
      textSize(10);
      text("Factor", 0, position+45);
      redFactor.show();
      
      noise.setFactor(redFactor.value);
    }
    popStyle();
  }
}
class OpenImage extends MenuItem{
  
  public OpenImage(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  public void mousePressed(){
    if(hovered){
      pause = true;
      selectInput("Select input image", "imageSelected");
    }
  }
  
  public void mouseReleased(){}
  
  public void mouseDragged(){}
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("Open image", 0, position+len*0.75f);
    popStyle();
  }

}
class SaveImage extends MenuItem{
  
  public SaveImage(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  public void mousePressed(){
    if(hovered){
      print = true;
    }
  }
  
  public void mouseReleased(){}
  
  public void mouseDragged(){}
  
  public void show(){
    pushStyle();
    super.show();
    textSize(len*0.75f);
    fill(255);
    stroke(0);
    text("Save image", 0, position+len*0.75f);
    popStyle();
  }

}
class Slider{
  float lowerBound;
  float higherBound;
  float increment;
  
  float value;
  
  PVector position;
  float length;
  
  boolean selected;
  
  public Slider(float low, float high, float inc, PVector pos, float l){
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
    PVector mouse = new PVector(mouseX, mouseY);
    
    float t = value/(higherBound-lowerBound);
    PVector pos = new PVector(position.x+t*length, position.y);
    
    if(mouse.dist(pos)<=5)
      selected = true;
  }
  
  public void mouseReleased(){
    selected = false;
  }
  
  public void mouseDragged(){
    
    
    int diff = mouseX - pmouseX;
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
    pushStyle();
    strokeWeight(2);
    stroke(0);
    line(position.x, position.y, position.x+length, position.y);
    strokeWeight(1);
    float t = value/(higherBound-lowerBound);
    fill(255);
    ellipse(position.x+t*length, position.y, 10, 10);
    textSize(8);
    text(value, position.x+t*length, position.y);
    popStyle();
  }
}
  public void settings() {  size(840,680); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pestreshop" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
