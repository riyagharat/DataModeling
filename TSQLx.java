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

  public static void commitCmd(String dataBaseName, ArrayList<String> cmdList){ //writing to DB
	File dbFile = dataBaseName + ".txt";

	FileWriter dbWriter  = new FileWriter(dbFile, true); // the true sets it to append
	int i;

	for (i = 0; i < cmdList.size(); i++) {
		dbWriter.write(cmdList.get(i));
	}
	dbWriter.close();
	return;
  }

  public static void saveDatabase(String dataBaseName, ArrayList<String> cmdList){ //writing to DB and closing DB
    File dbFile = dataBaseName + ".txt";

	FileWriter dbWriter  = new FileWriter(dbFile, true); // the true sets it to append
	int i;

	for (i = 0; i < cmdList.size(); i++) {
		dbWriter.write(cmdList.get(i));
	}
	dbWriter.close();
	dbFile.close();
	return;
  }

  public static void loadDatabase(String dataBaseName){
	File dbFile = new File(dataBaseName + ".txt");
	if (!dbFile.exists()) { // make sure the db that is trying to be loaded exists
		System.out.println("ERROR: Database does not exist");
		return;
	}
    Scanner fileIn = new Scanner(dbFile);
	Pattern cmdPat = new Pattern.compile("^(?<cmd>t?[A-Za-z]+).+$");

	while(fileIn.hasNextLine()) { // iterate through file, line by line
		String tempLine = in.nextLine();
		Matcher cmdMatcher = cmdPat.matcher(tempLine);
		
		switch(cmdMatcher.group("cmd")) { // decide which sql cmd is being read
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
