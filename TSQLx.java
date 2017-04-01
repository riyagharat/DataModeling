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
    Table newTable = (dataBaseName, "", "");
    listOfTables.add(newTable);
  }

  public static void dropDatabase(String dataBaseName){
    for(int i = 0; i < listOfTables.size()){
      if((listOfTables.get(i).getName()).equals(dataBaseName)){
        File file = dataBaseName + ".txt";
        file.delete();
        break;
      }
    }
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(){
    
  }
  
  public static void dropDatabase(String dataBaseName){

  }

  public static void saveDatabase(String dataBaseName){

  }

  public static void loadDatabase(String dataBaseName){

  }

  public static void createTable(){

  }

  public static void dropTable(String tableName){

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
