import java.util.ArrayList;

// TSQLx.java
public class TSQLx{
  public static void main(String [] args)
  {
	  createDatabase();
  
  }
  public static void createDatabase()
  {
	  ArrayList<String> fields = new ArrayList<String>();			
	  ArrayList<String> types = new ArrayList<String>();
	 
	  fields.add("Fname");
	  fields.add("Lname");					//column fields and types for testing
	  fields.add("Address");
	  fields.add("ZIP");
	  fields.add("Salary");
	  
	  types.add("char(15)");
	  types.add("char(20)");
	  types.add("char(30)");
	  types.add("number(5)");
	  types.add("integer(8,2)");
	  createTable(fields, types);
  }

  public static void dropDatabase(String dataBaseName){
    
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable(ArrayList<String> fields, ArrayList<String> types)
  {
	  Table newTable = new Table(fields, types, "Employee"); 
	  
	  newTable.displayColumns();
	  ArrayList<String> values = new ArrayList<String>();
	  ArrayList<String> values1 = new ArrayList<String>();
	  ArrayList<String> values2 = new ArrayList<String>();
	  ArrayList<String> fields1 = new ArrayList<String>();
	  ArrayList<String> fieldsEmpty = new ArrayList<String>();
	  values.add("Brian");
	  values.add("Phelan");
	  values.add("2657 Oakgrove Ave1");								//data for insertion testing
	  values.add("2049.49");
	  values.add("32092");
	  
	  values1.add("Brian");
	  values1.add("Phelan");
	  values1.add("2657 Oakgrove Ave1jkjhjjhkhjkhjkhkjhjkkjhhjkjhk");
	  values1.add("20939.49");
	  values1.add("32092");
	
	  values2.add("Brian1");
	  values2.add("Phelan12");
	  values2.add("45 Den St.");
	  
	  fields1.add("Fname");
	  fields1.add("Lname");
	  fields1.add("Address");
	  fields1.add("Salary");
	  fields1.add("ZIP");
	  

	 newTable.insert(fields1, values);
	 newTable.insert(fields1, values1);
	 newTable.insert(fieldsEmpty, values2);
	 newTable.displayTable();
	  
	 ArrayList<String> deleteConditions = new ArrayList<String>();
	 deleteConditions.add("Fname");
	 deleteConditions.add("=");
	 deleteConditions.add("Brian1");
	 deleteConditions.add("Lname");
	 deleteConditions.add("=");							//data for deletion testing
	 deleteConditions.add("Phelan12");					
	 deleteConditions.add("Address");
	 deleteConditions.add("=");
	 deleteConditions.add("45 Den St.");
	
	 newTable.delete(deleteConditions);
	 System.out.println("after deletion\n");
	  newTable.displayTable();
  }
}
