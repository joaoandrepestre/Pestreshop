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
    modulation = new Slider(0, 1, 0.008, new PVector(50, pos+45), width/8);
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
