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
    redTreshold = new Slider(0, 1, 0.008, new PVector(50, pos+45), width/8);
    greenTreshold = new Slider(0, 1, 0.008, new PVector(50, pos+55), width/8);
    blueTreshold = new Slider(0, 1, 0.008, new PVector(50, pos+65), width/8);
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
