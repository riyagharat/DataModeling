import java.util.ArrayList;

// Col class
class Column{
	 ArrayList<Row> list = new ArrayList<Row>();
	  private Column nextColumn;
	  private String name;
	  private String dataType;
	  private Column dateTime;
	  private int size;
	  private int decimal;
	private Boolean notNull;


  public Column(){

  }

  public Column(ArrayList<Row> list, String name, Boolean notNull) 
  {
	    this.list = list;
	    this.name = name;
	    this.notNull = notNull;
  }

  public String getName(){
    return name;
  }
  public void setName(String newName){
    this.name = newName;
  }
  public void setSize(int size, int decimal){
	    this.size = size;
	    this.decimal = decimal;
	  }
  public void setSize(int size){
	    this.size = size;
	  }
  public void setDecimal(int dec){
	    this.decimal = dec;
	  }
  public int getSize()
  {
	  return this.size;
  }
  public int getDecimal()
  {
	  return this.decimal;
  }

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getName() + " " + getNotNull() + "\n";
  }

  	public Boolean getNotNull() {
	return notNull;
	}

	public Column getNextColumn() {
	return nextColumn;
	}
	public String getType() {
	return this.dataType;
	}
	public void setType(String type) {
			this.dataType = type;
	}
	public void setNextColumn(Column nextColumn) {
		this.nextColumn = nextColumn;
	}
	public void initializeRowAL()
	{	
		Row newRec = new Row();
		newRec.setType(this.dataType);
		this.list.add(newRec);
	}
	public void insertRecord(String data)
	{
		this.list.get(list.size() - 1).setData(data);	
	}
	
	public ArrayList<Integer> findRowNoInitial(String operator, String rightSide)		//find all row #s where first condition is true
	{
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < this.list.size(); i++)
		{
			if(this.list.get(i).getType().equals("char"))
			{
				if(rightSide.matches(".*\\d+.*") == true)
				{
					System.out.println("Invalid comparison between type char and " + rightSide);
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else{
					if(this.list.get(i).findValueChar(operator, rightSide) == true)
						rowsToBeDeleted.add(i);
				}
			}
			else if(this.list.get(i).getType().equals("number") || this.list.get(i).getType().equals("integer"))
			{
				if(rightSide.matches(".*\\c+.*") == true)
				{
					System.out.println("Invalid comparison between type "+ this.list.get(i).getType() + " and " + rightSide);
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(i).findValue(operator, rightSide) == true)
						rowsToBeDeleted.add(i);
				}
			}

		}
		return rowsToBeDeleted;
	}
	public ArrayList<Integer> findRowNo(String operator, String rightSide, ArrayList<Integer> indicesTBC)		//find row #s where the 2nd, 3rd, etc condition is true
	{																											//only considers rows that are currently true
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < indicesTBC.size(); i++)
		{
			if(this.list.get(i).getType().equals("char"))
			{
				if(rightSide.matches(".*\\d+.*") == true)
				{
					System.out.println("Invalid comparison between type char and " + rightSide);
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(indicesTBC.get(i)).findValueChar(operator, rightSide) == true)
						rowsToBeDeleted.add(indicesTBC.get(i));
				}
			}
			else if(this.list.get(i).getType().equals("number") || this.list.get(i).getType().equals("integer"))
			{
				if(rightSide.matches(".*\\c+.*") == true)
				{
					System.out.println("Invalid comparison between type "+ this.list.get(i).getType() + " and " + rightSide);
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(indicesTBC.get(i)).findValue(operator, rightSide) == true)
						rowsToBeDeleted.add(indicesTBC.get(i));
				}
			}
		}

		
		
		return rowsToBeDeleted;
	}
	
	public void deleteFromList(int index)
	{
		this.list.remove(index);
	}
	
	public void displayRecords()
	{
		//System.out.println("array list:");
		for(int i = 0; i < this.list.size(); i++)
		{
			System.out.println(this.list.get(i).getData());
		}
		System.out.println();
		/*System.out.println("linked list:");
		Row tempRow;
		tempRow = firstRecord;
		while(tempRow != null)
		{
			System.out.println(tempRow.getData());
			tempRow = tempRow.getNextRow();
		}*/
		System.out.println();
	}

}
