public class Sunflowers extends Adventurer{
  int sunExposure, sunExposureMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Sunflowers(String name, int hp){
    super(name,hp);
    sunExposureMax = 10;
    sunExposure = sunExposureMax/2;
  }


  public (String name){
    this(name,35);
  }

  public Sunflowers(){
    this("Sunflowers");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Sunflowers";
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
	if(getSpecial() == 2){
		int damage = 2;
		setSpecial(getSpecial() - damage);
		other.applyDamage(damage);
		return this + " used sunExposure! They laid out " + damage + " sunExposure and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
	}
	else if(getSpecial() == 3){
		int damage = (int)(Math.random()*2)+2;
		setSpecial(getSpecial() - damage);
		other.applyDamage(damage);
		return this + " used sunburn! They laid out " + damage + " sunExposure and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
	}
    else if(getSpecial() > 3){
		int damage = (int)(Math.random()*3)+2;
		setSpecial(getSpecial() - damage);
		other.applyDamage(damage);
		return this + " used sunburn! They laid out " + damage + " sunExposure and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
    }else{
      return "Oh no! "+this + " doesn't have enough sunExposure to lure over its friends! Nothing happened..";
    }

  }
  /*If ally is a Sunflowers: restores 3 special. Else, restore 1 special.*/
  public String support(Adventurer other){
	if(other instanceof Sunflowers){
		other.restoreSpecial(3);
		return this + "used Vitamin D! They gave a Vitamin D to "+other+", making them smarter. " + other + " regains 3 sunExposure!";
	}
	else{
		return this + "used Vitamin D! They tried to give a Vitamin D to "+other+", but seeing as plants don't know computer science, they couldn't do it. Thus, "+ this + " decided to use the test as compost for " + other + ", restoring " + other.restoreSpecial(1)+" "+other.getSpecialName();
	}
  }
  /*75% chance of regaining 4 sunExposure, 25% chance of nothing happening*/
  public String support(){
	int chance = (int)(Math.random()*4);
	if(chance < 3){
		restoreSpecial(4);
		return this + " used Plant! It's their lucky day! They spotted 4 sunExposure, eagerly shambling over to eat them. 4 sunExposure were restored!";
	}
    else{
		return this + " used Plant! Unfortunately, no sunExposure were to be found nearby..";
	}
  }
}
