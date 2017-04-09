import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StackOverflowError;

public class Test{
   public static void main(String[] args){
      Scanner cLine = new Scanner(System.in);
      String testLine = cLine.nextLine();
      Parser parser = new Parser(testLine);
      parser.Scan();
   }
}

class Parser{
   String input;
   public static final String params = "((([a-zA-z]+)|([0-9]+)|([^a-zA-Z0-9\\s])))";
   public static final String[] keywords = {"CREATE","DROP","SAVE","LOAD","INSERT","SELECT",
      "tSELECT","CONVERT","COMMIT","INTEGER","NUMBER","CHARACTER","DATE","INTO","VALUES",
      "FROM","WHERE","XML","XSD","AS"};
   ArrayList<Token> tokens;
   //boolean isParsed;
   int choice;
   int j;
   boolean accept;
   
   public Parser(String input){
      this.input = input;
      tokens = new ArrayList<Token>();
      //isParsed = true;
      choice = 0;
      j = 0;
      this.accept = false;
   }
   
   public int Scan(){
      tokens = new ArrayList<Token>();
      Matcher m = Pattern.compile(params).matcher(input);
      while(m.find()){ 
         /*
         System.out.println(m.group());
         for(int i = 0; i < 6; i++){
            System.out.print("(" + m.group(i) + ")");
         }
         System.out.println();*/
         if(m.group(3)!=null){
            //System.out.println("WORD: " + m.group());
            if(isKeyword(m.group(3))) tokens.add(new Token("KW", m.group(3)));
            else tokens.add(new Token("ID", m.group(3)));
         }
         else if(m.group(4)!=null){
            //System.out.println("DIGIT: " + m.group());
            tokens.add(new Token("DI", m.group(4)));
         }
         else if(m.group(5)!=null){
            //System.out.println("OTHER: " + m.group());
            tokens.add(new Token("SP", m.group(5)));
         }
      }
      return Parse();
   }
   
   public boolean isKeyword(String id){
      for(int i = 0; i < keywords.length; i++){
         if(keywords[i].equalsIgnoreCase(id)) return true;
      }
      return false;
   }
   
   public int Parse(){
      choice = 0;
      j = 0;
      a();
      return choice;
   }
   
   void a(){
      if(tokens.get(j).getType().equals("KW")){
         for(int i = 0; i<keywords.length; i++){
            if(tokens.get(j).getName().equals(keywords[i])){
               b();
               break;
            }
         }
      }
      else this.accept = false;
   }
   
   void b(){
      if(tokens.get(j).getType().equals("KW")){
         for(int i = 0; i<keywords.length; i++){
            if(tokens.get(j).getName().equals(keywords[i])){
               c();
               break;
            }
         }
      }
      else this.accept = false;
   }
   
   void c(){
      if(tokens.get(j).getType().equals("KW")){
         for(int i = 0; i<keywords.length; i++){
            if(tokens.get(j).getName().equals(keywords[i])){
               d();
               acc(";", false);
               c();
               break;
            }
         }
      }
      else this.accept = false;
   }
   
   void d(){
      if(tokens.get(j).getName().equals("")){
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("create")){
         acc("create", true);
         e();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("drop")){
         acc("drop",true);
         n();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("save")){
         acc("save",true);
         o();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("load")){
         acc("load",true);
         o();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("insert")){
         acc("insert",true);
         p();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("delete")){
         acc("delete",true);
         u();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("tselect")){
         acc("tselect", true);
         w();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("select")){
         acc("select", true);
         w();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("convert")){
         acc("convert", true);
         z();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("commit")){
         acc("commit", true);
      }
   }
   
   void e(){
      //acc("ID", false);
      if(tokens.get(j).getType().equals("ID")){
         acc("ID", false);
         f();
      } 
      else this.accept = false;
   }
   
