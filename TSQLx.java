// TSQLx.java
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StackOverflowError;
import java.io.FileWriter;
import java.io.IOException;

public class TSQLx{
	public ArrayList<Table> tableList = new ArrayList<Table>();

  public static void main(String [] args){
    Console reader = System.console();
    String userInput = reader.readLine();
    Parser consoleInput = new Parser(userInput);
    int choice = 0;

    do{
      choice = consoleInput.scan();
      switch(choice){
        /*
        case 1: // createDatabase
                break;
        case 2: // dropDatabase
                break;
        case 3: // saveDatabase
                break;
        case 4: // loadDatabase
                break;
        */
        case 0:
            System.out.println("Reject");
            break;
         case 1:
            System.out.println("CREATE");
            create();
            break;
         case 2:
            System.out.println("DROP");
            drop();
            break;
         case 3:
            System.out.println("SAVE");
            saveDatabase();
            break;
         case 4:
            System.out.println("LOAD");
            loadDatabase();
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

  public static void commitCmd(){ // writes & saves to external memory. DOES NOT flush internal memory
	System.out.println("Your commands have been committed");
	
	return;
  }

  public static void saveDatabase(String dataBaseName){ // writes to external memory and saves external memory. Flushes internal memory

	tableList = new ArrayList<Table>(); // flush tables from internal memory

	return;
  }

  public static void loadDatabase(String dataBaseName){
	File dbFile = new File(dataBaseName + ".txt");
	if (!dbFile.exists()) { // check to see if the database exists
		System.out.println("ERROR: Database does not exist");
		return;
	}
    Scanner fileIn = new Scanner(dbFile);
	Pattern cmdPat = new Pattern.compile("^(?<cmd>[A-Za-z]+)\\s+.+$"); // Regex to isolate the command
	String tempLine = "";

	while(fileIn.hasNextLine()) { // iterate through file, line by line
		String tempLine = in.nextLine(); // take in line from file
		Matcher cmdMatcher = cmdPat.matcher(tempLine);
		
		switch(cmdMatcher.group("cmd")) { // decide which sql cmd is being parsed
			case "CREATE":
				
				break;

			case "DROP":
				
				break;

			case "INSERT":
				
				break;

			case "DELETE":
				
				break;

			case "SELECT":
				
				break;

			case "tSELECT":
				
				break;

			case "CONVERT":
				
				break;

		} // END switch(cmdStr)

	} // END while(fileIn.hasNextLine())
	return;
  } // END loadDatabase

  public static void create(){
>>>>>>> refs/remotes/origin/master
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
