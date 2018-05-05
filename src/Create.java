import java.io.IOException;
import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import com.csvreader.CsvWriter;
/**
 * �����ظ�������
 * 
 */
public class Create {
	private String C_Databas="DATABASE";
	private String C_Tables="TABLE";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	private String Table="";
	public FileTools fileOperation = new FileTools();
	private HashMap<String, String> table_column = new HashMap<>();
	private LinkedList<String> table_name =new LinkedList<>();
	private LinkedList<String> table_type =new LinkedList<>();
	private String column_name="";
	private String column_value="";
	
	
	/**
	 * 
	 * ��ʼ��Ϊ�˱�֤��һ�ε���䲻Ӱ����һ�Ρ����統ǰ·��������Щ����ֵ
	 * 
	 * */
	public boolean Init() throws IOException{




		
//		csvtools.ReadAll(getCurrentTable());
		return true;
	}
	
	/**
	 * 
	 * csv ��ʽ һ����д ��������String[]
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
           wr.write(Data[i]);
           wr.endRecord();
//           wr.flush();
//           wr.writeComment(header[i]);
       }  
       wr.close();  
	}  
	
	public void getExpression(){
//		HashMap<String, String> table_column=getTable_column();
		LinkedList<String> table_name =getTable_name();
		LinkedList<String> table_type = getTable_type();
		String name= String.valueOf(getColumn_name().toCharArray())   ;
		String value = String.valueOf(getColumn_value().toCharArray());
		System.out.println(getColumn_name());
		System.out.println(getColumn_value());
		table_name.add(name);
		table_type.add(value);
//		setTable_column(table_column);
		setTable_name(table_name);
		setTable_type(table_type);
		setColumn_name("");
		setColumn_value("");
		
		
//		return getTable_column();
	}
	



	/**
	 * ����洢�����������hashmap�������Ϊ����˳����롣���Ե�����ʱ��һ���ᰴ
	 * �����˳�������������
	 * 
	 */
	public void WriteTable() throws IOException{
		if(getCurrentDatabase()==""){
			System.out.println("���ȴ������ݿ�");
			System.out.println("д��ʧ��");
			return;
		}
		if(getTable()==""){
			
			System.out.println("д��ʧ��");
			return;
		}
		String table=getTable();
		table=table+".csv";
		CsvWriter cw = new CsvWriter(getCurrentPath()+"/"+getCurrentDatabase()+"/"+table,',', Charset.forName("gb2312"));
		
//		HashMap<String, String> table_column =getTable_column();
		LinkedList<String> table_name = getTable_name();//new LinkedList<>();
		LinkedList<String> table_type = getTable_type();//new LinkedList<>();
		
//		for(String e : table_column.keySet()){
//			table_name.add(e);
//			table_type.add(table_column.get(e));
//			System.out.println(e+" : "+table_column.get(e));
//		}
		for(String e : table_name){
			cw.write(e);
		}
		cw.endRecord();
		
		for(String e : table_type){
			cw.write(e);
		}
		cw.endRecord();
		//һ��Ҫ���ļ��رա�����û�а취д������
		cw.close();
//		table_column = new HashMap<>();
		table_name=new LinkedList<>();
		table_type = new LinkedList<>();
		setTable_name(table_name);
		setTable_type(table_type);
	}
	
	



	/**
	 * @param ����������,�������ļ�Ĭ��ΪCSV��ʽ
	 * csvAPI:http://javacsv.sourceforge.net/
	 * {@link http://javacsv.sourceforge.net/}
	 * 
	 * */
	public void CreateTable(String table){
		if(getCurrentDatabase()==""){
			System.out.println("���ȴ������ݿ�");
			return;
		}
		
		String path=getCurrentPath();
		
		path=path+"/"+getCurrentDatabase();
		path=path+"/"+table+".csv";
		//�ȼ�����ޱ���ֹ�ظ�����
		if(fileOperation.CheckTable(path)==false){
			fileOperation.CreateFile(path);
		}else{
			return;
		}
		
		setTable(table);
		
	}
	/**
	 * @param �������ݿ������
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

	public LinkedList<String> getTable_name() {
		return table_name;
	}

	public void setTable_name(LinkedList<String> table_name) {
		this.table_name = table_name;
	}

	public LinkedList<String> getTable_type() {
		return table_type;
	}

	public void setTable_type(LinkedList<String> table_type) {
		this.table_type = table_type;
	}
	

	

}
