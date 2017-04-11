import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Table Class

class Table{
	 ArrayList<Column> list = new ArrayList<Column>();
	  private Table nextTable;
	  private String name;


  public Table(){

  }


  public Table(String[] columnNames, String name) 
  {
	  Column prevCol = null;
    for(int i = 0; i < columnNames.length; i++)
    {
    	Column newCol = new Column();
    	newCol.setName(columnNames[i]);
    	this.list.add(newCol);
    	if(i > 0)
    		prevCol.setNextColumn(newCol);
    	prevCol = newCol;
    }
    this.name = name;
    System.out.println("Table " + this.name + " created with attributes " + Arrays.toString(columnNames));
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
  
  public void insert(ArrayList<String> fields, ArrayList<String> values)
  {	  
	  for(int i = 0; i < this.list.size(); i ++)			//create row objects
	  {
		  Column prevCol;
		 
		  if(i == 0)
			  prevCol = null;
		  else prevCol = list.get(i - 1);
		  
		  list.get(i).initializeRow(prevCol);
		  if(i == list.size() - 1)
		  {
			  Column firstCol = list.get(0);
			  list.get(i).setCircPtr(firstCol);
	      }
	
	  }
	  
	  if(fields.isEmpty())
	  {
		  for(int i = 0; i < values.size(); i ++)
			  list.get(i).insertRecord(values.get(i));  			//simple insert
	  }
	  else
	  {
		  for(int i = 0; i < values.size(); i ++)						//search for correct column name insert
		  {
			  int colIndex = 0;
			  while(colIndex < this.list.size() - 1 && !(this.list.get(colIndex).getName().equals(fields.get(i))))		//while not correct column	  
				  colIndex++;
		  
			  list.get(colIndex).insertRecord(values.get(i));  
	    	}
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
	  int i = 0;
	  while(this.list.get(i).lastCol != true)
	  {
		  System.out.println(this.list.get(i).getName() + " ");
		  System.out.println("--------------");
		  this.list.get(i).displayRecords();
		  i++;
	  }
	  System.out.println(this.list.get(i).getName() + " ");
	  System.out.println("--------------");
	  this.list.get(i).displayRecords();
	  i++;
  }  
  
  public void delete(ArrayList<String> conditions)
  {	
	  ArrayList<Integer> rowsToBeDeleted = new ArrayList<Integer>();
	 int i = 0;

		 String leftSide = conditions.get(i);
		 String operator = conditions.get(i+1);
		 String rightSide = conditions.get(i+2);
		 i += 3;
		 rowsToBeDeleted = deleteInitial(leftSide, operator, rightSide);		//get all indices that are true for the first condition

		 while(i < conditions.size())									//if more conditions
		 {
			 leftSide = conditions.get(i);
			 operator = conditions.get(i+1);
			 rightSide = conditions.get(i+2);
			 i += 3;
			 rowsToBeDeleted = deleteWhere(leftSide, operator, rightSide, rowsToBeDeleted);			//get indices that are still true
		 }
		 if(rowsToBeDeleted.isEmpty())
		 {
			 System.out.println("Condition failed so nothing was deleted.");
			 return;
		 }
		 else
			 {
			 for(int j = rowsToBeDeleted.size() - 1; j >= 0; j --)
				 deleteFromList(rowsToBeDeleted.get(j));
			 }
		
  }
  public ArrayList<Integer> deleteWhere(String leftSide, String operator, String rightSide, ArrayList<Integer> deletionIndices)		//finds the row #s that are still true (checks 2nd, 3rd, etc conditions)
  {
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))				//need type check
		  {
			  deletionIndices = this.list.get(i).findRowNo(operator, rightSide, deletionIndices);
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  return deletionIndices;
  }
  
  public ArrayList<Integer> deleteInitial(String leftSide, String operator, String rightSide)			//finds all the row #s where the first condition is true
  {
	  ArrayList<Integer> deletionIndices = new ArrayList<Integer>();
	  for(int i = 0; i < this.list.size(); i++)								//find correct column
	  {
		  if(this.list.get(i).getName().equals(leftSide))		//need type check
		  {
			  deletionIndices.addAll(this.list.get(i).findRowNoInitial(operator, rightSide));
			  return deletionIndices;
		  }
	  }
	  System.out.println(leftSide + " not found in " + this.name);
	  return deletionIndices;
  }
  public void deleteFromList(int index)
  {
	  for(int i = 0; i < this.list.size(); i ++)
		  this.list.get(i).deleteFromList(index);
  }

}
