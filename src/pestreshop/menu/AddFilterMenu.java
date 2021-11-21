package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.effects.Filter;

public class AddFilterMenu extends MenuItem{
  
  public AddFilterMenu(Pestreshop sk, float pos, Menu m){
    super(sk, pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Filter f = new Filter(sketch, true, 0.5f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      FilterMenu fMenu = new FilterMenu(sketch, p, menu, f);
      menu.effectsChain.addEffect(f);
      menu.menuItems.add(fMenu);
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
    sketch.text("+ Add new filter", 0f, position+len*0.75f);
    sketch.popStyle();
  }
}
