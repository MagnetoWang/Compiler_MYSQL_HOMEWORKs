import java.util.HashMap;
import java.util.LinkedList;

public class Insert {
	

	private String C_Insert="INSERT";
	private String C_Into="INTO";
	private String C_Values="VALUES";
	
	
	private boolean Column=false;
	private String Table_name="";
	private LinkedList<String> Table_column;
	private LinkedList<String> Value_columm;
	private int Table_column_count =0;
	private int Value_column_count= 0;
	
	
	private HashMap<String, String> Table_Row;
	private String currentPath="src/mysql";
	private String currentDatabase="";
	
	public FileTools fileOperation = new FileTools();
	
	
//	public
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	public String getTable_name() {
		return Table_name;
	}
	public void setTable_name(String table_name) {
		Table_name = table_name;
	}
	public LinkedList<String> getTable_column() {
		return Table_column;
	}
	public void setTable_column(LinkedList<String> table_column) {
		Table_column = table_column;
	}
	public LinkedList<String> getValue_columm() {
		return Value_columm;
	}
	public void setValue_columm(LinkedList<String> value_columm) {
		Value_columm = value_columm;
	}
	public String getCurrentDatabase() {
		return currentDatabase;
	}
	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}
	public String getC_Insert() {
		return C_Insert;
	}
	public String getC_Into() {
		return C_Into;
	}
	public String getC_Values() {
		return C_Values;
	}
	public String getCurrentPath() {
		return currentPath;
	}
	public HashMap<String, String> getTable_Row() {
		return Table_Row;
	}
	public void setTable_Row(HashMap<String, String> table_Row) {
		Table_Row = table_Row;
	}
	public boolean isColumn() {
		return Column;
	}
	public void setColumn(boolean column) {
		Column = column;
	}
	public int getValue_column_count() {
		return Value_column_count;
	}
	public void setValue_column_count(int value_column_count) {
		Value_column_count = value_column_count;
	}
	public int getTable_column_count() {
		return Table_column_count;
	}
	public void setTable_column_count(int table_column_count) {
		Table_column_count = table_column_count;
	}
	
	

}
