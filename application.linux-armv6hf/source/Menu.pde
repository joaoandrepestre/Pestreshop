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
