import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * @author Magneto_Wang
 * @Description: 专门处理插入，修改，删除表格的操作。根据已有的读写库，进一步包装
 *
 */

public class SQL_CSVTOOLS {
	private static List<String[]> data=new LinkedList<>();
	private static List<String[]> writeData ;
	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	
	private static String currentTable="";
	private static int DataColumn=0;
	
	
	

	
	
	public boolean IfHasColunm(String path) throws IOException{
		if(path==null||path==""){
			System.out.println("输入无效的表格名称");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("输入无效的数据库名称");
			return false;
		}
		setCurrentTable(path);
		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		CsvReader cr = new CsvReader(path);
		boolean result = false;
		// 读头操作
		result = cr.readHeaders();
		boolean type=cr.readRecord();
		cr.close();
		return (result&&type);
		
	}
	

	/**
	 * 
	 * 只需要输入文件名称
	 * 默认是CSV格式
	 * 读取指定文件的全部内容
	 * 
	 * */
	public boolean ReadAll(String path) throws IOException {
		if(path==null||path==""){
			System.out.println("输入无效的表格名称");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("输入无效的数据库名称");
			return false;
		}
		setCurrentTable(path);


		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		CsvReader cr = new CsvReader(path);
		boolean result;
		List<String[]> data = new LinkedList<>();

		// 读头操作
		result = cr.readHeaders();
		// long colnmus=cr.getCurrentRecord();

		String[] readHeader = cr.getHeaders();
		String[] raw = new String[readHeader.length];
		System.out.println("readHeader.length " + readHeader.length);
		setDataColumn(readHeader.length);
//		System.out.println(readHeader.length);
//		for (String e : readHeader) {
//			System.out.print(e + "  ");
//
//		}
//		System.out.println();
		int i = 0;

		cr.readRecord();
		for (String e : readHeader) {
//			System.out.print(cr.get(e) + "  ");
			raw[i] = cr.get(e);
			i++;
		}
		data.add(readHeader);
		data.add(raw);
//		System.out.println();
		

		// 读数据操作
//		System.out.println("");
		while (cr.readRecord()) {
			raw = new String[readHeader.length];
			i = 0;
			for (String e : readHeader) {
//				System.out.print(cr.get(e) + "  ");
				raw[i] = cr.get(e);
				i++;
			}
			data.add(raw);
//			System.out.println();
		}
		setData(data);
		cr.close();
		
		return true;
	}
	
	/**
	 * 读取指定文件的全部内容
	 * @throws IOException 
	 * 
	 * */
	public boolean WriteAll(String path) throws IOException{
		if(path==null||path==""){
			System.out.println("输入无效的表格名称");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("输入无效的数据库名称");
			return false;
		}
		setCurrentTable(path);
		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		
		CsvWriter cw = new CsvWriter(path);
		List<String[]> data=getData();
		System.out.println("data.size() " + data.size());
		
		for(int i=0;i<data.size();i++){
			String[] raw=data.get(i);
			cw.writeRecord(raw);
		}
		
		cw.close();
		return true;
	}
	
	public boolean InsertColumn(String path,String[] OneLine) throws IOException{
		if(data.size()==0){
			
			return InsertOneLine(path,OneLine);
		}
		if(data.size()==1){
			
			return InsertOneLine(path,OneLine);
		}
		System.out.println("已经有字段列表，禁止插入新列表");
		return false;
		
	}
	/**
	 * 
	 * 按照指定列名插入对应位置,在已经有列表名的情况下
	 * 
	 * */
	public boolean InsertSpecifiedColumn(String path,String[] Column ,String[] OneLine) throws IOException{
		if(data.size()<2){
			System.out.println("请先定义列表属性");
			return false;
			
		}
		
		
		return false;
		
	}
	
	/**
	 * 插入值在最后一行
	 * @throws IOException 
	 * 
	 * */
	 public boolean InsertValue(String path,String[] OneLine) throws IOException{
		 if(path==null||path==""){
				System.out.println("输入无效的表格名称");
				return false;
			}
			if(getCurrentDatabase()==null||getCurrentDatabase()==""){
				System.out.println("输入无效的数据库名称");
				return false;
			}
			if(data.size()<2){
				System.out.println("请先定义列表属性");
				return false;
				
			}
			
			
		
			
			return InsertOneLine(path,OneLine);
		 
	 }

		
		/**
		 * 插入值在最后一行
		 * @throws IOException 
		 * 
		 * */
		 public boolean InsertSpecifiedValue(String path,String[] OneLine,int index) throws IOException{
			 if(path==null||path==""){
					System.out.println("输入无效的表格名称");
					return false;
				}
				if(getCurrentDatabase()==null||getCurrentDatabase()==""){
					System.out.println("输入无效的数据库名称");
					return false;
				}
				if(data.size()<2){
					System.out.println("请先定义列表属性");
					return false;
					
				}
				

				
				
			
				
				return InsertspecifiedLine(path,OneLine,index);
			 
		 }
	
	/**
	 * 在末尾插入一行
	 * 
	 * */
	 
	 public boolean InsertOneLine(String path,String[] OneLine) throws IOException{
			if(path==null||path==""){
				System.out.println("输入无效的表格名称");
				return false;
			}
			if(getCurrentDatabase()==null||getCurrentDatabase()==""){
				System.out.println("输入无效的数据库名称");
				return false;
			}
			List<String[]> data=getData();
			

			
			
			
			path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
			
			

			
//			CsvWriter cw = new CsvWriter(path);

			System.out.println("插入值 ： "+OneLine);
			data.add(OneLine);
//			setData(data);
			
//			CsvWriter cw = new CsvWriter(path);
//			cw.close();
			

//			cw.writeRecord(OneLine);
			

			WriteAll(getCurrentTable());
			
			return true;
		 
	 }
	 
