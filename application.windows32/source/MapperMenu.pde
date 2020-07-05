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
