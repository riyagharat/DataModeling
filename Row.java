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
  public Boolean findValueChar(String operator, String rightSide)			
  {
	  if(this.data == null)
		  return false;
	  if(operator.equals("="))
	  {
		  if(rightSide.equals(this.data))
			  return true;
		  else return false;
	  }
	  else if(operator.equals("<>"))			
	  {
		  if(!rightSide.equals(this.data))
			  return true;
			 else return false;
	  }
	  else 
	  {
		  System.out.println(operator + " invalid for type " + this.type);
		  return false;
	  }
  }

  public Boolean findValue(String operator, String rightSide)			
  {
	  if(this.data == null)
		  return false;
	  float thisData = Float.parseFloat(this.data);
	  float rSide = Float.parseFloat(rightSide);
	  if(operator.equals("="))
	  {
			if(rSide == thisData)
			  return true;
			else return false;
	  }
	  else if(operator.equals("<>"))			
	  {
			if(rSide != thisData)
				  return true;
				else return false;
	  }
	  else if(operator.equals("<"))
	  {
		  if(rSide < thisData)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">="))
	  {
		  if(rSide >= thisData)
			  return true;
			else return false;
	  }
	  else  if(operator.equals("<="))
	  {
		  if(rSide <= thisData)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">"))
	  {
		  if(rSide > thisData)
			  return true;
			else return false;
	  }
	  else
	  {
		  System.out.println(operator + " invalid");
		  return false;
	  }
  	}
}
