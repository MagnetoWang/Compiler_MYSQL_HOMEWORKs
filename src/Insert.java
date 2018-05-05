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

	//false Ĭ��δ��ʼ���б�
	private static boolean OperationColumn = false;
	//false Ĭ��SQL������б�
	private static boolean WithColumn =false;

	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	private static String currentTable="";
	
	public static FileTools fileOperation = new FileTools();
	public static SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
	
	/**
	 * 
	 * ��ʼ��Ϊ�˱�֤��һ�ε���䲻Ӱ����һ�Ρ����統ǰ·��������Щ����ֵ
	 * 
	 * */
	public boolean Init() throws IOException{

		Value_columm=new LinkedList<>();
		Table_column=new LinkedList<>();
		setWithColumn(false);
		setOperationColumn(false);
		csvtools.setCurrentDatabase(currentDatabase);
		csvtools.setCurrentTable(currentTable);
		csvtools.ReadAll(currentTable);
		
//		csvtools.ReadAll(getCurrentTable());
		return true;
	}
	

	/**
	 * �������������Ϊ��������
	 * @param path
	 * @return 
	 */
	public boolean CheckTable(String path){
		if(path==""||path==null){
			System.out.println("��Ч���ݱ�");
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
		System.out.println("��ǰ���ݿ⣬û��������ݱ�");
		return false;
	}
	
	/**
	 * ture ��ʾ�г�ʼ�����б�
	 * false ��ʾ�޳�ʼ�����б�
	 * 
	 */
	public boolean CheckColumn(String path) throws IOException{
		if(path==""||path==null){
			System.out.println("��Ч���ݱ�");
			return false;
		}
//		path=currentPath+"/"+currentDatabase+"/"+path+".csv";
		
		
		return csvtools.IfHasColunm(path);
		
	}
	/**
	 * 
	 * ���б�˳�����
	 * ��δ��ʼ���б���ô�˳����б�����ȳ�ʼ��������ִ�в������
	 * @throws IOException 
	 */
	public boolean InsertValueWithColumn(String path) throws IOException{
		if(path==""||path==null){
			System.out.println("��Ч���ݱ�");
			return false;
		}
		if(isOperationColumn()==true){
			return InsertValueBYColumn(path);


		}else{
			System.out.println("�б��ֶ�δ��ʼ�����ȳ�ʼ��");
			return false;

		}
	}
	/**
	 * 
	 * @throws IOException 
	 * @todo ���ݸ������б�˳���������
	 */
	public boolean InsertValueBYColumn(String path) throws IOException{

		LinkedList<String> Table_column = getTable_column();
		LinkedList<String> Value_columm = getValue_columm();
		if(Table_column.size()!=Value_columm.size()){
			System.out.println("�б��ֵ����������һ��");
			return false;
		}
		
		List<String[]> data = csvtools.getData();
		HashMap<String, Integer> HeaderKey = new HashMap<>();
		String[] Header = data.get(0);
		int i=0;
		for( String e : Header){
			HeaderKey.put(e, i);
			i++;
		}
		
		@SuppressWarnings("unused")
		String[] ValueType = data.get(1);
		//Ӧ�ø�ֵ�Ͷ�Ӧ����һ����С�������ܺͱ���ֶ�����һ����
//		String[] TempColumn =new String[Table_column.size()];
//		String[] TempValue = new String[Value_columm.size()];
		//�������ġ����߸ĵ�ʱ��ҪӰ����С
//		String[] TempColumn=new String[Header.length];
//		String[] TempValue=new String[Header.length];
		String[] OneLine=new String[Header.length];
//		if(ListTransferOneLine(Table_column)==true){
//			i=0;
//			for( String e : getOneLine()){
//				TempColumn[i] = e;
//				i++;
//			}
//			
//			
//		}
//		if(ListTransferOneLine(Value_columm)==true){
//			i=0;
//			for( String e : getOneLine()){
//				TempValue[i] =e;
//				i++;
//			}
//		}
		
		for(i=0;i<OneLine.length;i++){
			OneLine[i]="nulls";
		}
		i=0;
		//�����и�bug ��Ϊ��Ƶ�nullֵ�����Զ��⴦��
		for(String e : Table_column){
			if(i<Table_column.size()){
				int index = HeaderKey.get(e);
				OneLine[index]=Value_columm.get(i);
				i++;
				
			}

		}
		


		return csvtools.InsertValue(path, OneLine);

	}

	
	/**
	 * 
	 * List ת�� String[]
	 * 
	 * 
	 * */
	
	public boolean ListTransferOneLine(LinkedList<String> List){
		if(List.size()<=0){
			return false;
		}
		if(List.size()>csvtools.getDataColumn()){
			System.out.println("�б������ֶ����� �� "+csvtools.getDataColumn());
			System.out.println("��������ֵ�����ֶ����Բ�������");
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
		return true;
	}

	public boolean DirectInsertValue(String path) throws IOException{
		if(currentDatabase==""){
			System.out.println("����ѡ�����ݿ�");
			return false;
		}
		if(path==""||path==null){
			System.out.println("��Ч���ݱ�");
			return false;
		}
		if(CheckTable(path)==false){
			System.out.println("����ѡ����ȷ���ݱ�");
			return false;
		}
		setCurrentTable(path);
		if(csvtools.IfHasColunm(getCurrentTable())==false){
			return false;
		}
		ListTransferOneLine(getValue_columm());
//		String[] oneLine =getOneLine();
	    
		
//		path=currentPath+"/"+currentDatabase+"/"+path+".csv";

		return csvtools.InsertValue(path, getOneLine());

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
//				String ans = cr.get(i);
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
	 * д�ļ�û��׷�ӹ���
	 * �ݶ�������ȫ����������д��ȥ
	 * 
	 * */
	@Test
	public void TestWriteValue() throws IOException{
		String path="src/mysql/JUST_FOR_TEST/STUDENT.csv";
		CsvReader cr = new CsvReader(path);
		cr.close();
		
		
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
			cw.writeRecord(bns);
			cw.write("ok");
		}
//		cw.endRecord();
		
		cw.close();
		
		
//		return true;
	}
	



	
	
	
	

	
	
	
	
	
	
	
	
	
	@SuppressWarnings("static-access")
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



	public static boolean isOperationColumn() {
		return OperationColumn;
	}



	public static void setOperationColumn(boolean operationColumn) {
		OperationColumn = operationColumn;
	}



	public static boolean isWithColumn() {
		return WithColumn;
	}



	public static void setWithColumn(boolean withColumn) {
		WithColumn = withColumn;
	}

}