	/**
	 * @todo 在指定位置插入一行，不包括两行的头描述
	 * 
	 * 
	 */
	public boolean InsertspecifiedLine(String path,String[] OneLine,int index) throws IOException {
		if(path==null||path==""){
			System.out.println("输入无效的表格名称");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("输入无效的数据库名称");
			return false;
		}
		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		
		index = index + 1;
		List<String[]> data = getData();
		if(data.size()<index){
			System.out.println("错误：插入的位置大于列表长度");
			return false;
		}
		CsvWriter cw = new CsvWriter(path);

		


		data.add(index, OneLine);
		setData(data);
		WriteAll(getCurrentTable());
		cw.close();
		return true;
	}
	
	
	
	
	

	/**
	 * 测试读写功能
	 * 
	 * 因为这不是工业库。而且API很不全。需要对应的源码来
	 * 不能直接读写。必须先有内部的读操作，然后才能获得文本数据相关内容。类似于先放入一个缓冲区。
	 * 然后才在缓冲区里面操作
	 * IO是个非常烦琐操作。这里必须包装好对应的工具才行
	 * 
	 * */
	@Test
	public void TestReadAll() throws IOException{
		
		String path="src/mysql/JUST_FOR_TEST/STUDENT.csv";
		CsvReader cr = new CsvReader(path);
		boolean result ;
		List<String[]> data=getData();
		
		
		//读头操作
		result=cr.readHeaders();
//		long colnmus=cr.getCurrentRecord();
		
		System.out.println("result "+result);
		String[] readHeader =cr.getHeaders();
		String[] raw=new String[readHeader.length];
		System.out.println(readHeader.length);
		for(String e:readHeader){
			System.out.print(e+"  ");
			
		}
		System.out.println();
		int i=0;
		
		cr.readRecord();
		for(String e:readHeader){
			System.out.print(cr.get(e)+"  ");
			raw[i]=cr.get(e);
			i++;
		}
		data.add(readHeader);
		data.add(raw);
		System.out.println();
		
		
		//读数据操作
		while(cr.readRecord()){
			raw=new String[readHeader.length];
			i=0;
			for(String e:readHeader ){
				System.out.print(cr.get(e)+"  ");
				raw[i]=cr.get(e);
				i++;
			}
			data.add(raw);
			System.out.println();
		}
		setData(data);
		cr.close();
	}
	
	@Test
	public void TestWriteAll() throws IOException{
		String path="src/mysql/JUST_FOR_TEST/TEST.csv";
		CsvWriter cw = new CsvWriter(path);
//		TestReadAll();
		List<String[]> data=getData();
		
		for(int i=0;i<data.size();i++){
			String[] raw=data.get(i);
			
			cw.writeRecord(raw);
		}
		cw.close();
		
	}
	/**
	 * 在末尾插入一行
	 * 
	 * */
	 @Test
	 public void TestInsertOneLine() throws IOException{
			String path="src/mysql/JUST_FOR_TEST/TEST.csv";
			CsvWriter cw = new CsvWriter(path);
			TestReadAll();
			List<String[]> data=getData();
			
			String str="x";
			String[] OneLine=new String[data.get(0).length];
			for(int i=0;i<data.get(0).length;i++){
				OneLine[i]=str;
			}
			data.add(OneLine);
			setData(data);
			TestWriteAll();
			cw.close();
		 
	 }
	 
	/**
	 * @todo 在指定位置插入一行，不包括两行的头描述
	 * 
	 * 
	 */
	@Test
	public void TestInsertspecifiedLine() throws IOException {
		String path = "src/mysql/JUST_FOR_TEST/TEST.csv";
		int index = 7 + 1;
		CsvWriter cw = new CsvWriter(path);
		TestReadAll();
		List<String[]> data = getData();
		if(data.size()<index){
			System.out.println("插入的位置大于列表长度");
			
		}

		String str = "y";
		String[] OneLine = new String[data.get(0).length];
		for (int i = 0; i < data.get(0).length; i++) {
			OneLine[i] = str;
		}
		data.add(index, OneLine);
		setData(data);
		TestWriteAll();
		cw.close();
	}
		 
	/**
	 * @todo 在删除最后一行，不包括两行的头描述
	 * 
	 * 
	 */
	@Test
	public void DeletespecifiedLine() throws IOException {
		String path = "src/mysql/JUST_FOR_TEST/TEST.csv";
		int index = 5 + 2;
		CsvWriter cw = new CsvWriter(path);
		TestReadAll();
		List<String[]> data = getData();

		String str = "x";
		String[] OneLine = new String[data.get(0).length];
		for (int i = 0; i < data.get(0).length; i++) {
			OneLine[i] = str;
		}
		data.add(index, OneLine);
		setData(data);
		TestWriteAll();
		cw.close();
	}
	
	public List<String[]> getData() {
		return data;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public String getCurrentDatabase() {
		return currentDatabase;
	}

	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}

	public String getCurrentTable() {
		return currentTable;
	}

	public void setCurrentTable(String currentTable) {
		this.currentTable = currentTable;
	}

	public int getDataColumn() {
		return DataColumn;
	}

	public void setDataColumn(int dataColumn) {
		DataColumn = dataColumn;
	}





	public static List<String[]> getWriteData() {
		return writeData;
	}


	public static void setWriteData(List<String[]> writeData) {
		SQL_CSVTOOLS.writeData = writeData;
	}
	

}
