package pestreshop.menu;

import processing.core.*;

import pestreshop.Pestreshop;
import pestreshop.effects.Noise;

public class AddNoiseMenu extends MenuItem{
  
  public AddNoiseMenu(Pestreshop sk, float pos, Menu m){
    super(sk, pos, m);
    len = 25;
  }
  
  @Override
  public void mousePressed(){
    float p = menu.creationMenu.get(menu.creationMenu.size()-1).position + menu.creationMenu.get(menu.creationMenu.size()-1).len;
    if(hovered){
      //if(!effects.isEmpty())
        //reference = effects.get(effects.size()-1).getOutput();
      Noise n = new Noise(sketch, 0.5f);
      if(!menu.menuItems.isEmpty())
        p = menu.menuItems.get(menu.menuItems.size()-1).position + menu.menuItems.get(menu.menuItems.size()-1).len;
      NoiseMenu nMenu = new NoiseMenu(sketch, p, menu, n);
      menu.effectsChain.addEffect(n);
      menu.menuItems.add(nMenu);
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
    sketch.text("+ Add noise", 0f, position+len*0.75f);
    sketch.popStyle();
  }
}
