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
    textSize(len*0.75);
    fill(255);
    stroke(0);
    text("Open image", 0, position+len*0.75);
    popStyle();
  }

}
