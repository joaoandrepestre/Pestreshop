class AddModulatorMenu extends MenuItem{
  
  public AddModulatorMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = m.creationMenu.get(m.creationMenu.size()-1).position + m.creationMenu.get(m.creationMenu.size()-1).len;
    if(hovered){
      Modulator mod = new Modulator(0.25);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      ModulatorMenu modMenu = new ModulatorMenu(p, menu, mod);
      menu.effectsChain.addEffect(mod);
      m.menuItems.add(modMenu);
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
    text("+ Add new modulator", 0, position+len*0.75);
    popStyle();
  }
}
