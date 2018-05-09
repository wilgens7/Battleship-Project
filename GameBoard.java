

public class GameBoard{

	protected char[][] gameBoardArray;


//=================================================Constructor==============================================================================
		public GameBoard(int row, int column){

			gameBoardArray = new char[row][column];
		}


 //======================================================================Method For Initializing Game Board==================================

		public void initializeGameBoard(char characterToPopulateArray){


			for(int i=0; i<this.gameBoardArray.length; i++){

			for (int j=0; j<this.gameBoardArray[0].length; j++){

				this.gameBoardArray[i][j]=characterToPopulateArray;
				
			}
			
		}

		}

 //======================================================================Method For Printing Game Board==================================
	public void printGameBoard(){

		//adding space
		System.out.println();

		for(int i=0; i<this.gameBoardArray.length; i++){

			for (int j=0; j<this.gameBoardArray[0].length; j++){

				System.out.print(this.gameBoardArray[i][j]+"    ");
			}
			System.out.print("\n\n");

		}
	}

 //======================================================================Method For upDating Game Board==================================

	public void updateGameBoard(int row, int column, char value){

		try{

		this.gameBoardArray[row][column]=value;
			}

		catch(Exception e){

				System.out.println("\n\nMake sure that your index values are not out of bounds.\n\n");

		}

	}


}