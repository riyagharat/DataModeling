// TSQLx.java
public class TSQLx{
  public static void main(String [] args){
    ArrayList<Table> listOfTables = new ArrayList<Table>;
    ArrayList<Column> listOfColumns = new ArrayList<Column>;
    Console reader = System.console();
    String userInput = reader.readLine();

    int choice = new Parser(userInput);

    while(true){
      switch(choice){
        case 1: // createDatabase
                break;
        case 2: // dropDatabase
                break;
        case 3: // saveDatabase
                break;
        case 4: // loadDatabase
                break;
      }
    }
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
    // Kevin
    File file = dataBaseName + ".txt";
    file.close();
  }

  public static void loadDatabase(String dataBaseName){
    // Kevin
  }

  public static void createTable(String tableName){
    // need to have the columns set up here
    Table newTable = (tableName, "", "");
    listOfTables.add(newTable);
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
    // Riya
    ArrayList parentList = new ArrayList();
    SAXReader xmlReader = new SAXReader();
    try{
     Document doc = xmlReader.read(xmlFile);
     Element parentElement = doc.getRootElement();
     if(parentElement.nodeCount()>0){
      parentList=(ArrayList)parentElement.elements();
      Element[] arrElem = new Element[parentList.size()] ;
      for(int i = 0;i<parentList.size();i++){
       arrElem[i] = (Element)parentList.get(i);
       System.out.println(arrElem[i].node(0).getText()+" | "arrElem[i].node(1).getText()+" |   "arrElem[i].node(2).getText()+" | "arrElem[i].node(3).getText());
      }
     }
    }catch (MalformedURLException ex) {
           ex.printStackTrace();
    } catch (DocumentException ex) {
           ex.printStackTrace();
    }
  }

  public static void inputFile(){
    // Riya
  }

  public static void deleteFromTable(){
    // Tanner
  }

  public static void simpleSelect(){
    // Tanner
  }

  public static void dateSelect(){
    // Chance
  }

  public static void dateSelectComplicated(){
    // Chance
  }
}
