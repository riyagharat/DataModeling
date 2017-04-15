// TSQLx.java
import java.util.ArrayList;
import java.io.File;

public class TSQLx{
   
  static ArrayList listOfTables = new ArrayList<Table>();
  
  public static void main(String [] args){
    
  }
  
  /*public static void createDatabase(String dataBaseName){
    File dataBaseName = new File();
    if(dataBaseName.exists()){
      System.out.println("Please stoperino before you make a duplicate filerino");
    }else dataBaseName.createNewFile();
  }

  public static void dropDatabase(String dataBaseName){
    if(dataBaseName.exists()){
        dataBaseName.delete();
        System.out.println(dataBaseName + " has been deleted");
    }else System.out.println("That doesn't exist my guy");    
  }*/

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(){
    
  }
       
  public static void simpleSelect(ArrayList<String> Printlist, ArrayList<String> TableNamer, ArrayList<String> Wheres){
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
      if(Printlist.get(0).equals("*")){
         printEverything(TableIndex);
         return;
      }else{
         ArrayList indices = new ArrayList<Integer>();
         int checking = 0;
         for(int k = 0; k < Printlist.size(); k++){
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
         printSomething(TableIndex, indices);
      }//end else
   }else{//OH SHIT BOIS IT'S A WHERE STATEMENT HOLD ON TO YOUR BUTTS
      ArrayList ShitWeNeed = new ArrayList<Integer>();
      int killMe = 0;
      switch(Case){
         case 1://less than? the world may never know
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(listOfTables.get(TableIndex).list.get(killMe).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }else{
                  if(listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().compareTo(Comparator) > 1){
                     ShitWeNeed.add(monsterMash);
                  }
               }
            }
            break;
         case 2://greater than? check later
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(listOfTables.get(TableIndex).list.get(killMe).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }else{
                  if(listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().compareTo(Comparator) < 1){
                     ShitWeNeed.add(monsterMash);
                  }
               }
            } 
            break;
         case 3://equal to
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().equals(Comparator)){
                  ShitWeNeed.add(monsterMash);
               }
            } 
            break;
         case 4://not equal   
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(!listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().equals(Comparator)){
                  ShitWeNeed.add(monsterMash);
               }
            } 
            break;
         case 5://less than or equal to?
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(listOfTables.get(TableIndex).list.get(killMe).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }else{
                  if(listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().compareTo(Comparator) < 1 || 
                     listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().equals(Comparator)){
                     ShitWeNeed.add(monsterMash);
                  }
               }
            } 
            break;
         case 6://greater than or equal to?
            killMe = 0;
            while(!listOfTables.get(TableIndex).list.get(killMe).equals(ColumnName))
               killMe++;
            for(int monsterMash = 0; listOfTables.get(TableIndex).list.get(killMe).list.size(); monsterMash++){
               if(listOfTables.get(TableIndex).list.get(killMe).type.equals("String")){
                  System.out.println("Yeah man, String less than whatever, great idea, please choke on a scooter");
                  return;
               }else{
                  if(listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().compareTo(Comparator) > 1 || 
                     listOfTables.get(TableIndex).list.get(killMe).list.get(monsterMash).data().equals(Comparator)){
                     ShitWeNeed.add(monsterMash);
                  }
               }
            } 
            break;   
      }
      if(Printlist.get(0).equals("*")){
         please(TableIndex, ShitWeNeed);
         return;
      }else{
         ArrayList indices = new ArrayList<Integer>();
         int checking = 0;
         for(int k = 0; k < Printlist.size(); k++){
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

public static void AbbassiWhy(int TableIndex, ArrayList helpMe){
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
