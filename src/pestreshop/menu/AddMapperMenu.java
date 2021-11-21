package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.effects.Mapper;

public class AddMapperMenu extends MenuItem{
  
  public AddMapperMenu(Pestreshop sk, float pos, Menu m){
    super(sk, pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      Mapper map = new Mapper(sketch);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      MapperMenu mapMenu = new MapperMenu(sketch, p, menu, map);
      menu.effectsChain.addEffect(map);
      menu.menuItems.add(mapMenu);
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
    sketch.text("+ Add new mapper", 0f, position+len*0.75f);
    sketch.popStyle();
  }
}
