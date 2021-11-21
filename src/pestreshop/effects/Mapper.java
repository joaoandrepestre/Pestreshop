package pestreshop.effects;

import processing.core.*;

import pestreshop.Pestreshop;

public class Mapper extends Effect{
  
  public boolean hsbMap;
  
  public int comp1, comp2, comp3;
  
  public Mapper(Pestreshop sk){
    sketch = sk;

    hsbMap = true;
    
    comp1 = 0;
    comp2 = 1;
    comp3 = 2;
  }
  
  public void toggleHSB(){
    hsbMap = !hsbMap;
  }
  
  public void setComp1(int c1){
    comp1 = c1;
  }
  
  public void setComp2(int c2){
    comp2 = c2;
  }
  
  public void setComp3(int c3){
    comp3 = c3;
  }

  public int apply(int pixel){
    float inputComp1, inputComp2, inputComp3;
    float outputComp1 = 0;
    float outputComp2 = 0;
    float outputComp3 = 0;
    int outPixel;
    
    if(hsbMap){
      inputComp1 = sketch.red(pixel);
      inputComp2 = sketch.green(pixel);
      inputComp3 = sketch.blue(pixel);
      
      switch(comp1){
        case 0:
          outputComp1 = inputComp1;
          break;
        case 1:
          outputComp1 = inputComp2;
          break;
        case 2:
          outputComp1 = inputComp3;
          break;
      }
      
      switch(comp2){
        case 0:
          outputComp2 = inputComp1;
          break;
        case 1:
          outputComp2 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      switch(comp3){
        case 0:
          outputComp3 = inputComp1;
          break;
        case 1:
          outputComp3 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      sketch.colorMode(sketch.HSB);
      outPixel = sketch.color(outputComp1, outputComp2, outputComp3);
    }else{
      inputComp1 = sketch.hue(pixel);
      inputComp2 = sketch.saturation(pixel);
      inputComp3 = sketch.brightness(pixel);
      
      switch(comp1){
        case 0:
          outputComp1 = inputComp1;
          break;
        case 1:
          outputComp1 = inputComp2;
          break;
        case 2:
          outputComp1 = inputComp3;
          break;
      }
      
      switch(comp2){
        case 0:
          outputComp2 = inputComp1;
          break;
        case 1:
          outputComp2 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      switch(comp3){
        case 0:
          outputComp3 = inputComp1;
          break;
        case 1:
          outputComp3 = inputComp2;
          break;
        case 2:
          outputComp3 = inputComp3;
          break;
      }
      
      sketch.colorMode(sketch.RGB);
      outPixel = sketch.color(outputComp1, outputComp2, outputComp3);
    }
    
    return outPixel;
  }
}
