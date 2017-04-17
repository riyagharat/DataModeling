import java.text.ParseException;
// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    Scanner reader = new Scanner(System.in);
    String userInput;
    int choice = 0;

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
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("SAVE");
            saveDatabase(consoleInput.getArg0().get(0));
            break;
         case 4:
            // Calls the load method
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("LOAD");
            loadDatabase(consoleInput.getArg0().get(0));
            break;
         case 5:
            // Calls the insert method
            // consoleInput.getArg0().get(0) is tablename
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the values
            System.out.println("INSERT");
            insert(consoleInput.getArg0().get(0), consoleInput.getArg1(), consoleInput.getArg2());
            break;
         case 6:
            // Calls the delete method
            // consoleInput.getArg1().get(0) is the tablename
            // consoleInput.getArg2() is the conditions
            System.out.println("DELETE");
            delete(consoleInput.getArg1().get(0), consoleInput.getArg2());
            break;
         case 7:
            // Calls the select method
            // consoleInput.getArg0() is * or conditions
            // consoleInput.getArg1() is the table name
            // consoleInput.getArg2() is the where condition
            // listOfTables is the arrayList of tables in the current database
            System.out.println("SELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            }catch(ParseException e){
            }
            break;
         case 8:
            // Calls the dateSelect method
            System.out.println("TSELECT");
            break;
         case 9:
            // Calls the convert method
            // consoleInput.getArg0().get(0) is the XML filename
            // consoleInput.getArg1().get(0) is the XSD filename
            // consoleInput.getArg2().get(0) is the filename to store the inserts
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CONVERT");
            String XSD = "";
            if(!(getArg1.size() == 0)){
              XSD = consoleInput.getArg1().get(0)
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
            inputFile(consoleInput.getArg0().get(0));
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
  public static void saveDatabase(String dataBaseName){
	tableList = new ArrayList<Table>(); // flush tables from internal memory
	return;
  }

  // Loads the file from an existing database file
  public static void loadDatabase(String dataBaseName){
	File dbFile = new File(dataBaseName + ".txt");

	if (!dbFile.exists()) { // check to see if the database exists
		System.out.println("ERROR: Database does not exist");
		return;
	}
    Scanner fileIn = new Scanner(dbFile);
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
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("SAVE");
            saveDatabase(consoleInput.getArg0().get(0));
            break;
         case 4:
            // Calls the load method
            // consoleInput.getArg0().get(0) is the database name
            System.out.println("LOAD");
            loadDatabase(consoleInput.getArg0().get(0));
            break;
         case 5:
            // Calls the insert method
            // consoleInput.getArg0().get(0) is tablename
            // consoleInput.getArg1() is the Column names
            // consoleInput.getArg2() is the values
            System.out.println("INSERT");
            insert(consoleInput.getArg0().get(0), consoleInput.getArg1(), consoleInput.getArg2());
            break;
         case 6:
            // Calls the delete method
            // consoleInput.getArg1().get(0) is the tablename
            // consoleInput.getArg2() is the conditions
            System.out.println("DELETE");
            delete(consoleInput.getArg1().get(0), consoleInput.getArg2());
            break;
         case 7:
            // Calls the select method
            // consoleInput.getArg0() is * or conditions
            // consoleInput.getArg1() is the table name
            // consoleInput.getArg2() is the where condition
            // listOfTables is the arrayList of tables in the current database
            System.out.println("SELECT");
            try{
              select(consoleInput.getArg0(), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            }catch(ParseException e){
            }
            break;
         case 8:
            // Calls the dateSelect method
            System.out.println("TSELECT");
            break;
         case 9:
            // Calls the convert method
            // consoleInput.getArg0().get(0) is the XML filename
            // consoleInput.getArg1().get(0) is the XSD filename
            // consoleInput.getArg2().get(0) is the filename to store the inserts
            // listOfTables is the arrayList of tables in the current database
            System.out.println("CONVERT");
            String XSD = "";
            if(!(getArg1.size() == 0)){
              XSD = consoleInput.getArg1().get(0)
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
            inputFile(consoleInput.getArg0().get(0));
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

	return;
  } // END loadDatabase

  // Overloaded create method, this one will create a database file with the given database name
  public static void create(String dataBaseName){
   File file = new File(dataBaseName + ".txt");
  }
  
  // Overloaded create method, this one will create a table with the given table name
  // and its field names and field definitions
  public static void create(String dataBaseName, ArrayList<String> fieldNames, ArrayList<String> fieldDefs){
    Table newTable = new Table(tableName, fieldNames, fieldDefs);
    listOfTables.add(newTable)
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
    file.delete();
  }

  // The SQL insert command, inserts into the database
  public static void insert(String tableName, ArrayList<String> fields, ArrayList<String> values){

  }

  // The SQL delete command, deletes from the table
  public static void delete(String tableName, ArrayList<String> conditions){

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

  public static void inputFile(String fileName){
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
               insert(fileInput.getArg0().get(0), fileInput.getArg1(), fileInput.getArg2());
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
    ArrayList<Table> listOfTables) throws ParseException{ // Begin select statement
    String TableName = TableNamer.get(0); //retrieve table name from arraylist
    int TableIndex = 0;
    String ColumnName = "";
    String Relational = "";
    String Comparator = "";
    int Case;
    if(Wheres.size() > 0){//if there is a where condition, get the parameters
       ColumnName = Wheres.get(0);
       Relational = Wheres.get(1);
       Comparator = Wheres.get(2);
    }

    if(Wheres.size() > 0){ //switch case based on comparison operator
       if(Relational.equals("<"))
          Case = 1;
       if(Relational.equals(">"))
          Case = 2;
       if(Relational.equals("="))
          Case = 3;
       if(Relational.equals("<>"))
          Case = 4;
       if(Relational.equals("<="))
          Case = 5;
       if(Relational.equals(">="))
          Case = 6;
    }

    while(!listOfTables.get(TableIndex).equals(TableName)){//Checking if the table exists
       TableIndex++;
       if(TableIndex == listOfTables.size()){//If you've gone too far, give up
          System.out.println("Table not found.");
          return;
       }
    }

    if(Wheres.size() == 0){ //if there is no where condition
       if(PrintList.get(0).equals("*")){ //SELECT * (everything)
          printEverything(TableIndex, listOfTables);
          return;
       }else{ //SELECT [columns]
          ArrayList indices = new ArrayList<Integer>(); //columns we want
          for(int k = 0; k < PrintList.size(); k++){//so long as there are more columns we want
             int Persona = 0;
             while(!listOfTables.get(TableIndex).list.get(Persona).equals(PrintList.get(k))){//while the column is not equal to what we want, increment
                Persona++;
                if(Persona == listOfTables.get(TableIndex).list.size()){//return if a column doesn't exist
                   System.out.println("Specified column not found.");
                   return;
                }
             indices.add(Persona); //the specified column was found - add the column we want
             }//end while
          }//end for loop
          printSomething(TableIndex, indices, listOfTables);
       }//end else
    }else{//Where clause
       ArrayList FilteredColumns = new ArrayList<Integer>();
       int ColumnNumber = 0;
       switch(Case){
          case 1://less than case
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))//while we are in the wrong column, move
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){//finding the right row
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("String")){//error if String type
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){//checking for dates
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");//check for date format
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); //4 digit years
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy"); //2 digit years
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); //formatted for comparison
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;//going to compare the dates as strings
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) > 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) > 0){
                      FilteredColumns.add(RowNumber);
                   }
                }
             }
             break;
          case 2://greater than? check later
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("String")){
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) < 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }else{
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) < 0){
                      FilteredColumns.add(RowNumber);
                   }
                }
             }
             break;
          case 3://equal to
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) == 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().equals(Comparator)){
                   FilteredColumns.add(RowNumber);
                }
             }
             break;
          case 4://not equal
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) != 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().equals(Comparator)){
                   FilteredColumns.add(RowNumber);
                }
             }
             break;
          case 5://less than or equal to?
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("String")){
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) >= 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                   }
                   else{
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) >= 0){
                      FilteredColumns.add(RowNumber);
                   }
                }
             }
             break;
          case 6://greater than or equal to?
             ColumnNumber = 0;
             while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
                ColumnNumber++;
             for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
                if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("String")){
                   System.out.println("Error. Cannot compare strings with less than.");
                   return;
                }
                else if(listOfTables.get(TableIndex).list.get(ColumnNumber).getType().equals("Date")){
                   if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                      System.out.println("Incorrect Date Format.");
                      return;
                   }
                   else{
                      SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                      SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                      String formattedDate = formatter.format(Comparator);
                      String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData());
                      Date rowValue;
                      Date tempDate;
                      String formattedRowValue;
                      if(theDate.length() > 8){
                         rowValue = parser.parse(theDate);
                      }else{
                         rowValue = otherParser.parse(theDate);
                      }
                      formattedRowValue = formatter.format(rowValue);
                      if(Comparator.length() > 8){
                         tempDate = parser.parse(Comparator);
                      }else{
                         tempDate = otherParser.parse(Comparator);
                      }
                      Comparator = formatter.format(tempDate);
                      if(formattedRowValue.compareTo(Comparator) <= 0){
                         FilteredColumns.add(RowNumber);
                      }
                   }
                   }
                   else{
                   if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).getData().compareTo(Comparator) <= 0){
                      FilteredColumns.add(RowNumber);
                   }
                }
             }
             break;
       }
       if(PrintList.get(0).equals("*")){
          please(TableIndex, FilteredColumns, listOfTables);
          return;
       }else{
          ArrayList indices = new ArrayList<Integer>();
          int checking = 0;
          for(int k = 0; k < PrintList.size(); k++){
             int Persona = 0;
             while(!listOfTables.get(TableIndex).list.get(k).equals(Wheres.get(Persona))){
                Persona++;
                if(Persona == listOfTables.get(TableIndex).list.size()){
                   System.out.println("Excuse me, WHAT column?");
                   return;
                }
             indices.add(Persona);
             }//end while
             k++;
          }//end forloop

       }//end else
    }
  }

  public static void please(int TableIndex, ArrayList helpMe, ArrayList<Table> listOfTables){
     //remind me to put the names of the columns up here some time
     for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
        for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); i++){//and this loop is getting those records
           if(helpMe.contains(j)){
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

  public static void AbbassiWhy(int TableIndex, ArrayList helpMe, ArrayList<Integer> indices, ArrayList<Table> listOfTables){
  //remind me to put the names of the columns up here some time
     for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
        if(indices.contains(i)){
           for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); i++){//and here we got some rows
              if(helpMe.contains(j)){
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

  public static void printEverything(int TableIndex, ArrayList<Table> listOfTables){
     //remind me to put the names of the columns up here some time
     for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
        for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); i++){//and this loop is getting those records
           if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for empty
              System.out.print("\t\t");
           }else{
              System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
           }
        }
        System.out.print("\n");
     }
  }

  public static void printSomething(int TableIndex, ArrayList<Integer> indices, ArrayList<Table> listOfTables){
     //remind me to put the names of the columns up here some time
     for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
        if(indices.contains(i)){
           for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.size(); i++){//and here we got some rows
                 if(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() == null){//just tab twice for rows
                    System.out.print("\t\t");
                 }else{
                    System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).getData() + "\t");
                 }
           }
           System.out.print("\n");
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
