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
	  public int deletionIndex;


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
}
