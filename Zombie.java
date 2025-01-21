public class Zombie extends Adventurer{
  int brains, brainsMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Zombie(String name, int hp){
    super(name,hp);
    brainsMax = 10;
    brains = brainsMax/2;
  }


  public Zombie(String name){
    this(name,35);
  }

  public Zombie(){
    this("Zombie");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "brains";
  }

  public int getSpecial(){
    return brains;
  }

  public void setSpecial(int n){
    brains = n;
  }

  public int getSpecialMax(){
    return brainsMax;
  }

  /*Deal 3 damage to opponent, increases brains by 1*/
  public String attack(Adventurer other){
    if (other.getShield() == true){
      other.setShield(false);
      return other + " had a shield up! The attack did nothing...";
    }
    int damage = 3;
    other.applyDamage(damage);
	if(getSpecial() == getSpecialMax()){
		return this + " used Devour! They eat " + other + "'s brains and deal 3 damage to them, but they were too full to swallow the brain, so they just spit it out instead.";
	}
    restoreSpecial(1);
    return this + " used Devour! They eat "+ other + "'s brains and deal 3 damage to them, while also gaining 1 brain.";
  }

  /*Deal 2*3 to 4*3 damage to opponent, only if brains > 2.
  *Decrease brains by 2-4.
  */
  public String specialAttack(Adventurer other){
    if (other.getShield() == true){
      other.setShield(false); 
      return other + " had a shield up! The attack did nothing...";
    }
    else {
      if(getSpecial() == 2){
        int damage = 2;
        setSpecial(getSpecial() - damage);
        other.applyDamage(damage);
        return this + " used Horde! They laid out " + damage + " brains and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
      }
      else if(getSpecial() == 3){
        int damage = (int)(Math.random()*2)+2;
        setSpecial(getSpecial() - damage);
        other.applyDamage(damage);
        return this + " used Horde! They laid out " + damage + " brains and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
      }
      else if(getSpecial() > 3){
        int damage = (int)(Math.random()*3)+2;
        setSpecial(getSpecial() - damage);
        other.applyDamage(damage);
        return this + " used Horde! They laid out " + damage + " brains and lured " + damage + " of its friends to come help it! They each attack " + other + " one time, dealing " + damage + " points of damage.";
      }else{
          return "Oh no! "+this + " doesn't have enough brains to lure over its friends! Nothing happened..";
      }
    }
  }

  /*If ally is a Zombie: restores 3 special. Else, restore 1 special.*/
  public String support(Adventurer other){
	boolean limit = other.getSpecial() == other.getSpecialMax();
    if (other instanceof Chomper && !(other.getSpecial() < 2)){
      other.setSpecial(other.getSpecial()-1);
      return this + " used CS Test! They tried to give a CS test to "+other+", but seeing as plants don't know computer science, they couldn't do it. Thus, "+ this + " decided to use the test as compost for " + other + ", subtracting 1" +other.getSpecialName() +".";
    }
    else if(other instanceof Zombie && !limit){
      other.restoreSpecial(3);
      return this + " used CS Test! They gave a CS test to "+other+", making them smarter. " + other + " regains 3 brains!";
    }
    else if(!limit){
      return this + " used CS Test! They tried to give a CS test to "+other+", but seeing as plants don't know computer science, they couldn't do it. Thus, "+ this + " decided to use the test as compost for " + other + ", restoring " + other.restoreSpecial(1)+" "+other.getSpecialName() +".";
    }
	else{
	  return this + " tried to use CS Test, but " + other + "'s special stat was already maxed out!";	
	}
  }
  /*75% chance of regaining 4 brains, 25% chance of nothing happening*/
  public String support(){
    int chance = (int)(Math.random()*4);
    if(chance < 3){
		if(getSpecial()+4 < getSpecialMax()){
			restoreSpecial(4);
			return this + " used Scavenge! It's their lucky day! They spotted 4 brains, eagerly shambling over to eat them. 4 brains were restored!";
		}
		if(getSpecial() == getSpecialMax()){
			return this + " used Scavenge! It's their lucky day! They spotted 4 brains..but what's this? " + this + " looks too full to eat them! They ignore the brains and shamble onwards.";
		}
		else{
			int restoration = getSpecialMax()-getSpecial();
			restoreSpecial(restoration);
			return this + " used Scavenge! It's their lucky day! They spotted "+restoration+" brains, eagerly shambling over to eat them. "+restoration+" brains were restored!";
		}
      
    }
      else{
      return this + " used Scavenge! Unfortunately, no brains were to be found nearby..";
    }
  }
}
