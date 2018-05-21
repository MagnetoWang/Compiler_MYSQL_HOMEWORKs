import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;



/**
 * @
 * @see 
 * 因为select的功能过于复杂。为了以后可扩展行。这次是先设计对象，然后再来写代码
 * 这次相比之前应该说，代码更加稳健和成熟
 * 首先，采用非静态的类。每次执行select语句都是重新new
 * 减少大量的维护工作量
 * 
 * 
 */
public class Select {
	//存放最终选择的行
	private List<Integer[]> Result = new LinkedList<>();
	//存放 选择的表名
	private List<String> TableName= new LinkedList<>();
	//存放打印的列表
	private List<String> ShowColumn = new LinkedList<>();
	//检查有无 where
	private boolean IsWhere = false;
	//检查有无*
	private boolean IsStart = false;
	//存放整张表
	private List<String[]> data = new LinkedList<>();
	
	

	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	private static String currentTable="";
	
	
	public FileTools fileOperation = new FileTools();
	public SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
	public ColumnTools columnTools = new ColumnTools();
	
	
	
	public boolean Init() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			System.out.println("路径错误");
			return false;
		}
		csvtools.setCurrentDatabase(currentDatabase);
		csvtools.setCurrentTable(currentTable);
		columnTools.setCurrentDatabase(currentDatabase);
		columnTools.setCurrentTable(currentTable);
		csvtools.ReadAll(currentTable);
		setData(csvtools.getData());
		columnTools.setData(data);
		columnTools.Init();
		
		return true;
	}
	
	/**
	 * @see
	 * 在之前的设计中一直没有专门写一个完整检查机制
	 * 现在结合之前零零散散的检查，整合起来
	 * 后续如果完全更新，可以加入基本工具操作中
	 * 
	 */
	public boolean CheakPath(String path) throws IOException{
		if(path==""||path==null){
			System.out.println("无效数据表");
			return false;
		}
		String checkPath=getCurrentPath()+"/"+getCurrentDatabase();
		String[] files = fileOperation.getAllFiles(checkPath);
		checkPath =path+".csv";
		for(String e : files){
			if(e.equals(checkPath)){
				
				return csvtools.IfHasColunm(path);
			}
		}
		System.out.println("当前数据库，没有这个数据表");
		return false;
		
	}
	/**
	 * @Description  打印表，带星号，无where语句
	 * 
	 * 
	 */
	public boolean PrintStarWithSingleTableWithoutWhere() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			return false;
		}
		if(getTableName().size()==1){
			String Table=getTableName().get(0);
			setCurrentTable(Table);
			Init();
			PrintAllRaw(Table);
			

		}else{
			System.out.println("打印表失败");
			return false;
		}
		
		
		return true;
	}
	/**
	 * @see
	 * 打印表，无星号，单表，指定列，无where子句
	 * 
	 */
	
	public boolean PrintSpecifiedColumnsWithouWhere() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			return false;
		}
		if(getTableName().size()==1){
			String Table=getTableName().get(0);
			setCurrentTable(Table);
			Init();
//			columnTools.setShowColumn(getShowColumn());
			csvtools.ReadAll(getCurrentTable());
			setData(csvtools.getData());
			
			int[] row=new int[data.size()-2];
			for(int i=0;i<row.length;i++){
				row[i]=i;
			}
			String[] oneLine = csvtools.ListTransferOneLine(getShowColumn());
			
			PrintColumn(getCurrentTable(), oneLine, row);
			
			
			
			

		}else{
			System.out.println("打印表失败");
			return false;
		}
		
		
		return true;
	}
	/**
	 * @see
	 * 在where子语句下。打印指定列或者*号情况
	 * 
	 */
	public boolean PrintWhere() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			System.out.println("路径错误");
			return false;
		}
		Result = columnTools.whereStatement();
		/**
		 * @see
		 * TODO 提取表达式，先and语句处理，然后是or语句
		 * 
		 * 行数确定好，再进行列数的确定
		 * 
		 */
		
		if(IsStart==true){

			
			

		}else{
			
		}
		
		
		return false;
	}
	
	
	/**
	 * @see
	 * 已知行
	 * 
	 * 打印所有列,也就是 符号 *
	 */
	
	public boolean PrintAllColumn(String path,int[] rows) throws IOException{
		
		if(CheakPath(path)==false){
			return false;
		}

		if(rows==null&&rows.length==0){
			System.out.println("未指定行号");
			return false;
		}
		



		csvtools.ReadAll(path);
		setData(csvtools.getData());
		List<String[]> data = getData();
		String[] columnName = data.get(0);
		PrintFisrtTwoRaw(path);
		



		
		for (int i=0;i<rows.length;i++) {
			PrintRaw(path, i);
		}
		System.out.println();
		return true;
		
	}
	
	
