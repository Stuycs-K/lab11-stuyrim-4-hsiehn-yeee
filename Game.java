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
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
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
		//Text.go(30, 80); Text.showCursor();
	/*	try {
    Thread.sleep(2000);
  }
    catch (InterruptedException e) {
  }*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
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
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    Text.go(row, col);
    int place = 0;
	int i = 0;
    String rowText = "";
    for(i = 0; i < height && place < text.length(); i++){
		if(place+width > text.length()){
			rowText = text.substring(place, text.length());
		}
		else{
			rowText = text.substring(place, place+width);
		}
      drawText(rowText, i+row, col);
	  for(int j = rowText.length(); j < width; j++){
		 drawText(" ", i+row, j+col);
	  }
	  place += width;
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
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
      String member = "";
      for(int i = 0; i < party.size(); i++){
        member = "" + party.get(i);
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
    Text.go(10, 2);

  }

  public static String userInput(Scanner in, int playerNum){
    Scanner userInput = new Scanner(System.in);

      //Move cursor to prompt location
      Text.go(10+playerNum, 2);
      String input = userInput.nextLine();

      //show cursor
      Text.showCursor();

      //clear the text that was written
      TextBox(10+playerNum, 2, 77, 1, " ");
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();
   // System.out.println("\033[2J");


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  	enemies.add(createRandomAdventurer("Enemy 1"));
  	enemies.add(createRandomAdventurer("Enemy 2"));
  	enemies.add(createRandomAdventurer("Enemy 3"));
    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    drawBackground();
    String name = "";
    String playerClass = "";
        Scanner in = new Scanner(System.in);
    for(int i = 0; i < 3; i++){
      TextBox(10, 2, 78, 19, "Enter name of player " + i + ": ");
      name = userInput(in, 1);
      TextBox(10, 2, 78, 19, "Enter class of player " + i + "(chomper/sunflowers/zombie):");
      playerClass = userInput(in, 1);
      while(!(playerClass.equals("chomper") || playerClass.equals("sunflowers") || playerClass.equals("zombie"))){
        TextBox(10, 2, 78, 19, "Invalid input. Enter class of player " + i + "(chomper/sunflowers/zombie):");
        playerClass = userInput(in, 1);
      }
      if(playerClass.equals("chomper")){
        party.add(new Chomper(name, 25));
      }
      else if(playerClass.equals("sunflowers")){
        party.add(new Sunflowers(name, 30));
      }
      else{
        party.add(new Zombie(name, 35));
      }
    }
      boolean partyTurn = true;
      int whichPlayer = 0;
      int whichOpponent = 0;
      int turn = 0;
      String input = "";//blank to get into the main loop.

  	int extra = 0;
    String action = "";
    int actLen = 0;
    //Draw the window border
	   //System.out.println(party);
    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.
    // TextBox(2, 2, 78, 28, "abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz_abcdefghijklmnopqrstuvwxyz");

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
	  extra = preprompt.length()/78+1;
	  System.out.print(preprompt);
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in, actLen+extra);
      if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
        quit();
      }else{

	  while((partyTurn) && !(input.startsWith("a ") || input.startsWith("attack ") || input.startsWith("sp ") || input.startsWith("special ") || input.startsWith("su ") || input.startsWith("support "))){
      preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
		  TextBox(10+actLen, 2, 78, preprompt.length()/78+2, "Invalid input. Please retry. " + preprompt);
		  extra = ("Invalid input. Please retry. " + preprompt).length()/78+1;
		  input = userInput(in, actLen+extra);
	  }
	  while(partyTurn && ((input.startsWith("a")) && (Integer.parseInt(""+input.charAt(2)) >= enemies.size()) || (input.startsWith("sp")) && (Integer.parseInt(""+input.charAt(3)) >= enemies.size()) || (input.startsWith("su")) && (Integer.parseInt(""+input.charAt(3)) >= party.size()))){
		preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q) ";
		  TextBox(10+actLen, 2, 78, preprompt.length()/78+2, "Invalid input. Please retry. " + preprompt);
		  extra = ("Invalid input. Please retry. " + preprompt).length()/78+1;
		  input = userInput(in, actLen+extra);
	  }
      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        //Process user input for the last Adventurer:

        // ATTACK
        if(input.startsWith("a ") || input.startsWith("attack ") ){

          if(input.indexOf("a ") == -1){
            whichOpponent = Integer.parseInt(""+input.charAt(7));
          }
          else{
            whichOpponent = Integer.parseInt(""+input.charAt(2));
          }
          action = party.get(whichPlayer).attack(enemies.get(whichOpponent));
          TextBox(10+actLen, 2, 78, action.length()/78+1, action);
		      actLen += action.length()/78+1;
        }

        // SPECIAL
        else if(input.startsWith("sp ") || input.startsWith("special ")){
          if(input.indexOf("sp ") == -1){
            whichOpponent = Integer.parseInt(""+input.charAt(8));
          }
          else{
            whichOpponent = Integer.parseInt(""+input.charAt(3));
          }
          action = party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
          TextBox(10+actLen, 2, 78, action.length()/78+1, action);
		      actLen += action.length()/78+1;
        }

        // SUPPORTS
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          if(input.indexOf("su ") == -1){
            whichOpponent = Integer.parseInt(""+input.charAt(8));
          }
          else{
            whichOpponent = Integer.parseInt(""+input.charAt(3));
          }
          if(whichOpponent == whichPlayer){
            action = party.get(whichPlayer).support();
            TextBox(10+actLen, 2, 78, action.length()/78+1, action);
			    actLen += action.length()/78+1;
          }
          else{
            action = party.get(whichPlayer).support(party.get(whichOpponent));
            TextBox(10+actLen, 2, 78, action.length()/78+1, action);
			actLen += action.length()/78+1;
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
		  TextBox(10+actLen, 2, 78, prompt.length()/78+1, prompt);
		  extra = 1;


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
        //  drawText("went into enter monster", 31, 1);
          String prompt = "press enter to see monster's turn";
          partyTurn = false;
          whichOpponent = 0;
		  TextBox(10+actLen, 2, 78, prompt.length()/78+1, prompt);
          actLen = 0;
         // TextBox(10, 2, 79, 19, prompt);
          extra = 1;
        }
        //done with one party member
      }else{
        //not the party turn!
		if(whichOpponent == 0){
			TextBox(10, 2, 79, 19, " ");
		}

        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        int randoAdven = (int)(Math.random()*3);
        while(party.get(randoAdven).getHP() == 0){
          randoAdven = (int)(Math.random()*3);
        }
        if((int)(Math.random()*3) == 0){
			action = enemies.get(whichOpponent).attack(party.get(randoAdven));
          TextBox(10+actLen, 2, 78, action.length()/78+1, action);
		  actLen += action.length()/78+1;
        }
        else if((int)(Math.random()*3) == 0){
			action = enemies.get(whichOpponent).specialAttack(party.get(randoAdven));
          TextBox(10+actLen, 2, 78, action.length()/78+1, action);
		  actLen += action.length()/78+1;
        }
        else{
          while(randoAdven == whichOpponent && enemies.get(whichOpponent) instanceof Chomper){
            randoAdven = (int)(Math.random()*3);
          }
		  if(randoAdven == whichOpponent){
			  action = enemies.get(whichOpponent).support();
			  TextBox(10+actLen, 2, 78, action.length()/78+1, action);
			  actLen += action.length()/78+1;
		  }
		  else{
			  action = enemies.get(whichOpponent).support(enemies.get(randoAdven));
			  TextBox(10+actLen, 2, 78, action.length()/78+1, action);
			  actLen += action.length()/78+1;
		  }
        }

        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        TextBox(10+actLen, 2, 78, 2, prompt);
        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.

		String prompt = "press enter to continue to your turn";
		TextBox(10+actLen, 2, 65, 1, prompt);
		input = userInput(in, 0);
        whichPlayer = 0;
		actLen = 0;
        turn++;
        partyTurn=true;
    //    drawText("went into enter command", 31, 1);
        //display this prompt before player's turn
		drawScreen(party, enemies);
        prompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/special(sp)/support(su)/quit(q)";
        TextBox(10, 2, 78, 19, prompt);

      }
	  else{//display the updated screen after input has been processed.
		  drawScreen(party, enemies);
	  }
  }






    }//end of main game loop


    //After quit reset things:
    quit();
  }

  // Add chomper rule that PH would decrease instead of increase
  // Add death, because after they die they're still prompted for an action
  // Add quit
  /// add variability in the enemy party
}
