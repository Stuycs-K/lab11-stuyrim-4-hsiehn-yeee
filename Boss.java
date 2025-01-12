public class Zombies extends Adventurer{
  int brains, brainsMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Zombies(String name, int hp){
    super(name,hp);
    brainsMax = 10;
    brains = brainsMax/2;
  }


  public Zombies(String name){
    this(name,35);
  }

  public Zombies(){
    this("Zombies");
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
    int damage = (int)(Math.random()*3)+1;
    other.applyDamage(damage);
    this.applyDamage(damage);
    return this + " used Devour! Zombies launch themselves at "+ other + " and deal " + damage + " to them, while also losing " + damage + " itself.";
  }

  /*Decrease opponent's special by 25%, also dropping their own by 10%. No threshold of special required
  */
  public String specialAttack(Adventurer other){
    int dropOtherSpecial = (int) 0.25*other.getSpecial();
    int dropOwnSpecial = (int) 0.1*this.getSpecial();
    other.setSpecial(other.getSpecial() - dropOtherSpecial);
    this.setSpecial(this.getSpecial() - dropOwnSpecial);
    return this + " used Horde! They emanated a bright light, temporarily blinding and burning its opponent, dropping their " + other.getSpecialName() + " by " + dropOtherSpecial + ", while also losing " + dropOwnSpecial + " itself.";
  }
  /*Shields the allys from the next atack and take two damage NOTE: A shield attribute must be applied to all other adventurers.*/
  public String support(Adventurer other){
    applyDamage(2);
    setShield(true);
  	return this + "used CS Test! They gave two Zombies to be eaten for the strengthening of their bones!, sheilding " + other + " from the next attack!";
  }
  /*Regains 5-7HP while also gaining 2 special*/
  public String support(){
  	int heal = (int)(Math.random()*3) + 5 ;
    restoreHP(heal);
  	restoreSpecial(2); 
    return this + " used Scavenge! Zombie seeds were sowed, and " + heal + " new sunflowers have sprouted, gaining " + heal + " HP, and raining brains by 2!";
  }
}
