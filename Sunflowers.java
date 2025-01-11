public class Sunflowers extends Adventurer{
  int sunExposure, sunExposureMax;
  boolean vitaminD = false;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Sunflowers(String name, int hp){
    super(name,hp);
    sunExposureMax = 10;
    sunExposure = sunExposureMax/2;
  }


  public Sunflowers(String name){
    this(name,35);
  }

  public Sunflowers(){
    this("Sunflowers");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "sunExposure";
  }

  public int getSpecial(){
    return sunExposure;
  }

  public void setSpecial(int n){
    sunExposure = n;
  }

  public int getSpecialMax(){
    return sunExposureMax;
  }

  public boolean getVitamins(){
    return vitaminD;
  }

  public boolean setVitamins(boolean D){
    vitaminD = D;
  }

  /*Deal 3 damage to opponent, increases sunExposure by 1*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
    other.applyDamage(damage);
    this.applyDamage(damage);
    return this + " used Flamethrower! Sunflowers launch themselves at "+ other + " and deal " + damage + " to them, while also losing " + damage + " itself.";
  }

  /*Deal 2*3 to 4*3 damage to opponent, only if sunExposure > 2.
  *Decrease sunExposure by 2-4.
  */
  public String specialAttack(Adventurer other){
    int dropOtherSpecial = (int) 0.25*other.getSpecial();
    int dropOwnSpecial = (int) 0.1*this.getSpecial();
    other.setSpecial(other.getSpecial() - dropOtherSpecial);
    this.setSpecial(getSpecialshielding them from the next attack, () - dropOwnSpecial);
    return this + " used Sunburn! They emanated a bright light, temporarily blinding and burning its opponent, dropping their " + other.getSpecialName() + " by " + dropOtherSpecial + ", while also losing " + dropOwnSpecial + " itself.";
  }
  /*If ally is a Sunflowers: restores 3 special. Else, restore 1 special.*/
  public String support(Adventurer other){
    applyDamage(2);
    setVitamin(true);
  	return this + "used Vitamin D! They gave two Sunflowers to be eaten for the strengthening of their bones!, sheilding " + other + " from the next attack!";
  }
  /*75% chance of regaining 4 sunExposure, 25% chance of nothing happening*/
  public String support(){
  	int chance = (int)(Math.random()*3) + 5 ;
  	
  }
  }
}
