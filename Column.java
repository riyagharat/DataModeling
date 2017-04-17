import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Col class
class Column{
	 ArrayList<Row> list = new ArrayList<Row>();
	  private String name;
	  private String dataType;

	  private int size;
	  private int decimal;
	private Boolean notNull;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


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

  public String toString()
  {
      return getName() + " " + getNotNull() + "\n";
  }

  	public Boolean getNotNull() {
	return notNull;
	}

	public String getType() {
	return this.dataType;
	}
	public void setType(String type) {
			this.dataType = type;
	}
	public void initializeRowAL()
	{	
		Row newRec = new Row();
		newRec.setType(this.dataType);
		this.list.add(newRec);
	}
	public void insertRecord(String data)					
	//insert data and set timestamp into last record (will have been initialized by initializeRowAL
	{
		Date date = new Date();
		dateFormat.format(date);
		this.list.get(list.size() - 1).setData(data);	
		this.list.get(list.size() - 1).setDateTime(date);
	}
	public ArrayList<Integer> findRowNoInitialRSC(String operator, Column rightSide) throws ParseException		
	//find all row #s where first condition is true when the right side of a condition is a column
	{
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < this.list.size(); i++)
		{
			if(this.list.get(i).getType().equalsIgnoreCase("CHAR"))
			{
				if(rightSide.list.get(i).getData().matches("[0-9]*") == true)			
					//check for type compatibility
				{
					System.out.println("Invalid comparison between type char and " + rightSide);
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else{
					if(this.list.get(i).findValueChar(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(i);
				}
			}
			else if(this.list.get(i).getType().equalsIgnoreCase("NUMBER") || this.list.get(i).getType().equalsIgnoreCase("INTEGER"))		
			{	
				if(rightSide.list.get(i).getData().matches("[a-zA-z]*") == true)				
					//check for type compatibility		
				{
					System.out.println("Invalid comparison between type "+ this.list.get(i).getType() + " and " + rightSide.list.get(i).getData());
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(i).findValue(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(i);
				}
			}
			else if(this.list.get(i).getType().equalsIgnoreCase("DATE"))
			{
				if(rightSide.list.get(i).getData().matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)		
					//check for type compatibility
				{
					System.out.println("Invalid format for type date");
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(i).findValueDate(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(i);
				}
			}
		}
		return rowsToBeDeleted;
	}	
	public ArrayList<Integer> findRowNoInitial(String operator, String rightSide) throws ParseException		
	//find all row #s where first condition is true
	{
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < this.list.size(); i++)
		{
			if(this.list.get(i).getType().equalsIgnoreCase("CHAR"))							
				//check for type compatibility
			{
				if(rightSide.matches("[0-9]*") == true)
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
			else if(this.list.get(i).getType().equalsIgnoreCase("NUMBER") || this.list.get(i).getType().equalsIgnoreCase("INTEGER"))
			{
				if(rightSide.matches("[a-zA-z]*") == true)							
					//check for type compatibility
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
			else if(this.list.get(i).getType().equalsIgnoreCase("DATE"))						
				//check for type compatibility
			{
				if(rightSide.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)
				{
					System.out.println("Invalid format for type date");
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(i).findValueDate(operator, rightSide) == true)
						rowsToBeDeleted.add(i);
				}
			}
		}
		return rowsToBeDeleted;
	}
	public ArrayList<Integer> findRowNo(String operator, String rightSide, ArrayList<Integer> indicesTBC) throws ParseException		
	//find row #s where the 2nd, 3rd, etc condition is true
	{																											
		//only considers rows that are currently true
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < indicesTBC.size(); i++)
		{
			if(this.list.get(i).getType().equalsIgnoreCase("CHAR"))					//check for type compatibility
			{
				if(rightSide.matches("[0-9]*") == true)
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
			else if(this.list.get(i).getType().equalsIgnoreCase("NUMBER") || this.list.get(i).getType().equalsIgnoreCase("INTEGER"))				
				//check for type compatibility
			{
				if(rightSide.matches("[a-zA-z]*") == true)
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
			else if(this.list.get(i).getType().equalsIgnoreCase("DATE"))						
				//check for type compatibility
			{
				if(rightSide.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)
				{
					System.out.println("Invalid format for type date");
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(i).findValueDate(operator, rightSide) == true)
						rowsToBeDeleted.add(indicesTBC.get(i));
				}
			}
		}
		return rowsToBeDeleted;
	}
	public ArrayList<Integer> findRowNoRSC(String operator, Column rightSide, ArrayList<Integer> indicesTBC) throws ParseException		
	//find all row #s where first condition is true and the right side of the condition is a column
	{	
		ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
		for(int i = 0; i < indicesTBC.size(); i++)
		{
			if(this.list.get(i).getType().equalsIgnoreCase("CHAR"))						
				//check for type compatibility
			{
				if(rightSide.list.get(i).getData().matches("[0-9]*") == true)
				{
					System.out.println("Invalid comparison between type char and " + rightSide.list.get(i).getData());
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(indicesTBC.get(i)).findValueChar(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(indicesTBC.get(i));
				}
			}
			else if(this.list.get(i).getType().equalsIgnoreCase("NUMBER") || this.list.get(i).getType().equalsIgnoreCase("INTEGER"))
			{
				if(rightSide.list.get(i).getData().matches("[a-zA-z]*") == true)				
					//check for type compatibility
				{
					System.out.println("Invalid comparison between type "+ this.list.get(i).getType() + " and " + rightSide.list.get(i).getData());
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(indicesTBC.get(i)).findValue(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(indicesTBC.get(i));
				}
			}
			else if(this.list.get(i).getType().equalsIgnoreCase("DATE"))							
				//check for type compatibility
			{
				if(rightSide.list.get(i).getData().matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)
				{
					System.out.println("Invalid format for type date");
					rowsToBeDeleted.clear();
					return rowsToBeDeleted;
				}
				else
				{
					if(this.list.get(indicesTBC.get(i)).findValueDate(operator, rightSide.list.get(i).getData()) == true)
						rowsToBeDeleted.add(i);
				}
			}
		}
		return rowsToBeDeleted;
	}
	public void deleteFromList(int index)
	{
		this.list.remove(index);
	}
	public void deleteAll()
	{
		this.list.clear();
	}
	public void displayRecords()
	{
		for(int i = 0; i < this.list.size(); i++)
			System.out.println(this.list.get(i).getData() + " \t" + this.list.get(i).getDateTime());
		System.out.println();
	}

}