   void f(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         h();
         g();
         acc(")", false);
      }
      else if(tokens.get(j).getName().equals(";")){
         return;
      }
      else this.accept = false;
   }
   
   void g(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         h();
         g();
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void h(){
      if(tokens.get(j).getType().equals("ID")){
         acc("ID", false);
         i();
      }
      else this.accept = false;
   }
   
   void i(){
      if(tokens.get(j).getName().equalsIgnoreCase("integer")){
         acc("integer",true);
         j();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("number")){
         acc("number", true);
         k();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("character")){
         acc("character", true);
         acc("(",false);
         acc("DI", false);
         acc(")", false);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("date")){
         acc("date", true);
         acc("(", false);
         if(tokens.get(j).getType().equals("DI")){
            if(tokens.get(j).getName().length() > 2){
               this.accept = false;
            }
            else{
               acc("DI", false);
               acc("/",false);
               if(tokens.get(j).getType().equals("DI")){
                  if(tokens.get(j).getName().length() > 2){
                     this.accept = false;
                  }
                  else{
                     acc("DI", false);
                     acc("/", false);
                     if(tokens.get(j).getType().equals("DI")){
                        if(tokens.get(j).getName().length()>4){
                           this.accept = false;
                        }
                        else{
                           acc("DI", false);
                           acc(")", false);
                        }
                     }
                     else this.accept = false;
                  }
               }
               else this.accept = false;   
            }
         }
         else this.accept = false;
      }
      else this.accept = false;
   }
   
   void j(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         acc("DI", false);
         acc(")", false);
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void k(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         acc("DI", false);
         l();
         acc(")", false);
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void l(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         acc("DI", false);
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void m(){
   }
   
   void n(){
      if(tokens.get(j).getType().equals("ID")){
         acc("ID", false);
      }
      else this.accept = false;
   }
   
   void o(){
      if(tokens.get(j).getType().equals("ID")){
         acc("ID", false);
      }
      else this.accept = false;
   }
   
   void p(){
      if(tokens.get(j).getName().equalsIgnoreCase("into")){
         acc("into", true);
         acc("ID", false);
         q();
         acc("values", true);
         s();
      }
      else this.accept = false;
   }
   
   void q(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         acc("ID", false);
         r();
         acc(")", false);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("values")){
         return;
      }
      else this.accept = false;
   }
   
   void r(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         acc("ID", false);
         r();
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void s(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         if(tokens.get(j).getName().equals("'")){
            acc("'", false);
            acc("ID", false);
            acc("'",false);
            t();
            acc(")", false);
         }
         else if(tokens.get(j).getType().equals("DI")){
            acc("DI", false);
            t();
            acc(")", false);
         }
         else this.accept = false;
         
      }
      else this.accept = false;
   }
   
   void t(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         if(tokens.get(j).getName().equals("'")){
            acc("'", false);
            acc("ID", false);
            acc("'",false);
            t();
         }
         else if(tokens.get(j).getType().equals("DI")){
            acc("DI", false);
            t();
         }
         else this.accept = false;
         
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void u(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("from", true);
         acc("ID", true);
         v();
      }
      else this.accept = false;
   }
   
   void v(){
      if(tokens.get(j).getName().equalsIgnoreCase("where")){
         //acc("where", true);
      }
      else if(tokens.get(j).getName().equals(";")){
         return;
      }
      else this.accept = false;
   }
   
   void w(){
      if(tokens.get(j).getName().equals("*")){
         acc("*", false);
      }
      else if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         acc("ID", false);
         q();
         acc(")", false);
      }
      else this.accept = false;
   }
   
   void x(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("from", true);
         acc("ID", false);
         v();
      }
      else this.accept = false;
   }
   
   void y(){
      
   }
   
   void z(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("xml", true);
         acc("ID", false);
         aa();
         acc("as", true);
         acc("ID", false);
      }
      else this.accept = false;
   }
   
   void aa(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         acc("xsd", true);
         acc("ID", false);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("as")){
         return;
      }
      else this.accept = false;
   }
   
   void acc(String str, boolean ignoreCase){
      //System.out.println(this.accept);
      if(!ignoreCase){
         if(tokens.get(j).getName().equals(str)||tokens.get(j).getType().equals(str)){
            System.out.println(str + " " + j + " " + (tokens.size()-1) + " " + tokens.get(j).getName());
            if(j < (tokens.size()-1)){
               j++;
            }
            //else this.complete = true;
         }
         else this.accept = false;
      }
      else{
         if(tokens.get(j).getName().equalsIgnoreCase(str)||tokens.get(j).getType().equalsIgnoreCase (str)){
            System.out.println(str + " " + j + " " + (tokens.size()-1) + " " + tokens.get(j).getName());
            if(j < (tokens.size()-1)){
               j++;
            }
            //else this.complete = true;
         }
         else this.accept = false;
      }
      }
   }


class Token{
   private String type;
   private String name;
   
   public Token(String type, String name){
      this.type = type;
      this.name = name; 
   }
   
   public String getType(){
      return this.type;
   }
   
   public void setType(String type){
      this.type = type;
   }
   
   public String getName(){
      return this.name;
   }
   
   public void setName(String name){
      this.name = name;
   }
}