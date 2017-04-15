// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    
  }
  
  public static void createDatabase(String dataBaseName){
    File dataBaseName = new File();
    if(dataBaseName.exists(){
      System.out.println("Please stoperino before you make a duplicate filerino");
    }else dataBaseName.createNewFile();
  }

  public static void dropDatabase(String dataBaseName){
    if(dataBaseName.exists(){
        dataBaseName.delete();
        System.out.println(dataBaseName + " has been deleted");
    }else System.out.println("That doesn't exist my guy");    
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(){
    
  }
       
  public static void simpleSelect(Arraylist<String> Printlist, Arraylist<String> TableNamer, Arraylist<String> Wheres){
   
   String TableName = TableNamer.get(0);
   int TableIndex = 0;
   String ColumnName;
   String Relational;
   String Comparator;
   if(Wheres.size > 0){
      String ColumnName = Wheres.get(0);
      String Relational = Wheres.get(1);
      String Comparator = Wheres.get(2);
   }
   
   while(!listOfTables.get(TableIndex).equals(TableName)){//Is this table a thing?
      TableIndex++;
      if(TableIndex = listOfTables.size()){//If you've gone too far, give up
         System.out.println("That table doesn't exist ya silly goose");
         return;
      }
   }
   
   if(Wheres.size = 0){
      if(Printlist.get(0).equals("*")){
         printEverything(TableIndex);
         return;
      }else{
         Arraylist indices = new Arraylist<int>;
         int checking = 0;
         for(int k = 0; k < Wheres.size()){
            int Persona = 0;
            while(!listOfTables.get(TableIndex).list.get(i).equals(Wheres.get(Persona))){
               Persona++;
               if(Persona = listOfTables.get(TableIndex).list.size()){
                  System.out.println("Excuse me, WHAT column?");
                  return;
               }
            indices.add(Persona);   
            }//end while
         }//end forloop
         
      }//end else
   }//end if statement
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

public static void printSomething(int TableIndex, Arraylist<int> indices){
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
