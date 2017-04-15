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

  public Boolean findValue(String operator, String rightSide)			
  {
	  if(operator.equals("="))
	  {
	//	  if(this.type.equals("char"))
	//	  {
			  if(rightSide.equals(this.data))
				  return true;
			  else return false;
/*		  }
		  else if(this.type.equals("number"))
		  {
			  if(rightSide == this.data)
				  return true;
			  else return false;
		  }*/
	  }
	  else if(operator.equals("<>"))			
	  {
		  if(this.type.equals("char"))
		  {
			  if(!rightSide.equals(this.data))
				  return true;
			  else return false;
		  }
		  else if(this.type.equals("number"))
		  {
			  if(rightSide != this.data)
				  return true;
			  else return false;
		  }
	  }
	  else if(operator.equals("<"))
	  {
		  if (rightSide.matches(".*[a-zA-Z].*")) 
		  { 
			  System.out.println("invalid use of " + operator + " on type " + this.type);
			  return false;
		  }
		  else  if(Integer.parseInt(this.data) < Integer.parseInt(rightSide))
			  return true;
		  else return false;
		  
	  }
	  else  if(operator.equals(">="))
	  {
		  if (rightSide.matches(".*[a-zA-Z].*")) 
		  { 
			  System.out.println("invalid use of " + operator + " on type " + this.type);
			  return false;
		  }
		  else  if(Integer.parseInt(this.data) >= Integer.parseInt(rightSide))
			  return true;
		  else return false;
		  
	  }
	  else  if(operator.equals("<="))
	  {
		  if (rightSide.matches(".*[a-zA-Z].*")) 
		  { 
			  System.out.println("invalid use of " + operator + " on type " + this.type);
			  return false;
		  }
		  else  if(Integer.parseInt(this.data) <= Integer.parseInt(rightSide))
			  return true;
		  else return false;
		  
	  }
	  else  if(operator.equals(">"))
	  {
		  if (rightSide.matches(".*[a-zA-Z].*")) 
		  { 
			  System.out.println("invalid use of " + operator + " on type " + this.type);
			  return false;
		  }
		  else  if(Integer.parseInt(this.data) > Integer.parseInt(rightSide))
			  return true;
		  else return false;
		  
	  }
	  else
	  {
		  System.out.println(operator + " invalid");
		  return false;
	  }
	return false;

  	}
  
  	public void delete()
  	{
	  
  	}
}
