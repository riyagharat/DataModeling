// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    
  }
  public static void createDatabase(String dataBaseName){
    File file = dataBaseName + ".txt";
    file.open();
  }

  public static void dropDatabase(String dataBaseName){
    File file = dataBaseName + ".txt";
    file.delete();
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(){
    // need to have the columns set up here
    Table newTable = (tableName, "", "");
    listOfTables.add(newTable);
  }

  public static void createTable(){

  }

  public static void dropTable(String tableName){
    for(int i = 0; i < listOfTables.size()){
      if((listOfTables.get(i).getName()).equals(tableName)){
        listOfTables.get(i).remove();
      }
    }
  }

  public static void SQLInsert(){

  }

  public static void convertXML(){

  }

  public static void inputFile(){

  }

  public static void deleteFromTable(){

  }

  public static void simpleSelect(){

  }

  public static void dateSelect(){

  }

  public static void dateSelectComplicated(){
    
  }
}
