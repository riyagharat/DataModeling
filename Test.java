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

/*
   This class creates a parser for the SQL language. The parser will perform lexical and syntax analysis. Semantic analysis will 
   be done in individual functinos. The parser class main purpose is to return an integer which will be associated with a sql command
   (e.g: create, save, load, etc), and to store arguments in sql commands as arraylists, which can be referenced by other functions.
*/
class Parser{
   //Variable declaration
   String input;
   //static final variables are used for both the regex, and the keywords listings
   public static final String params = "((([a-zA-z]+)|([0-9]+(\\.[0-9]+)?)|([^a-zA-Z0-9\\s])))";
   public static final String[] keywords = {"CREATE","DROP","SAVE","LOAD","INSERT","SELECT",
      "tSELECT","CONVERT","COMMIT","INTEGER","INPUT", "DELETE","NUMBER","CHARACTER","DATE","INTO","VALUES",
      "FROM","WHERE","XML","XSD","AS", "DATABASE", "TABLE"};
   ArrayList<Token> tokens;
   //boolean isParsed;
   int choice;
   int j;
   int errorIndex;
   boolean accept;
   boolean rejected;
   //Arguments for other functions held in arg0, arg1, and arg2
   ArrayList<String> arg0;
   ArrayList<String> arg1;
   ArrayList<String> arg2;
   String temp;
   String error;
   
   //class constructor
   public Parser(String input){
      this.input = input;
      tokens = new ArrayList<Token>();
      //isParsed = true;
      choice = 0;
      j = 0;
      this.accept = true;
      this.rejected = false;
      temp = "";
      error = "";
      errorIndex = 0;
   }
   
   /*
      Scan function will parse input tokens, and seperate tokens that are numbers, letters or other characters.
      Note: combinations of letters associate to the "id" type, meaning ids can't contain any special characters or numbers 
   */
   public int Scan(){
      //reinitialize the arguments
      arg0 = new ArrayList<String>();
      arg1 = new ArrayList<String>();
      arg2 = new ArrayList<String>();
      //create compiler for regex following the construct of params
      tokens = new ArrayList<Token>();
      Matcher m = Pattern.compile(params).matcher(input);
      while(m.find()){ 
         //groups seperated by regex statement. 3:ID or KeywWord, 4:Digit or Float, 6: other
         if(m.group(3)!=null){
            if(isKeyword(m.group(3))) tokens.add(new Token("KW", m.group(3), m.start()));
            else tokens.add(new Token("ID", m.group(3), m.start()));
         }
         else if(m.group(4)!=null){
            if(checkFloat(m.group(4))){
               tokens.add(new Token("FL", m.group(4), m.start()));
            }
            else{
               tokens.add(new Token("DI", m.group(4), m.start()));
            }
         }
         else if(m.group(6)!=null){
            tokens.add(new Token("SP", m.group(6), m.start()));
         }
      }
      //call parse function to return an integer, associated with a switch statement
      return Parse();
   }
   
   //checks if a digit is either a float or an int
   public boolean checkFloat (String check){
      for (int i = 0; i<check.length(); i++){
         if(check.charAt(i)=='.'){
            return true;
         }
      }
      return false;
   }
   
   /*
      The following three functions return argument lists for functions that call the sql commands and execute them.
      
      $ == empty

      a->b|exit
      b->c
      c->d;
      d->create_e|drop_n|save_o|load_o|insert_p|delete_u|tselect_w|select_w|convert_z|commit|input_ID
      e->database_ID|table_ID_f
      f->(h_g)
      g->,h_g|$
      h->ID_i
      i->integer_j|number_k|character(Digit)|date
      j->(Digit)|$
      k->(Digit_l)|$
      l->,Digit|$
      n->database_ID|table_ID
      o->database_ID
      p->into_ID_q_values_s
      q->(ID_r)|$
      r->,ID_r|$
      s->('ID't)|('Float't)|('Digit't)|('Digit/Digit/Digit't)
      t->,'ID't|,'Float't|,'Digit't|,'Digit/Digit/Digit't|$
      u->from_ID_v
      v->where_va|$
      va->ID_vb|Digit_vb
      vb-><_vc|<=_vc|<>_vc|=_vc|>_vc|>=_vc
      vc->ID|Digit
      w->*_u|(ID_wa)_u
      wa->,ID_r|$
      x->from_ID_v
      z->xml_ID_aa_AS_ID
      aa->,XSD_ID|$
   */
   public ArrayList<String> getArg0(){
      return this.arg0;
   }
   
   public ArrayList<String> getArg1(){
      return this.arg1;
   }
   
