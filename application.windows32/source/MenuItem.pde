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
  
  abstract void mousePressed();
  
  abstract void mouseReleased();
  
  abstract void mouseDragged();
  
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
