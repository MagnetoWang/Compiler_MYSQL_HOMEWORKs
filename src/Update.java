import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Update {
	//�������ѡ�����
		private List<Integer[]> Result = new LinkedList<>();
		//��� ѡ��ı���
		private List<String> TableName= new LinkedList<>();
		//��Ŵ�ӡ���б�
		private List<String> ShowColumn = new LinkedList<>();
		//������� where
		private boolean IsWhere = false;

		//������ű�
		private List<String[]> data = new LinkedList<>();
		
		public boolean Init() throws IOException{

			if(getTableName().size()==1){
				currentTable=getTableName().get(0);
				
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
		 * 
		 * @return
		 * @throws IOException
		 */
		public boolean UpdateWhere() throws IOException{
			if(CheakPath(getCurrentTable())==false){
				System.out.println("·������");
				return false;
			}
			Result = columnTools.whereStatement();
			if(Result.get(0).length<=0){
				System.out.println("δ�ҵ���������������");
				return false;
			}
			/**
			 * result ������Ҫ�ʹ�ӡ�Ľӿ�һ�²���
			 * 
			 */
			for(Integer e : Result.get(0)){
				System.out.println(e+"  ");
				e++;
			}
			/**
			 * @see
			 * TODO ��ȡ���ʽ����and��䴦��Ȼ����or���
			 * 
			 * ����ȷ���ã��ٽ���������ȷ��
			 * 
			 */
			List<String[]> data = getData();
			String[] columnName = data.get(0);
			System.out.println("============================");
			System.out.println("��ʼ����");
			
			
			
			
			System.out.println("============================");
			
			
			return false;
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


		public List<String[]> getData() {
			return data;
		}

		public void setData(List<String[]> data) {
			this.data = data;
		}

		public static String getCurrentPath() {
			return currentPath;
		}

		public static void setCurrentPath(String currentPath) {
			Update.currentPath = currentPath;
		}

		public static String getCurrentDatabase() {
			return currentDatabase;
		}

		public static void setCurrentDatabase(String currentDatabase) {
			Update.currentDatabase = currentDatabase;
		}

		public static String getCurrentTable() {
			return currentTable;
		}

		public static void setCurrentTable(String currentTable) {
			Update.currentTable = currentTable;
		}

		public FileTools getFileOperation() {
			return fileOperation;
		}

		public void setFileOperation(FileTools fileOperation) {
			this.fileOperation = fileOperation;
		}

		public SQL_CSVTOOLS getCsvtools() {
			return csvtools;
		}

		public void setCsvtools(SQL_CSVTOOLS csvtools) {
			this.csvtools = csvtools;
		}

		public ColumnTools getColumnTools() {
			return columnTools;
		}

		public void setColumnTools(ColumnTools columnTools) {
			this.columnTools = columnTools;
		}

		private static String currentPath="src/mysql";
		private static String currentDatabase="";
		private static String currentTable="";
		
		
		public FileTools fileOperation = new FileTools();
		public SQL_CSVTOOLS csvtools = new SQL_CSVTOOLS();
		public ColumnTools columnTools = new ColumnTools();
		
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

		
		
		
		
}
