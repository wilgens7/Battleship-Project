
//Imports
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Driver Class
public class BattleShipDriver{

	//Variable Declarations
	private static Scanner userInput;
	private static int guessedRow;
	private static int guessedColumn;

	private static int tries;
	private static Location guessedLocation;
		
	private static ArrayList<Location> battleships;
	private static GameBoard gameBoard;

	private static Random battleshipLocationGenerator;
	private static Location locationOfBattleship1;
	private static Location locationOfBattleship2;
	private static Location locationOfBattleship3;





	
	//=============================================================MAIN METHOD==================================================================
	public static void main(String[] args){

		//just to add some space at top of game
		System.out.print("\n\n");


		


		//=====================Creation Of Battleships==================================================================================================

		battleships = new ArrayList<>();
		battleshipLocationGenerator = new Random(System.currentTimeMillis());

		//Creation Of Battleships
		locationOfBattleship1 = new Location(battleshipLocationGenerator.nextInt(5),battleshipLocationGenerator.nextInt(5));
	    locationOfBattleship2 = new Location(battleshipLocationGenerator.nextInt(5),battleshipLocationGenerator.nextInt(5));
	    locationOfBattleship3 = new Location(battleshipLocationGenerator.nextInt(5),battleshipLocationGenerator.nextInt(5));

		//Appending Battleships To List
		battleships.add(locationOfBattleship1);
		battleships.add(locationOfBattleship2);
		battleships.add(locationOfBattleship3);
		

		//==============For testing values WILL REMOVE===========================
		System.out.println("**THESE ARE LOCATIONS OF THE BATTLESHIPS PRINTED FOR TESTING PURPOSES.");
			for(Location location: battleships){

			System.out.println(location.getRow()+", "+location.getColumn());

			}
			System.out.println("Number of Ships: "+battleships.size());
        //=====================================================================

		//This location will be compared to the locations of the battleships via equals method.
		//The row and column will be set to the Users guesses inside of the the startGame method.
		guessedLocation = new Location(0,0);

		//creating gameBoard================================================================================================================
		
		gameBoard= new GameBoard(5,5);
		
		//========================================================================================================================================
		//String userAnswer = "";
		

        
	
	    //========================================GAME LOOP===========================================================
	   

			
	    	//set to promt user again after catch block.
			boolean fromCatch = false;
	    	startGame();
	    	
	    	
	    	
	    	while(!(tries<=0 || battleships.isEmpty())){
	    		//System.out.print("\n INSIDE OF INNER WHILE LOOP \n");

	    	try{

	    		//System.out.print("\n INSIDE TRY STATEMENT\n");

	    		
					//if player wins
					if(battleships.isEmpty()){

						playerWins();
						break;
					 }
					//if player loses
					else if(tries==0 && !battleships.isEmpty()){

					 	playerLoses();
					 	break;
					 }
					 //if catch stament just ran
					else if(fromCatch){
			    		System.out.println("Guess a row.");
						guessedRow = userInput.nextInt();

						System.out.println("Guess a column.");
						guessedColumn = userInput.nextInt();

						guessedLocation.setRow(guessedRow);
						guessedLocation.setColumn(guessedColumn);
						fromCatch = false;
			     }
					//if battleship is hit 
					else if(guessedLocation.equals(locationOfBattleship1)){

						battleshipDestroyed(locationOfBattleship1);
					}
					else if(guessedLocation.equals(locationOfBattleship2)){

						battleshipDestroyed(locationOfBattleship2);
					}
					else if(guessedLocation.equals(locationOfBattleship3)){

						battleshipDestroyed(locationOfBattleship3);
					}
					//if battleship is missed
					else if(!guessedLocation.equals(locationOfBattleship1) &&
							!guessedLocation.equals(locationOfBattleship2) &&
							!guessedLocation.equals(locationOfBattleship3)){

						battleshipMissed();
					}
					

				}
				catch(Exception e){

					tries--;
					if(tries==0){

						playerLoses();
						break;
					}
					else{
	    			//System.out.println("CATCH BLOCK IN GAME LOOP\n");
	    			System.out.println("Make sure that you only enter integers as inputs.\n");
	    			
	    			userInput.nextLine();

	    			System.out.println("You have "+tries+" tries.\n");
					System.out.println("There are "+battleships.size()+"  battleships.\n");

					fromCatch = true;
	    			continue;
	    		}
	    	
	         }
	         }
	   		 

	    	
         //System.out.print("\n PRESS 'Q' TO END PROGRAM. PRESS ANY OTHER KEY TO CONTINUE.\n");


	     //===========================================END OF GAME LOOP================================================


     }//===============================================END OF MAIN METHOD=============================================


	//===============================================HELPER METHODS===================================================

