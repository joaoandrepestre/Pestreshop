class AddFilterMenu extends MenuItem{
  
  public AddFilterMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Filter f = new Filter(true, 0.5);
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
    textSize(len*0.75);
    fill(255);
    stroke(0);
    text("+ Add new filter", 0, position+len*0.75);
    popStyle();
  }
}
