import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Table Class

class Table{
	 ArrayList<Column> list = new ArrayList<Column>();
	  private String name;

  public Table(){

  }


  public Table(String name, ArrayList<String> fields, ArrayList<String> types) 
  {
    for(int i = 0; i < fields.size(); i++)					//create columns and set types/size
    {
    	Column newCol = new Column();
    	newCol.setName(fields.get(i));
    	String type = "";
    	if(types.get(i).contains("(") && types.get(i).contains(")"))					//if a size is specified
    	{
    			type = types.get(i).substring(0, types.get(i).indexOf("("));		
    	    	if(type.equals("integer"))										//if float, set size, decimal size
    	    	{
    	    		newCol.setSize(Integer.parseInt(types.get(i).substring(types.get(i).indexOf("(") + 1, types.get(i).indexOf(","))));
    	    		newCol.setDecimal(Integer.parseInt(types.get(i).substring(types.get(i).indexOf(",") + 1, types.get(i).indexOf(")"))));
    	    	}
    	    	else															//else set size
    	    	{
    	    		newCol.setSize(Integer.parseInt(types.get(i).substring(types.get(i).indexOf("(") + 1, types.get(i).indexOf(")"))));
    	    	}
    	}
    	else														//if no size is specified, set size,?(decimal) to 255
    	{
    		type = types.get(i);
    		if(type.equals("integer"))
	    	{
    			newCol.setSize(255);
    			newCol.setDecimal(255);
	    	}
    		else if(type.equals("date"))
    			newCol.setSize(10);
    		else newCol.setSize(255);
    	}
    	
    	newCol.setType(type);		
    	this.list.add(newCol);
    }
    this.name = name;
    System.out.println("Table " + this.name + " created with attributes ");
}
  public String getName(){
    return name;
  }