   public ArrayList<String> getArg2(){
      return this.arg2;
   }
   
   //checks if the given id is a keyword. Returns false if it is not a keyword
   public boolean isKeyword(String id){
      for(int i = 0; i < keywords.length; i++){
         if(keywords[i].equalsIgnoreCase(id)) return true;
      }
      return false;
   }
   
   //returns the index of the error in an incorrect sql command
   public int getError(){
      //return this.errorIndex;
      return errorIndex;
   }
   
   //used to set the accpet state to false. Caused by an incorrect input 
   public void setFalse(){
      if(!this.rejected){
         this.accept = false;            
         this.rejected = true;
         errorIndex = tokens.get(j).getIndex();
         //error = str;
      }
   }
   
   /*
      parses the tokens collected by the scanner, and checks if those tokens follow the grammar rules of sql
   */
   public int Parse(){
      this.accept = true;
      this.rejected = false;
      choice = 0;
      j = 0;
      if(tokens.size()>0){
         //runs the first state of the sql grammar
         a();
      }
      else setFalse();
      if(this.accept){
      }
      else{ 
         choice = 0;
      }
      //returns the choice of the sql command
      return choice;
   }
   
   /*
      All following methods with simple names like a, b, etc are different staes in the sql grammar. the gramma follows as:
      a->b|exit
      b->
   */
   void a(){
      if(tokens.get(j).getType().equals("KW")){
         for(int i = 0; i<keywords.length; i++){
            if(tokens.get(j).getName().equalsIgnoreCase(keywords[i])){
               b();
               break;
            }
         }
      }
      else if(tokens.get(j).getName().equalsIgnoreCase("exit")){
         choice = 12;
         acc("exit",true);
      }
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
   }
   
   void f(){
      if(tokens.get(j).getName().equals("(")){
         acc("(", false);
         h();
         g();
         acc(")", false);
      }
      else setFalse();
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
      else setFalse();
   }
   
   void h(){
      if(tokens.get(j).getType().equals("ID")){
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         i();
      }
      else setFalse();
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
      }
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
      }
      else setFalse();
   }
   
   void o(){
      if(tokens.get(j).getName().equalsIgnoreCase("database")){
         acc("database", true);
         arg0.add(tokens.get(j).getName());
         acc("ID", false);
      } 
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
                     else setFalse();
                        }
                     }
                  }
                  else if(tokens.get(j).getName().equals("'")){
                  }
                  else setFalse();
               }
               arg2.add(temp);
            }
            else setFalse();
            acc("'",false);
            t();
            acc(")", false);
         }
         else setFalse();
         
      }
      else setFalse();
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
                     else setFalse();
                        }
                     }
                  }
                  else if(tokens.get(j).getName().equals("'")){
                  }
                  else setFalse();
               }
               arg2.add(temp);
            }
            else setFalse();
            acc("'",false);
            t();
         }
         else setFalse();
      }
      else if(tokens.get(j).getName().equals(")")){
         return;
      }
      else setFalse();
      
   }
   
   void u(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("from", true);
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         v();
      }
      else setFalse();
   }
   
   void v(){
      if(tokens.get(j).getName().equalsIgnoreCase("where")){
         acc("where", true);
         va();
      }
      else if(tokens.get(j).getName().equals(";")){
         return;
      }
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
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
      else setFalse();
   }
   
   void x(){
      if(tokens.get(j).getName().equalsIgnoreCase("from")){
         acc("from", true);
         arg1.add(tokens.get(j).getName());
         acc("ID", false);
         v();
      }
      else setFalse();
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
      else setFalse();
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
      else setFalse();
   }
   
   /*
      compares the current string with the expected string, and will set accept to false if they don't match up with each other
   */
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
         else{ 
            setFalse();
         }
      }
      else{
         if(tokens.get(j).getName().equalsIgnoreCase(str)||tokens.get(j).getType().equalsIgnoreCase (str)){
            //System.out.println(str + " " + j + " " + (tokens.size()-1) + " " + tokens.get(j).getName());
            if(j < (tokens.size()-1)){
               j++;
            }
            //else this.complete = true;
         }
         else{ 
            setFalse();
         }
      }
   }
}

/*
   Token class will store necassary information of each token in the sql command
*/
class Token{
   /*
      type and name used for syntax checks, while index is used for error checking
   */
   private String type;
   private String name;
   private int index;
   
   public Token(String type, String name, int index){
      this.index = index;
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
   
   public int getIndex(){
      return this.index;
   }
   
   public void setIndex(int index){
      this.index = index;
   }
}

