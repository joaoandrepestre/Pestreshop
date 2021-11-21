package pestreshop.effects;

import processing.core.*;

import pestreshop.Pestreshop;

public class Noise extends Effect{
  
  float redFactor, greenFactor, blueFactor;
  
  public Noise(Pestreshop sk, float factor){
    sketch = sk;

    redFactor = factor;
    greenFactor = factor;
    blueFactor = factor;
  }
  
  public void setFactor(float factor){
    redFactor = factor;
    greenFactor = factor;
    blueFactor = factor;
  }
  
  public void setRedFactor(float factor){
    redFactor = factor;
  }
  
  public void setGreenFactor(float factor){
    greenFactor = factor;
  }
  
  public void setBlueFactor(float factor){
    blueFactor = factor;
  }
  
  public int apply(int pixel){
    float inputRed, inputGreen, inputBlue;
    float redNoise, greenNoise, blueNoise;
    inputRed = sketch.red(pixel);
    inputGreen = sketch.green(pixel);
    inputBlue = sketch.blue(pixel);
      
    redNoise = sketch.random(-inputRed*redFactor, inputRed*redFactor);
    greenNoise = sketch.random(-inputGreen*greenFactor, inputGreen*greenFactor);
    blueNoise = sketch.random(-inputBlue*blueFactor, inputBlue*blueFactor);
    
    sketch.colorMode(sketch.RGB);
    return sketch.color(inputRed+redNoise, inputGreen+greenNoise, inputBlue+blueNoise);
  }
}
