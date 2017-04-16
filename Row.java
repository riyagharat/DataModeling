import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Row class
class Row{
  private String data;
  private String type;
  private Date dateTime;

 
  public Row(){

  }
  public Date getDateTime()
  {
	  return this.dateTime;
  }
  public void setDateTime(Date newDate)
  {
	  this.dateTime = newDate;
  }


  public Row(String data, String type) {
    this.data = data;
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

  public Boolean findValueChar(String operator, String rightSide)			//use .equals to compare strings
  {																			//only allow = and <> operators
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
	  float thisData = Float.parseFloat(this.data);					//convert data to numerical type to allow for all comparison operators
	  float rSide = Float.parseFloat(rightSide);
	  if(operator.equals("="))
	  {
		if(thisData == rSide)
		  return true;
		else return false;
	  }
	  else if(operator.equals("<>"))
	  {
		if(thisData != rSide)
			  return true;
			else return false;
	  }
	  else if(operator.equals("<"))
	  {
		  if(thisData < rSide)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">="))
	  {
		  if(thisData >= rSide)
			  return true;
			else return false;
	  }
	  else  if(operator.equals("<="))
	  {
		  if(thisData <= rSide)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">"))
	  {
		  if(thisData > rSide)
			  return true;
			else return false;
	  }
	  else
	  {
		  System.out.println(operator + " invalid");
		  return false;
	  }
  	}
  public Boolean findValueDate(String operator, String rightSide) throws ParseException			
  {
	  if(this.data == null)
		  return false;
	  SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
	  SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
	  Date thisDate; 
	  Date rSide;
	  if(this.data.length() > 8)
		  thisDate = parser.parse(this.data);
	  else thisDate = otherParser.parse(this.data);
	  
	  if(rightSide.length() > 8)
		  rSide = parser.parse(rightSide);
	  else rSide = otherParser.parse(rightSide);


	  if(operator.equals("="))
	  {
			if(thisDate.compareTo(rSide) == 0)
			  return true;
			else return false;
	  }
	  else if(operator.equals("<>"))			
	  {
			if(thisDate.compareTo(rSide) != 0)
				  return true;
				else return false;
	  }
	  else if(operator.equals("<"))
	  {
		  if(thisDate.compareTo(rSide) < 0)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">="))
	  {
		  if(thisDate.compareTo(rSide) >= 0)
			  return true;
			else return false;
	  }
	  else  if(operator.equals("<="))
	  {
		  if(thisDate.compareTo(rSide) <= 0)
			  return true;
			else return false;
	  }
	  else  if(operator.equals(">"))
	  {
		  if(thisDate.compareTo(rSide) > 0)
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
