import java.sql.ResultSet;
import java.sql.SQLException;


public class Backaned {	
	private DB dbs;
	Backaned(){
	dbs= new DB();
	}
	
/*
	public void loadDataOfbookList() {
		
			
}
*/
	/**  
	 * @return void 
	 * @see com.vikas.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	public boolean validateUser(int roll) {
		String sql = "select * from registereduser";
		try {
			ResultSet rs = dbs.runSql(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				if(id==roll)
					return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void initalize(Book[] bookList, Student[] issuedBookList) {
	String bookTitle,bookAuthor,bookSubject,bookPublisher;
	int count;
	String sql = "select * from book";
	try {
		ResultSet rs = dbs.runSql(sql);
		while(rs.next()){
			bookTitle=rs.getString("title");
			bookAuthor=rs.getString("author");
			bookSubject=rs.getString("sub");
			bookPublisher=rs.getString("publisher");
			//count=
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
