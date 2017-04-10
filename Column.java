import java.util.ArrayList;

// Col class
class Column{
	 ArrayList<Row> list = new ArrayList<Row>();
	 private Row firstRecord;
	  private Column nextColumn;
	  private String name;
	  private String dataType;
	  private Column dateTime;
	private Boolean notNull;
	public Boolean firstCol = false;
	public Boolean lastCol = false;


  public Column(){

  }

  public Column(ArrayList<Row> list, String name, Boolean notNull) 
  {
	    this.list = list;
	    this.name = name;
	    this.notNull = notNull;
  }
  public void setFirstCol(Boolean firstCol)
  {
	  this.firstCol = firstCol;
  }
  public Boolean getfirstCol()
  {
	    return this.firstCol;
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
      return getName() + " " + getNotNull() + "\n";
  }

  	public Boolean getNotNull() {
	return notNull;
	}

	public Column getNextColumn() {
	return nextColumn;
	}

	public void setNextColumn(Column nextColumn) {
		this.nextColumn = nextColumn;
	}
	
	public void initializeRow(Column prevCol)
	{	
		Row curRec;
		Row prevRec = null;
		Row newRec = new Row();
		int index = 0;
		curRec = this.firstRecord;
		while(curRec != null)						//find next available row
		{		
			prevRec = curRec;
			curRec = curRec.getNextRow();
			index++;
		}
		
		if(prevRec == null)							//if first row
		{
			this.firstRecord = newRec;
			newRec.setType(this.dataType);
			list.add(newRec);
			
			if(prevCol == null)
				this.firstCol = true;
			else prevCol.list.get(index).setsideRow(newRec);
				
			return;
		}
		else
		{
			prevRec.setnextRow(newRec);
			newRec.setType(this.dataType);
			list.add(newRec);
			
			if(prevCol == null)
				this.firstCol = true;
			else prevCol.list.get(index).setsideRow(newRec);
			
			return;
		}
	}
	
	public void setCircPtr(Column firstCol)			//get last record from first and last columns. set the last record from the
	{
		this.list.get(list.size() - 1).setsideRow(firstCol.list.get(list.size() - 1));
		this.lastCol = true;
		firstCol.firstCol = true;
		this.nextColumn = firstCol;
	}

	public void insertRecord(String data)
	{
		this.list.get(list.size() - 1).setData(data);	
	}
	public void insertRec(String data)
	{
		Row curRow = this.firstRecord;
		while(curRow != null)
			curRow = curRow.getNextRow();
		
		curRow.setData(data);
	}
}
	
