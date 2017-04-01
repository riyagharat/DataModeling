// Col class
class Column{
  private Record firstRecord;
  private Column nextColumn;
  private String name;
  private String dataType;
  private Column dateTime;
private Boolean notNull;

  public Column(){

  }

  public Column(Record firstRecord, String name, Boolean notNull) {
    this.firstRecord = firstRecord;
    this.name = name;
    this.notNull = notNull;
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
}
