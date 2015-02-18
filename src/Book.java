
/**
 * @author Vikas Kumar Thakur 
 * @author brijesh kumar
 * @author dileep kumar  jaiswal
 * @version 2.11 
 * @serial or @serialField or @serialData) 
 */
public class Book {
	private final  String bookTitle;
	private final String bookAuthor;
	private String bookSubject;
	private String bookPublisher;
	private	int numberOfTimes;
	
				Book(String bookTitle,String bookAuthor,String bookSubject,String bookPublisher,int numberOfTimes){
			this.bookTitle=bookTitle;
			this.bookAuthor=bookAuthor;
			this.bookSubject=bookSubject;
			this.bookPublisher=bookPublisher;
			this.numberOfTimes=numberOfTimes;
		}	
				String getBookTitle(){
					return bookTitle;
				}
				String getBookAuthor(){
					return bookAuthor;
				}	
				String getBookSubject(){
					return bookSubject;
				}
				String getBookPublisher(){
					return bookPublisher;
				}
				int getNumberOfTimes(){
					return numberOfTimes;
				}
				void setBookInformation(String bookSubject , String bookPublisher , int numberOfTimes){
					this.bookPublisher=bookPublisher;
					this.bookSubject=bookSubject;
					this.numberOfTimes=numberOfTimes;
				}
				void incrementNOFIssue(){
					numberOfTimes+=1;
				}
}
