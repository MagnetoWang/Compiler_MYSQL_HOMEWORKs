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
	* @date  2018年5月5日 下午9:23:25
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
	 * 初始化数据表格，数据头信息
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
	 * @see "VARCHAR2"  默认String
	 * "REAL"    暂时不处理
	 * @see "INT"     默认 int
	 * "DATE"	暂时不处理
	 * "BINARY_INTEGER"	暂时不处理
	 * @see "BOOLEAN"   暂时不处理
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
	 * 传入一行数据,和列名
	 * 打印一行数据
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
