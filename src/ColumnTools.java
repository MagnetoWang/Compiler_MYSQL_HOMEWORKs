import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author Magneto_Wang
 *
 */
public class ColumnTools {

	/** 
	*
	* @ClassName : ColumnTools.java
	* @author : Magneto_Wang
	* @date  2018��5��5�� ����9:23:25
	*/
	
	private List<String> ColumnName =new LinkedList<>();
	private List<String> ColumnValue =new LinkedList<>();
	private List<String> CompareTo =new LinkedList<>();
	private List<Integer[]> Result = new LinkedList<>();
	
	private List<String[]> data;
	private HashMap<String, String> Header = new HashMap<>();
	
	



	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	private static String currentTable="";
	
	
	public FileTools fileOperation = new FileTools();
	public SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
	public List<String> Operation = new LinkedList<>();
	
	
	/*
	 * ��ʼ�����ݱ������ͷ��Ϣ
	 * 
	 */
	public boolean Init(){
		HashMap<String, String> Header = getHeader();
		List<String[]> data =getData();
		int i=0;
		for(String e : data.get(0)){
			Header.put(e, data.get(1)[i]);
			i++;
		}

		
		
		return true;
		
		
	}
	


	
	/**
	 * 
	 * @see "CHAR"
	 * @see "VARCHAR"
	 * @see "VARCHAR2"  Ĭ��String
	 * "REAL"    ��ʱ������
	 * @see "INT"     Ĭ�� int
	 * "DATE"	��ʱ������
	 * "BINARY_INTEGER"	��ʱ������
	 * @see "BOOLEAN"   ��ʱ������
	 * 
	 * 
	 */
	public String getType(String ColumnName){
		HashMap<String, String> Header = getHeader();
		return Header.get(ColumnName);
	}
	
	
	public Integer[] StingArrayToIntArray(String[] OneLine){
		Integer[] TransferOneLine = new Integer[OneLine.length];
		int i=0;
		for(String e : OneLine){
			TransferOneLine[i] = Integer.parseInt(e);
		}
		return TransferOneLine;
		
		
	}





	/**
	 * 
	 *  ( "=" | "!="  | "<>" | ">" | ">=" | "<" | "<=")
	 * 
	 */
	public boolean LogicalExpression(){
		



		return true;
	}



	public boolean OperationAnd(String ColumnName,String CompareTo,String Value){

		return true;
	}

	public boolean OperationOr(){

		return true;
	}





		/*
	 * ����һ������,������
	 * ��ӡһ������
	 * 
	 */
	public void printRaw(String[] OneLine,String[] ColumnName){
		
		int length =ColumnName.length;
		int diff=0;
		for(int i =0 ;i<length;i++){
			if(ColumnName[i].length()==OneLine[i].length()){
				diff=ColumnName[i].length();
				for (int space = 0; space < diff; space++) {
					System.out.print("-");
				}
				System.out.print("-");
			}
			if(ColumnName[i].length()>OneLine[i].length()){
				diff=ColumnName[i].length();
				for (int space = 0; space < diff; space++) {
					System.out.print("-");
				}
				System.out.print("-");
			}
			
			if(ColumnName[i].length()<OneLine[i].length()){
				diff=OneLine[i].length();
				for (int space = 0; space < diff; space++) {
					System.out.print("-");
				}
				System.out.print("-");
			}
		}
		System.out.println();
		
		for(int i =0 ;i<length;i++){
			if(ColumnName[i].length()==OneLine[i].length()){
				System.out.print(OneLine[i]);
			}
			if(ColumnName[i].length()>OneLine[i].length()){
				diff=ColumnName[i].length()-OneLine[i].length();
				System.out.print(OneLine[i]);
//				for (int space = 0; space < diff; space++) {
//					System.out.print(" ");
//				}
				
				
				
			}
			
			if(ColumnName[i].length()<OneLine[i].length()){
				diff=OneLine[i].length()-ColumnName[i].length();
				System.out.print(OneLine[i]);
//				for (int space = 0; space < diff; space++) {
//					System.out.print(" ");
//				}
//				System.out.print("|");
			}
			System.out.print(" ");
			System.out.print("|");
		}
		System.out.println();
	}



	
	@Test
	public void TestprintRaw( ){
		String variable ="Allen";
		int length =variable.length();
		for(int j =0 ;j<length;j++){
			for(int i =0 ;i<=length;i++){
				System.out.print('-');
			}
			System.out.print("--");
		}

		System.out.println();
		for(int i =0 ;i<length;i++){
			System.out.print(variable+" | ");
		}
	}
	
	
	
	
	
	public List<String> getColumnName() {
		return ColumnName;
	}


	public void setColumnName(List<String> columnName) {
		ColumnName = columnName;
	}


	public List<String> getColumnValue() {
		return ColumnValue;
	}


	public void setColumnValue(List<String> columnValue) {
		ColumnValue = columnValue;
	}


	public List<Integer[]> getResult() {
		return Result;
	}


	public void setResult(List<Integer[]> result) {
		Result = result;
	}


	public static String getCurrentPath() {
		return currentPath;
	}


	public static void setCurrentPath(String currentPath) {
		ColumnTools.currentPath = currentPath;
	}


	public static String getCurrentDatabase() {
		return currentDatabase;
	}


	public static void setCurrentDatabase(String currentDatabase) {
		ColumnTools.currentDatabase = currentDatabase;
	}


	public static String getCurrentTable() {
		return currentTable;
	}


	public static void setCurrentTable(String currentTable) {
		ColumnTools.currentTable = currentTable;
	}


	public List<String[]> getData() {
		return data;
	}


	public void setData(List<String[]> data) {
		this.data = data;
	}




	public HashMap<String, String> getHeader() {
		return Header;
	}




	public void setHeader(HashMap<String, String> header) {
		Header = header;
	}




	public List<String> getCompareTo() {
		return CompareTo;
	}




	public void setCompareTo(List<String> compareTo) {
		CompareTo = compareTo;
	}
	

	
	
		
		
		
		 
//		for(int i =0 ;i<length;i++){
//			if(ColumnName[i].length()>OneLine[i].length()){
//				diff=ColumnName[i].length();
//			}else{
//				diff=OneLine[i].length();
//				
//			}
//			for(int space =0;space<diff;space++){
//				System.out.print("-");
//			}
////			System.out.print("--");
//		}
//
//		System.out.println();
//		for(int i =0 ;i<length;i++){
//			
//			System.out.print(OneLine[i]);
//			if(ColumnName[i].length()>OneLine[i].length()){
//				diff=ColumnName[i].length()-OneLine[i].length();
//			}else{
//				diff=ColumnName[i].length()-OneLine[i].length();
//				diff=0-diff;
//			}
//			if(diff==0){
//				diff=1;
//			}
//			for(int space =0;space<diff;space++){
//				System.out.print(" ");
//			}
//			
//			
//		}
	
	
	
}
