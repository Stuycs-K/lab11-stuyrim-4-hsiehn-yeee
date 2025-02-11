public class Sunflowers extends Adventurer{
  int sunExposure, sunExposureMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Sunflowers(String name, int hp){
    super(name,hp);
    sunExposureMax = 15;
    sunExposure = 5;
  }

  public Sunflowers(String name){
    this(name,30);
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

  /*Deal 3 damage to opponent, increases sunExposure by 1*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
	if(getHP() < damage){
		return this + " didn't have enough sunflowers to use Flamethrower!";
	}
    if (other.getShield() == true){
      other.applyDamage(damage);
      return other + " had a shield up! The attack did nothing...";
    }
    other.applyDamage(damage);
    this.applyDamage(damage);
    return this + " used Flamethrower! Sunflowers launch themselves at "+ other + " and deal " + damage + " damage to them, while also losing " + damage + " itself.";
  }

  /*Decrease opponent's special by 25%, also dropping their own by 10%. No threshold of special required
  */
  public String specialAttack(Adventurer other){
    int dropOtherSpecial = (int) (0.50*other.getSpecial());
    int dropOwnSpecial = (int) (0.35*this.getSpecial());
	if(dropOtherSpecial == 0 || dropOwnSpecial == 0){
		return this + " tried to use Sunburn, but they couldn't emanate a bright enough light to drop the other's special by a significant amount..";
	}
	this.setSpecial(this.getSpecial() - dropOwnSpecial);
    if (other instanceof Chomper){
      other.setSpecial(other.getSpecial() + dropOtherSpecial);
	  return this + " used Sunburn! They emanated a bright light, temporarily blinding and burning their opponent, raising their " + other.getSpecialName() + " by " + dropOtherSpecial + ", while also losing " + dropOwnSpecial + " itself.";
    }else{
      other.setSpecial(other.getSpecial() - dropOtherSpecial);
	  return this + " used Sunburn! They emanated a bright light, temporarily blinding and burning their opponent, dropping their " + other.getSpecialName() + " by " + dropOtherSpecial + ", while also losing " + dropOwnSpecial + " itself.";
    }
    
    
  }
  /*Shields the allys from the next atack and take two damage NOTE: A shield attribute must be applied to all other adventurers.*/
  public String support(Adventurer other){
	if(getHP() < 2){
		return this + " tried to use Vitamin D, but they didn't have enough Sunflowers to be eaten!";
	}
    applyDamage(2);
    other.setShield(true);
  	return this + " used Vitamin D! They gave two Sunflowers to be eaten for the strengthening of their bones!, shielding " + other + " from the next attack!";
  }
  /*Regains 5-7HP while also gaining 2 special*/
  public String support(){
  	int heal = (int)(Math.random()*3) + 5 ;
    if(getHP()+heal <= getmaxHP()){
		restoreHP(heal);
	}
    else{
		setHP(getmaxHP());
	}
	int restoration = 0;
	if(getSpecial()+2 > getSpecialMax()){
		restoration = getSpecialMax()-getSpecial();
		setSpecial(getSpecialMax());
	}
	else{
		restoration = 2;
		restoreSpecial(2);
	}
  	
    return this + " used Plant! Sunflower seeds were sown, and " + heal + " new sunflowers have sprouted, gaining " + heal + " HP and raising sunExposure by "+restoration+"!";
  }
}
