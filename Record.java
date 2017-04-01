// Row class
class Record{
  private String data;
  private String type;
  private Record nextRow;
  private Record sideRow;
  private Boolean notNull;

  public Record(){

  }

  public Record(String data, Boolean notNull, String type) {
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

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getData() + " " + getType() + "  "+ getNotNull() + "\n";
  }
  
  private Boolean getNotNull() {
	return notNull;
  }

  public void delete(String operator, String value)			// idk how we will do comparisons with an operator as a String. although maybe there's a better way to read in the condition
  {
	 int data1;
     if(this.type.equals("integer"))
    	 data1 = Integer.parseInt(this.data);
     
     // if (this.data operator value == true)
     		//delete
  }
}
