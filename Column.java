// Col class
class Column{
  private List<Row> list;
  private Column nextColumn;
  private String name;

  public Column(){

  }

  public Column(List<Row> list, String name) {
    this.list = list;
    this.name = name;
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
}
