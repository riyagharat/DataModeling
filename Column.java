// Col class
class Column{
  private List<Row> list;
  private Column nextColumn;
  private String name;
  private Boolean notNull;
  private Column dateTime;

  public Column(){

  }

  public Column(List<Row> list, String name, Boolean notNull) {
    this.list = list;
    this.name = name;
    this.notNull = notNull;
  }

  public String getName(){
    return name;
  }

  public Boolean getNotNull(){
    return notNull;
  }

  public void setName(String newName){
    this.name = newName;
  }

  public void setNotNull(Boolean newNotNull){
    this.notNull = newNotNull;
  }

  public boolean equals(Object obj){
   return true;
  }

  public String toString(){
      return getName() + " " + getNotNull() + "\n";
  }
}
