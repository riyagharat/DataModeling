// Table Class

class Table{
  private Column firstCol;
  private Table nextTable;
  private String name;

  public Table(){

  }

  public Table(Column firstCol, String name) {
    this.firstCol = firstCol;
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
  
  public void findRecordFromWhere(String from, String where)
  {
    String attribute;
    String operator;
    String value;
    

    
    
  }
}
