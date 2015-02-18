
/**
 * @author Vikas Kumar Thakur 
 * @author brijesh kumar
 * @author dileep kumar  jaiswal
 * @version 2.11 
 * @serial or @serialField or @serialData) 
 */
public interface LibraryManagement {
	int INITIAL_CAPACITY=500;

	String failedLoading=" failed loading process ";
	String failedWritingProcess="  failed writing process ";
	String fileNotFound="no file is present in the specified location ";
	String failedSavingRecord="saving Record in file is unable to issue the book";
	String invalidReference="error: an invalid reference is use" +
			   "previoud data is not loded";
	/*
	 * when program start it validate user
	 * 
	 */
	boolean validateUser(int roll);
	/*
	 * issue book to user
	 * 
	 */
	boolean issueTheBook(String bookTitle , String bookAuthor , String nameOfStudent , String studentID );
	/*
	 * manage book return by user
	 * 
	 */
	boolean returnTheBook(String bookTitle , String bookAuthor);

	/*
	 * add or change book record
	 * 
	 */
	Book addOrChangeEntry
		(String bookTitle, String bookAuthor ,String bookSubject,String bookPublisher,int numberOfTimes);
	/*
	 * remove the book record
	 * 
	 */
	Book removeBookEntry(String bookTitle , String bookAuthor);
	/*
	 * save book record file
	 * 
	 */
	void saveBookRecord();
	/*
	 * display sorted book list
	 * 
	 */
	boolean displayBookByTitle();	
	/*
	 * display book on the basis of subject
	 * 
	 */
	boolean displayBookOnSubject(String bookSubject);
	/*
	 *  issue book to student
	 * 
	 */
	void saveIssueBookRecord();
	void loadDataSet();

}
