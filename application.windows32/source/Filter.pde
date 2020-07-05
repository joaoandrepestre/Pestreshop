class Filter extends Effect{
  
  boolean highPass;
  
  float redTreshold;
  float greenTreshold;
  float blueTreshold;
  
  public Filter(boolean pass, float treshold){    
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
  public color apply(color pixel){
    float inputRed = red(pixel);
    float inputGreen = green(pixel);
    float inputBlue = blue(pixel);
  
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
  
    colorMode(RGB);
    return color(outputRed,
                  outputGreen,
                  outputBlue);
  
  }
}
