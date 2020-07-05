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
