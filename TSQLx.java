import java.util.*;
import java.io.*;
import java.text.*;
// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    Console reader = System.console();
    String userInput;
    int choice = 0;
    ArrayList<Table> listOfTables = new ArrayList<Table>();
    /*
       fromat:
          call function name
          params = (consoleInput.getArg0(),...Arg1(),...Arg2());
    */
    do{
      System.out.print("TSQLx>");
      userInput = reader.readLine();
      Parser consoleInput = new Parser(userInput);
      choice = consoleInput.Scan();
      switch(choice){
        case 0:
            System.out.print("      ");
            for(int k = 0; k<consoleInput.getError(); k++){
               System.out.print(" ");
            }
            System.out.println("*");
            System.out.println("Error at index: "+consoleInput.getError());
            break;
         case 1:
            System.out.println("CREATE");
            if(!(consoleInput.getArg1().size() == 0)){
              create((consoleInput.getArg0().get(0)), consoleInput.getArg1(), consoleInput.getArg2(), listOfTables);
            }else{
              create(consoleInput.getArg0().get(0));
            }
            break;
         case 2:
            System.out.println("DROP");
            if(!(consoleInput.getArg1().size() == 0)){
              dropT(consoleInput.getArg1().get(0), listOfTables);
            }else{
              dropDB(consoleInput.getArg0().get(0));
            }
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
            String XSD = "";
            if(!(consoleInput.getArg1().size() == 0)){
              XSD = consoleInput.getArg1().get(0);
            }else{
              XSD = "";
            }
            convertXML(consoleInput.getArg0().get(0), XSD, consoleInput.getArg2().get(0));
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

  public static void create(String dataBaseName){
    File file = new File(dataBaseName + ".txt");
  }

  public static void create(String tableName, ArrayList<String> fieldNames, ArrayList<String> fieldDefs, ArrayList<Table> listOfTables){
    Table newTable = new Table(tableName, fieldNames, fieldDefs);
    listOfTables.add(newTable);
  }

  public static void dropT(String tableName, ArrayList<Table> listOfTables){
    for(int i = 0; i < listOfTables.size(); i++){
      if((listOfTables.get(i).getName()).equals(tableName)){
        listOfTables.remove(i);
      }
    }
  }

  public static void dropDB(String dataBaseName){
    File file = new File(dataBaseName + ".txt");
    file.delete();
  }

  public static void insert(){

  }

  public static void delete(){

  }

  public static void convertXML(String XMLFileName, String XSDFileName, String inputFileName){
    if((XSDFileName.equals(""))){;
      File XMLfile = new File(XMLFileName + ".xml");
      oneFile(XMLfile, inputFileName);
    }else{
      File XSDfile = new File(XSDFileName + ".xsd");
      File XMLfile = new File(XMLFileName + ".xml");
      bothFiles(XSDfile, XMLfile, inputFileName);
    }
  }

  public static void bothFiles(File XSDfile, File XMLfile, String inputFileName){
    ArrayList<XSDFormat> XSDList = new ArrayList<XSDFormat>();
    // need to add the XSD stuff to an array List
    // Would check the XML format against this
    try{
      Scanner input = new Scanner(XSDfile);
      Scanner input2 = new Scanner(XMLfile);
      while(input.hasNextLine()){
        String text = input.nextLine();
        if(text.contains("xsd:complexType name=")){
          String tablename = "";
          for(int i = (text.indexOf("=") +2); i < text.length(); i++){
            if(!(text.charAt(i) == ('\"'))){
              tablename = tablename + text.charAt(i);
            }else{
              break;
            }
          }
          XSDFormat format = new XSDFormat(tablename, "", "", "", "", "");
          XSDList.add(format);
          System.out.println("");
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
          System.out.println("");
          XSDFormat format2 = new XSDFormat("", attributeName, "", "", "", "");
          int colonIndex = 0;
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
            if(attributeType.equals("string")){
              attributeType = "char";
            }
            attributeType = attributeType.toUpperCase();
            format2.setAttributeType(attributeType);
          }
          int index = 0;
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
            format2.setAttributeLength(attributeLength);
          }
          int fractionIndex = 0;
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
            format2.setFraction(decimals);
          }
          int dateIndex = 0;
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
            format2.setDateFormat(dateFormat);
          }
          XSDList.add(format2);
        }
      }

      for(int i = 0; i < XSDList.size(); i++){
        System.out.println(XSDList.get(i));
      }
      // Create columns here with attributes
      boolean check = false;
      ArrayList<String> attributeNames = new ArrayList<String>();
      ArrayList<String> attributeValues = new ArrayList<String>();
      int counter = 0;
      String attributes = "";
      String values = "";
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
              counter++;
              attributeNames.add(attributename);
              attributeValues.add(value);
            }else{
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

  public static void oneFile(File XMLfile, String inputFileName){
    try{
      Scanner input = new Scanner(XMLfile);
      String tablename = "";
      String attributename = "";
      String value = "";
      boolean check = false;
      ArrayList<String> attributeNames = new ArrayList<String>();
      ArrayList<String> attributeValues = new ArrayList<String>();
      ArrayList<String> tableNames = new ArrayList<String>();
      int counter = 0;
      String attributes = "";
      String values = "";
      PrintStream fileStream = new PrintStream(new FileOutputStream(inputFileName + ".txt", true));
      // Create columns here with attributes
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
            counter++;
            attributeNames.add(attributename);
            attributeValues.add(value);
          }else{
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

  public static void inputFile(){

  }

  public static void select(ArrayList<String> PrintList, ArrayList<String> TableNamer, ArrayList<String> Wheres,
    ArrayList<Table> listOfTables) throws ParseException{ // Begin select statement
    String TableName = TableNamer.get(0); //retrieve table name from arraylist
    int TableIndex = 0;
    String ColumnName = "";
    String Relational = "";
    String Comparator = "";
    int Case = 0;
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
