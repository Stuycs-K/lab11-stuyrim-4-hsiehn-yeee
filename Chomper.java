public class Chomper extends Adventurer{
  int pH, pHmax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Chomper(String name, int hp){
    super(name,hp);
    pHMax = 14;
    pH = pHMax/2;
  }


  public (String name){
    this(name,25);
  }

  public Chomper(){
    this("Chompie");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "pH";
  }

  public int getSpecial(){
    return pH;
  }

  public void setSpecial(int n){
    pH = n;
  }

  public int getSpecialMax(){
    return pHMax;
  }

  /*Deal 5-7 damage to opponent, restores 2 pH*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+5;
    other.applyDamage(damage);
    if(pH > 2){
		setSpecial(pH - 2);
	}
	else{
		setSpecial(0);
	}
    return this + " used Fly Trap! They snatched "+ other + " in their jaws and dealt "+ damage +
    " points of damage. They then absorbed the opponent's life energy to lower their pH by 2";
  }

  /*Deal 14-pH damage to opponent, only if pH is low enough (<7).
  *Increase pH by 3.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial()  < 7){
		int damage = 14-pH;
		restoreSpecial(3);
		other.applyDamage(damage);
		return this + " used Withering! They spit out acid around it. This melted "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Oh no! "+this + " is too basic to spit out potent acid! Nothing happened..";
    }

  }
  /*Restores 1 special or 1 HP to other, depending on which one is lower*/
  public String support(Adventurer other){
	if(other.getSpecial() > other.getHP()){
		other.applyDamage(-1);
		return this + "used Blooming! They spit out some helpful acid for "+other+", helping them reinvigorate themselves and regain 1 HP.";
	}
	else{
		return this + "used Blooming! They spit out some helpful acid for "+other+", helping them reinvigorate themselves and regain " + other.restoreSpecial(1)+" "+other.getSpecialName();
	}
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
