// Col class
class Column{
  private List<Row> list;
  private String name;
  private String dataType;
  private Column dateTime;

  public Column(){

  }

  public Column(List<Row> list, String name, String dataType) {
    this.list = list;
    this.name = name;
    this.dataType = dataType;
  }

  public String getName(){
    return name;
  }

  public String getDataType(){
    return dataType;
  }

  public void setName(String newName){
    this.name = newName;
  }

  public void setDataType(String newDataType){
    this.dataType = newDataType;
  }

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getName() + " " + getDataType() + "\n";
  }
}
