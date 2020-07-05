class Mapper extends Effect{
  
  boolean hsbMap;
  
  int comp1, comp2, comp3;
  
  public Mapper(){
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

  public color apply(color pixel){
    float inputComp1, inputComp2, inputComp3;
    float outputComp1 = 0;
    float outputComp2 = 0;
    float outputComp3 = 0;
    color outPixel;
    
    if(hsbMap){
      inputComp1 = red(pixel);
      inputComp2 = green(pixel);
      inputComp3 = blue(pixel);
      
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
      
      colorMode(HSB);
      outPixel = color(outputComp1, outputComp2, outputComp3);
    }else{
      inputComp1 = hue(pixel);
      inputComp2 = saturation(pixel);
      inputComp3 = brightness(pixel);
      
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
      
      colorMode(RGB);
      outPixel = color(outputComp1, outputComp2, outputComp3);
    }
    
    return outPixel;
  }
}
