// Table Class

class Table{
  private Column firstCol;
  private Table nextTable;
  private String name;

  public Table(){

  }

  public Table(Column firstCol, String name) {
    this.firstCol = firstCol;
    this.name = name;
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
  
  public void findRecordWhere(String condition)
  {	
	String[] cond = splitCondition(condition);
	Column curCol = this.firstCol;
	
	while(!cond[0].equals(curCol.getName()))		// search for specified attribute, checking for end of table
	{
		if(curCol.getNextColumn() == null)
		{
			System.out.println("Atrribute " + cond[0] + " does not exist in table " + this.name);
			return;
		}
		else curCol = curCol.getNextColumn();
	}
	
	
  }
  public String[] splitCondition(String condition)		// splits a condition (ex. SNO>4) into an array 
  {

	    String[] ops = condition.split("\\s*[a-zA-Z0-9]+\\s*");
	    String[] notOps = condition.split("\\s*[^a-zA-Z0-9]+\\s*");
	    String[] result = new String[ops.length + notOps.length-1];
	    for(int i = 0; i < result.length; i++) 
	    	result[i] = i%2==0 ? notOps[i/2] : ops[i/2+1];
	    	
	    return result;
  }
}
