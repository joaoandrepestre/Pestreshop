class EffectsChain{

  PImage inputImage;
  PImage outputImage;
  
  ArrayList<Effect> effects;
  
  public EffectsChain(PImage reference){
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
    if(!pause){
      color pixel;
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
