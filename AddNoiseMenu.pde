class AddNoiseMenu extends MenuItem{
  
  public AddNoiseMenu(float pos, Menu m){
    super(pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      //if(!effects.isEmpty())
        //reference = effects.get(effects.size()-1).getOutput();
      Noise n = new Noise(0.5);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      NoiseMenu nMenu = new NoiseMenu(p, menu, n);
      menu.effectsChain.addEffect(n);
      menu.menuItems.add(nMenu);
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
    text("+ Add noise", 0, position+len*0.75);
    popStyle();
  }
}
