import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;



/**
 * @
 * @see 
 * ��Ϊselect�Ĺ��ܹ��ڸ��ӡ�Ϊ���Ժ����չ�С����������ƶ���Ȼ������д����
 * ������֮ǰӦ��˵����������Ƚ��ͳ���
 * ���ȣ����÷Ǿ�̬���ࡣÿ��ִ��select��䶼������new
 * ���ٴ�����ά��������
 * 
 * 
 */
public class Select {
	//�������ѡ�����
	private List<Integer[]> Result = new LinkedList<>();
	//��� ѡ��ı���
	private List<String> TableName= new LinkedList<>();
	//��Ŵ�ӡ���б�
	private List<String> ShowColumn = new LinkedList<>();
	//������� where
	private boolean IsWhere = false;
	//�������*
	private boolean IsStart = false;
	//������ű�
	private List<String[]> data = new LinkedList<>();
	
	

	private static String currentPath="src/mysql";
	private static String currentDatabase="";
	private static String currentTable="";
	
	
	public FileTools fileOperation = new FileTools();
	public SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
	public ColumnTools columnTools = new ColumnTools();
	
	
	
	public boolean Init() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			System.out.println("·������");
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
	 * ��֮ǰ�������һֱû��ר��дһ������������
	 * ���ڽ��֮ǰ����ɢɢ�ļ�飬��������
	 * ���������ȫ���£����Լ���������߲�����
	 * 
	 */
	public boolean CheakPath(String path) throws IOException{
		if(path==""||path==null){
			System.out.println("��Ч���ݱ�");
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
		System.out.println("��ǰ���ݿ⣬û��������ݱ�");
		return false;
		
	}
	/**
	 * @Description  ��ӡ�����Ǻţ���where���
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
			System.out.println("��ӡ��ʧ��");
			return false;
		}
		
		
		return true;
	}
	/**
	 * @see
	 * ��ӡ�����Ǻţ�����ָ���У���where�Ӿ�
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
			System.out.println("��ӡ��ʧ��");
			return false;
		}
		
		
		return true;
	}
	/**
	 * @see
	 * ��where������¡���ӡָ���л���*�����
	 * 
	 */
	public boolean PrintWhere() throws IOException{
		if(CheakPath(getCurrentPath())==false){
			System.out.println("·������");
			return false;
		}
		Result = columnTools.whereStatement();
		/**
		 * @see
		 * TODO ��ȡ���ʽ����and��䴦��Ȼ����or���
		 * 
		 * ����ȷ���ã��ٽ���������ȷ��
		 * 
		 */
		
		if(IsStart==true){

			
			

		}else{
			
		}
		
		
		return false;
	}
	
	
	/**
	 * @see
	 * ��֪��
	 * 
	 * ��ӡ������,Ҳ���� ���� *
	 */
	
	public boolean PrintAllColumn(String path,int[] rows) throws IOException{
		
		if(CheakPath(path)==false){
			return false;
		}

		if(rows==null&&rows.length==0){
			System.out.println("δָ���к�");
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
//	 * ��ӡָ��һϵ�е���
//	 * ��where
//	 * 
//	 */
//	public boolean PrintSpecifiedColumn(){
//		
//	}
	
	/**
	 * 
	 * @see
	 * ��֪��
	 * ��ӡָ����
	 * 
	 * ָ��������ָ������
	 * 
	 */
	
	public boolean PrintColumn(String path,String[] columnName,int[] rows) throws IOException{
		
		if(CheakPath(path)==false){
			return false;
		}
		if(columnName==null&&columnName.length==0){
			System.out.println("δָ������");
			return false;
		}
		if(rows==null&&rows.length==0){
			System.out.println("δָ���к�");
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
	 * ��ӡ������
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
	 * ��ӡ�б����
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
	 * ֻ��ӡһ�У������б����
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
	 * ֻ��ӡһ�У����б����
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
	 * ��֪��
	 * ��ӡ������
	 */
	@Test
	public void TestPrintAllColumn(){
		
	}
	
	/*
	 * ��֪��
	 * ��ӡָ����
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
	 * ��ӡ������
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
	 * ��ӡָ����
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
