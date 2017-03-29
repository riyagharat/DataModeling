// Table Class

class Table{
  private List<Column> list;
  private Table nextTable;
  private String name;

  public Table(){

  }

  public Table(List<Column> list, String name) {
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
      return getName() + "\n";
  }
}
