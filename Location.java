

class Location{
	
	//Attributes
	private int row, column;

	//setter for row
	public void setRow(int row){
		this.row=row;
	}

	//getter for row
	public int getRow(){
		return this.row;
	}

	//setter for column
	public void setColumn(int column){
		this.column=column;
	}

	//getter for column
	public int getColumn(){
		return this.column;
	}

	//Constructor 
	public Location(int row, int column){
		this.row=row;
		this.column=column;
	}

	public boolean equals(Location location){

		return location.row==this.row && location.column==this.column;

	}
}