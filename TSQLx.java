// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    String userInput = args[0];

    while(true){
      switch(userInput){
        case " ":
          break;
      }
    }
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
    File file = dataBaseName + ".txt";
    file.close();
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(String tableName){
    Table newTable = (tableName, "", "");
    listOfTables.add(newTable);
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
