import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Update {
	//存放最终选择的行
		private List<Integer[]> Result = new LinkedList<>();
		//存放 选择的表名
		private List<String> TableName= new LinkedList<>();
		//存放打印的列表
		private List<String> ShowColumn = new LinkedList<>();
		//检查有无 where
		private boolean IsWhere = false;

		//存放整张表
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
				System.out.println("路径错误");
				return false;
			}
			Result = columnTools.whereStatement();
			if(Result.get(0).length<=0){
				System.out.println("未找到符合条件的数据");
				return false;
			}
			/**
			 * result 的行数要和打印的接口一致才行
			 * 
			 */
			for(Integer e : Result.get(0)){
				System.out.println(e+"  ");
				e++;
			}
			/**
			 * @see
			 * TODO 提取表达式，先and语句处理，然后是or语句
			 * 
			 * 行数确定好，再进行列数的确定
			 * 
			 */
			List<String[]> data = getData();
			String[] columnName = data.get(0);
			System.out.println("============================");
			System.out.println("开始更新");
			
			
			
			
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

		
		
		
		
}
