package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.effects.Modulator;

public class AddModulatorMenu extends MenuItem{
  
  public AddModulatorMenu(Pestreshop sk, float pos, Menu m){
    super(sk, pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Modulator mod = new Modulator(sketch, 0.25f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      ModulatorMenu modMenu = new ModulatorMenu(sketch, p, menu, mod);
      menu.effectsChain.addEffect(mod);
      menu.menuItems.add(modMenu);
    }
  }
  
  public void mouseReleased(){
  }
  
  public void mouseDragged(){
  }
  
  public void show(){
    sketch.pushStyle();
    super.show();
    sketch.textSize(len*0.75f);
    sketch.fill(255);
    sketch.stroke(0);
    sketch.text("+ Add new modulator", 0f, position+len*0.75f);
    sketch.popStyle();
  }
}
