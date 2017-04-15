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
      switch(parser.Scan()){
         case 0:
            System.out.println("Reject");
            break;
         case 1:
            System.out.println("CREATE");
            break;
         case 2:
            System.out.println("DROP");
            break;
         case 3:
            System.out.println("SAVE");
            break;
         case 4:
            System.out.println("LOAD");
            break;
         case 5:
            System.out.println("INSERT");
            break;
         case 6:
            System.out.println("DELETE");
            break;
         case 7:
            System.out.println("SELECT");
            break;
         case 8:
            System.out.println("TSELECT");
            break;
         case 9:
            System.out.println("CONVERT");
            break;
         case 10:
            System.out.println("COMMIT");
            break;
         case 11:
            System.out.println("INPUT");
            break;
         case 12:
            System.out.println("EXIT");
            break;
         default:
            System.out.println("Reject");
            break;
         
      }
      //parser.Scan();
   }
}

class Parser{
   String input;
   public static final String params = "((([a-zA-z]+)|([0-9]+(\\.[0-9]+)?)|([^a-zA-Z0-9\\s])))";
   public static final String[] keywords = {"CREATE","DROP","SAVE","LOAD","INSERT","SELECT",
      "tSELECT","CONVERT","COMMIT","INTEGER","INPUT", "EXIT", "DELETE","NUMBER","CHARACTER","DATE","INTO","VALUES",
      "FROM","WHERE","XML","XSD","AS", "DATABASE", "TABLE"};
   ArrayList<Token> tokens;
   //boolean isParsed;
   int choice;
   int j;
   boolean accept;
   ArrayList<String> arg0;
   ArrayList<String> arg1;
   ArrayList<String> arg2;
   String temp;
   
   public Parser(String input){
      this.input = input;
      tokens = new ArrayList<Token>();
      //isParsed = true;
      choice = 0;
      j = 0;
      this.accept = true;
      temp = "";
   }
   
   public int Scan(){
      arg0 = new ArrayList<String>();
      arg1 = new ArrayList<String>();
      arg2 = new ArrayList<String>();
      tokens = new ArrayList<Token>();
      Matcher m = Pattern.compile(params).matcher(input);
      while(m.find()){ 
         
         //System.out.println(m.group());
         /*for(int i = 0; i < 7; i++){
            System.out.print("(" + m.group(i) + ")");
         }*/
         //System.out.println();
         if(m.group(3)!=null){
            //System.out.println("WORD: " + m.group());
            if(isKeyword(m.group(3))) tokens.add(new Token("KW", m.group(3)));
            else tokens.add(new Token("ID", m.group(3)));
         }
         else if(m.group(4)!=null){
            //System.out.println("DIGIT: " + m.group());
            if(checkFloat(m.group(4))){
               tokens.add(new Token("FL", m.group(4)));
            }
            else{
               tokens.add(new Token("DI", m.group(4)));
            }
         }
         else if(m.group(6)!=null){
            //System.out.println("OTHER: " + m.group());
            tokens.add(new Token("SP", m.group(6)));
         }
      }
      return Parse();
   }
   
   public boolean checkFloat (String check){
      for (int i = 0; i<check.length(); i++){
         if(check.charAt(i)=='.'){
            return true;
         }
      }
      return false;
   }
   
   public ArrayList<String> getArg0(){
      return this.arg0;
   }
   
   public ArrayList<String> getArg1(){
      return this.arg1;
   }
   
   public ArrayList<String> getArg2(){
      return this.arg2;
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
      if(tokens.size()>0){
         a();
      }
      else this.accept = false;
      if(this.accept){
         //System.out.println("ACCEPT");
      }
      else{ 
         choice = 0;
         //System.out.println("REJECT");
      }
      /*
      for(int i = 0; i<arg0.size(); i++){
         System.out.print(arg0.get(i)+ ", ");
      }
      System.out.println();
      for(int i = 0; i<arg1.size(); i++){
         System.out.print(arg1.get(i) + ", ");
      }
      System.out.println();
      for(int i = 0; i<arg2.size(); i++){
         System.out.print(arg2.get(i) + ", ");
      }
      System.out.println();*/
      return choice;
   }
   
