import java.util.ArrayList;

// TSQLx.java
public class TSQLx{
  public static void main(String [] args)
  {
	  createTable();
	 // newTable.findRecordWhere("SNO <= 4");
    
  }
  public static void createDatabase(String dataBaseName){
  
  }

  public static void dropDatabase(String dataBaseName){
    
  }

  public static void saveDatabase(String dataBaseName){
    
  }

  public static void loadDatabase(String dataBaseName){
    
  }

  public static void createTable()
  {
	  String[] attributes = new String[5];
	  attributes[0] = "Lname";
	  attributes[1] = "Fname";
	  attributes[2] = "Phone No";
	  attributes[3] = "Address";
	  attributes[4] = "ZIP";
	  
	  Table newTable = new Table(attributes, "Table 1");
	  newTable.displayColumns();
	  ArrayList<String> values = new ArrayList<String>();
	  ArrayList<String> values1 = new ArrayList<String>();
	  ArrayList<String> values2 = new ArrayList<String>();
	  
	  ArrayList<String> fields = new ArrayList<String>();
	  ArrayList<String> fieldsEmpty = new ArrayList<String>();
	  values.add("Brian1");
	  values.add("Phelan12");
	  values.add("2657 Oakgrove Ave1");
	  values.add("32092");
	  values.add("9046524033");
	  
	  values1.add("Brian1");
	  values1.add("Phelan12");
	  values1.add("2657 Oakgrove Ave1");
	  values1.add("320921");
	  values1.add("90465240331");
	
	  values2.add("Phelan12");
	  values2.add("Brian1");
	  values2.add("942032093");
	  
	  fields.add("Fname");
	  fields.add("Lname");
	  fields.add("Address");
	  fields.add("ZIP");
	  fields.add("Phone No");

	 newTable.insert(fields, values);
	 newTable.insert(fields, values1);
	 newTable.insert(fieldsEmpty, values2);
	 newTable.displayTable();
	  
	 ArrayList<String> deleteConditions = new ArrayList<String>();
	 deleteConditions.add("Fname");
	 deleteConditions.add("=");
	 deleteConditions.add("Brian1");
	 deleteConditions.add("Lname");
	 deleteConditions.add("=");
	 deleteConditions.add("Phelan12");
	 deleteConditions.add("Address");
	 deleteConditions.add("=");
	 deleteConditions.add("2657 Oakgrove Ave1");
	
	 newTable.delete(deleteConditions);

	  newTable.displayTable();
  }
}
