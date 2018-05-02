
public class Drop {
	private String C_Database="DATABASE";
	private String C_Table="TABLE";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	public FileTools fileOperation = new FileTools();
	
	
	/**
	 * @param �������ݿ������
	 * 
	 * 
	 * 
	 * */
	public boolean DropDatabase(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
			return false;
		}
		path=getCurrentPath()+"/"+path;
		
		
		return fileOperation.DeleteFolder(path);
	}
	/**
	 * @param ����������
	 * 
	 * 
	 * 
	 * */
	public boolean DropTable(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
			return false;
		}
		if(getCurrentDatabase()==""){
			System.out.println("ERROR:��Ч���ݿ�");
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
