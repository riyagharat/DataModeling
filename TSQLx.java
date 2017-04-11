// TSQLx.java
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StackOverflowError;

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
    File dbFile = dataBaseName + ".txt";
	dbFile.close();
  }

  public static void loadDatabase(String dataBaseName){
    Scanner fileIn = new Scanner(dataBaseName + ".txt");
	Pattern cmdPat = new Pattern.compile("^(?<cmd>t?[A-Z]+)\\s+.+$");

	while(filein.hasNextLine()) { // iterate through file, line by line
		String tempLine = in.nextLine();
		Matcher cmdMatcher = cmdPat.matcher(tempLine);
		/*if (!(cmdMatcher.matches())) { //Not sure if this is needed
			continue;
		}*/
		switch(cmdMatcher.group(1)) {
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

	} // END while(in.hasNextLine())
	return;
  } // END loadDatabase

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
