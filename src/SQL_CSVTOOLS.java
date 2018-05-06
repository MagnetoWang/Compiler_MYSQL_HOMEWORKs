import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * @author Magneto_Wang
 * @Description: ר�Ŵ�����룬�޸ģ�ɾ�����Ĳ������������еĶ�д�⣬��һ����װ
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
			System.out.println("������Ч�ı������");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("������Ч�����ݿ�����");
			return false;
		}
		setCurrentTable(path);
		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		CsvReader cr = new CsvReader(path);
		boolean result = false;
		// ��ͷ����
		result = cr.readHeaders();
		boolean type=cr.readRecord();
		cr.close();
		return (result&&type);
		
	}
	

	/**
	 * 
	 * ֻ��Ҫ�����ļ�����
	 * Ĭ����CSV��ʽ
	 * ��ȡָ���ļ���ȫ������
	 * 
	 * */
	public boolean ReadAll(String path) throws IOException {
		if(path==null||path==""){
			System.out.println("������Ч�ı������");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("������Ч�����ݿ�����");
			return false;
		}
		setCurrentTable(path);


		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		CsvReader cr = new CsvReader(path);
		boolean result;
		List<String[]> data = new LinkedList<>();

		// ��ͷ����
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
		

		// �����ݲ���
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
	 * ��ȡָ���ļ���ȫ������
	 * @throws IOException 
	 * 
	 * */
	public boolean WriteAll(String path) throws IOException{
		if(path==null||path==""){
			System.out.println("������Ч�ı������");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("������Ч�����ݿ�����");
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
		System.out.println("�Ѿ����ֶ��б���ֹ�������б�");
		return false;
		
	}
	/**
	 * 
	 * ����ָ�����������Ӧλ��,���Ѿ����б����������
	 * 
	 * */
	public boolean InsertSpecifiedColumn(String path,String[] Column ,String[] OneLine) throws IOException{
		if(data.size()<2){
			System.out.println("���ȶ����б�����");
			return false;
			
		}
		
		
		return false;
		
	}
	
	/**
	 * ����ֵ�����һ��
	 * @throws IOException 
	 * 
	 * */
	 public boolean InsertValue(String path,String[] OneLine) throws IOException{
		 if(path==null||path==""){
				System.out.println("������Ч�ı������");
				return false;
			}
			if(getCurrentDatabase()==null||getCurrentDatabase()==""){
				System.out.println("������Ч�����ݿ�����");
				return false;
			}
			if(data.size()<2){
				System.out.println("���ȶ����б�����");
				return false;
				
			}
			
			
		
			
			return InsertOneLine(path,OneLine);
		 
	 }

		
		/**
		 * ����ֵ�����һ��
		 * @throws IOException 
		 * 
		 * */
		 public boolean InsertSpecifiedValue(String path,String[] OneLine,int index) throws IOException{
			 if(path==null||path==""){
					System.out.println("������Ч�ı������");
					return false;
				}
				if(getCurrentDatabase()==null||getCurrentDatabase()==""){
					System.out.println("������Ч�����ݿ�����");
					return false;
				}
				if(data.size()<2){
					System.out.println("���ȶ����б�����");
					return false;
					
				}
				

				
				
			
				
				return InsertspecifiedLine(path,OneLine,index);
			 
		 }
	
	/**
	 * ��ĩβ����һ��
	 * 
	 * */
	 
	 public boolean InsertOneLine(String path,String[] OneLine) throws IOException{
			if(path==null||path==""){
				System.out.println("������Ч�ı������");
				return false;
			}
			if(getCurrentDatabase()==null||getCurrentDatabase()==""){
				System.out.println("������Ч�����ݿ�����");
				return false;
			}
			List<String[]> data=getData();
			

			
			
			
			path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
			
			

			
//			CsvWriter cw = new CsvWriter(path);

			System.out.println("����ֵ �� "+OneLine);
			data.add(OneLine);
//			setData(data);
			
//			CsvWriter cw = new CsvWriter(path);
//			cw.close();
			

//			cw.writeRecord(OneLine);
			

			WriteAll(getCurrentTable());
			
			return true;
		 
	 }
	 
	/**
	 * @todo ��ָ��λ�ò���һ�У����������е�ͷ����
	 * 
	 * 
	 */
	public boolean InsertspecifiedLine(String path,String[] OneLine,int index) throws IOException {
		if(path==null||path==""){
			System.out.println("������Ч�ı������");
			return false;
		}
		if(getCurrentDatabase()==null||getCurrentDatabase()==""){
			System.out.println("������Ч�����ݿ�����");
			return false;
		}
		path = getCurrentPath()+"/"+getCurrentDatabase()+"/"+getCurrentTable()+".csv";
		
		index = index + 1;
		List<String[]> data = getData();
		if(data.size()<index){
			System.out.println("���󣺲����λ�ô����б���");
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
	 * ���Զ�д����
	 * 
	 * ��Ϊ�ⲻ�ǹ�ҵ�⡣����API�ܲ�ȫ����Ҫ��Ӧ��Դ����
	 * ����ֱ�Ӷ�д�����������ڲ��Ķ�������Ȼ����ܻ���ı�����������ݡ��������ȷ���һ����������
	 * Ȼ����ڻ������������
	 * IO�Ǹ��ǳ�������������������װ�ö�Ӧ�Ĺ��߲���
	 * 
	 * */
	@Test
	public void TestReadAll() throws IOException{
		
		String path="src/mysql/JUST_FOR_TEST/STUDENT.csv";
		CsvReader cr = new CsvReader(path);
		boolean result ;
		List<String[]> data=getData();
		
		
		//��ͷ����
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
		
		
		//�����ݲ���
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
	 * ��ĩβ����һ��
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
	 * @todo ��ָ��λ�ò���һ�У����������е�ͷ����
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
			System.out.println("�����λ�ô����б���");
			
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
	 * @todo ��ɾ�����һ�У����������е�ͷ����
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
