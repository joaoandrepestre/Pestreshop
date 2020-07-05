class Noise extends Effect{
  
  float redFactor, greenFactor, blueFactor;
  
  public Noise(float factor){    
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
  
  public color apply(color pixel){
    float inputRed, inputGreen, inputBlue;
    float redNoise, greenNoise, blueNoise;
    inputRed = red(pixel);
    inputGreen = green(pixel);
    inputBlue = blue(pixel);
      
    redNoise = random(-inputRed*redFactor, inputRed*redFactor);
    greenNoise = random(-inputGreen*greenFactor, inputGreen*greenFactor);
    blueNoise = random(-inputBlue*blueFactor, inputBlue*blueFactor);
    
    colorMode(RGB);
    return color(inputRed+redNoise, inputGreen+greenNoise, inputBlue+blueNoise);
  }
}
