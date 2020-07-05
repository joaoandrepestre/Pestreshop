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
    redFactor = new Slider(0, 1, 0.005, new PVector(50, pos+45), width/8);
    greenFactor = new Slider(0, 1, 0.005, new PVector(50, pos+55), width/8);
    blueFactor = new Slider(0, 1, 0.005, new PVector(50, pos+65), width/8);
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
