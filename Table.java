import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Table Class

class Table{
	 ArrayList<Column> list = new ArrayList<Column>();
	  private String name;

  public Table(){}
  
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
    		else if(type.equals("date"))					//set date by default to size 10
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
  public void simpleInsert(ArrayList<String> values)					//insert values sequentially
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
	  for(int i = 0; i < values.size(); i ++)				//insert data
		  list.get(i).insertRecord(values.get(i));  	
  }
  public void specificInsert(ArrayList<String> fields, ArrayList<String> values)			//insert values into specified columns
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
  
  public void insert(ArrayList<String> fields, ArrayList<String> values)		//decision maker
  {	  	
	  if(fields.isEmpty())									//if no fields specified, simple insert
		  simpleInsert(values);
	  else specificInsert(fields, values);
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
	  if(conditions.isEmpty())								//if no conditions specified, delete all data from this table
	  {
		  for(int i = 0; i < this.list.size(); i++)
			  this.list.get(i).deleteAll();
		  
		  this.list.clear();
	  }
	  ArrayList<Integer> rowsToBeDeletedAND = new ArrayList<Integer>();
	  ArrayList<Integer> rowsToBeDeletedOR = new ArrayList<Integer>();
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
				 j = 0;
				 break;
			 }
		  }
		 if(rSideCol == true)				//if the right side of a condition is a column
		 {
			 rowsToBeDeletedAND = deleteInitialCol(leftSide, operator, this.list.get(rColIndex));		//send the appropriate column
		 }																												//if not, simply send the data
		 else rowsToBeDeletedAND = deleteInitial(leftSide, operator, rightSide);		//get all indices that are true for the first condition
		 rSideCol = false;
		
		 while(i < conditions.size())									//while there are more conditions
		 {
			 if(conditions.get(i).equalsIgnoreCase("AND"))					// if AND		
			 {
				 i++;
				 leftSide = conditions.get(i);
				 operator = conditions.get(i+1);
				 rightSide = conditions.get(i+2);
				 for(; j < this.list.size(); j++)								//find if right side is an attribute in this table
				  {
					 if(this.list.get(j).getName().equals(rightSide))
					 {
						 rSideCol = true;
						 rColIndex = j;
						 j = 0;
						 break;
					 }
				  }
				 i += 3;
				 if(rSideCol == true)										
				 {																		//send the appropriate column																											//if not, simply send the data
					 rowsToBeDeletedAND = deleteWhereCol(leftSide, operator, this.list.get(rColIndex), rowsToBeDeletedAND);			//get indices that are still true
				 }																		//if not, simply send the data
				 else rowsToBeDeletedAND = deleteWhere(leftSide, operator, rightSide, rowsToBeDeletedAND);			//get indices that are still true
				 rSideCol = false;
				 }
			 else																// OR
			 {
				 i++;
				 leftSide = conditions.get(i);
				 operator = conditions.get(i+1);
				 rightSide = conditions.get(i+2);
				 for(; j < this.list.size(); j++)								//find if right side is an attribute in this table
				  {
					 if(this.list.get(j).getName().equals(rightSide))
					 {
						 rSideCol = true;
						 rColIndex = j;
						 j = 0;
						 break;
					 }
				  }
				 i += 3;
				 if(rSideCol == true)										
				 {																		//send the appropriate column																											//if not, simply send the data
					 rowsToBeDeletedOR.addAll(deleteInitialCol(leftSide, operator, this.list.get(rColIndex)));			//get all indices that are true for this condition
				 }																		//if not, simply send the data
				 else rowsToBeDeletedOR.addAll(deleteInitial(leftSide, operator, rightSide));			
				 rSideCol = false;
			 }
		 }
		 ArrayList<Integer> rowsTBD = combineWithoutDuplicates(rowsToBeDeletedAND, rowsToBeDeletedOR);
		 if(rowsTBD.isEmpty())
		 {
			 System.out.println("Condition failed so nothing was deleted.");
			 return;
		 }
		 else
		 {
			 for(int k = rowsTBD.size() - 1; k >= 0; k --)
				 deleteFromList(rowsTBD.get(k));
		 }
		
  }
  public ArrayList<Integer> combineWithoutDuplicates(ArrayList<Integer> rowsTBDAND, ArrayList<Integer> rowsTBDOR)		//combine while rejecting duplicates and sort numerically
  {
	  for(int i = 0; i < rowsTBDOR.size(); i++)
		  if(!rowsTBDAND.contains(rowsTBDOR.get(i)))
			  rowsTBDAND.add(rowsTBDOR.get(i));
	  Collections.sort(rowsTBDAND);
	  return rowsTBDAND;
  }
  public ArrayList<Integer> deleteInitialCol(String leftSide, String operator, Column rightSide) throws ParseException		
  {	
	  ArrayList<Integer> deletionIndices = new ArrayList<Integer>();										//finds all the row #s where the first condition is true and the right side of a condition is a column
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))		
		  {
			  deletionIndices.addAll(this.list.get(i).findRowNoInitialRSC(operator, rightSide));
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);			//if left side is not present in this table, clear the indices being considered and report the error
	  deletionIndices.clear();
	  return deletionIndices;
  }
  public ArrayList<Integer> deleteWhereCol(String leftSide, String operator, Column rightSide, ArrayList<Integer> deletionIndices) throws ParseException		
  {																											//finds the row #s that are still true (checks 2nd, 3rd, etc conditions) and the right side of a condition is a column
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))				
		  {
			  deletionIndices = this.list.get(i).findRowNo(operator, rightSide.list.get(i).getData(), deletionIndices);
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);		//if left side is not present in this table, clear the indices being considered and report the error
	  deletionIndices.clear();
	  return deletionIndices;
  }
  
  public ArrayList<Integer> deleteWhere(String leftSide, String operator, String rightSide, ArrayList<Integer> deletionIndices) throws ParseException		
  {																											//finds the row #s that are still true (checks 2nd, 3rd, etc conditions)
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))				
		  {
			  deletionIndices = this.list.get(i).findRowNo(operator, rightSide, deletionIndices);
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);			//if left side is not present in this table, clear the indices being considered and report the error
	  deletionIndices.clear();
	  return deletionIndices;
  }
  
  public ArrayList<Integer> deleteInitial(String leftSide, String operator, String rightSide) throws ParseException			
  {																										//finds all the row #s where the first condition is true
	  ArrayList<Integer> deletionIndices = new ArrayList<Integer>();
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))		
		  {
			  deletionIndices.addAll(this.list.get(i).findRowNoInitial(operator, rightSide));
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);			//if left side is not present in this table, clear the indices being considered and report the error
	  deletionIndices.clear();
	  return deletionIndices;
  }
  public void deleteFromList(int index)												
  {
	  for(int i = 0; i < this.list.size(); i ++)
		  this.list.get(i).deleteFromList(index);
  }

}
