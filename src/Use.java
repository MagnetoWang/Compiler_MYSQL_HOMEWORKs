
public class Use {
	private String C_Use="USE";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	public FileTools fileOperation = new FileTools();
	
	
	public boolean Use(String Database){
		setCurrentDatabase(Database);
		return true;
	}
	
	
	
	
	
	
	
	

	public String getCurrentDatabase() {
		return currentDatabase;
	}
	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}
	public String getC_Use() {
		return C_Use;
	}
	public String getCurrentPath() {
		return currentPath;
	}
	

}
