import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import com.csvreader.CsvWriter;

public class Create {
	private String C_Databas="DATABASE";
	private String C_Tables="TABLE";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	private String Table="";
	public FileTools fileOperation = new FileTools();
	private HashMap<String, String> table_column = new HashMap<>();
	private String column_name="";
	private String column_value="";
	
	/**
	 * 
	 * csv 格式 一行行写 传入数据String[]
	 * */
	@Test
	public void WriteCSV()  throws IOException{  
       
	
       CsvWriter wr = new CsvWriter(getCurrentPath()+"/"+"test.csv",',', Charset.forName("gb2312"));  
       String[] header = { "Name","Province","City","Address","Tel","Website","Server_content","Jigou_cengji","Type","Parent_level1","Parent_level2","Branch_level" };  
      
       for(int i=0;i<header.length;i++)  
       {  
           String[] Data= header;
//           wr.writeRecord(Data); 
           wr.write(header[i]);
           wr.write(header[i]);
           wr.endRecord();
//           wr.flush();
//           wr.writeComment(header[i]);
       }  
       wr.close();  
	}  
	
	public void getExpression(){
		HashMap<String, String> table_column=getTable_column();
		String name= String.valueOf(getColumn_name().toCharArray())   ;
		String value = String.valueOf(getColumn_value().toCharArray());
		System.out.println(getColumn_name());
		System.out.println(getColumn_value());
		table_column.put(name,value);
		setTable_column(table_column);
		setColumn_name("");
		setColumn_value("");
		
		
//		return getTable_column();
	}
	



	
	public void WriteTable() throws IOException{
		String table=getTable();
		table=table+".csv";
		CsvWriter cw = new CsvWriter(getCurrentPath()+"/"+getCurrentDatabase()+"/"+table,',', Charset.forName("gb2312"));
		HashMap<String, String> table_column =getTable_column();
		LinkedList<String> table_name = new LinkedList<>();
		LinkedList<String> table_type = new LinkedList<>();
		for(String e : table_column.keySet()){
			table_name.add(e);
			table_type.add(table_column.get(e));
			System.out.println(e+" : "+table_column.get(e));
		}
		for(String e : table_name){
			cw.write(e);
		}
		cw.endRecord();
		
		for(String e : table_type){
			cw.write(e);
		}
		cw.endRecord();
		//一定要把文件关闭。否则没有办法写入数据
		cw.close();
	}
	
	



	/**
	 * @param 传入表的名字,创建的文件默认为CSV格式
	 * csvAPI:http://javacsv.sourceforge.net/
	 * {@link http://javacsv.sourceforge.net/}
	 * 
	 * */
	public void CreateTable(String table){
		if(getCurrentDatabase()==""){
			System.out.println("请先创建数据库");
			return;
		}
		String path=getCurrentPath();
		
		path=path+"/"+getCurrentDatabase();
		path=path+"/"+table+".csv";
		fileOperation.CreateFile(path);
		setTable(table);
		
	}
	/**
	 * @param 传入数据库的名字
	 * 
	 * 
	 * 
	 * */
	public void CreateDatabase(String database){
		String path=getCurrentPath();
		path=path+"/"+database;
		setCurrentDatabase(database);
		fileOperation.CreateFolder(path);
	}
	public String getCurrentDatabase() {
		return currentDatabase;
	}
	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}
	public String getCurrentPath() {
		return currentPath;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	public String getC_Databas() {
		return C_Databas;
	}
	public String getC_Tables() {
		return C_Tables;
	}
	public String getTable() {
		return Table;
	}
	public void setTable(String table) {
		Table = table;
	}
	public HashMap<String, String> getTable_column() {
		
		return table_column;
	}



	public void setTable_column(HashMap<String, String> table_column) {
		this.table_column = table_column;
	}
	public String getColumn_name() {
		return column_name;
	}



	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}



	public String getColumn_value() {
		return column_value;
	}



	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}
	

	

}
