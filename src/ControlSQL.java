

import java.util.Scanner;

public class ControlSQL {
	
	private Token head ;
	private Token tail ;
	
	public ControlSQL(Token head,Token tail) {
		this.head = head ;
		this.tail = tail ;
	}
	@SuppressWarnings({ "static-access", "deprecation", "resource" })
	public static void main(String[] args) {
		

	      System.out.println("============================");
	      System.out.println("\u5a06\u3223\u7e4b\u6d63\u8de8\u6564\u7ee0\ufffd\u9357\u66e0\u5897\u93c8\ue102\u6b91MYSQL\u7f02\u682c\u7627\u9363\ufffd");
	      System.out.println("\u9417\u581f\u6e70\u9359\u51e4\u7d300049");
	      System.out.println("Welcome to the easy MYSQL");
	      System.out.println("Version 0049");
	      
	      
	      System.out.println("Please input Start for StartMySQL");
	      





	      System.out.println("============================");
			Scanner in =new Scanner(System.in);
			 java.io.StringReader sr = new java.io.StringReader( in.nextLine() );
		  		java.io.Reader r = new java.io.BufferedReader( sr );
			 MySQL parser;

		      parser = new MySQL(r);
		      
	      
	      
	      while (true)
	    {
	      // System.out.println("Reading from standard input...");
	      System.out.println();
	      System.out.print("MYSQL>>");
	      // System.out.println();
	      try
	      {
	 		  sr = new java.io.StringReader( in.nextLine() );
		  		 r = new java.io.BufferedReader( sr );
		  		MySQL.ReInit(r);
		  		MySQLTokenManager TM=parser.token_source;
	        MySQL.one_line();
	        @SuppressWarnings("unused")
			Token TokenTest =  MySQL.getToken(2);
	        SimpleCharStream StreamTest = MySQL.jj_input_stream;
//	        System.out.println(StreamTest.getColumn());
//	        System.out.println(StreamTest.getBeginLine());
//	        System.out.println(StreamTest.GetImage());
//	        System.out.println(StreamTest.GetSuffix(StreamTest.getColumn()-5));
//	        System.out.println(StreamTest.GetSuffix(StreamTest.getColumn()));
//					System.out.println(MySQL.token);
//					System.out.println(MySQL.jj_nt);
//					System.out.println(TM.toString());
//					System.out.println(TM.toString());




	        

	      }
	      catch (Exception e)
	      {
	        System.out.println("NOK.");
	        System.out.println(e.getMessage());
	        MySQL.ReInit(System.in);
	      }
	      catch (Error e)
	      {
	        System.out.println("Oops.");
	        System.out.println(e.getMessage());
	        break;
	      }
	    }
	}
}
