import java.util.*;
import java.io.*;

public class XMLtoSQL{
  public static void main(String [] args){
    if(args.length == 2){
      File XSDfile = new File(args[1]);
      File XMLfile = new File(args[0]);
      bothFiles(XSDfile, XMLfile);
    }else{
      File XMLfile = new File(args[0]);
      oneFile(XMLfile);
    }
  }

  public static void bothFiles(File XSDfile, File XMLfile){
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
          System.out.println("Table Name/ Object Name: " + tablename);
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
          System.out.println("Attribute Name: " + attributeName);
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
            System.out.println("Attribute Type: " + attributeType);
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
            System.out.println("Attribute Length: " + attributeLength);
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
            System.out.println("Decimal Places: " + decimals);
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
            System.out.println("Date Format: " + dateFormat);
          }
        }
      }
      // Create columns here with attributes
      while(input2.hasNextLine()){
        String text = input2.nextLine();
        
      }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
  }

  public static void oneFile(File XMLfile){
    try{
      Scanner input = new Scanner(XMLfile);
      // attributes are given as tags in the file, create columns here

    }catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