   void a(){
      if(tokens.get(j).getType().equals("KW")){
         for(int i = 0; i<keywords.length; i++){
            if(tokens.get(j).getName().equalsIgnoreCase(keywords[i])){
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
            if(tokens.get(j).getName().equalsIgnoreCase(keywords[i])){
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
            if(tokens.get(j).getName().equalsIgnoreCase(keywords[i])){
               d();
               acc(";", false);
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
         choice = 1;
         acc("create", true);
         e();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("drop")){
         choice = 2;
         acc("drop",true);
         n();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("save")){
         choice = 3;
         acc("save",true);
         o();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("load")){
         choice = 4;
         acc("load",true);
         o();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("insert")){
         choice = 5;
         acc("insert",true);
         p();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("delete")){
         choice = 6;
         acc("delete",true);
         u();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("tselect")){
         choice = 8;
         acc("tselect", true);
         w();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("select")){
         choice = 7;
         acc("select", true);
         w();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("convert")){
         choice = 9;
         acc("convert", true);
         z();
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("commit")){
         choice = 10;
         acc("commit", true);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("input")){
         choice = 11;
         acc("input", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("exit")){
         choice = 12;
         acc("exit", true);
      }
      else this.accept = false;
   }
   
   void e(){
      //acc("ID", false);
      if(tokens.get(j).getName().equalsIgnoreCase("database")){
         acc("database", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      } 
      else if(tokens.get(j).getName().equalsIgnoreCase("table")){
         acc("table", true);
         arg0.add(tokens.get(j).getName());
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
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         i();
         //arg2.add(temp);
      }
      else this.accept = false;
   }
   
   void i(){
      temp = "";
      if(tokens.get(j).getName().equalsIgnoreCase("integer")){
         temp += tokens.get(j).getName();
         acc("integer",true);
         j();
         arg2.add(temp);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("number")){
         temp += tokens.get(j).getName();
         acc("number", true);
         k();
         arg2.add(temp);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("character")){
         temp += tokens.get(j).getName();
         acc("character", true);
         temp += tokens.get(j).getName();
         acc("(",false);
         temp += tokens.get(j).getName();
         acc("DI", false);
         temp += tokens.get(j).getName();
         acc(")", false);
         arg2.add(temp);
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("date")){
         temp += tokens.get(j).getName();
         acc("date", true);
         arg2.add(temp);
         //temp += tokens.get(j).getName();
         /*acc("(", false);
         if(tokens.get(j).getType().equals("DI")){
            if(tokens.get(j).getName().length() > 2){
               this.accept = false;
            }
            else{
               temp += tokens.get(j).getName();
               acc("DI", false);
               temp += tokens.get(j).getName();
               acc("/",false);
               if(tokens.get(j).getType().equals("DI")){
                  if(tokens.get(j).getName().length() > 2){
                     this.accept = false;
                  }
                  else{
                     temp += tokens.get(j).getName();
                     acc("DI", false);
                     temp += tokens.get(j).getName();
                     acc("/", false);
                     if(tokens.get(j).getType().equals("DI")){
                        if(tokens.get(j).getName().length()>4){
                           this.accept = false;
                        }
                        else{
                           temp += tokens.get(j).getName();
                           acc("DI", false);
                           temp += tokens.get(j).getName();
                           acc(")", false);
                        }
                     }
                     else this.accept = false;
                  }
               }
               else this.accept = false;   
            }
         }
         else this.accept = false;*/
      }
      else this.accept = false;
   }
   
   void j(){
      if(tokens.get(j).getName().equals("(")){
         temp += tokens.get(j).getName();
         acc("(", false);
         temp += tokens.get(j).getName();
         acc("DI", false);
         temp += tokens.get(j).getName();
         acc(")", false);
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void k(){
      if(tokens.get(j).getName().equals("(")){
         temp += tokens.get(j).getName();
         acc("(", false);
         temp += tokens.get(j).getName();
         acc("DI", false);
         l();
         temp += tokens.get(j).getName();
         acc(")", false);
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void l(){
      if(tokens.get(j).getName().equals(",")){
         //temp += tokens.get(j).getName();
         acc(",", false);
         temp += tokens.get(j).getName();
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
      if(tokens.get(j).getName().equalsIgnoreCase("database")){
         acc("database", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      } 
      else if(tokens.get(j).getName().equalsIgnoreCase("table")){
         acc("table", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      }
      else this.accept = false;
   }
   
   void o(){
      if(tokens.get(j).getName().equalsIgnoreCase("database")){
         acc("database", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      } 
      else this.accept = false;
   }
   
   void p(){
      if(tokens.get(j).getName().equalsIgnoreCase("into")){
         acc("into", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
         q();
         acc("values", true);
         s();
      }
      else this.accept = false;
   }
   
   void q(){
      //temp = "";
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         //temp+=tokens.get(j).getName();
         arg1.add(tokens.get(j).getName());
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
         arg1.add(tokens.get(j).getName());
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
            if(tokens.get(j).getType().equals("ID")){
               arg2.add(tokens.get(j).getName());
               acc("ID", false);
            }
            else if(tokens.get(j).getType().equals("FL")){
               arg2.add(tokens.get(j).getName());
               acc("FL", false);
            }
            else if(tokens.get(j).getType().equals("DI")){
               temp = "";
               if(tokens.get(j).getName().length()>2){
                  arg2.add(tokens.get(j).getName());
                  acc("DI", false);
               }
               else{
                  temp+=tokens.get(j).getName();
                  acc("DI", false);
                  if(tokens.get(j).getName().equals("/")){
                     temp+=tokens.get(j).getName();
                     acc("/", false);
                     if(tokens.get(j).getType().equals("DI")){
                        if(tokens.get(j).getName().length()>2){
                           this.accept = false;
                        }
                        else{
                           temp+=tokens.get(j).getName();
                           acc("DI", false);
                           temp+=tokens.get(j).getName();
                           acc("/", false);
                           if(tokens.get(j).getType().equals("DI")){
                           if(tokens.get(j).getName().length()>4){
                              this.accept = false;
                           }
                           else{
                              temp+=tokens.get(j).getName();
                              acc("DI", false);
                              arg2.add(temp);
                           }
                     }
                     else this.accept = false;
                        }
                     }
                  }
                  else if(tokens.get(j).getName().equals("'")){
                  }
                  else this.accept = false;
               }
               arg2.add(temp);
            }
            else this.accept = false;
            acc("'",false);
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
            if(tokens.get(j).getType().equals("ID")){
               arg2.add(tokens.get(j).getName());
               acc("ID", false);
            }
            else if(tokens.get(j).getType().equals("FL")){
               arg2.add(tokens.get(j).getName());
               acc("FL", false);
            }
            else if(tokens.get(j).getType().equals("DI")){
               temp = "";
               if(tokens.get(j).getName().length()>2){
                  arg2.add(tokens.get(j).getName());
                  acc("DI", false);
               }
               else{
                  temp+=tokens.get(j).getName();
                  acc("DI", false);
                  if(tokens.get(j).getName().equals("/")){
                     temp+=tokens.get(j).getName();
                     acc("/", false);
                     if(tokens.get(j).getType().equals("DI")){
                        if(tokens.get(j).getName().length()>2){
                           this.accept = false;
                        }
                        else{
                           temp+=tokens.get(j).getName();
                           acc("DI", false);
                           temp+=tokens.get(j).getName();
                           acc("/", false);
                           if(tokens.get(j).getType().equals("DI")){
                           if(tokens.get(j).getName().length()>4){
                              this.accept = false;
                           }
                           else{
                              temp+=tokens.get(j).getName();
                              acc("DI", false);
                              arg2.add(temp);
                           }
                     }
                     else this.accept = false;
                        }
                     }
                  }
                  else if(tokens.get(j).getName().equals("'")){
                  }
                  else this.accept = false;
               }
               arg2.add(temp);
            }
            else this.accept = false;
            acc("'",false);
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
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         v();
      }
      else this.accept = false;
   }
   
   void v(){
      if(tokens.get(j).getName().equalsIgnoreCase("where")){
         acc("where", true);
         va();
      }
      else if(tokens.get(j).getName().equals(";")){
         return;
      }
      else this.accept = false;
   }
   
   void va(){
      if(tokens.get(j).getType().equals("ID")){
         arg2.add(tokens.get(j).getName());
         acc("ID", false);
         vb();
      }
      else if(tokens.get(j).getType().equals("DI")){
         arg2.add(tokens.get(j).getName());
         acc("DI",false);
         vb();
      }
      else this.accept = false;
   }
   
   void vb(){
      temp = "";
      if(tokens.get(j).getName().equals("<")){
         /*acc("<>", false);
         vc();*/
         //arg2.add(tokens.get(j).getName());
         temp = tokens.get(j).getName();
         acc("<", false);
         if(tokens.get(j).getName().equals(">")){
            temp+=tokens.get(j).getName();
            acc(">", false);
            arg2.add(temp);
         }
         else if(tokens.get(j).getName().equals("=")){
            temp+=tokens.get(j).getName();
            acc("=", false);
            arg2.add(temp);
         }
         else {
            arg2.add(temp);
         }
         vc();
      }
      else if(tokens.get(j).getName().equals("=")){
         arg2.add(tokens.get(j).getName());
         acc("=", false);
         vc();
      }
      else if(tokens.get(j).getName().equals(">")){
         temp = tokens.get(j).getName();
         acc(">", false);
         if(tokens.get(j).getName().equals("=")){
            temp+=tokens.get(j).getName();
            acc("=", false);
            arg2.add(temp);
         }
         else {
            arg2.add(temp);
         }
         vc();
      }
      else this.accept = false;
   }
   
   void vc(){
      if(tokens.get(j).getType().equals("ID")){
         arg2.add(tokens.get(j).getName());
         acc("ID", false);
      }
      else if(tokens.get(j).getType().equals("DI")){
         arg2.add(tokens.get(j).getName());
         acc("DI",false);
      }
      else this.accept = false;
   }
   
   void w(){
      if(tokens.get(j).getName().equals("*")){
         arg0.add(tokens.get(j).getName());
         acc("*", false);
         u();
      }
      else if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
         wa();
         acc(")", false);
         u();
      }
      else this.accept = false;
   }
   
   void wa(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
         r();
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else this.accept = false;
   }
   
   void x(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("from", true);
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         v();
      }
      else this.accept = false;
   }
   
   void y(){
      
   }
   
   void z(){
      if(tokens.get(j).getName().equalsIgnoreCase("xml")){
         acc("xml", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
         aa();
         acc("as", true);
         arg2.add(tokens.get(j).getName());
         acc("ID", false);
      }
      else this.accept = false;
   }
   
   void aa(){
      if(tokens.get(j).getName().equals(",")){
         acc(",", false);
         acc("xsd", true);
         arg1.add(tokens.get(j).getName());
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
            //System.out.println(str + " " + j + " " + (tokens.size()-1) + " " + tokens.get(j).getName());
            if(j < (tokens.size()-1)){
               j++;
            }
            //else this.complete = true;
         }
         else this.accept = false;
      }
      else{
         if(tokens.get(j).getName().equalsIgnoreCase(str)||tokens.get(j).getType().equalsIgnoreCase (str)){
            //System.out.println(str + " " + j + " " + (tokens.size()-1) + " " + tokens.get(j).getName());
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
