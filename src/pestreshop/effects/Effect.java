package pestreshop.effects;

import pestreshop.Pestreshop;

public abstract class Effect {

  public Pestreshop sketch;
  
  abstract int apply(int pixel);
}
