// Row class
class Row{
  private String data;
  private String type;
  private Row nextRow;
  private Row sideRow;
  private Row prevRow;
  private Boolean notNull;
 

  public Row(){

  }

  public Row(String data, Boolean notNull, String type) {
    this.data = data;
    this.notNull = notNull;
    this.type = type;
  }

  public String getData(){
    return data;
  }

  public String getType(){
    return type;
  }

  public void setData(String newData){
    this.data = newData;
  }
  
  public void setType(String newType){
    this.type = newType;
  }
  public void setnextRow(Row nextRow){
	    this.nextRow = nextRow;
	  }
  public void setsideRow(Row sideRow){
	    this.sideRow = sideRow;
	  }
  public Row getNextRow(){
	    return this.nextRow;
	  }
  public void setPrevRow(Row prevRow){
	    this.prevRow = prevRow;
	  }
  public Row getPrevRow(){
	    return this.prevRow;
	  }
public Row getsideRow(){
	    return this.sideRow;
	  }

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getData() + " " + getType() + "  "+ getNotNull() + "\n";
  }
  
  private Boolean getNotNull() {
	return notNull;
  }
}