	public static void startGame(){

		

		//initializing game board
		gameBoard.initializeGameBoard('O');
		gameBoard.printGameBoard();

		//just to add some space
		System.out.print("\n\n");

		System.out.print("\n GAME STARTED\n");

		//Scanner Object For User Input
		userInput = new Scanner(System.in);
	    guessedRow=gameBoard.gameBoardArray.length;
     	guessedColumn=gameBoard.gameBoardArray.length;


		tries = 4;
	    System.out.println("You have "+tries+" tries.\n");
		System.out.println("There are "+battleships.size()+"  battleships.\n");

		//set to loop cause user prompt to loop until input is valid
		boolean loop = true;

		while(loop){

	    		try{
	    			System.out.println("Guess a row.");
					guessedRow = userInput.nextInt();

					System.out.println("Guess a column.");
					guessedColumn = userInput.nextInt();

					guessedLocation.setRow(guessedRow);
					guessedLocation.setColumn(guessedColumn);

					loop = false;
				}
				catch(Exception E){

					loop=true;
					System.out.println("Please pick a valid input to start game.");
					userInput.nextLine();
				}
	    	
				}

		

	 }

	public static void battleshipDestroyed(Location locationOfBattleship){

		//just to add some space
		System.out.print("\n\n");

		//System.out.print("\n BATTLESHIP DESTROYED METHOD RUNNING");

		//removes the battleship only if battleship is empty 
		if(!battleships.isEmpty()){
			//System.out.print("\n REMOVING BATTLESHIP.\n");
			battleships.remove(locationOfBattleship);
		}     
		if(battleships.isEmpty()){
			//System.out.print("\n BATTLESHIP IS EMPTY AFTER DESTROYED PLAYER WINS METHOD WILL RUN.");
		    	playerWins();
		 }

		 else if(tries==0){
		 	playerLoses();
		 }

				//if battleship is still not empty
		else if(!(battleships.isEmpty())){
			//System.out.print("\n SECOND BATTLESHIP NOT EMPTY CONDITION RUNNING .\n");

			System.out.println("One down\n");
			
			gameBoard.updateGameBoard(locationOfBattleship.getRow(), locationOfBattleship.getColumn(), '*');

			//==================decrement tries by 1 and setup next pass through loop.
			tries--;
			System.out.println("You have "+tries+" tries remaining.\n");
			System.out.println("There are "+battleships.size()+"  battleships left.\n");
			System.out.println("Take another guess.\n\n");

			gameBoard.printGameBoard();

			//Restoring User Inputs
			System.out.println("\nGuess a row.");
			guessedRow = userInput.nextInt();

			System.out.println("\nGuess a column.");
			guessedColumn = userInput.nextInt();

			guessedLocation.setRow(guessedRow);
			guessedLocation.setColumn(guessedColumn);
		 }
		
			
	 }
	
	public static void battleshipMissed(){

		tries--;
		//just to add some space
		System.out.print("\n\n");

		//System.out.print("\n BATTLESHIP MISSED METHOD RUNNING");

		//if player loses
		if(tries<=0){
			//System.out.print("\n NO TRIES LEFT PLAYERLOSES METHOD WILL RUN");
			playerLoses();
		}

		else{
			//System.out.print("\n MORE TRIES LEFT. LOOP WILL CONTINUE");
		gameBoard.updateGameBoard(guessedRow, guessedColumn, 'X');
		System.out.println("HA HA! Guess Again!.\n");
		


		//==================setup next pass through loop.===========================
		
		System.out.println("You have "+tries+" tries remaining.\n");
		System.out.println("There are "+battleships.size()+"  battleships left.\n");
		System.out.println("Take another guess.\n\n");

			

		gameBoard.printGameBoard();

		
		//Restoring User Inputs
		System.out.println("\nGuess a row.");
		guessedRow = userInput.nextInt();

		System.out.println("\nGuess a column.");
		guessedColumn = userInput.nextInt();

		guessedLocation.setRow(guessedRow);
		guessedLocation.setColumn(guessedColumn);

	     }

	 }

	public static void playerWins(){

		//System.out.print("\n PLAYERWINS METHOD RUNNING");
		//just to add some space
		System.out.print("\n\n");
		System.out.println("CONGRATULATIONS!!! YOU WIN!!");
			
	 }

	public static void playerLoses(){

		//just to add some space
		System.out.print("\n\n");
		//System.out.print("\n PLAYERLOSES METHOD RUNNING");
		System.out.println("GAME OVER! YOU LOSE!");

		//fills game board with '*'
		for(int i=0; i<gameBoard.gameBoardArray.length; i++){

			for (int j=0; j<gameBoard.gameBoardArray[0].length; j++){

				gameBoard.updateGameBoard(i,j,'*');
				
			}

		}

		gameBoard.printGameBoard();

		System.out.println("THe battleships were hidden at location:\n");
		//prints location of battleships
		for(Location location: battleships){

			System.out.println(location.getRow()+", "+location.getColumn());
		}
     }

}