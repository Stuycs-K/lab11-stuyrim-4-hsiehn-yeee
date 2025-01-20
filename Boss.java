public class Boss extends Adventurer{
  int musclePower, musclePowerMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp){
    super(name,hp);
    musclePowerMax = 30;
    musclePower = musclePowerMax/2;
  }

  public Boss(String name){
    this(name, 40);
  }

  public Boss(){
    this("Zomboss");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "musclePower";
  }

  public int getSpecial(){
    return musclePower;
  }

  public void setSpecial(int n){
    musclePower = n;
  }

  public int getSpecialMax(){
    return musclePowerMax;
  }

  /*Deals 6-8 damage, makes opponent lose 1 special (chompers gain), and gains 4 special.*/
  public String attack(Adventurer other){
    if (other.getShield() == true){
      return other + " had a shield up! The attack did nothing...";
    }
    else {
      int damage = (int)(Math.random()*3)+8;
      other.applyDamage(damage);
      if (other instanceof Chomper){
        other.setSpecial(other.getSpecial() + 2);
      }else{
        other.setSpecial(other.getSpecial() - 2); 
      }
      setSpecial(getSpecial() + 4);
      return this + " used Clobber! Zomboss clubbed "+ other + " and deals " + damage + " damage to them, paralyzing them and making them lose 2" + other.getSpecialName() + ", while also gaining 4 musclePower";
    }
  }

  /*"Apocalypse": The Zomboss commands a horde of 8-10 zombies, dealing 2 damage per each zombie and losing 8-10 musclePower. Zombies are careless when they fight, so they damage the Zomboss as well, dealing 1-5 damage to the Zomboss. (musclePower must be greater than 25)
  */
  public String specialAttack(Adventurer other){
    int damage = (int)(Math.random()*3 + 8);
    int ownDamage = (int)(Math.random()*5 + 2);
    if (getSpecial() > 25){
      other.applyDamage(damage * 2);
      setSpecial(getSpecial()-damage);
      applyDamage(ownDamage);
      return this + " used Apocalypse! They commanded a horde of " + damage + " zombies, which dealt " + (damage * 2) + ", damage to " + other + ". " + this + " was also hurt in the process, and lost " + ownDamage + " HP. Lost " + damage + " musclePower. ";
    }else{
      return "oops! "+ this+ " did not have enough musclePower to use Apocalypse!";
    }
  }

  public String support(Adventurer other){
    return "Oh no..."+this+" doesn't have any real life friends :( his efforts to support others were in vain and nothing happened...";
  }


  /*"Shield": The Zomboss collects fallen zombie parts to form a shield, hiding behind them while healing itself, regaining 6-8 HP, with a 50% chance it will gain 2 musclePower from the workout of collecting zombies.
  */
  public String support(){
    int heal = (int)(Math.random()*3 + 6);
    double chance = Math.random();
    if (getHP() < 15){
      setShield(true); 
    }
    restoreHP(heal);
    if (chance > 0.5){
      restoreSpecial(2);
      return this + "used Shield! They collected fallen zombie parts and made a shield, allowing them to regain " + heal + "HP. They also gained 2 musclePower from the workout.";
    }
  	return this + "used Shield! They collected fallen zombie parts and made a shield, allowing them to regain " + heal + "HP.";
  }
  /* Support others does not exist */
}