  public void setName(String newName){
    this.name = newName;
  }

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getName() + "\n";
  }
  public void simpleInsert(ArrayList<String> values)
  {
	  ArrayList<Integer> insertHere = new ArrayList<Integer>();	
	  for(int i = 0; i < values.size(); i ++)						
	  {			 
		  if(this.list.get(i).getType().equals("char"))				//semantic checks
		  {
			  if(values.get(i).contains("[0-9]+") == true)			//decline if data contains any digits
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type char");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(i).getSize())			//decline if too large
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
				  return;
				}
		  }
		  else if(this.list.get(i).getType().equals("number"))
		  {
			  if(values.get(i).contains("[a-zA-Z]+") == true)				//decline if data contains any letters
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type number");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(i).getSize())				//decline if too large	
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
				  return;
				}
		  }
		  else if(this.list.get(i).getType().equals("integer"))
		  {
			  String leftSide = values.get(i).substring(0, values.get(i).indexOf('.'));
			  String rightSide = values.get(i).substring(values.get(i).indexOf('.') + 1, values.get(i).length());
			  if(values.get(i).contains("[a-zA-Z]+") == true)
			  {
				  System.out.println("can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type number");
				  return;
			  }
			  else if (leftSide.length() > this.list.get(i).getSize())					//decline if too large
				{
				  System.out.println(values.get(i) + " has too many digits before the decimal " + this.list.get(i).getName());
				  return;
				}
			  else if (rightSide.length() > this.list.get(i).getDecimal())				//decline if too large
				{
				  System.out.println(values.get(i) + " has too many digits after the decimal " + this.list.get(i).getName());
				  return;
				}
		  } 
		  else if(this.list.get(i).getType().equals("date"))
		  {
			  if(values.get(i).matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)				//decline if data is not in date format
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type date");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(i).getSize())		//decline if too large
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
				  return;
				}
			  else insertHere.add(i);
			  
		  }
    	}
	  for(int i = 0; i < this.list.size(); i ++)			//create row objects
		  list.get(i).initializeRowAL();
	  for(int i = 0; i < values.size(); i ++)
		  list.get(i).insertRecord(values.get(i));  	
  }
  public void specificInsert(ArrayList<String> fields, ArrayList<String> values)
  {
	  ArrayList<Integer> insertHere = new ArrayList<Integer>();		
	  for(int i = 0; i < values.size(); i ++)						//search for correct column name insert
	  {
		  int colIndex = 0;
		  while(colIndex < this.list.size() - 1 && !(this.list.get(colIndex).getName().equals(fields.get(i))))		//while not correct column	  
			  colIndex++;
		 
		  if(this.list.get(colIndex).getType().equals("char"))				//semantic checks
		  {
			  if(values.get(i).contains("[0-9]+") == true)				//decline if data contains any digits
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type char");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(colIndex).getSize())		//decline if too large
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
				  return;
				}
			  else insertHere.add(colIndex);
		  }
		  else if(this.list.get(colIndex).getType().equals("number"))
		  {
			  if(values.get(i).contains("[a-zA-Z]+") == true)				//decline if data contains any letters
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(colIndex).getName() + ", type number");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(colIndex).getSize())			//decline if too large
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
				  return;
				}
			  else insertHere.add(colIndex);
		  }
		  else if(this.list.get(colIndex).getType().equals("integer"))
		  {
			  String leftSide = values.get(i).substring(0, values.get(i).indexOf('.'));
			  String rightSide = values.get(i).substring(values.get(i).indexOf('.') + 1, values.get(i).length());
			  if(values.get(i).contains("[a-zA-Z]+") == true)
			  {
				  System.out.println("can't insert " + fields.get(i) + " into attribute" + this.list.get(colIndex).getName() + ", type number");
				  return;
			  }
			  else if (leftSide.length() > this.list.get(colIndex).getSize())		//decline if too large
				{
				  System.out.println(values.get(i) + " has too many digits before the decimal " + this.list.get(colIndex).getName());
				  return;
				}
			  else if (rightSide.length() > this.list.get(colIndex).getDecimal())		//decline if too large
				{
				  System.out.println(values.get(i) + " has too many digits after the decimal " + this.list.get(colIndex).getName());
				  return;
				}
			  else insertHere.add(colIndex);
		  }
		  else if(this.list.get(colIndex).getType().equals("date"))
		  {
			  if(values.get(i).matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)				//decline if data is not in date format
			  {
				  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type date");
				  return;
			  }
			  else if (values.get(i).length() > this.list.get(colIndex).getSize())		//decline if too large
				{
				  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
				  return;
				}
			  else insertHere.add(colIndex);
			  
		  }
    	}
	  for(int i = 0; i < this.list.size(); i ++)			//create row objects
		  list.get(i).initializeRowAL();
	  for(int i = 0; i < values.size(); i ++)				//insert data at appropriate columns
		  list.get(insertHere.get(i)).insertRecord(values.get(i)); 
  }
  
  public void insert(ArrayList<String> fields, ArrayList<String> values)
  {	  	
	  if(fields.isEmpty())					//simple insert
		  simpleInsert(values);
	  else specificInsert(fields, values);
  }
  public void oldInsert(ArrayList<String> fields, ArrayList<String> values)
  {	  
	  ArrayList<Integer> insertHere = new ArrayList<Integer>();			
	  if(fields.isEmpty())					//simple insert
	  {
		  for(int i = 0; i < values.size(); i ++)						
		  {			 
			  if(this.list.get(i).getType().equals("char"))				//semantic checks
			  {
				  if(values.get(i).contains("[0-9]+") == true)			//decline if data contains any digits
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type char");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(i).getSize())			//decline if too large
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
					  return;
					}
			  }
			  else if(this.list.get(i).getType().equals("number"))
			  {
				  if(values.get(i).contains("[a-zA-Z]+") == true)				//decline if data contains any letters
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type number");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(i).getSize())				//decline if too large	
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
					  return;
					}
			  }
			  else if(this.list.get(i).getType().equals("integer"))
			  {
				  String leftSide = values.get(i).substring(0, values.get(i).indexOf('.'));
				  String rightSide = values.get(i).substring(values.get(i).indexOf('.') + 1, values.get(i).length());
				  if(fields.get(i).contains("[a-zA-Z]+") == true)
				  {
					  System.out.println("can't insert " + fields.get(i) + " into attribute" + this.list.get(i).getName() + ", type number");
					  return;
				  }
				  else if (leftSide.length() > this.list.get(i).getSize())					//decline if too large
					{
					  System.out.println(fields.get(i) + " has too many digits before the decimal " + this.list.get(i).getName());
					  return;
					}
				  else if (rightSide.length() > this.list.get(i).getDecimal())				//decline if too large
					{
					  System.out.println(fields.get(i) + " has too many digits after the decimal " + this.list.get(i).getName());
					  return;
					}
			  } 
			  else if(this.list.get(i).getType().equals("date"))
			  {
				  if(values.get(i).matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)				//decline if data is not in date format
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type date");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(i).getSize())		//decline if too large
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(i).getName());
					  return;
					}
				  else insertHere.add(i);
				  
			  }
	    	}
		  for(int i = 0; i < this.list.size(); i ++)			//create row objects
			  list.get(i).initializeRowAL();
		  for(int i = 0; i < values.size(); i ++)
			  list.get(i).insertRecord(values.get(i));  			
	  }
	  else
	  {
		  for(int i = 0; i < values.size(); i ++)						//search for correct column name insert
		  {
			  int colIndex = 0;
			  while(colIndex < this.list.size() - 1 && !(this.list.get(colIndex).getName().equals(fields.get(i))))		//while not correct column	  
				  colIndex++;
			 
			  if(this.list.get(colIndex).getType().equals("char"))				//semantic checks
			  {
				  if(values.get(i).contains("[0-9]+") == true)				//decline if data contains any digits
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type char");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(colIndex).getSize())		//decline if too large
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
					  return;
					}
				  else insertHere.add(colIndex);
			  }
			  else if(this.list.get(colIndex).getType().equals("number"))
			  {
				  if(values.get(i).contains("[a-zA-Z]+") == true)				//decline if data contains any letters
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(colIndex).getName() + ", type number");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(colIndex).getSize())			//decline if too large
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
					  return;
					}
				  else insertHere.add(colIndex);
			  }
			  else if(this.list.get(colIndex).getType().equals("integer"))
			  {
				  String leftSide = values.get(i).substring(0, values.get(i).indexOf('.'));
				  String rightSide = values.get(i).substring(values.get(i).indexOf('.') + 1, values.get(i).length());
				  if(fields.get(i).contains("[a-zA-Z]+") == true)
				  {
					  System.out.println("can't insert " + fields.get(i) + " into attribute" + this.list.get(colIndex).getName() + ", type number");
					  return;
				  }
				  else if (leftSide.length() > this.list.get(colIndex).getSize())		//decline if too large
					{
					  System.out.println(fields.get(i) + " has too many digits before the decimal " + this.list.get(colIndex).getName());
					  return;
					}
				  else if (rightSide.length() > this.list.get(colIndex).getDecimal())		//decline if too large
					{
					  System.out.println(fields.get(i) + " has too many digits after the decimal " + this.list.get(colIndex).getName());
					  return;
					}
				  else insertHere.add(colIndex);
			  }
			  else if(this.list.get(colIndex).getType().equals("date"))
			  {
				  if(values.get(i).matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false)				//decline if data is not in date format
				  {
					  System.out.println("Can't insert " + values.get(i) + " into attribute" + this.list.get(i).getName() + ", type date");
					  return;
				  }
				  else if (values.get(i).length() > this.list.get(colIndex).getSize())		//decline if too large
					{
					  System.out.println(values.get(i) + " too large for " + this.list.get(colIndex).getName());
					  return;
					}
				  else insertHere.add(colIndex);
				  
			  }
	    	}
		  for(int i = 0; i < this.list.size(); i ++)			//create row objects
			  list.get(i).initializeRowAL();
		  for(int i = 0; i < values.size(); i ++)				//insert data at appropriate columns
			  list.get(insertHere.get(i)).insertRecord(values.get(i)); 
	  }
  }
  public void displayColumns()
  {
	  for(int i = 0; i < this.list.size(); i ++)
		  System.out.print(this.list.get(i).getName() + " ");
	  
	  System.out.println();
  }
  
  public void displayTable()
  {	  
	  for(int i = 0; i<this.list.size() ; i++)
	  {
		  System.out.println(this.list.get(i).getName() + " " + this.list.get(i).getType() + this.list.get(i).getSize() + "," + this.list.get(i).getDecimal() + " ");
		  System.out.println("--------------");
		  this.list.get(i).displayRecords();
	  }
  }  
  public void delete(ArrayList<String> conditions) throws ParseException
  {	
	  ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
	 int i = 0;
	 int j = 0;
	 Boolean rSideCol = false;
	 int rColIndex = 0;

		 String leftSide = conditions.get(i);
		 String operator = conditions.get(i+1);
		 String rightSide = conditions.get(i+2);
		 i += 3;
		 for(; j < this.list.size(); j++)								//find if right side is an attribute in this table
		  {
			 if(this.list.get(j).getName().equals(rightSide))
			 {
				 rSideCol = true;
				 rColIndex = j;
				 break;
			 }
		  }
		 if(rSideCol = true)				//if the right side of a condition is a column
		 {
			 rowsToBeDeleted = deleteInitialCol(leftSide, operator, this.list.get(rColIndex));		//send the appropriate column
		 }																												//if not, simply send the data
		 else rowsToBeDeleted = deleteInitial(leftSide, operator, rightSide);		//get all indices that are true for the first condition

		 while(i < conditions.size())									//if more conditions
		 {
			 leftSide = conditions.get(i);
			 operator = conditions.get(i+1);
			 rightSide = conditions.get(i+2);
			 i += 3;
			 if(this.list.contains(rightSide))										
			 {																		//send the appropriate column																											//if not, simply send the data
				 rowsToBeDeleted = deleteWhereCol(leftSide, operator, this.list.get(rColIndex), rowsToBeDeleted);
			 }																		//if not, simply send the data
			 else rowsToBeDeleted = deleteWhere(leftSide, operator, rightSide, rowsToBeDeleted);			//get indices that are still true
		 }
		 if(rowsToBeDeleted.isEmpty())
		 {
			 System.out.println("Condition failed so nothing was deleted.");
			 return;
		 }
		 else
			 {
			 for(int k = rowsToBeDeleted.size() - 1; k >= 0; k --)
				 deleteFromList(rowsToBeDeleted.get(k));
			 }
		
  }
  public ArrayList<Integer> deleteInitialCol(String leftSide, String operator, Column rightSide) throws ParseException		//finds the row #s that are still true (checks 2nd, 3rd, etc conditions)
  {
	  ArrayList<Integer> deletionIndices = new ArrayList<Integer>();
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))		
		  {
			  deletionIndices.addAll(this.list.get(i).findRowNoInitialRSC(operator, rightSide));
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  deletionIndices.clear();
	  return deletionIndices;
  }
  public ArrayList<Integer> deleteWhereCol(String leftSide, String operator, Column rightSide, ArrayList<Integer> deletionIndices) throws ParseException		//finds the row #s that are still true (checks 2nd, 3rd, etc conditions)
  {
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))				
		  {
			  deletionIndices = this.list.get(i).findRowNo(operator, rightSide.list.get(i).getData(), deletionIndices);
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  deletionIndices.clear();
	  return deletionIndices;
  }
  
  public ArrayList<Integer> deleteWhere(String leftSide, String operator, String rightSide, ArrayList<Integer> deletionIndices) throws ParseException		//finds the row #s that are still true (checks 2nd, 3rd, etc conditions)
  {
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))				
		  {
			  deletionIndices = this.list.get(i).findRowNo(operator, rightSide, deletionIndices);
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  deletionIndices.clear();
	  return deletionIndices;
  }
  
  public ArrayList<Integer> deleteInitial(String leftSide, String operator, String rightSide) throws ParseException			//finds all the row #s where the first condition is true
  {
	  ArrayList<Integer> deletionIndices = new ArrayList<Integer>();
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))		
		  {
			  deletionIndices.addAll(this.list.get(i).findRowNoInitial(operator, rightSide));
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  deletionIndices.clear();
	  return deletionIndices;
  }
  public void deleteFromList(int index)
  {
	  for(int i = 0; i < this.list.size(); i ++)
		  this.list.get(i).deleteFromList(index);
  }

}
