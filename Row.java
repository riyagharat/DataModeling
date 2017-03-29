// Row class
class Row{
  private String data;
  private String type;
  private Row nextRow;
  private Row nextField;

  public Row(){

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

  public String toString(){
      return getData() + " " + getType() + "  "+ getNotNull() + "\n";
  }
}
