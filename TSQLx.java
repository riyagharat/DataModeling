// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    Scanner reader = new Scanner(System.in);
    String userInput;
    int choice = 0;

    do{
      System.out.print("TSQLx>");
      userInput = reader.nextLine();
      Parser consoleInput = new Parser(userInput);
      choice = consoleInput.Scan();
      switch(choice){
         /*
            fromat:
               call function name
               params = (consoleInput.getArg0(),...Arg1(),...Arg2());
         */
        case 0:
            System.out.print("      ");
            for(int k = 0; k<consoleInput.getError(); k++){
               System.out.print(" ");
            }
            System.out.println("*");
            break;
         case 1:
            System.out.println("CREATE");
            create();
            break;
         case 2:
            System.out.println("DROP");
            drop("q");
            break;
         case 3:
            System.out.println("SAVE");
            saveDatabase(consoleInput.getArg0().get(0));
            break;
         case 4:
            System.out.println("LOAD");
            loadDatabase("q");
            break;
         case 5:
            System.out.println("INSERT");
            insert();
            break;
         case 6:
            System.out.println("DELETE");
            delete();
            break;
         case 7:
            System.out.println("SELECT");
            break;
         case 8:
            System.out.println("TSELECT");
            break;
         case 9:
            System.out.println("CONVERT");
            convertXML();
            break;
         case 10:
            System.out.println("COMMIT");
            break;
         case 11:
            System.out.println("INPUT");
            inputFile();
            break;
         case 12:
            System.out.println("EXIT");
            break;
         default:
            System.out.println("Reject");
            break;
        
      }
    }while(choice!=12);
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
