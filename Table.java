// Table Class

class Table{
  private ArrayList<Column> listOfColumns;
  private String name;

  public Table(){

  }

  public Table(ArrayList<Column> listOfColumns, String name) {
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
