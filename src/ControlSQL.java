

import java.util.Scanner;

public class ControlSQL {
	
	private Token head ;
	private Token tail ;
	
	public ControlSQL(Token head,Token tail) {
		this.head = head ;
		this.tail = tail ;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		

	      System.out.println("============================");
	      System.out.println("��ӭʹ�ü򵥰汾��MYSQL������");
	      System.out.println("�汾�ţ�0049");
		  System.out.println("Welcome to the easy MYSQL");
	      System.out.println("Version 0049");
	      
	      
	      System.out.println("Please input Start for StartMySQL");
	      





	      System.out.println("============================");
			Scanner in =new Scanner(System.in);
			String text=in.nextLine();
			 java.io.StringReader sr = new java.io.StringReader( text );
		  		java.io.Reader r = new java.io.BufferedReader( sr );
			
			MySQL parser=new MySQL(r);

		    
		      
	      
	      
	      while (true)
	    {
	      // System.out.println("Reading from standard input...");
	      System.out.println();
	      System.out.print("MYSQL>>");
	      // System.out.println();
	      try
	      {    text=in.nextLine();
	 		   sr = new java.io.StringReader( text );
		  		 r = new java.io.BufferedReader( sr );
		  		 
		  		System.out.println("============================");
		  		
		  		System.out.println("��ʼ�ʷ�����");
		  		MySQL.state=MySQL.LEX;
//		  		System.out.println("============================");
		  		parser.ReInit(r);
		  		parser.one_line();
		  		
//		  		System.out.println("============================");
		  		
		  		System.out.println("����������ʽ");
		  		System.out.println("============================");
		  		System.out.println();
		  		
		  		
		  		System.out.println("============================");
		  		
		  		System.out.println("��ʼ�﷨����");
		  		System.out.println("============================");
		  		
		  		
		  		MySQL.state=MySQL.AST;
		 		  sr = new java.io.StringReader( text );
			  		 r = new java.io.BufferedReader( sr );
		  		
		  		parser.ReInit(r);
		  		parser.one_line();
		  		
		  		
		  		System.out.println("============================");
		  		
		  		System.out.println("��ʼ�﷨�Ƶ�����");
		  		System.out.println("============================");
		  		
		  		MySQL.state=MySQL.SDT;
		 		  sr = new java.io.StringReader( text );
			  		 r = new java.io.BufferedReader( sr );
		  		
		  		parser.ReInit(r);
		  		parser.one_line();
		  		
		  		

		  		
//		  		MySQLTokenManager TM=parser.token_source;
//	        
//	        @SuppressWarnings("unused")
//			Token TokenTest =  MySQL.getToken(2);
//	        SimpleCharStream StreamTest = MySQL.jj_input_stream;
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
	      finally {
			
		}
	    }
	}
}
