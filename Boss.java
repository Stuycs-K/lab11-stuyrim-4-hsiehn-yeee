public class Boss extends Adventurer{
  int musclePower, musclePowerMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp){
    super(name,hp);
    musclePowerMax = 10;
    musclePower = musclePowerMax/2;
  }

  public Boss(String name){
    this(name,35);
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
    int damage = (int)(Math.random()*3)+6;
    other.applyDamage(damage);
    other.setSpecial(other.getSpecial() - 1); 
    setSpecial(getSpecial() + 4);
    return this + " used Clobber! Zomboss clubbed "+ other + " and deals " + damage + " to them, paralyzing them and making them lose 1 itself." + other.getSpecialName() + ", while also gaining 4 musclePower";
  }

  /*Decrease opponent's special by 25%, also dropping their own by 10%. No threshold of special required
  */
  public String specialAttack(Adventurer other){
    if (getSpecial() > 25){
      int damage = (int)(Math.random()*3 + 8);
      other.applyDamage(damage * 2); 
      setSpecial(getSpecial()-damage);
      int ownDamage = (int)(Math.random()*5 + 1);
      applyDamage(ownDamage);
    }
    return this + " used Apocalypes! They commanded a horde of " + damage + " zombies, which dealt " + (damage * 2) + ", damage to " + other + ". " + this + " was also hurt in the process, and lost " + ownDamage + " HP. Lost " + damage + " musclePower. ";
  }
  /*Shields the allys from the next atack and take two damage NOTE: A shield attribute must be applied to all other adventurers.*/
  public String support(Adventurer other){
    applyDamage(2);
    setShield(true);
  	return this + "used CS Test! They gave two Zomboss to be eaten for the strengthening of their bones!, sheilding " + other + " from the next attack!";
  }
  /*Regains 5-7HP while also gaining 2 special*/
  public String support(){
  	int heal = (int)(Math.random()*3) + 5 ;
    restoreHP(heal);
  	restoreSpecial(2); 
    return this + " used Scavenge! Zomboss seeds were sowed, and " + heal + " new sunflowers have sprouted, gaining " + heal + " HP, and raining musclePower by 2!";
  }
}
