package pestreshop.menu;

import processing.core.*;
import java.util.List;
import java.util.ArrayList;

import pestreshop.Pestreshop;
import pestreshop.effects.*;

public class Menu {

  public Pestreshop sketch;
  
  List<MenuItem> creationMenu;
  List<MenuItem> menuItems;
  
  EffectsChain effectsChain;
  
  public Menu(Pestreshop sk, EffectsChain ec){
    sketch = sk;
    effectsChain = ec;
    creationMenu = new ArrayList<MenuItem>();
    menuItems = new ArrayList<MenuItem>();
    creationMenu.add(new OpenImage(sketch, 0f, this));
    creationMenu.add(new SaveImage(sketch, 25f,this));
    creationMenu.add(new AddFilterMenu(sketch, 50f, this));
    creationMenu.add(new AddNoiseMenu(sketch, 75f, this));
    creationMenu.add(new AddModulatorMenu(sketch, 100f, this));
    creationMenu.add(new AddMapperMenu(sketch, 125f, this));
  }
  
  public void removeMenuItem(MenuItem mi){
    int index = menuItems.indexOf(mi);
    menuItems.remove(mi);
    for(int i=index;i<menuItems.size();i++){
      menuItems.get(i).updatePosition(menuItems.get(i).position-mi.len);
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
    sketch.pushStyle();
    sketch.noStroke();
    if(sketch.mouseX <= sketch.width/4){
      sketch.fill(51,75);
      sketch.rect(0,0,sketch.width/4,sketch.height);
      for(MenuItem mi: creationMenu){
        mi.show();
      }
      for(MenuItem mi: menuItems){
        mi.show();
      }
    }
    sketch.popStyle();
  }
}
