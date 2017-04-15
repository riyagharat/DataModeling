import java.util.*;
import java.io.*;

public class XMLtoSQL{
  public static void main(String [] args){
    if(args.length == 2){
      File XSDfile = new File(args[1]);
      File XMLfile = new File(args[0]);
      String inputFileName = new String(args[2]);
      bothFiles(XSDfile, XMLfile, inputFileName);
    }else{
      File XMLfile = new File(args[0]);
      String inputFileName = new String(args[1]);
      oneFile(XMLfile, inputFileName);
    }
  }

  public static void bothFiles(File XSDfile, File XMLfile, String inputFileName){
    File inputFileName = new File(inputFileName + ".txt");
    ArrayList<XSDFormat> XSDList = new ArrayList<XSDFormat>();
    // need to add the XSD stuff to an array List
    // Would check the XML format against this
    try{
      Scanner input = new Scanner(XSDfile);
      Scanner input2 = new Scanner(XMLfile);
      while(input.hasNextLine()){
        String text = input.nextLine();
        if(text.contains("xsd:complexType name=")){
    //      System.out.println(text.indexOf("="));
          String tablename = "";
          for(int i = (text.indexOf("=") +2); i < text.length(); i++){
            if(!(text.charAt(i) == ('\"'))){
              tablename = tablename + text.charAt(i);
            }else{
              break;
            }
          }
//          System.out.println("Table Name/ Object Name: " + tablename);
          XSDFormat format = new XSDFormat(tablename, "", "", "", "", "");
          XSDList.add(format);
          System.out.println("");
        }else if(text.contains("xsd:element name=")){
          int firstEqualIndex = text.indexOf("=");
    //      System.out.println(firstEqualIndex);
          String attributeName = "";
          for(int i = firstEqualIndex+2; i < text.length(); i++){
            if(!(text.charAt(i) == ('\"'))){
              attributeName = attributeName + text.charAt(i);
            }else{
              break;
            }
          }
          System.out.println("");
    //      System.out.println("Attribute Name: " + attributeName);
          XSDFormat format2 = new XSDFormat("", attributeName, "", "", "", "");
          int colonIndex = 0;
          if(text.contains("type=")){
            colonIndex = text.indexOf(":", firstEqualIndex);
  //          System.out.println(colonIndex);
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
    //        System.out.println("Attribute Type: " + attributeType);
            format2.setAttributeType(attributeType);
          }
          int index = 0;
          if(text.contains("maxOccurs=")){
            index = text.indexOf("=", colonIndex);
  //          System.out.println(index);
            String attributeLength = "";
            for(int i = index+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                attributeLength = attributeLength + text.charAt(i);
              }else{
                break;
              }
            }
  //          System.out.println("Attribute Length: " + attributeLength);
            format2.setAttributeLength(attributeLength);
          }
          int fractionIndex = 0;
          if(text.contains("fraction=")){
            fractionIndex = text.lastIndexOf("=");
  //          System.out.println(fractionIndex);
            String decimals = "";
            for(int i = fractionIndex+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                decimals = decimals + text.charAt(i);
              }else{
                break;
              }
            }
  //          System.out.println("Decimal Places: " + decimals);
            format2.setFraction(decimals);
          }
          int dateIndex = 0;
          if(text.contains("date=")){
            dateIndex = text.lastIndexOf("=");
  //          System.out.println(dateIndex);
            String dateFormat = "";
            for(int i = dateIndex+2; i < text.length(); i++){
              if(!(text.charAt(i) == ('\"'))){
                dateFormat = dateFormat + text.charAt(i);
              }else{
                break;
              }
            }
  //          System.out.println("Date Format: " + dateFormat);
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
                  if((!(text.charAt(j) == ('<'))) /*&& (!(text.charAt(j+1) == ('/')))*/){
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
  //        System.out.println("INSERT INTO " + XSDList.get(0).getTableName() + " () VALUES()")
      //    System.out.println("check: " + check);
          if(check == true){
            counter++;
    //        System.out.println("check: " + counter);
            attributeNames.add(attributename);
            attributeValues.add(value);
          //  System.out.println("Table Name/ Object Name: " + XSDList.get(0).getTableName());
          //  System.out.println("Attribute: " + attributename);
          //  System.out.println("Value: " + value);
          //  System.out.println("");
          }else{
            if(attributes.length() > 0){
              attributes = attributes.substring(1, attributes.length()-1);
            }
        //    System.out.println(attributes);

            if(values.length() > 0){
              values = values.substring(1, values.length()-1);
            }
        //    System.out.println(values);
            if(!(attributes.equals(""))){
              System.out.println("INSERT INTO " + XSDList.get(0).getTableName() + " (" + attributes
                + ") VALUES (" + values + ");");
            }

            counter = 0;
            attributeNames.clear();
            attributeValues.clear();
          }
    //      if(!(attributename.equals(""))){
    //        System.out.println("Table Name/ Object Name: " + XSDList.get(0).getTableName());
    //        System.out.println("Attribute: " + attributename);
    //        System.out.println("Value: " + value);
    //        System.out.println("");
    //      }
        }
        attributes = "";
        for(int i = 0; i < attributeNames.size(); i++){
          attributes = attributes + " " + attributeNames.get(i) + ",";
        }

  //      attributes = attributes.trim();

    //    System.out.println(attributes);

        values = "";
        for(int i = 0; i < attributeValues.size(); i++){
          values = values + " " + attributeValues.get(i) + ",";
        }
//        values = values.trim();
  //      System.out.println(values);
      }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
  }

  public static void oneFile(File XMLfile, String inputFileName){
    try{
      Scanner input = new Scanner(XMLfile);

      // Create columns here with attributes
      while(input.hasNextLine()){
        String text = input.nextLine();
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
                break;
              }
            }else if((text.indexOf(">") + 1) < text.length()){
              if((((text.charAt(text.indexOf(">") + 1)) >= 'a') && ((text.charAt(text.indexOf(">") + 1)) <= 'z'))
                  || (((text.charAt(text.indexOf(">") + 1)) >= 'A') && ((text.charAt(text.indexOf(">") + 1)) <= 'Z'))
                  || (((text.charAt(text.indexOf(">") + 1)) >= '0') && ((text.charAt(text.indexOf(">") + 1)) <= '9'))){
                attributename = tablename;
                tablename = "";
                for(int j = (text.indexOf(">") +1); j < text.length(); j++){
                  if((!(text.charAt(j) == ('<'))) /*&& (!(text.charAt(j+1) == ('/')))*/){
                    value = value + text.charAt(j);
                  }else{
                    break;
                  }
                }
              }
              break;
            }else{
              break;
            }
          }
          System.out.println("Table Name/ Object Name: " + tablename);
          System.out.println("Attribute: " + attributename);
          System.out.println("Value: " + value);
          System.out.println("");
        }
      }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
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