//	/**
//	 * 打印指定一系列的列
//	 * 无where
//	 * 
//	 */
//	public boolean PrintSpecifiedColumn(){
//		
//	}
	
	/**
	 * 
	 * @see
	 * 已知行
	 * 打印指定列
	 * 
	 * 指定列名，指定行数
	 * 
	 */
	
	public boolean PrintColumn(String path,String[] columnName,int[] rows) throws IOException{
		
		if(CheakPath(path)==false){
			return false;
		}
		if(columnName==null&&columnName.length==0){
			System.out.println("未指定列名");
			return false;
		}
		if(rows==null&&rows.length==0){
			System.out.println("未指定行号");
			return false;
		}
		
		

		
//		csvtools.setCurrentDatabase("TEST");
		List<String[]> data = getData();
		HashMap<String, Integer> ColumnPosition = new HashMap<>();
		for(int i = 0 ;i <data.get(0).length;i++){
			ColumnPosition.put(data.get(0)[i], i);
		}
		
		columnTools.printOneLine(columnName, columnName);
		System.out.println();
		String[] OneLine =new String[columnName.length];
		for(int i=0;i<rows.length;i++){
			for(int j = 0;j<columnName.length;j++){
				OneLine[j]=data.get(i+2)[ColumnPosition.get(columnName[j])];
			}
			columnTools.printOneLine(OneLine, columnName);
			System.out.println();
		}
		
		
		
		
		
		return true;
		
		
	}
	
	/**
	 * 
	 * @see
	 * 打印所有行
	 */
	
	
	public boolean PrintAllRaw(String path) throws IOException{
		if(CheakPath(path)==false){
			return false;
		}
		csvtools.ReadAll(path);
		setData(csvtools.getData());
		List<String[]> data = getData();
		columnTools.printRaw(data.get(0),data.get(0));		
//		System.out.println();
		for(int i=2;i<data.size();i++){
			columnTools.printRaw(data.get(i), data.get(0));
		}
//		System.out.println();
		
		return true;
		
		

		
	}
	
	/**
	 * 
	 * @see
	 * 打印列表参数
	 * 
	 */
	public boolean PrintFisrtTwoRaw(String path) throws IOException{
		if(CheakPath(path)==false){
			return false;
		}
		
		



		List<String[]> data = getData();
		columnTools.printRaw(data.get(0),data.get(0));
		System.out.println();
		columnTools.printRaw(data.get(1),data.get(0));
		System.out.println();
		
		
		
		
		return true;
		
	}
	
	
	
	/**
	 * 
	 * @see
	 * 只打印一行，不带列表参数
	 * 
	 */
	public boolean PrintRaw(String path,int index) throws IOException{
		if(CheakPath(path)==false){
			return false;
		}
		
		index+=2;
		List<String[]> data = getData();
		columnTools.printRaw(data.get(index),data.get(0));
		System.out.println();
		return true;
		
	}
	
	
	
	/**
	 * 
	 * 
	 * @see
	 * 只打印一行，带列表参数
	 */
	
	public boolean PrintOnlyRaw(String path,int index) throws IOException{
		if(CheakPath(path)==false){
			return false;
		}
		
//		String path="src/mysql/TEST/TEST.csv";
//		String fileName="TEST";
		index+=2;

		csvtools.ReadAll(path);
		setData(csvtools.getData());
		List<String[]> data = getData();
		columnTools.printRaw(data.get(0),data.get(0));

		System.out.println();
		
		columnTools.printRaw(data.get(index),data.get(0));
		return true;
	}
	
	
	/**
	 * @see
	 * 
	 * 已知行
	 * 打印所有列
	 */
	@Test
	public void TestPrintAllColumn(){
		
	}
	
	/*
	 * 已知行
	 * 打印指定列
	 */
	@Test
	public void TestPrintColumn() throws IOException{
		
		String fileName="TEST";
		int index=5+1;
		int[] columns ={0,1,2,3,4,5};
		csvtools.setCurrentDatabase("TEST");
		csvtools.ReadAll(fileName);
		setData(csvtools.getData());
		List<String[]> data = getData();
		String[] columnName = data.get(0);
		for(int i=0;i<columns.length;i++){
			System.out.print(columnName[columns[i]] + " ");
		}
		System.out.println();

		String[] columnValue =data.get(index);
		for (int i=0;i<columns.length;i++) {
			System.out.print(columnValue[columns[i]]+"||");
		}
		System.out.println();
		
		
		
	}
	
	/*
	 * 打印所有行
	 */
	
	@Test
	public void TestPrintAllRaw() throws IOException{
		
		String fileName="TEST";
		int index=5+1;
		csvtools.setCurrentDatabase("TEST");
		csvtools.ReadAll(fileName);
		setData(csvtools.getData());
		List<String[]> data = getData();
		for (String e : data.get(0)) {
			System.out.print(e + " ");
		}
		System.out.println();
		for(int i =1;i<data.size();i++){
			for (String e : data.get(i)) {
				System.out.print(e + " ");
			}
			System.out.println();
			
		}


		
	}
	
	/*
	 * 打印指定行
	 */
	@Test
	public void TestPrintRaw() throws IOException{
		
//		String path="src/mysql/TEST/TEST.csv";
		String fileName="TEST";
		int index=5+1;
		csvtools.setCurrentDatabase("TEST");
		csvtools.ReadAll(fileName);
		setData(csvtools.getData());
		List<String[]> data = getData();
		columnTools.printRaw(data.get(0),data.get(0));
//		for (String e : data.get(0)) {
//			System.out.print(e + " ");
//		}
		System.out.println();
		
		columnTools.printRaw(data.get(index),data.get(0));

//		for (String e : data.get(index)) {
//			System.out.print(e + " ");
//		}
//		System.out.println();


		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Integer[]> getResult() {
		return Result;
	}
	public void setResult(List<Integer[]> result) {
		Result = result;
	}
	public List<String> getTableName() {
		return TableName;
	}
	public void setTableName(List<String> tableName) {
		TableName = tableName;
	}
	public List<String> getShowColumn() {
		return ShowColumn;
	}
	public void setShowColumn(List<String> showColumn) {
		ShowColumn = showColumn;
	}
	public boolean isIsWhere() {
		return IsWhere;
	}
	public void setIsWhere(boolean isWhere) {
		IsWhere = isWhere;
	}
	public static String getCurrentPath() {
		return currentPath;
	}
	public static void setCurrentPath(String currentPath) {
		Select.currentPath = currentPath;
	}
	public static String getCurrentDatabase() {
		return currentDatabase;
	}
	public static void setCurrentDatabase(String currentDatabase) {
		Select.currentDatabase = currentDatabase;
	}
	public static String getCurrentTable() {
		return currentTable;
	}
	public static void setCurrentTable(String currentTable) {
		Select.currentTable = currentTable;
	}
	public List<String[]> getData() {
		return data;
	}


	public void setData(List<String[]> data) {
		this.data = data;
	}

	public boolean isIsStart() {
		return IsStart;
	}

	public void setIsStart(boolean isStart) {
		IsStart = isStart;
	}
	
	

	
	
	
	
	

}
