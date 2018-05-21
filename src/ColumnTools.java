import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import sun.security.util.Cache.EqualByteArray;

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
	private List<String> ShowColumn=new LinkedList<>();
	
	private List<String[]> data;
	private HashMap<String, String> Header = new HashMap<>();
	private HashMap<String, Integer> HeaderColumn = new HashMap<>();
	



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
		i=0;
		for(String e : data.get(0)){
			HeaderColumn.put(e, i);	
			i++;
		}
		return true;
		
		
	}
	
	public List<Integer[]> whereStatement(){
		
		LogicalExpression();
		ExecAnd_Or();
		 return Result;
	}
	public void ExecAnd_Or(){
		List<String[]> data =getData();
		List<Integer[]>  result =getResult();
		List<String> newOperation =new LinkedList<>();
		for(int i=0;i<Operation.size();i++){
			if(Operation.get(i).equals("AND")==true){
				OperationAnd(i,i+1);
				
			}
//			if(Operation.get(i).equals("OR")==true){
//				newOperation.add("OR");	
//			}

		}
		for(int i=0;i<Operation.size();i++){
			if(Operation.get(i).equals("OR")==true){
				OperationOr(i,i+1);
				
			}

		}
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
	 * @param OneLine
	 * @see List<Integer> 转化 Integer[] 工具
	 * @return
	 */
	public Integer[] ListArrayToIntArray(List<Integer> OneLine){
		Integer[] TransferOneLine = new Integer[OneLine.size()];
		
		for(int i=0;i<OneLine.size();i++){
			TransferOneLine[i]=OneLine.get(i);
		}
		return TransferOneLine;
	}






	public String Equal="=";
	public String Unequal ="!=";
	public String More=">";
	public String MoreThan=">=";
	public String Less="<";
	public String LessThan="<=";
	/**
	 * @see 集中处理所有表达式的结果
	 * 目前没有实现逻辑表达式运算，数据类型分类。只是完成了字符串的比较
	 * 
	 * @读取表达式的函数
	 *  ( "=" | "!="  | "<>" | ">" | ">=" | "<" | "<=")
	 * 
	 */
	public boolean LogicalExpression(){
		if(CompareTo.size()!=ColumnName.size()
				||ColumnName.size()!=ColumnValue.size()
				||CompareTo.size()!=ColumnValue.size()){
			return false;
		}
		int i=0;
		for(String e : CompareTo){
			String name=ColumnName.get(i);
			String value=ColumnValue.get(i);
			i++;
			if(e.equals(Equal)==true){
				ExecEqual(name,value);
			}
			if(e.equals(Unequal)==true){
				ExecUnequal(name, value);
			}
			if(e.equals(More)==true){
				ExecMore(name, value);
			}
			if(e.equals(MoreThan)==true){
				ExecMoreThan(name, value);
			}
			if(e.equals(Less)==true){
				ExecLess(name, value);
			}
			if(e.equals(LessThan)==true){
				ExecLessThan(name, value);
			}
		}
		


		return true;
	}
	public boolean ExecEqual(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].equals(value)==true){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}
	public boolean ExecUnequal(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].equals(value)==false){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}
	public boolean ExecMore(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].compareTo(value)>0){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}
	public boolean ExecMoreThan(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].compareTo(value)>=0){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}	
	public boolean ExecLess(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].compareTo(value)<0){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}
	public boolean ExecLessThan(String name,String value){
		if(name==null||value==null){
			return false;
		}
		List<String[]> data =getData();
		int index = HeaderColumn.get(name);
		List<Integer> ResultLine=new LinkedList<>();
		for(int i=2;i<data.size();i++){
			if(data.get(i)[index].compareTo(value)<=0){
				ResultLine.add(i);
			}
		}
		Result.add(ListArrayToIntArray(ResultLine)) ;
		return true;
		
	}

	public boolean OperationAnd(int front, int behind){
		Integer[] Frontresult =Result.get(front);
		Integer[] Behindresult =Result.get(behind);
//		Set<Integer> FrontSet=IntegerArrayToSet(Frontresult);
//		Set<Integer> BehindSet=IntegerArrayToSet(Behindresult);
		Set<Integer> setAll = new HashSet<>();
		for(Integer e : Frontresult){
			setAll.add(e);
		}
		for(Integer e : Behindresult){
			setAll.add(e);
		}
		Integer[] result=new Integer[setAll.size()];
		int i=0;
		for(Integer e: setAll){
			result[i]=e;
			i++;
		}
		Result.set(front,	 result);
		Result.set(behind,	 result);
		
		
		return true;
	}
	
	public Set<Integer> IntegerArrayToSet(Integer[] intArray){
		Set<Integer> setInteger = new HashSet<>();
		for(Integer e : intArray){
			setInteger.add(e);
		}
		return setInteger;
	}

	public boolean OperationOr(int front, int behind){

		return true;
	}
	
	
	/*
	 * 传入一行数据,和列名
	 * 单纯的打印某一行数据。只有一行数据，不包括任何多余信息
	 * 
	 */
	public void printOneLine(String[] OneLine,String[] ColumnName){
		int length =ColumnName.length;
		int diff=0;
	
		
		for(int i =0 ;i<length;i++){
			if(ColumnName[i].length()==OneLine[i].length()){
				System.out.print(OneLine[i]);
			}
			if(ColumnName[i].length()>OneLine[i].length()){
				diff=ColumnName[i].length()-OneLine[i].length();
				System.out.print(OneLine[i]);

				
				
			}
			
			if(ColumnName[i].length()<OneLine[i].length()){
				diff=OneLine[i].length()-ColumnName[i].length();
				System.out.print(OneLine[i]);

			}
			System.out.print(" ");
			System.out.print("|");
		}
		
	}
	
	





	/*
	 * @return  传入一行数据,和列名
	 *  打印一行数据包括列名
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





	public List<String> getShowColumn() {
		return ShowColumn;
	}





	public void setShowColumn(List<String> showColumn) {
		ShowColumn = showColumn;
	}

	public HashMap<String, Integer> getHeaderColumn() {
		return HeaderColumn;
	}

	public void setHeaderColumn(HashMap<String, Integer> headerColumn) {
		HeaderColumn = headerColumn;
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
