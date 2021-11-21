package pestreshop.effects;

import processing.core.*;

import pestreshop.Pestreshop;

public class Filter extends Effect{
  
  public boolean highPass;
  
  float redTreshold;
  float greenTreshold;
  float blueTreshold;
  
  public Filter(Pestreshop sk, boolean pass, float treshold){
    sketch = sk;    
    highPass = pass;
    
    redTreshold = treshold;
    greenTreshold = treshold;
    blueTreshold = treshold;
  }
  
  public void toggleHighPass(){
    highPass = !highPass;
  }
  
  public void setTreshold(float treshold){
    redTreshold = treshold;
    greenTreshold = treshold;
    blueTreshold = treshold;
  }
  
  public void setRedTreshold(float treshold){
    redTreshold = treshold;
  }
  
  public void setGreenTreshold(float treshold){
    greenTreshold = treshold;
  }
  
  public void setBlueTreshold(float treshold){
    blueTreshold = treshold;
  }
  
  @Override
  public int apply(int pixel){
    float inputRed = sketch.red(pixel);
    float inputGreen = sketch.green(pixel);
    float inputBlue = sketch.blue(pixel);
  
    float outputRed = 0;
    float outputGreen = 0;
    float outputBlue = 0;
    if((highPass && inputRed >= 255*redTreshold) || (!highPass && inputRed <= 255*redTreshold)){
      outputRed = inputRed;
    }
    if((highPass && inputGreen >= 255*greenTreshold) || (!highPass && inputGreen <= 255*greenTreshold)){
      outputGreen = inputGreen;
    }
    if((highPass && inputBlue >= 255*blueTreshold) || (!highPass && inputBlue <= 255*blueTreshold)){
      outputBlue = inputBlue;
    }
  
    sketch.colorMode(sketch.RGB);
    return sketch.color(outputRed,
                  outputGreen,
                  outputBlue);
  
  }
}
