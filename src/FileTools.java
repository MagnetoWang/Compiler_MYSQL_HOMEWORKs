import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//按测试名字升序的顺序来测试
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTools {
	public File   SQL_Folder;
	public File   SQL_File;
	public boolean SQL_bool=false;
	
	public boolean CheckTable(String path){
		if(path==""||path==null){
			System.out.println("无效文件路径");
			return false;
		}
		
		String[] files = getAllFiles(path);
		path=path+".csv";
		for(String e : files){
			if(e.equals(path)){
				return true;
			}
		}
		
		System.out.println("当前文件夹中，没有这个文件");
		System.out.println("无效文件路径"+path);
		return false;
	}
	
	
	
	
	
		/**
	 * @param path 传入的是路径名,不带后缀类型
	 * 
	 * */
	public boolean OpenFile(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		
		

		return true;
	}
	


















	/**
	 * @param path 传入的是路径名,不带后缀类型
	 * 
	 * */
	public boolean DeleteFolder(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		try {
			SQL_Folder=new File(path);
			if(SQL_Folder.isDirectory()==true){
				File[] files=SQL_Folder.listFiles();
				for(File e:files){
					e.delete();
				}
			}
			
			SQL_bool=SQL_Folder.delete();
			
			
			System.out.println(SQL_bool);
		} catch (Exception e) {
			
		}finally {
			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
		return true;
	}	
	/**
	 * @param path 传入的是路径名,带后缀类型
	 * 
	 * */
	public boolean DeleteFile(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		try {
			SQL_File=new File(path);
			SQL_bool=SQL_File.delete();
			
			
//			System.out.println(SQL_bool);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
		return true;
	}
	/**
	 * @param path 传入的是路径名,不带后缀类型
	 * 
	 * */
	public String[] getAllFiles(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return null;
		}
		String[] files = null;
		try {
			SQL_Folder=new File(path);
			if(SQL_Folder.isDirectory()==true){
				files=SQL_Folder.list();
			}
		} finally{
			
			
		}
		return files;
		
	}
	/**
	 * @param path 传入的是路径名,不带后缀类型
	 * 
	 * */
	public boolean CreateFolder(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		try {
//			name="src/MYSQL/"+name;
			SQL_Folder=new File(path);
			SQL_bool=SQL_Folder.mkdir();
			

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(SQL_bool==false){
				System.out.println("文件夹创建失败");
				
			}
			SQL_bool=false;
			
		}
		return true;
	}
	
	/**
	 * @param path 传入的是路径名,并且带有后缀文件类型
	 * 
	 * */
	public boolean CreateFile(String path){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		try {
			SQL_File=new File(path);
			SQL_bool=SQL_File.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(SQL_bool==false){
				System.out.println("文件创建失败");
				
			}
			SQL_bool=false;
		}
		return true;
	}
	/**
	 * @param path 传入的是路径名
	 * @param Suffix 指定文件类型
	 * */
	public boolean CreateFile(String path,String Suffix){
		if(path==null){
			System.out.println("ERROR:无效路径");
			return false;
		}
		try {
			path=path+"."+Suffix;
			SQL_File=new File(path);
			SQL_bool=SQL_File.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(SQL_bool==false){
				System.out.println("创建失败");
				
			}
			SQL_bool=false;
		}
		return true;
	}
	

	public File getSQL_Folder() {
		return SQL_Folder;
	}
	public File getSQL_File() {
		return SQL_File;
	}
	@Test
	public void test(){
		System.out.println("ok");
//		assertEquals();
	}
	
	@Ignore("Test is ignored as a demonstration")
	@Test
	public void TestCreateFolder(){
		try {
			SQL_Folder=new File("src/mysql");
			SQL_bool=SQL_Folder.mkdir();
			

		} catch (Exception e) {
			
		}finally {
			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
		
		
	}
	@Ignore
	@Test
	public void TestCreateFile(){
		try {
			SQL_File=new File("src/mysql/MYSQL.txt");
			SQL_bool=SQL_File.createNewFile();
			System.out.println(SQL_bool);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
//		return true;
	}
	@Ignore
	@After
	public void TestDeleteFile(){
		try {
			SQL_File=new File("src/mysql/MYSQL.txt");
			SQL_bool=SQL_File.delete();
			
			
//			System.out.println(SQL_bool);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
	}
	@Ignore
	@Test
	public void TestDeleteFolder(){
		try {
			SQL_Folder=new File("src/mysql/Database");
			if(SQL_Folder.isDirectory()==true){
				File[] files=SQL_Folder.listFiles();
				for(File e:files){
					e.delete();
				}
			}
			
			SQL_bool=SQL_Folder.delete();
			
			
			System.out.println(SQL_bool);
		} catch (Exception e) {
			
		}finally {
			assertEquals(true, SQL_bool);
			SQL_bool=false;
		}
	}

	
}
