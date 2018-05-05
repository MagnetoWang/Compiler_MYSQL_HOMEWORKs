import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class Show {
	
	private String C_Databas="DATABASES";
	private String C_Tables="TABLES";
	private String currentPath="src/mysql";
	private String currentDatabase="";
	public FileTools fileOperation = new FileTools();
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
	public String getC_Databas() {
		return C_Databas;
	}
	public String getC_Tables() {
		return C_Tables;
	}
	
	/**
	 * @deprecated 弃用
	 * {@docRoot 展示数据库列名}
	 * 
	 * 
	 * 
	 * */
	public boolean ShowDatabase() {
//		File DatabasesList=new File(getCurrentPath());
		String[] files=fileOperation.getAllFiles(getCurrentPath());
		for(String e : files){
			System.out.println(e);
		}
		
		return true;
	}
	public boolean ShowTable() {
		//		File DatabasesList=new File(getCurrentPath());
				if(getCurrentDatabase()==""){
					System.out.println("没有指定有效数据库。");
					return false;
				}
				String[] files=fileOperation.getAllFiles(getCurrentPath()+"/"+getCurrentDatabase());
				
				for(String e : files){
					System.out.println(e);
				}
				return true;
			}
			






	@Ignore
	@Test
	public void TestShowDatabase() {
//		File DatabasesList=new File(getCurrentPath());
		String[] files=fileOperation.getAllFiles(getCurrentPath());
		for(String e : files)
		System.out.println(e);
	}
	
	
	@Ignore
	@Test
	public void TestShowTable() {
//		File DatabasesList=new File(getCurrentPath());
		String[] files=fileOperation.getAllFiles(getCurrentPath()+"/Database");
		
		for(String e : files){
			System.out.println(e);
		}
		
	}
	
}
