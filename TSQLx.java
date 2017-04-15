// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void create(){
    // need to have the columns set up here
    Table newTable = (tableName, "", "");
    listOfTables.add(newTable);
  }

  public static void drop(String tableName){
    // drop database
    File file = dataBaseName + ".txt";
    file.delete();
    
    // drop tables
    for(int i = 0; i < listOfTables.size()){
      if((listOfTables.get(i).getName()).equals(tableName)){
        listOfTables.get(i).remove();
      }
    }
  }

  public static void insert(){

  }
  
  public static void delete(){
    
  }

  public static void convertXML(){

  }

  public static void inputFile(){

  }

  public static void select(){

  }

  public static void dateSelect(){

  }
}
