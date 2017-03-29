// Row class
class Row{
  private String data;
  private String type;
  private Row nextRow;
  private Row nextField;

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

  public Boolean getNotNull(){
    return notNull;
  }

  public String getType(){
    return type;
  }

  public void setData(String newData){
    this.data = newData;
  }

  public void setNotNull(Boolean newNotNull){
    this.notNull = newNotNull;
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
}
