import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//���������������˳��������
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTools {
	public File   SQL_Folder;
	public File   SQL_File;
	public boolean SQL_bool=false;
	
	public boolean CheckTable(String path){
		if(path==""||path==null){
			System.out.println("��Ч�ļ�·��");
			return false;
		}
		
		String[] files = getAllFiles(path);
		path=path+".csv";
		for(String e : files){
			if(e.equals(path)){
				return true;
			}
		}
		
		System.out.println("��ǰ�ļ����У�û������ļ�");
		System.out.println("��Ч�ļ�·��"+path);
		return false;
	}
	
	
	
	
	
		/**
	 * @param path �������·����,������׺����
	 * 
	 * */
	public boolean OpenFile(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
			return false;
		}
		
		

		return true;
	}
	


















	/**
	 * @param path �������·����,������׺����
	 * 
	 * */
	public boolean DeleteFolder(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
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
	 * @param path �������·����,����׺����
	 * 
	 * */
	public boolean DeleteFile(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
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
	 * @param path �������·����,������׺����
	 * 
	 * */
	public String[] getAllFiles(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
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
	 * @param path �������·����,������׺����
	 * 
	 * */
	public boolean CreateFolder(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
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
				System.out.println("�ļ��д���ʧ��");
				
			}
			SQL_bool=false;
			
		}
		return true;
	}
	
	/**
	 * @param path �������·����,���Ҵ��к�׺�ļ�����
	 * 
	 * */
	public boolean CreateFile(String path){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
			return false;
		}
		try {
			SQL_File=new File(path);
			SQL_bool=SQL_File.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(SQL_bool==false){
				System.out.println("�ļ�����ʧ��");
				
			}
			SQL_bool=false;
		}
		return true;
	}
	/**
	 * @param path �������·����
	 * @param Suffix ָ���ļ�����
	 * */
	public boolean CreateFile(String path,String Suffix){
		if(path==null){
			System.out.println("ERROR:��Ч·��");
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
				System.out.println("����ʧ��");
				
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
