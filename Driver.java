// Copy pasted from Adventurer Classwork 
import java.util.Random;
import java.util.Scanner;
public class Driver {
  public static void main(String[] args) {
    Adventurer player = new sunflowers("player");
    Adventurer enemy = new sunflowers("Enemy");

    String playerHP =  player.getHP() + "/" + player.getmaxHP();
    String playerSpecial = player.getSpecial() + "/" + player.getSpecialMax();
    String enemyHP =  enemy.getHP() + "/" + enemy.getmaxHP();
    String enemySpecial = enemy.getSpecial() + "/" + enemy.getSpecialMax();

    //do this once
    Scanner userInput = new Scanner(System.in);

    //You can do the rest many times:
    boolean quit = false; 
    while (quit == false && player.getHP()>0 && enemy.getHP()>0){
      System.out.println("Your stats: " + " Health = " + player.getHP() + "/" + player.getmaxHP() + ", Special = " + player.getSpecial() + "/" + player.getSpecialMax());
      System.out.println("Enemy stats: Health =" + enemy.getHP() + "/" + enemy.getmaxHP() +", Special = " + enemy.getSpecial() + "/" + enemy.getSpecialMax());
      
      System.out.println("What do you want to do? (a)ttack / (sp)ecial / (su)pport / quit");
      String winner = ""; 
      String a = userInput.nextLine();

      if (a.equals("quit")){
        quit = true;
      }
        else{
        if (a.equals("attack") || a.equals("a")){
          System.out.println(player.attack(enemy)); 
        }
        if (a.equals("special") || a.equals("sp")){
          System.out.println(player.specialAttack(enemy)); 
        }
        if (a.equals("support") || a.equals("su")){
          System.out.println(player.support());
        }

        // Enemy attacks
        if (enemy.getHP() > 0){
          double rand = Math.random();
          if (rand > 0.5 && rand < 0.75){
            System.out.println(enemy.support());
          }
          else if(rand > 0.75){
            System.out.println(enemy.specialAttack(player));
          }
          else{
            System.out.println(enemy.attack(player));
          }
        }
      }
    }
      if (player.getHP() <= 0){
        System.out.println(player + " defeated the mighty " + enemy + "!");
      }
      else if (enemy.getHP() <= 0){
        System.out.println("oh no... you failed to defeat " + enemy);
      }
  }
}


















    }
}