
/**
 * @author Vikas Kumar Thakur 
 * @author brijesh kumar
 * @author dileep kumar  jaiswal
 * @version 2.11 
 * @serial or @serialField or @serialData) 
 */
public class Student {
			String issuedbookTitle;
			String nameOfPerson;
			String personID;
			String dateOfIssue;
			Student(String bookTitle,String nameOfPerson,String personID,String dateOfIssue){
				this.issuedbookTitle=bookTitle;
				this.nameOfPerson=nameOfPerson;
				this.personID=personID;
				this.dateOfIssue=dateOfIssue;
			}
			String getBookTitle(){
				return issuedbookTitle;
			}
			String getNameOfPerson(){
				return nameOfPerson;
			}
			String getPersonID(){
				return personID;
			}
			String getDateOfIssue(){
				return dateOfIssue;
			}
}
