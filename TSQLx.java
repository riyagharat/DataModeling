import java.text.ParseException;
import java.io.*;
import java.text.*;
import java.util.*;
// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    Scanner reader = new Scanner(System.in);
    String userInput;
    int choice = 0;
    ArrayList<Table> listOfTables = new ArrayList<Table>();

    do{
      System.out.print("TSQLx>");
      // reads input from the console
      userInput = reader.nextLine();
      // passes the user input to the Parser
      Parser consoleInput = new Parser(userInput);
      // returns the parsed input as a choice to the main function
      choice = consoleInput.Scan();
      switch(choice){
         /*
            fromat:
               call function name
               params = (consoleInput.getArg0(),...Arg1(),...Arg2());
         */
        case 0:
            // locates an error and displays where it is in the SQL statment
            System.out.print("      ");
            for(int k = 0; k<consoleInput.getError(); k++){
               System.out.print(" ");
            }
            System.out.println("*");
            System.out.println("Error at index: "+consoleInput.getError());
            break;
         case 1:
            // Calls the create method
            // consoleInput.getArg0().get(0) is tablename or dataBaseName
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the fieldDefs
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CREATE");
            if(!(consoleInput.getArg1().size() == 0)){
              create((consoleInput.getArg0().get(0)), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            }else{
              create(consoleInput.getArg0().get(0));
            }
            break;
         case 2:
            // Calls the drop method
            // consoleInput.getArg1().get(0) is tablename
            // consoleInput.getArg0() is the database name
            // consoleInput.getArg2() is the fieldDefs
            // listOfTables is the arrayList of tables in the current database
            System.out.println("DROP");
            if(!(consoleInput.getArg1().size() == 0)){
              dropT(consoleInput.getArg1().get(0), listOfTables);
            }else{
              dropDB(consoleInput.getArg0().get(0));
            }
            break;
         case 3:
            // Calls the save method
            System.out.println("SAVE");
            saveDatabase(listOfTables);
            break;
         case 4:
            // Calls the load method
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("LOAD");
            loadDatabase(consoleInput.getArg0().get(0), listOfTables);
            break;
         case 5:
            // Calls the insert method
            // consoleInput.getArg0().get(0) is tablename
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the values
            System.out.println("INSERT");
            insert(consoleInput.getArg0().get(0), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            break;
         case 6:
            // Calls the delete method
            // consoleInput.getArg1().get(0) is the tablename
            // consoleInput.getArg2() is the conditions
            System.out.println("DELETE");
            try{
             delete(consoleInput.getArg1().get(0), consoleInput.getArg2(), listOfTables);
            }catch(ParseException e){
               e.printStackTrace();
            }
            break;
         case 7:
            // Calls the select method
            // consoleInput.getArg0() is * or conditions
            // consoleInput.getArg1() is the table name
            // consoleInput.getArg2() is the where condition
            // listOfTables is the arrayList of tables in the current database
            System.out.println("SELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables, false);
            }catch(ParseException e){
            }
            break;
         case 8:
            // Calls the dateSelect method
            System.out.println("TSELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables, true);
            }catch(ParseException e){
            }
            break;
         case 9:
            // Calls the convert method
            // consoleInput.getArg0().get(0) is the XML filename
            // consoleInput.getArg1().get(0) is the XSD filename
            // consoleInput.getArg2().get(0) is the filename to store the inserts
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CONVERT");
            String XSD = "";
            if(!(consoleInput.getArg1().size() == 0)){
              XSD = consoleInput.getArg1().get(0);
            }else{
              XSD = "";
            }
            convertXML(consoleInput.getArg0().get(0), XSD, consoleInput.getArg2().get(0), listOfTables);
            break;
         case 10:
            // Currently Autocommiting
            System.out.println("COMMIT");
            break;
         case 11:
            // Calls the input method
            // consoleInput.getArg0().get(0) is the filename to be inserted
            System.out.println("INPUT");
            inputFile(consoleInput.getArg0().get(0), listOfTables);
            break;
         case 12:
            // Prints EXIT and exits the application
            System.out.println("EXIT");
            break;
         default:
            // Prints Reject and prompts the user to enter another input on the command line
            System.out.println("Reject");
            break;

      }
    }while(choice!=12);
  }

  // Saves the database to the file
  public static void saveDatabase(ArrayList<Table> tablesList){
	tablesList.clear(); // flush tables from internal memory
	return;
  }

  // Loads the file from an existing database file
  public static void loadDatabase(String dataBaseName, ArrayList<Table> listOfTables){
	File dbFile = new File(dataBaseName + ".txt");
	// check to see if the database exists
	if (!dbFile.exists()) {
		System.out.println("ERROR: Database does not exist"); // database doesn't exist,
		return;
	}
   try{
    Scanner fileIn = new Scanner(dbFile); // create scanner to read through the file
	String userInput = "";
	int choice = 0;

    do{
      System.out.print("TSQLx Loading>");
      // reads input from the console
      userInput = fileIn.nextLine();
      // passes the user input to the Parser
      Parser consoleInput = new Parser(userInput);
      // returns the parsed input as a choice to the main function
      choice = consoleInput.Scan();
      switch(choice){
         /*
            fromat:
               call function name
               params = (consoleInput.getArg0(),...Arg1(),...Arg2());
         */
        case 0:
            // locates an error and displays where it is in the SQL statment
            System.out.print("      ");
            for(int k = 0; k<consoleInput.getError(); k++){
               System.out.print(" ");
            }
            System.out.println("*");
            System.out.println("Error at index: "+consoleInput.getError());
            break;
         case 1:
            // Calls the create method
            // consoleInput.getArg0().get(0) is tablename or dataBaseName
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the fieldDefs
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CREATE");
            if(!(consoleInput.getArg1().size() == 0)){
              create((consoleInput.getArg0().get(0)), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            }else{
              create(consoleInput.getArg0().get(0));
            }
            break;
         case 2:
            // Calls the drop method
            // consoleInput.getArg1().get(0) is tablename
            // consoleInput.getArg0() is the database name
            // consoleInput.getArg2() is the fieldDefs
            // listOfTables is the arrayList of tables in the current database
            System.out.println("DROP");
            if(!(consoleInput.getArg1().size() == 0)){
              dropT(consoleInput.getArg1().get(0), listOfTables);
            }else{
              dropDB(consoleInput.getArg0().get(0));
            }
            break;
         case 3:
            // Calls the save method
            System.out.println("SAVE");
            saveDatabase(listOfTables);
            break;
         case 4:
            // Calls the load method
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("LOAD");
            loadDatabase(consoleInput.getArg0().get(0), listOfTables);
            break;
         case 5:
            // Calls the insert method
            // consoleInput.getArg0().get(0) is tablename
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the values
            System.out.println("INSERT");
            insert(consoleInput.getArg0().get(0), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            break;
         case 6:
            // Calls the delete method
            // consoleInput.getArg1().get(0) is the tablename
            // consoleInput.getArg2() is the conditions
            System.out.println("DELETE");
            try{
             delete(consoleInput.getArg1().get(0), consoleInput.getArg2(), listOfTables);
            }catch(ParseException e){
               e.printStackTrace();
            }
            break;
         case 7:
            // Calls the select method
            // consoleInput.getArg0() is * or conditions
            // consoleInput.getArg1() is the table name
            // consoleInput.getArg2() is the where condition
            // listOfTables is the arrayList of tables in the current database
            System.out.println("SELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables, false);
            }catch(ParseException e){
            }
            break;
         case 8:
            // Calls the dateSelect method
            System.out.println("TSELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables, true);
            }catch(ParseException e){
            }
            break;
         case 9:
            // Calls the convert method
            // consoleInput.getArg0().get(0) is the XML filename
            // consoleInput.getArg1().get(0) is the XSD filename
            // consoleInput.getArg2().get(0) is the filename to store the inserts
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CONVERT");
            String XSD = "";
            if(!(consoleInput.getArg1().size() == 0)){
              XSD = consoleInput.getArg1().get(0);
            }else{
              XSD = "";
            }
            convertXML(consoleInput.getArg0().get(0), XSD, consoleInput.getArg2().get(0), listOfTables);
            break;
         case 10:
            // Currently Autocommiting
            System.out.println("COMMIT");
            break;
         case 11:
            // Calls the input method
            // consoleInput.getArg0().get(0) is the filename to be inserted
            System.out.println("INPUT");
            inputFile(consoleInput.getArg0().get(0), listOfTables);
            break;
         case 12:
            // Prints EXIT and exits the application
            System.out.println("EXIT");
            break;
         default:
            // Prints Reject and prompts the user to enter another input on the command line
            System.out.println("Reject");
            break;

      }
    }while(choice!=12);
      }catch(FileNotFoundException e){
      e.printStackTrace();
   }
	return;
  } // END loadDatabase

  // Overloaded create method, this one will create a database file with the given database name
  public static void create(String dataBaseName){
   File file = new File(dataBaseName + ".txt");
    if(file.exists()){
        System.out.println("ERROR: Database specified exists");
    //    file.delete();
    }
  }

  // Overloaded create method, this one will create a table with the given table name
  // and its field names and field definitions
  public static void create(String tableName, ArrayList<String> fieldNames, ArrayList<String> fieldDefs, ArrayList<Table> listOfTables){
    Table newTable = new Table(tableName, fieldNames, fieldDefs);
    listOfTables.add(newTable);
  }

  // Drops the table from the arraylist if it matches the table name
  public static void dropT(String tableName, ArrayList<Table> listOfTables){
    for(int i = 0; i < listOfTables.size(); i++){
      if((listOfTables.get(i).getName()).equals(tableName)){
        listOfTables.remove(i);
      }
    }
  }

  // Deletes the Database file from the system
  public static void dropDB(String dataBaseName){
    File file = new File(dataBaseName + ".txt");
    if(file.exists()){
      file.delete();
    }else{
      System.out.println("ERROR: Database specified does not exist");
    }
  }

  // The SQL insert command, inserts into the database
  public static void insert(String tableName, ArrayList<String> fields, ArrayList<String> values, ArrayList<Table> listOfTables){
	 for(int i = 0; i < listOfTables.size(); i++)
	 	if((listOfTables.get(i).getName()).equals(tableName))
			listOfTables.get(i).insert(fields, values);
			
  }

  // The SQL delete command, deletes from the table
  public static void delete(String tableName, ArrayList<String> conditions, ArrayList<Table> listOfTables)throws ParseException{
	for(int i = 0; i < listOfTables.size(); i++){
	 	if((listOfTables.get(i).getName()).equals(tableName))
			listOfTables.get(i).delete(conditions);

      
    }
  }

  // Reads if there are two files or one and accordingly calls the right method
  public static void convertXML(String XMLFileName, String XSDFileName, String inputFileName, ArrayList<Table> listOfTables){
    if((XSDFileName.equals(""))){;
      File XMLfile = new File(XMLFileName + ".xml");
      oneFile(XMLfile, inputFileName);
    }else{
      File XSDfile = new File(XSDFileName + ".xsd");
      File XMLfile = new File(XMLFileName + ".xml");
      bothFiles(XSDfile, XMLfile, inputFileName, listOfTables);
    }
  }

  // Given both an XSD file and a XML file, the XSD file is parsed and the field names and field definitions are stored
  // A table is created from that. Then the XML file is parsed and the INSERT INTO commands are generated and stored in
  // the current database under a separate table
  public static void bothFiles(File XSDfile, File XMLfile, String inputFileName, ArrayList<Table> listOfTables){
    // Stores the parse of the XSD file
    ArrayList<XSDFormat> XSDList = new ArrayList<XSDFormat>();
    // Stores the field names from the XSD file
    ArrayList<String> fieldNames = new ArrayList<String>();
    // Stores the field definitions from the XSD file
    ArrayList<String> fieldDefs = new ArrayList<String>();
    try{
      Scanner input = new Scanner(XSDfile);
      Scanner input2 = new Scanner(XMLfile);
      // Runs through and reads the XSD file
      while(input.hasNextLine()){
        String text = input.nextLine();
        // "xsd:complexType name=" will always indicate the table name
        if(text.contains("xsd:complexType name=")){
          String tablename = "";
          for(int i = (text.indexOf("=") +2); i < text.length(); i++){
            if(!(text.charAt(i) == ('\"'))){
              tablename = tablename + text.charAt(i);
            }else{
              break;
            }
          }
          // Create a XSDFormat object that can be added to the XSDList
          XSDFormat format = new XSDFormat(tablename, "", "", "", "", "");
          XSDList.add(format);
        // "xsd:element name=" will always indicate the attributeName
        }else if(text.contains("xsd:element name=")){
          int firstEqualIndex = text.indexOf("=");
          String attributeName = "";
          for(int i = firstEqualIndex+2; i < text.length(); i++){
            if(!(text.charAt(i) == ('\"'))){
              attributeName = attributeName + text.charAt(i);
            }else{
              break;
            }
          }
          // Create another XSDFormat object that will begin to store an attributeName and the
          // associated information
          XSDFormat format2 = new XSDFormat("", attributeName, "", "", "", "");
          fieldNames.add(attributeName);
          // Defines the string fieldDef which will be used during the print insert into statment
          String fieldDef = "";
          int colonIndex = 0;
          // "type=" will indicate if its a char or an integer or a number
          if(text.contains("type=")){
            colonIndex = text.indexOf(":", firstEqualIndex);
            String attributeType = "";
            for(int i = colonIndex+1; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                attributeType = attributeType + text.charAt(i);
              }else{
                break;
              }
            }
            // Rewrites the "string" tag as a "char" tag
            if(attributeType.equals("string")){
              attributeType = "char";
            }
            attributeType = attributeType.toUpperCase();
            // Begins building the field Def String
            fieldDef = fieldDef + attributeType;
            // Assigns an attributeType to the XSDFormat object
            format2.setAttributeType(attributeType);
          }
          int index = 0;
          // "maxOccurs=" will indicate the length of the attribute
          if(text.contains("maxOccurs=")){
            index = text.indexOf("=", colonIndex);
            String attributeLength = "";
            for(int i = index+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                attributeLength = attributeLength + text.charAt(i);
              }else{
                break;
              }
            }
            // Generates the fieldDef with (length)
            fieldDef = fieldDef + "(" + attributeLength + ")";
            // Assigns an attributeLength to the XSDFormat object
            format2.setAttributeLength(attributeLength);
          }
          int fractionIndex = 0;
          // "fraction=" will indicate if the Number has any decimals
          if(text.contains("fraction=")){
            fractionIndex = text.lastIndexOf("=");
            String decimals = "";
            for(int i = fractionIndex+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                decimals = decimals + text.charAt(i);
              }else{
                break;
              }
            }
            // Assigns decimals to the XSDFormat object
            format2.setFraction(decimals);
          }
          int dateIndex = 0;
          // "date=" will indicate if it is a date type
          if(text.contains("date=")){
            dateIndex = text.lastIndexOf("=");
            String dateFormat = "";
            for(int i = dateIndex+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                dateFormat = dateFormat + text.charAt(i);
              }else{
                break;
              }
            }
            // Generates the fieldDef appending the dateFormat
            fieldDef = fieldDef + dateFormat;
            // Assigns dateFormat to the XSDFormat object
            format2.setDateFormat(dateFormat);
          }
          // Adds the XSDFormat object to the XSDList
          XSDList.add(format2);
          // Adds the fieldDef object to the fieldDefs list
          fieldDefs.add(fieldDef);
        }
      }

      // Creates a table with the given field names and field definitions
      Table newTable = new Table(XSDList.get(0).getTableName(), fieldNames, fieldDefs);
      // Adds the table to listOfTables
      listOfTables.add(newTable);

      // Checks the XML file if its currently looking at a record or not
      boolean check = false;
      // Stores the list of attributeNames
      ArrayList<String> attributeNames = new ArrayList<String>();
      // Stores the associated list of attributeValues
      ArrayList<String> attributeValues = new ArrayList<String>();
      // Counter counts the number of attributes to a record and keeps incrementing until
      // it is out of a record
      int counter = 0;
      String attributes = "";
      String values = "";
      // Generates the fileStream to print to the file that was created
      PrintStream fileStream = new PrintStream(new FileOutputStream(inputFileName + ".txt", true));
        while(input2.hasNextLine()){
          String text = input2.nextLine();
          if(text.contains("<")){
            String tablename = "";
            String attributename = "";
            String value = "";
            int newIndex = 0;
            for(int i = (text.indexOf("<") +1); i < text.length(); i++){
              if((!(text.charAt(i) == ('>'))) && (!(text.charAt(i) == ('?')))){
                if(!(text.charAt(i) == ('/'))){
                  tablename = tablename + text.charAt(i);
                }else{
                  check = false;
                  break;
                }
               // Checks if the next character after ">" exists, if so then it is not a table
              // declaration, but is a attribute with a value
              }else if((text.indexOf(">") + 1) < text.length()){
                if((((text.charAt(text.indexOf(">") + 1)) >= 'a') && ((text.charAt(text.indexOf(">") + 1)) <= 'z'))
                    || (((text.charAt(text.indexOf(">") + 1)) >= 'A') && ((text.charAt(text.indexOf(">") + 1)) <= 'Z'))
                    || (((text.charAt(text.indexOf(">") + 1)) >= '0') && ((text.charAt(text.indexOf(">") + 1)) <= '9'))){
                  attributename = tablename;
                  tablename = "";
                  for(int j = (text.indexOf(">") +1); j < text.length(); j++){
                    if((!(text.charAt(j) == ('<')))){
                      value = value + text.charAt(j);
                    }else{
                      break;
                    }
                  }
                }
                check = true;
                break;
              }else{
                break;
              }
            }
            if(check == true){
              // Continues to append attributes and their values while it is still in a record
              counter++;
              attributeNames.add(attributename);
              attributeValues.add(value);
            }else{
              // Once it is no longer in a record, it builds the string for both the attributeNames
              // and the attributeValues and the creates an INSERT INTO command which is inserted into
              // a file
              if(attributes.length() > 0){
                attributes = attributes.substring(1, attributes.length()-1);
              }

              if(values.length() > 0){
                values = values.substring(1, values.length()-1);
              }
              if(!(attributes.equals(""))){
                fileStream.println("INSERT INTO " + XSDList.get(0).getTableName() + " (" + attributes
                    + ") VALUES (" + values + ");");
              }

              counter = 0;
              attributeNames.clear();
              attributeValues.clear();
            }
          }

          attributes = "";
          for(int i = 0; i < attributeNames.size(); i++){
            attributes = attributes + " " + attributeNames.get(i) + ",";
          }

          values = "";
          for(int i = 0; i < attributeValues.size(); i++){
            values = values + " " + attributeValues.get(i) + ",";
          }
        }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
  }

  // Given only an XML file, the XML file is parsed and the INSERT INTO commands are generated and stored in
  // the current database under a separate table that should be existing
  public static void oneFile(File XMLfile, String inputFileName){
    try{
      Scanner input = new Scanner(XMLfile);
      String tablename = "";
      String attributename = "";
      String value = "";
      // Checks the XML file if its currently looking at a record or not
      boolean check = false;
      // Stores the list of attributeNames
      ArrayList<String> attributeNames = new ArrayList<String>();
      // Stores the associated list of attributeValues
      ArrayList<String> attributeValues = new ArrayList<String>();
      // Stores the associated list of table names
      ArrayList<String> tableNames = new ArrayList<String>();
      // Counter counts the number of attributes to a record and keeps incrementing until
      // it is out of a record
      int counter = 0;
      String attributes = "";
      String values = "";
      // Generates the fileStream to print to the file that was created
      PrintStream fileStream = new PrintStream(new FileOutputStream(inputFileName + ".txt", true));
      while(input.hasNextLine()){
        tablename = "";
        attributename = "";
        value = "";
        String text = input.nextLine();
        if(text.contains("<")){
          tablename = "";
          attributename = "";
          value = "";
          int newIndex = 0;
          for(int i = (text.indexOf("<") +1); i < text.length(); i++){
            if((!(text.charAt(i) == ('>'))) && (!(text.charAt(i) == ('?')))){
              if(!(text.charAt(i) == ('/'))){
                tablename = tablename + text.charAt(i);
              }else{
                check = false;
                break;
              }
            // Checks if the next character after ">" exists, if so then it is not a table
            // declaration, but is a attribute with a value
            }else if((text.indexOf(">") + 1) < text.length()){
              if((((text.charAt(text.indexOf(">") + 1)) >= 'a') && ((text.charAt(text.indexOf(">") + 1)) <= 'z'))
                  || (((text.charAt(text.indexOf(">") + 1)) >= 'A') && ((text.charAt(text.indexOf(">") + 1)) <= 'Z'))
                  || (((text.charAt(text.indexOf(">") + 1)) >= '0') && ((text.charAt(text.indexOf(">") + 1)) <= '9'))){
                attributename = tablename;
                tablename = "";
                for(int j = (text.indexOf(">") +1); j < text.length(); j++){
                  if((!(text.charAt(j) == ('<')))){
                    value = value + text.charAt(j);
                  }else{
                    break;
                  }
                }
              }
              check = true;
              break;
            }else{
              break;
            }
          }
          if(check == true){
            // Continues to append attributes and their values while it is still in a record
            counter++;
            attributeNames.add(attributename);
            attributeValues.add(value);
          }else{
            // Once it is no longer in a record, it builds the string for both the attributeNames
            // and the attributeValues and the creates an INSERT INTO command which is inserted into
            // a file
            if(attributes.length() > 0){
              attributes = attributes.substring(1, attributes.length()-1);
            }

            if(values.length() > 0){
              values = values.substring(1, values.length()-1);
            }

            if(!(attributes.equals(""))){
              fileStream.println("INSERT INTO " + tableNames.get(0) + " (" + attributes
                  + ") VALUES (" + values + ");");
            }

            counter = 0;
            tableNames.clear();
            attributeNames.clear();
            attributeValues.clear();
          }
        }
        attributes = "";
        for(int i = 0; i < attributeNames.size(); i++){
          attributes = attributes + " " + attributeNames.get(i) + ",";
        }

        values = "";
        for(int i = 0; i < attributeValues.size(); i++){
          values = values + " " + attributeValues.get(i) + ",";
        }
        if(!(tablename.equals(""))){
          tableNames.add(tablename);
        }
      }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
  }

  public static void inputFile(String fileName, ArrayList<Table> listOfTables){
    File file = new File((fileName + ".txt"));
    if(file.exists()){
      try{
        Scanner input = new Scanner(file);
        int choice = 0;
        while(input.hasNextLine()){
          String text = input.nextLine();
          Parser fileInput = new Parser(text);
          choice = fileInput.Scan();
          switch(choice){
            case 5:
               // Calls the insert method
               // fileInput.getArg0().get(0) is tablename
               // fileInput.getArg1() is the Column names
               // fileInput.getArg2() is the values
               System.out.println("INSERT");
               insert(fileInput.getArg0().get(0), fileInput.getArg1(), fileInput.getArg2(), listOfTables);
               break;
          }
        }
      }catch(FileNotFoundException e){
        e.printStackTrace();
      }
    }else{
      System.out.println("ERROR: File specified does not exist");
    }
  }

  public static void select(ArrayList<String> PrintList, ArrayList<String> TableNamer, ArrayList<String> Wheres,
    ArrayList<Table> listOfTables, boolean dateYes) throws ParseException{ // Begin select statement
    String TableName = TableNamer.get(0); //retrieve table name from arraylist
    int TableIndex = 0;//By default we're gonna start at 0, this'll be changed later
    String ColumnName = "";//initialize these to nothing
    String Relational = "";//that way the code doesn't get tripped up later
    String Comparator = "";//if there's a where condition, these'll get changed
    int Case = 0;//If there's a where, this int will determine what part of the switch we do
    int ColumnChecker = 0;
    if(Wheres.size() > 0){//if there is a where condition, get the parameters
       ColumnName = Wheres.get(0);//The name of the column we want to compare to
       Relational = Wheres.get(1);//What type of relational operator we have
       Comparator = Wheres.get(2);//the number or string that we are comparing to
    }

    if(Wheres.size() > 0){ //switch case based on comparison operator
       if(Relational.equals("<"))//Check for less than case
          Case = 1;
       else if(Relational.equals(">"))//Check for greater than case
          Case = 2;
       else if(Relational.equals("="))//Check for equal to case
          Case = 3;
       else if(Relational.equals("<>"))//Check for 'not equal to' case
          Case = 4;
       else if(Relational.equals("<="))//Less than or equal to
          Case = 5;
       else if(Relational.equals(">="))//Greater than or equal to
          Case = 6;
       else{
          System.out.print("Error, invalid relational operator.");//If it's none of the above, then we have a problem
          return;
       }
    }

    while(!listOfTables.get(TableIndex).equals(TableName)){//Checking if the table exists, also why we initialized to 0
       TableIndex++;//Increase the index until we get a hit
       if(TableIndex == listOfTables.size()){//If you've gone too far, give up
          System.out.println("Table not found.");//Report the error and return
          return;
       }
    }

    if(Wheres.size() == 0){ //if there is no where condition
       if(PrintList.get(0).equals("*")){ //SELECT * (everything)
          printEverything(TableIndex, listOfTables, dateYes);//Calls the statement that prints indiscriminately
          return;
       }else{ //SELECT [columns]
          ArrayList indices = new ArrayList<Integer>(); //columns we want
          for(int k = 0; k < PrintList.size(); k++){//so long as there are more columns we want
             int ComlumnChecker = 0;//We're gonna cycle through columns, so we start at 0
             while(!listOfTables.get(TableIndex).list.get(ColumnChecker).equals(PrintList.get(k))){//while the column is not equal to what we want, increment
                ColumnChecker++;//Increase the column we're looking for
                if(ColumnChecker == listOfTables.get(TableIndex).list.size()){//return if a column doesn't exist
                   System.out.println("Specified column not found.");
                   return;
                }
             indices.add(ColumnChecker); //the specified column was found - add the column we want
             }//end while
          }//end for loop
          printSomething(TableIndex, indices, listOfTables, dateYes);//Runs the print statement that selects columns
       }//end else
    }else{//Where clause
       ArrayList FilteredColumns = new ArrayList<Integer>();//Create a list of the columns we want
       int ColumnNumber = 0;
       switch(Case){
          case 1://less than case
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){//error if String type
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); //4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy"); //2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); //formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//Versus if the date is stored with only 2 year characters
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) > 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
		   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) > 0){
                      FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                   }
                }
             }
             break;
          case 2://greater than case
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){//error if String type
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");//4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");//2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//Versus if the date is stored with only 2 year characters
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) < 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) < 0){
                   FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                }
             }
             break;
          case 3://equal to
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");//4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");//2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//If the date is stored with 2 characters in year
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) == 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().equals(Comparator)){
                   FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                }
             }
             break;
          case 4://not equal
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");//4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");//2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//If the date is stored with 2 characters in year
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) != 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().equals(Comparator)){
                   FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                }
             }
             break;
          case 5://less than or equal to?
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
   	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){//error if String type
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");//4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");//2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//If the date is stored with 2 characters in year
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) >= 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
                   }
                   else{
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) >= 0){
                      FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                   }
                }
             }
             break;
          case 6://greater than or equal to?
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
	     if(Comparator.indexOf(".") >= 0 && listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Integer")) {
	     	System.out.println("Error, incompatible type comparison");
		return;
	     }
	     if (Comparator.contains("[a-zA-Z]+") == true && !listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){
		System.out.println("Error, incompatible type comparison");
		return;
	     } 
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("char")){//error if String type
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equalsIgnoreCase("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");//4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");//2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);//If the date is stored with 4 characters in year
                      }else{
                         rowValue = otherParser.parse(theDate);//If the date is stored with 2 characters in year
                      }
                      formattedRowValue = formatter.format(rowValue);//Format our string
                      if(Comparator.length() > 8){//Repeat for our Comparator
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) <= 0){//And this checks if the where condition is fulfilled
                         FilteredColumns.add(RowNumber);
                      }
                   }
                   }
                   else{
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) <= 0){
                      FilteredColumns.add(RowNumber);//This checks for any other case such as integer
                   }
                }
             }
             break;
       }
       if(PrintList.get(0).equals("*")){//This calls the select-all with a where printing function
          whereAll(TableIndex, FilteredColumns, listOfTables, dateYes);
          return;
       }else{
          ArrayList indices = new ArrayList<Integer>();//Checks specific columns
          int checking = 0;
          for(int k = 0; k < PrintList.size(); k++){//checking for printed columns
             int ColumnChecking = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnChecking).equals(PrintList.get(k))){//Cycle until the column is equal to the value in the PrintList
                ColumnChecking++;
                if(ColumnChecking == listOfTables.get(TableIndex).list.size()){
                   System.out.println("Error, column not found");
                   return;
                }
             indices.add(ColumnChecking);
             }//end while
             k++;
          }//end forloop
       whereSelective(TableIndex, FilteredColumns, indices, listOfTables, dateYes);
       }//end else
    }
  }

  public static void whereAll(int TableIndex, ArrayList filteredColumns, ArrayList<Table> listOfTables, boolean dateYes){
     if(dateYes == true){
        //remind me to put the names of the columns up here some time
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
           for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//and this loop is getting those records
              if(filteredColumns.contains(j)){
                 if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for empty
                    System.out.print("\t\t");
                 }else{
                    System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                 }
              }
           }
           System.out.print("\n");
        }
     }else{
       //remind me to put the names of the columns up here some time
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
           for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//and this loop is getting those records
              if(filteredColumns.contains(j)){
                 if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for empty
                    System.out.print("\t\t");
                 }else{
                    System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                 }
              }
           }
           System.out.print("\n");
        }
      }
  }

  public static void whereSelective(int TableIndex, ArrayList filteredColumns, ArrayList<Integer> indices, ArrayList<Table> listOfTables, boolean dateYes){

     if(dateYes == true){
        //remind me to put the names of the columns up here some time
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
           if(indices.contains(i)){
              for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//and here we got some rows
                 if(filteredColumns.contains(j)){
                    if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for empty
                       System.out.print("\t\t");
                    }else{
                       System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                    }
                 }
              }
              System.out.print("\n");
           }
        }
     }else{
       //remind me to put the names of the columns up here some time
	int j = 0;
	int i = 0;
        for(j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//this for loop's checking for column size
           if(indices.contains(i)){
              for(i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//and here we got some rows
                 if(filteredColumns.contains(j)){
                    if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for empty
                       System.out.print("\t\t");
                    }else{
                       System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                    }
                 }
              }
              System.out.print("\n");
           }
        }
     return;
  }
  }

  public static void printEverything(int TableIndex, ArrayList<Table> listOfTables, boolean dateYes){
     //remind me to put the names of the columns up here some time
     if(dateYes == true){
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of columns
           for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size()-1; j++){//and this loop is getting those records
              if(listOfTables.get(TableIndex).list.get(i).list.get(j) == null){//just tab twice for empty
                 System.out.print("\t\t");
              }else{
                 System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
              }
           }
           System.out.print("\n");
        }
     }else{
	int i = 0;
	int j = 0;
        for(j = 0; j < listOfTables.get(TableIndex).list.get(j).list.size()-1; j++){//this for loop's checking for number of columns minus date
           for(i = 0; i < listOfTables.get(TableIndex).list.size()-1; i++){//and this loop is getting those records
              if(listOfTables.get(TableIndex).list.get(j).list.get(i) == null){//just tab twice for empty
                 System.out.print("\t\t");
              }else{
                 System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
              }
           }
           System.out.print("\n");
        }
        }
  }

  public static void printSomething(int TableIndex, ArrayList<Integer> indices, ArrayList<Table> listOfTables, boolean dateYes){
     if(dateYes == true){
        //remind me to put the names of the columns up here some time
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
           if(indices.contains(i)){
              for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//and here we got some rows
                    if(listOfTables.get(TableIndex).list.get(i).list.get(j) == null){//just tab twice for rows
                       System.out.print("\t\t");
                    }else{
                       System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                    }
              }
              System.out.print("\n");
           }
        }
     }else{
        //remind me to put the names of the columns up here some time
        for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
           if(indices.contains(i)){
              for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); j++){//and here we got some rows
                    if(listOfTables.get(TableIndex).list.get(i).list.get(j) == null){//just tab twice for rows
                       System.out.print("\t\t");
                    }else{
                       System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                    }
              }
              System.out.print("\n");
           }
        }
     }
  }

  public static void dateSelect(){

  }

  static class XSDFormat{
    private String tableName;
    private String attributeName;
    private String attributeType;
    private String attributeLength;
    private String dateFormat;
    private String fraction;

    // Param constructors
    public XSDFormat(){
    }

    public XSDFormat(String tableName, String attributeName, String attributeType,
      String attributeLength, String dateFormat, String fraction){
      this.tableName = tableName;
      this.attributeName = attributeName;
      this.attributeType = attributeType;
      this.attributeLength = attributeLength;
      this.dateFormat = dateFormat;
      this.fraction = fraction;
    }

    //Methods for the XSDFormat Class

    // Get Methods for XSDFormat Class
    public String getTableName(){
      return tableName;
    }

    public String getAttributeName(){
      return attributeName;
    }

    public String getAttributeType(){
      return attributeType;
    }

    public String getAttributeLength(){
      return attributeLength;
    }

    public String getDateFormat(){
      return dateFormat;
    }

    public String getFraction(){
      return fraction;
    }

    // Set Methods for XSDFormat Class
    public void setTableName(String newTableName){
      this.tableName = newTableName;
    }

    public void setAttributeName(String newAttributeName){
      this.attributeName = newAttributeName;
    }

    public void setAttributeType(String newAttributeType){
      this.attributeType = newAttributeType;
    }

    public void setAttributeLength(String newAttributeLength){
      this.attributeLength = newAttributeLength;
    }

    public void setDateFormat(String newDateFormat){
      this.dateFormat = newDateFormat;
    }

    public void setFraction(String newFraction){
      this.fraction = newFraction;
    }

    public boolean equals(Object obj){
       return true;
    }

    public void displayTokenAll(){
       System.out.println("Table Name: " + tableName + "      Attribute Name: " + attributeName
        + "    Attribute Type: " +  attributeType + "      Attribute Length: " + attributeLength
        + "    Date Format " + dateFormat + "     Fraction: " + fraction);
    }

    public String toString(){
      return "\n" + getTableName() + " " + getAttributeName() + "  "+ getAttributeType() + "  "
        + getAttributeLength() + "  " + getDateFormat() + "  " + getFraction()+ "\n";
    }
  }

  static class XMLParse{
    private String tableName;
    private String attributeName;
    private String value;

    // Param constructors
    public XMLParse(){
    }

    public XMLParse(String tableName, String attributeName, String value){
      this.tableName = tableName;
      this.attributeName = attributeName;
      this.value = value;
    }

    //Methods for the XMLParse Class

    // Get Methods for XMLParse Class
    public String getTableName(){
      return tableName;
    }

    public String getAttributeName(){
      return attributeName;
    }

    public String getValue(){
      return value;
    }

    // Set Methods for XMLParse Class
    public void setTableName(String newTableName){
      this.tableName = newTableName;
    }

    public void setAttributeName(String newAttributeName){
      this.attributeName = newAttributeName;
    }

    public void setValue(String newValue){
      this.value = newValue;
    }

    public boolean equals(Object obj){
       return true;
    }

    public void displayTokenAll(){
       System.out.println("Table Name: " + tableName + "      Attribute Name: " + attributeName
        + "    Value: " +  value);
    }

    public String toString(){
      return "\n" + getTableName() + " " + getAttributeName() + "  "+ getValue() + "\n";
    }
  }
}
