import java.util.*;
import java.util.Scanner;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
    //System.out.println(createRandomAdventurer("Bobbie"));
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.go(1, 1);
		for(int i = 1; i <= WIDTH; i++){
			System.out.print(Text.colorize(" ", 47));
		}
		//Text.go(1, 80); Text.showCursor();
		for(int i = 2; i < HEIGHT; i++){
			Text.go(i, 1);
			System.out.print(Text.colorize(" ", 47));
      Text.reset();
			Text.go(i, WIDTH);
      Text.reset();
			System.out.print(Text.colorize(" ", 47));
		}
		Text.go(HEIGHT, 1);
		for(int i = 1; i <= WIDTH; i++){
			System.out.print(Text.colorize(" ", 47));
		}
    Text.go(10, 2); 
    for (int i = 2; i < WIDTH; i ++){
      System.out.print(Text.colorize("-", Text.WHITE));
    }
		
    //Text.go(30, 80); Text.showCursor();

    /*try {
      Thread.sleep(2000);
    }
      catch (InterruptedException e) {
    }*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    Text.go(row, col);
    int place = 0;
	  int i = 0;
    String rowText = "";

    for(i = 0; i < height && place < text.length(); i++){
      if(place + width >= text.length()){
        rowText = text.substring(place, text.length());
        place = text.length(); 
      }
      else{
        rowText = text.substring(place, place+width);

        int wordEnd = rowText.lastIndexOf(' '); 
        if (wordEnd != -1){
          rowText = rowText.substring(0, wordEnd); 
          place += wordEnd + 1; 
        } else {
          place += width; 
        }
      }

      drawText(rowText, i+row, col);
      for(int j = rowText.length(); j < width; j++){
      drawText(" ", i+row, j+col);
      }
    // System.out.print(i);
    }
    for(; i < height; i++){
      for(int j = 0; j < width; j++){
        drawText(" ", i+row, j+col);
      }
    }
  }


    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
  public static Adventurer createRandomAdventurer(String name){
    int rando = (int)(Math.random()*100);
    if (rando < 33){
      return new Chomper(name);
    }else if (rando < 66){
      return new Sunflowers(name);
    }else{
      return new Zombie(name);
    }
  }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
  public static void drawParty(ArrayList<Adventurer> party,int startRow){
    String member = "";
    String death = "☠️"; // right now the emoji isn't working
    for(int i = 0; i < party.size(); i ++){
      if (party.get(i).isDead()){
        member = party.get(i) + " (DEAD)"; 
      }
      else{
        member = "" + party.get(i);

      }
      TextBox(startRow, 2+i*(78/party.size()), 78/party.size(), 1, member);
      member = "HP: " + colorByPercent(party.get(i).getHP(), party.get(i).getmaxHP());
      TextBox(startRow+1, 2+i*(78/party.size()), 78/party.size(), 1, member);
      member = party.get(i).getSpecialName() + ": " + party.get(i).getSpecial();
      TextBox(startRow+2, 2+i*(78/party.size()), 78/party.size(), 1, member);
      // System.out.print("done");
    }
  }

  //Use this to create a colorizeized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    if (hp < 0.25*maxHP){
      output = Text.colorize(output, Text.RED);
    }
    else if (hp < 0.75*maxHP){
      output = Text.colorize(output, Text.YELLOW);
    }else{
      output = Text.colorize(output, Text.GREEN);
    }
    return output;
  }

  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){
    drawBackground();
    //draw player party
    drawParty(party, 2);
    //draw enemy party
	  drawParty(enemies, 6);

    //place the curser where use will type input
    Text.go(11, 2);

  }

  public static String userInput(Scanner in, int playerNum){
    Scanner userInput = new Scanner(System.in);

    //Move cursor to prompt location
    Text.go(11+playerNum, 2);
    String input = userInput.nextLine();

    //show cursor
    Text.showCursor();

    //clear the text that was written
    TextBox(11+playerNum, 2, 77, 1, " ");
    return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  //////////////////////// THE GAME ///////////////////////////

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();
    // System.out.println("\033[2J");
	  boolean hasQuitten = false;
    drawBackground();

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    
    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.

    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();

    TextBox(11, 2, 78, 17, "Choose your difficulty level! (Hard(1)/Easy(2)/Medium(3)/Quit(q):");
    Scanner in = new Scanner(System.in);
    String choice = userInput(in, 1);
	  
    if(choice.equalsIgnoreCase("q")){
		  hasQuitten = true;
		  quit();
	  } 
    else{
		  while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("quit"))){
      TextBox(11, 2, 78, 17, "Invalid input. Please retry. Choose your difficulty level! (Hard(1)/Easy(2)/Medium(3)/Quit(q)):");
      choice = userInput(in, 2);
      }
      if (choice.equals("1")){
        enemies.add(new Boss("Voldemort", 50)); 
      }
      else if (choice.equals("2")){
        enemies.add(createRandomAdventurer("Evil Jessie")); 
        enemies.add(createRandomAdventurer("Evil James")); 
      }
      else if (choice.equals("3")){
        enemies.add(createRandomAdventurer("Evil Blossom"));
        enemies.add(createRandomAdventurer("Evil Buttercup"));
        enemies.add(createRandomAdventurer("Evil Bubbles"));
      }
      else{
        quit();
        hasQuitten = true;
      }
	  }

    // CHOOSING ADVENTURER PARTY 
	  ArrayList<Adventurer> party = new ArrayList<>();
	  if(!hasQuitten){
		
      // Default Party
      TextBox(11, 2, 78, 17, "Choose your party! Would you like to use the default party (a), customize your party (b) or quit (q)?");  
      in = new Scanner(System.in);
      choice = userInput(in, 2); 
      if(choice.equalsIgnoreCase("q")){
        hasQuitten = true;
        quit();
      }
      else{
        while(!(choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("quit"))){
        TextBox(11, 2, 78, 17, "Invalid input. Please retry. Choose your party! Would you like to use the default party (a), customize your party (b), or quit (q)?");
        choice = userInput(in, 2);
        }
        if (choice.equalsIgnoreCase("a")){
          party.add(new Chomper("Piranha Plant", 25)); 
          party.add(new Sunflowers("Bullet Bill", 30)); 
          party.add(new Zombie("Koopa Paratroopa", 35));
          TextBox(11, 2, 78, 20, " "); 
        }else if (choice.startsWith("b")){
          String name = "";
          String playerClass = "";
        
          // Costumize Party 
          for(int i = 0; i < 3; i++){
            TextBox(11, 2, 78, 17, "Enter name of player " + i + ": ");
            name = userInput(in, 1);
            TextBox(11, 2, 78, 17, "Enter class of player " + i + "(chomper/sunflowers/zombie):");
            playerClass = userInput(in, 1);
            while(!(playerClass.equals("chomper") || playerClass.equals("sunflowers") || playerClass.equals("zombie"))){
              TextBox(11, 2, 78, 17, "Invalid input. Enter class of player " + i + "(chomper/sunflowers/zombie):");
              playerClass = userInput(in, 1);
            }
            if(playerClass.equalsIgnoreCase("chomper")){
              party.add(new Chomper(name, 25));
            }
            else if(playerClass.equalsIgnoreCase("sunflowers")){
              party.add(new Sunflowers(name, 30));
            }
            else{
              party.add(new Zombie(name, 35));
            }
          }
		    }
		    else{
			    hasQuitten = true;
				quit(); 
		    }
	    }
	  }
    
	
    if(!hasQuitten){
      String input = "";//blank to get into the main loop.'
      
      int extra = 0; 
      String action = ""; 
      int actLen = 0; 
      boolean gameOver = false; 

      boolean partyTurn = true;
      int whichPlayer = 0;
      int whichOpponent = 0;
      int turn = 0;

      //Draw the window border
      //System.out.println(party);
      //You can add parameters to draw screen!

      drawScreen(party, enemies);//initial state.

      // TextBox(2, 2, 78, 28, "abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz");

      //Main loop

      //display this prompt at the start of the game.
      String preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su) target # or quit(q)";
      extra = preprompt.length()/78+1;
      TextBox(11, 2, 78, 2, preprompt);
      while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) && !gameOver){
        
        int enemyHealth = 0; 
        int partyHealth = 0; 

        // Calculate health for both parties 
        for (Adventurer player : party){
          partyHealth += player.getHP(); 
        }
        for (Adventurer enemy : enemies){
          enemyHealth += enemy.getHP(); 
        }

        if (partyHealth == 0 || enemyHealth == 0){

          if (enemyHealth == 0 && partyHealth > 0){
            TextBox(11, 2, 78, 17, "YOU HAVE WON! Congratulations! You successfully defeated all the enemies!! Would you like to play again? (Y/N)");
          }
          else if (partyHealth == 0 && enemyHealth > 0){
            TextBox(11, 2, 78, 17, "Oh No. You have been defeated by the enemy. You fought bravely. Would you like to play again? (Y/N)");
          }

          input = userInput(in, actLen+extra+1);
          if (input.equalsIgnoreCase("y")){
            run(); 
          }
          else {
            gameOver = true; 
          }
        }
        else{


          //Read user input
          input = userInput(in, actLen+extra);

          if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
            quit();
          }
          else{
            

            // ** TO DO: IMPLEMENT AN ANTI-CRASHING SYSTEM, b/c right now system crashes when you enter "a 0 " with a space at the end!
            while((partyTurn) && !(input.startsWith("a ") || input.startsWith("attack ") || input.startsWith("sp ") || input.startsWith("special ") || input.startsWith("su ") || input.startsWith("support ") || input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
              preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
              TextBox(11+actLen, 2, 78, preprompt.length()/78+2, "Invalid input. Please retry. " + preprompt);
              extra = ("Invalid input. Please retry. " + preprompt).length()/78+1;
              input = userInput(in, actLen+extra);
            }
            while(partyTurn && (input.charAt(input.length()-1) >= 48 && input.charAt(input.length()-1) <= 57) && Integer.parseInt(""+input.charAt(input.length()-1)) >= party.size() || (input.startsWith("a") || input.startsWith("sp")) && (input.startsWith("su") && (input.charAt(input.length()-1) >= 48 && input.charAt(input.length()-1) <= 57) && (Integer.parseInt(""+input.charAt(input.length()-1)) >= enemies.size()) || (input.charAt(input.length()-2) != ' ') || input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
            preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
              TextBox(11+actLen, 2, 78, preprompt.length()/78+2, "Invalid input. Please retry. " + preprompt);
              extra = ("Invalid input. Please retry. " + preprompt).length()/78+1;
              input = userInput(in, actLen+extra);
            }
            //example debug statment
            //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );
			if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
				quit();
				hasQuitten = true;
			}
    
    // PARTY'S TURN 
            //display event based on last turn's input
            if(partyTurn && !hasQuitten){
              // Add quit when prompted to enter adventurer again? 

              if (party.get(whichPlayer).isDead()){
                TextBox(11+actLen, 2, 78, 2, party.get(whichPlayer) + " HAS FALLEN! They are no longer able to perform any actions.");
              }
              else {
                //Process user input for the last Adventurer:

                // ATTACK
                if(input.startsWith("a ") || input.startsWith("attack ") ){

                  whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));

                  while (enemies.get(whichOpponent).isDead()){
                    TextBox(11+actLen, 2, 78, 2, "Target has already fallen! Please choose another target by entering another target:");
                    input = userInput(in, actLen + 2);
                    whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));
                  }
                    action = party.get(whichPlayer).attack(enemies.get(whichOpponent));
                    TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                    actLen += action.length()/78+2;
                }

                // SPECIAL
                else if(input.startsWith("sp ") || input.startsWith("special ")){

                  whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));

                  while (enemies.get(whichOpponent).isDead()){
                    TextBox(11+actLen, 2, 78, 2, "Target has already fallen! Please choose another target by entering another target:");
                    input = userInput(in, actLen + 2);
                    whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));
                  }
                  action = party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
                  TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                  actLen += action.length()/78+2;
                }

                // SUPPORTS
                else if(input.startsWith("su ") || input.startsWith("support ")){
                  //"support 0" or "su 0" or "su 2" etc.
                  //assume the value that follows su  is an integer.
                  whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));

                  if(whichOpponent == whichPlayer){
                    action = party.get(whichPlayer).support();
                    TextBox(11+actLen, 2, 78, action.length()/78+1, action);
                    actLen += action.length()/78+2;
                  }
                  else{
                    while (party.get(whichOpponent).isDead()){
                      TextBox(11+actLen, 2, 78, 2, "Target has already fallen! Please choose another target by entering another target: ");
                      input = userInput(in, actLen + 2);
                      whichOpponent = Integer.parseInt(""+input.charAt(input.length()-1));
                    }
                    action = party.get(whichPlayer).support(party.get(whichOpponent));
                    TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                    actLen += action.length()/78+2;    
                  }
                }
              }

              //You should decide when you want to re-ask for user input
              //If no errors:
              whichPlayer++;

              if(whichPlayer < party.size()){
                //This is a player turn.
                //Decide where to draw the following prompt:
                String prompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
                Text.showCursor();
                TextBox(11+actLen, 2, 78, prompt.length()/78+1, prompt);
                extra = 1;

    // ENEMIES TURN 
              }else{
                //This is after the player's turn, and allows the user to see the enemy turn
                //Decide where to draw the following prompt:
                //drawText("went into enter monster", 31, 1);
                String prompt = "press enter to see monster's turn";
                partyTurn = false;
                whichOpponent = 0;
                TextBox(11+actLen, 2, 78, prompt.length()/78+1, prompt);
                actLen = 0;
              // TextBox(11, 2, 79, 19, prompt);
                extra = 1;
              }
              //done with one party member
            
            }else if(!hasQuitten){
              //not the party turn!
              while (whichOpponent < enemies.size()){
                if(whichOpponent == 0){
                  TextBox(11, 2, 78, 19, " ");
                }

                //enemy attacks a randomly chosen person with a randomly chosen attack.z`
                //Enemy action choices go here!
                if (enemies.get(whichOpponent).isDead()){
                  TextBox(11+actLen, 2, 78, 2, enemies.get(whichOpponent) + " HAS FALLEN! They are no longer able to perform any actions.");
                }
                else{
                  int randoAdven = (int)(Math.random()*party.size());
                  while(party.get(randoAdven).getHP() == 0){
                    randoAdven = (int)(Math.random()*party.size());
                  }
                  if((int)(Math.random()*3) == 0){
                    action = enemies.get(whichOpponent).attack(party.get(randoAdven));
                    TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                    actLen += action.length()/78+1;
                  }
                  else if((int)(Math.random()*3) == 0){
                    action = enemies.get(whichOpponent).specialAttack(party.get(randoAdven));
                    TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                    actLen += action.length()/78+1;
                  }
                  else{
                    while(randoAdven >= enemies.size() || enemies.get(randoAdven).getHP()== 0 || randoAdven == whichOpponent && enemies.get(whichOpponent) instanceof Chomper){
                      randoAdven = (int)(Math.random()*enemies.size());
                    }
                    if(randoAdven == whichOpponent || enemies.size() == 1){
                      action = enemies.get(whichOpponent).support();
                      TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                      actLen += action.length()/78+1;
                    }
                    else{
                      if(randoAdven == whichOpponent || enemies.size() == 1){
                        action = enemies.get(whichOpponent).support();
                        TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                        actLen += action.length()/78+1;
                      }
                      else{
                        if(randoAdven >= enemies.size()){
                          randoAdven = (int)(Math.random()*enemies.size());
                        }
                        action = enemies.get(whichOpponent).support(enemies.get(randoAdven));
                        TextBox(11+actLen, 2, 78, action.length()/78+2, action);
                        actLen += action.length()/78+1;
                      }
                    }
                  }
                }
              
              //Decide where to draw the following prompt:
			  if(whichOpponent < enemies.size()-1){
				  
              String prompt = "press enter to see next turn";
              TextBox(11+actLen, 2, 78, 2, prompt);
			  input = userInput(in, 0);
			  }
			  whichOpponent++;
              } 
            }//end of one enemy.

            //modify this if statement.
            if(!partyTurn && whichOpponent >= enemies.size() && !hasQuitten){
            //THIS BLOCK IS TO END THE ENEMY TURN
            //It only triggers after the last enemy goes.

              String prompt = "press enter to continue to your turn";
              TextBox(11+actLen, 2, 65, 1, prompt);
              input = userInput(in, 0);
              whichPlayer = 0;
              actLen = 0;
              turn++;
              partyTurn=true;
              //drawText("went into enter command", 31, 1);
              //display this prompt before player's turn
              drawScreen(party, enemies);
              prompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q)";
              TextBox(11, 2, 78, 19, prompt);

            }
            else{//display the updated screen after input has been processed.
              drawScreen(party, enemies);
            }
          }
        }
      }
      if (gameOver){
          quit(); 
      }
    }
    
  //end of main game loop

  //After quit reset things:
    else {
      quit();
    }

  } // END OF RUN 

  /* TO-DO LIST 
  Add chomper rule that PH would decrease instead of increase; DONE 
  Add death, because after they die they're still prompted for an action; DONE
  Add quit; DONE 
  add variability in the enemy party; DONE 
  change the drawScreen 
  Add default group option; DONE 
  add party size option for enemy and boss; DONE 
  add win/lose screen; DONE 
  add add enemy prefer an action 
  ADD Text for shield behavior; DONE 
  Fix the screen when enemy 2 has fallen. 
  Right now all the enemies die too fast - adjust settings of the adventurers 
  */
  /* NOTES
    - The quit function doesn't work when user inputs invalid text; DONE 
    - sometimes the invalid text makes the program crash, specifically when you enter: 
      "a 1 ", "a ", "a -"
  */ 
}

