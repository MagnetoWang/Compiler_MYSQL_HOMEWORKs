
public class Drop {
	private String C_Database="DATABASE";
	private String C_Table="TABLE";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	public FileTools fileOperation = new FileTools();
	
	
	/**
	 * @param 传入数据库的名字
	 * 
	 * 
	 * 
	 * */
	public boolean DropDatabase(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		path=getCurrentPath()+"/"+path;
		
		
		return fileOperation.DeleteFolder(path);
	}
	/**
	 * @param 传入表的名字
	 * 
	 * 
	 * 
	 * */
	public boolean DropTable(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		if(getCurrentDatabase()==""){
			System.out.println("ERROR:无效数据库");
			return false;
		}
		path=getCurrentPath()+"/"+getCurrentDatabase()+"/"+path;
		
		return fileOperation.DeleteFile(path);
	}
	
	
	public String getCurrentDatabase() {
		return currentDatabase;
	}
	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}
	public String getC_Database() {
		return C_Database;
	}
	public String getC_Table() {
		return C_Table;
	}
	public String getCurrentPath() {
		return currentPath;
	}

}
