// TSQLx.java
import java.util.ArrayList;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TSQLx{
   
  
  public static void main(String [] args){
    
  }
  
  public static void createDatabase(){
     
    
    /*File dataBaseName = new File();
    if(dataBaseName.exists()){
      System.out.println("Please stoperino before you make a duplicate filerino");
    }else dataBaseName.createNewFile();*/
  

  /*public static void dropDatabase(String dataBaseName){
    if(dataBaseName.exists()){
        dataBaseName.delete();
        System.out.println(dataBaseName + " has been deleted");
    }else System.out.println("That doesn't exist my guy");    
  }*/
  }
  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(){
    
  }
       
  public static void simpleSelect(ArrayList<String> PrintList, ArrayList<String> TableNamer, ArrayList<String> Wheres){
   String TableName = TableNamer.get(0);
   int TableIndex = 0;
   String ColumnName;
   String Relational;
   String Comparator;
   int Case;
   if(Wheres.size() > 0){
      ColumnName = Wheres.get(0);
      Relational = Wheres.get(1);
      Comparator = Wheres.get(2);
      Wheres.remove(2);
      Wheres.remove(1);
      Wheres.remove(0);   
   }
   
   if(Wheres.size() > 0){
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
   
   while(!listOfTables.get(TableIndex).equals(TableName)){//Is this table a thing?
      TableIndex++;
      if(TableIndex == listOfTables.size()){//If you've gone too far, give up
         System.out.println("That table doesn't exist ya silly goose");
         return;
      }
   }
   
   if(Wheres.size() == 0){
      if(PrintList.get(0).equals("*")){
         printEverything(TableIndex);
         return;
      }else{
         ArrayList indices = new ArrayList<Integer>();
         int checking = 0;
         for(int k = 0; k < PrintList.size(); k++){
            int Persona = 0;
            while(!listOfTables.get(TableIndex).list.get(k).equals(PrintList.get(Persona))){
               Persona++;
               if(Persona == listOfTables.get(TableIndex).list.size()){
                  System.out.println("Excuse me, WHAT column?");
                  return;
               }
            indices.add(Persona);   
            }//end while
            k++;
         }//end for loop
         printSomething(TableIndex, indices);
      }//end else
   }else{//OH SHIT BOIS IT'S A WHERE STATEMENT HOLD ON TO YOUR BUTTS
      ArrayList FilteredColumns = new ArrayList<Integer>();
      int ColumnNumber = 0;
      switch(Case){
         case 1://less than? the world may never know
            ColumnNumber = 0;
            while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
               ColumnNumber++;
            for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }else if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
                     if(formattedRowValue.compareTo(Comparator) > 0){
                        FilteredColumns.add(RowNumber);
                     }
                  }  
                  if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().compareTo(Comparator) > 0){
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
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }
               else if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
                  if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().compareTo(Comparator) < 0){
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
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
               else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().equals(Comparator)){
                  FilteredColumns.add(RowNumber);
               }
            } 
            break;
         case 4://not equal   
            ColumnNumber = 0;
            while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
               ColumnNumber++;
            for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
               else if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().equals(Comparator)){
                  FilteredColumns.add(RowNumber);
               }
            } 
            break;
         case 5://less than or equal to?
            ColumnNumber = 0;
            while(!listOfTables.get(TableIndex).list.get(ColumnNumber).equals(ColumnName))
               ColumnNumber++;
            for(int RowNumber = 0; RowNumber < listOfTables.get(TableIndex).list.get(ColumnNumber).list.size(); RowNumber++){
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }
               else if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
                  if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().compareTo(Comparator) >= 0){
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
               if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }
               else if(listOfTables.get(TableIndex).list.get(ColumnNumber).type.equals("Date")){//run shutdown.exe
                  if(Comparator.matches("\\d\\d\\/\\d\\d\\/(\\d\\d)?\\d\\d") == false){
                     System.out.println("What kind of cockweasel screws up their date input");
                     return;
                  }
                  else{
                     SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                     SimpleDateFormat otherParser = new SimpleDateFormat("MM/dd/yy");
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     String formattedDate = formatter.format(Comparator);
                     String theDate = new String(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data());
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
                  if(listOfTables.get(TableIndex).list.get(ColumnNumber).list.get(RowNumber).data().compareTo(Comparator) <= 0){
                     FilteredColumns.add(RowNumber);
                  }
               }
            } 
            break;   
      }
      if(PrintList.get(0).equals("*")){
         please(TableIndex, FilteredColumns);
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

public static void please(int TableIndex, ArrayList helpMe){
   //remind me to put the names of the columns up here some time
   for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
      for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.get(j); i++){//and this loop is getting those records
         if(helpMe.contains(j)){
            if(listOfTables.get(TableIndex).list.get(i).list.get(j).data = null){//just tab twice for empty
               System.out.print("\t\t");
            }else{
               System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).data + "\t");
            }
         }
      }
      System.out.print("\n");
   }
}

public static void AbbassiWhy(int TableIndex, ArrayList helpMe, ArrayList<Integer> indices){
//remind me to put the names of the columns up here some time
   for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
      if(indices.contains(i)){
         for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.get(j); i++){//and here we got some rows
            if(helpMe.contains(j)){
               if(listOfTables.get(TableIndex).list.get(i).list.get(j).data = null){//just tab twice for empty
                  System.out.print("\t\t");
               }else{
                  System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).data + "\t");
               }
            }   
         }
         System.out.print("\n");
      }
   }
   return;
}   

public static void printEverything(int TableIndex){
   //remind me to put the names of the columns up here some time
   for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for number of records
      for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.get(j); i++){//and this loop is getting those records
         if(listOfTables.get(TableIndex).list.get(i).list.get(j).data = null){//just tab twice for empty
            System.out.print("\t\t");
         }else{
            System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).data + "\t");
         }   
      }
      System.out.print("\n");
   }
}

public static void printSomething(int TableIndex, ArrayList<Integer> indices){
   //remind me to put the names of the columns up here some time
   for(int i = 0; i < listOfTables.get(TableIndex).list.size(); i++){//this for loop's checking for column size
      if(indices.contains(i)){
         for(int j = 0; j < listOfTables.get(TableIndex).list.get(i).list.get(j); i++){//and here we got some rows
               if(listOfTables.get(TableIndex).list.get(i).list.get(j).data = null){//just tab twice for rows
                  System.out.print("\t\t");
               }else{
                  System.out.print(listOfTables.get(TableIndex).list.get(i).list.get(j).data + "\t");
               }   
         }
         System.out.print("\n");
      }
   }
}    
       
       
}
