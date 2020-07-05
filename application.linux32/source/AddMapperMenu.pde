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
    textSize(len*0.75);
    fill(255);
    stroke(0);
    text("+ Add new mapper", 0, position+len*0.75);
    popStyle();
  }
}
