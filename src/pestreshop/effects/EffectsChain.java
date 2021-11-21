package pestreshop.effects;

import processing.core.*;
import java.util.List;
import java.util.ArrayList;

import pestreshop.Pestreshop;

public class EffectsChain{

  public Pestreshop sketch;

  PImage inputImage;
  PImage outputImage;
  
  List<Effect> effects;
  
  public EffectsChain(Pestreshop sk, PImage reference){
    sketch = sk;

    inputImage = reference;
    outputImage = reference.copy();
    
    effects = new ArrayList<Effect>();
  }
  
  public void addEffect(Effect e){
    effects.add(e);
  }
  
  public void removeEffect(Effect e){
    effects.remove(e);
  }
  
  public void redefineInputImage(PImage reference){
    inputImage = reference;
    outputImage = reference.copy();
  }
  
  public PImage apply(){
    if(!sketch.pause){
      int pixel;
      for(int i=0;i<inputImage.pixels.length;i++){
        pixel = inputImage.pixels[i];
        for(Effect e: effects){
          pixel = e.apply(pixel);
        }
        outputImage.pixels[i] = pixel;
      }
      outputImage.updatePixels();
    }
    return outputImage;
  }
}
