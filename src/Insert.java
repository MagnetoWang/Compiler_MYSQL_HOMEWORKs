import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.opencsv.CSVReader;
import com.opencsv.stream.reader.LineReader;

public class Insert {
	private  LinkedList<String> Table_column=new LinkedList<>();
	private  LinkedList<String> Value_columm=new LinkedList<>();
	private static String[] OneLine;

	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	private static String currentTable="";
	
	public static FileTools fileOperation = new FileTools();
	public static SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
	
	
	public boolean Init() throws IOException{

		Value_columm=new LinkedList<>();
		Table_column=new LinkedList<>();
		csvtools.setCurrentDatabase(currentDatabase);
		csvtools.setCurrentTable(currentTable);
		csvtools.ReadAll(currentTable);
		
//		csvtools.ReadAll(getCurrentTable());
		return true;
	}
	


	public boolean CheckTable(String path){
		if(path==""||path==null){
			System.out.println("无效数据表");
			return false;
		}
		String checkPath=currentPath+"/"+currentDatabase;
		String[] files = fileOperation.getAllFiles(checkPath);
		path=path+".csv";
		for(String e : files){
			if(e.equals(path)){
				return true;
			}
		}
		System.out.println("当前数据库，没有这个数据表");
		return false;
	}
	
	/**
	 * 
	 * List 转换 String[]
	 * 
	 * 
	 * */
	
	public boolean ListTransferOneLine(LinkedList<String> List){
		if(List.size()<=0){
			return false;
		}
		if(List.size()>csvtools.getDataColumn()){
			System.out.println("列表属性字段数量 ： "+csvtools.getDataColumn());
			System.out.println("输入属性值多于字段属性参数数量");
			return false;
		}
		String[] oneLine = new String[csvtools.getDataColumn()];
		int i=0;
		for(String e:List){
			oneLine[i]=e;
			i++;
		}
		if(csvtools.getDataColumn()>List.size()){
			oneLine[i]="null";
		}
		setOneLine(oneLine);
		return false;
	}

	public boolean DirectInsertValue(String path) throws IOException{
		if(currentDatabase==""){
			System.out.println("请先选择数据库");
			return false;
		}
		if(path==""||path==null){
			System.out.println("无效数据表");
			return false;
		}
		if(CheckTable(path)==false){
			System.out.println("请先选择正确数据表");
			return false;
		}
		setCurrentTable(path);
		if(csvtools.IfHasColunm(getCurrentTable())==false){
			return false;
		}
		ListTransferOneLine(getValue_columm());
		String[] oneLine =getOneLine();
	    
		
//		path=currentPath+"/"+currentDatabase+"/"+path+".csv";

		return csvtools.InsertValue(path, getOneLine());

	}
	public boolean WriteValue(String path) throws IOException{
		
		return true;
		
		
	}

	public boolean InsertColumn(String path){


		return false;
	}
	
	
	@Test
	public void TestOpenFile() throws FileNotFoundException{
		String path="src/mysql/JUST_FOR_TEST/STUDENT.csv";

		//,',',Charset.forName("gb2312"));
		try {
			CsvReader cr=new CsvReader(new InputStreamReader(
					new FileInputStream(path), Charset.forName("UTF-8")), ',');
			System.out.println(cr.readRecord());
			System.out.println(cr.readRecord());
			System.out.println(cr.getColumnCount());
			for(int i=0;i<cr.getColumnCount();i++){
				String ans = cr.get(i);
//				System.out.println(ans);
//				System.out.println(cr.get("SNAME"));
//				System.out.println(cr.getHeader(i));
//				System.out.println(cr.getRawRecord());
				//result : SSEX,SNAME,SAGE
//				System.out.println(cr.getCurrentRecord() );
				System.out.println(cr.readRecord());
				System.out.println(cr.getRawRecord());
			}

//			for(String e:ans){
//				System.out.println(e);
//			}
			cr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
	}
	
	@Test	
	public void TestCheckTable(){
		String path="STUDENT";
		String checkPath=currentPath+"/JUST_FOR_TEST";
		String[] files = fileOperation.getAllFiles(checkPath);
		path=path+".csv";
		for(String e : files){
			System.out.println(e);
			if(e.equals(path)){
				
				System.out.println("true");
			}
		}
		
		
	}
	/**
	 * 写文件没有追加功能
	 * 暂定方案：全部读出来再写进去
	 * 
	 * */
	@Test
	public void TestWriteValue() throws IOException{
		String path="src/mysql/JUST_FOR_TEST/STUDENT.csv";
		CsvReader cr = new CsvReader(path);
		
		
		CsvWriter cw = new CsvWriter(path);
		String[] ans ={"x","x","x"};
		for(int i=0;i<3;i++){
			cw.writeRecord(ans);
		}
//		cw.endRecord();
		cw.close();
		
		
		String[] bns ={"y","y","y"};
		cw = new CsvWriter(path);
		
		System.out.println("OK");
		for(int i=0;i<3;i++){
//			cw.writeRecord(bns);
			cw.write("ok");
		}
//		cw.endRecord();
		
		cw.close();
		
		
//		return true;
	}
	



	
	
	
	

	
	
	
	
	
	
	
	
	
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public LinkedList<String> getTable_column() {
		return Table_column;
	}
	public void setTable_column(LinkedList<String> table_column) {
		Table_column = table_column;
	}
	public LinkedList<String> getValue_columm() {
		return Value_columm;
	}
	public void setValue_columm(LinkedList<String> value_columm) {
		Value_columm = value_columm;
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

	public String getCurrentTable() {
		return currentTable;
	}
	public void setCurrentTable(String currentTable) {
		this.currentTable = currentTable;
	}
	
	public SQL_CSVTOOLS getCsvtools() {
		return csvtools;
	}
	public FileTools getFileOperation() {
		return fileOperation;
	}

	public void setFileOperation(FileTools fileOperation) {
		this.fileOperation = fileOperation;
	}

	public void setCsvtools(SQL_CSVTOOLS csvtools) {
		this.csvtools = csvtools;
	}



	public String[] getOneLine() {
		return OneLine;
	}



	public void setOneLine(String[] oneLine) {
		OneLine = oneLine;
	}

}
