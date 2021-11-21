package pestreshop.effects;

import processing.core.*;

import pestreshop.Pestreshop;

public class Modulator extends Effect {
  
  float modulation;
  
  public int redModulator, greenModulator, blueModulator;
  
  public Modulator(Pestreshop sk, float mod){
    sketch = sk;

    modulation = mod;
    
    redModulator = 1;
    greenModulator = 1;
    blueModulator = 1;
  }
  
  public void setModulation(float mod){
    modulation = mod;
  }
  
  public void setModulator(int mod){
    redModulator = mod;
    greenModulator = mod;
    blueModulator = mod;
  }
  
  public void setRedModulator(int redMod){
    redModulator = redMod;
  }
  
  public void setGreenModulator(int greenMod){
    greenModulator = greenMod;
  }
  
  public void setBlueModulator(int blueMod){
    blueModulator = blueMod;
  }
  
  public void setSelfModulation(){
    redModulator = 1;
    greenModulator = 2;
    blueModulator = 3;
  }
  
  public int apply(int pixel){
    float inputRed = sketch.red(pixel);
    float inputGreen = sketch.green(pixel);
    float inputBlue = sketch.blue(pixel);
    
    float outputRed = 0;
    float outputGreen = 0;
    float outputBlue = 0;
    
    switch(redModulator){
      case 0:
        outputRed = inputRed;
        break;
      case 1:
        outputRed = inputRed%(modulation*inputRed);
        break;
      case 2:
        outputRed = inputRed%(modulation*inputGreen);
        break;
      case 3:        
        outputRed = inputRed%(modulation*inputBlue);
        break;
    }
    
    switch(greenModulator){
      case 0:
        outputGreen = inputGreen;
        break;
      case 1:
        outputGreen = inputGreen%(modulation*inputRed);
        break;
      case 2:
        outputGreen = inputGreen%(modulation*inputGreen);
        break;
      case 3:        
        outputGreen = inputGreen%(modulation*inputBlue);
        break;
    }
    
    switch(blueModulator){
      case 0:
        outputBlue = inputBlue;
        break;
      case 1:
        outputBlue = inputBlue%(modulation*inputRed);
        break;
      case 2:
        outputBlue = inputBlue%(modulation*inputGreen);
        break;
      case 3:        
        outputBlue = inputBlue%(modulation*inputBlue);
        break;
    }
    
    sketch.colorMode(sketch.RGB);
    return sketch.color(outputRed, 
                 outputGreen, 
                 outputBlue);
  }
}
