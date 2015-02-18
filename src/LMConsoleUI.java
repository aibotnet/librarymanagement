
/**
 * @author Vikas Kumar Thakur 
 * @author brijesh kumar
 * @author dileep kumar  jaiswal
 * @version 2.11 
 * @serial or @serialField or @serialData) 
 */
import java.util.*;
public class LMConsoleUI {	

	private LibraryManagement lmobj=new Librarian();
	private Scanner input;
	private String  nameOfStudent;
	private String  studentID;
	boolean thecondition;
		public LMConsoleUI(){
			input=new Scanner(System.in);
		}
		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		public void ProcessCommand(){
			String[]  option = {
									"Add  or change of book entry",
									"delete the entry of book",
									"Issue the book",
									"return book ",
									"Save the data",
									"search book by subject",
									"display book list sorted according to title",
									"save and quit"
								};
			while(doValidateUser()==false){
			System.out.println("Invalid Record Enter again:");	
			}
			lmobj.loadDataSet();
			int choice;
			boolean condition = true;
					do {
								for (int i = 0; i < option.length; i++)
									{
									System.out.println("Select " + i + ": "+ option[i]);
									}

								try {
											choice = input.nextInt();
											input.nextLine();
											switch (choice) {
											case 0:
												
												doAddChangeBookEntry();
												break;
												
											case 1:
												doRemoveEntry();
												break;
												
											case 2:
												doIssueTheBook();
												break;
												
											case 3:
												
												doReturnTheBook();
												break;
												
											case 4:
												
												doSave();
												break;
												
											case 5:
												
												doDisplayBooksBySubject();
												break;
												
											case 6:
												
												doDisplayBooksByTitle();
												break;
							
											case 7:
												lmobj.saveBookRecord();
												lmobj.saveIssueBookRecord();
												System.out.println("Thanks to visit");
												System.exit(0)
												
;											default:
												System.out.println("Invalid choice  "
														+ choice
														+ "  try again!");
													}
									}
								catch (InputMismatchException ex) {
											System.out.println("Incorrect selection entry - "
													+ "enter an integer between 0 and "
													+ (option.length-1));
													input.nextLine(); 
													choice = -1;
													}
								catch (NoSuchElementException ns)
													{
									System.out.println("Incorrect selection entry - "
											+ "enter an integer between 0 and "
											+ (option.length-1));
									input.nextLine(); 
									choice = -1;
													}
						}while (condition);
		}

		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		private void doRemoveEntry() {
			
			String bookTitle;
			String bookAuthor;
			
			do{
	  			thecondition=false;
	  		System.out.println("Enter name of the book");
	  		bookTitle = input.nextLine();
	  			if(bookTitle.equals("")||validateName(bookTitle))
	  			{
	  				System.out.println("you entered invalid name");
	  				thecondition=true;
	  			}
	  		}while(thecondition);
			
			do{
	  			thecondition=false;
	  		System.out.println("Enter name of the author of the  book");
	  		bookAuthor = input.nextLine();
	  			if(bookTitle.equals("")||validateName(bookTitle))
	  			{
	  				System.out.println("you entered invalid author");
	  				thecondition=true;
	  			}
	  		}while(thecondition);
			
			
			Book removed=lmobj.removeBookEntry(bookTitle, bookAuthor);
			
			if(removed==null)
				System.out.println("this book is not present in library can not remove");
			else
			{
				System.out.println(
										"the book with title--->"+
										removed.getBookTitle()+
										"\nand author--->"+
										removed.getBookAuthor()+
										"\nare removed from the library entry"
									);
			}
			
		}
				
		/**
		 * @param String Name  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */		
		
		private boolean validateName(String name) {	
  			int ascii;
			for(int i=0;i<name.length();i++){
				ascii=name.charAt(i) ;
				if( !(  (ascii>=65 && ascii<=90) || (ascii>=97 && ascii<=122) || (ascii==32) )  )
					return true;
			}			
			return false;
		}
		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */

		private void doDisplayBooksByTitle() {	
				boolean result=false;
				result=lmobj.displayBookByTitle();
				if(!result)
					System.out.println("error: unable to search");
		}
		
		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		private void doDisplayBooksBySubject() {			
			String bookSubject;
			do{
	  			thecondition=false;
	  			System.out.println("Enter subject of the book");
	  			bookSubject = input.nextLine();	
	  			if(bookSubject.equals("")||validateName(bookSubject))
	  				{
	  					System.out.println("you entered invalid name");
	  					thecondition=true;
	  				}
	  		}while(thecondition);
			boolean result=lmobj.displayBookOnSubject(bookSubject);
			if(!result)
				System.out.println("error : unable to display bookm list");
		}
		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		private void doSave() {
			lmobj.saveBookRecord();
			lmobj.saveIssueBookRecord();
			
		}

		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		
		private void doReturnTheBook() {
			String bookTitle;
			String bookAuthor;
			do{
	  			thecondition=false;
	  				System.out.println("Enter the title of the book " +
	  									"\nthis for conforming it is valid or not");
	  				bookTitle = input.nextLine();
	  				if(bookTitle.equals("")||validateName(bookTitle)){
	  					System.out.println("you entered invalid name");
	  					thecondition=true;
	  					}
	  		}while(thecondition);
			
			do{
	  			thecondition=false;
	  			System.out.println("enter the author of the book");
	  			bookAuthor= input.nextLine();			
	  			if(bookTitle.equals("")||validateName(bookAuthor)){
	  				System.out.println("you entered invalid name");
	  				thecondition=true;
	  			}
	  		}while(thecondition);
			boolean result=lmobj.returnTheBook(bookTitle,bookAuthor);
			if(!result)
				System.out.println("this book is not issued by this library system");
			else
			{
				System.out.println("book submited");
			}
		}

		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */		
		private void doIssueTheBook() {
			
			String bookTitle;
			String bookAuthor;
			do{
	  			thecondition=false;
	  				System.out.println("Enter title of the book");
	  				bookTitle = input.nextLine();
	  					
	  				if(bookTitle.equals("")||validateName(bookTitle))
	  						{
	  						System.out.println("you entered invalid name");
	  						thecondition=true;
	  						}
	  		}while(thecondition);
			
			
			
			do{
	  			thecondition=false;
	  		System.out.println("Enter name of the author of the  book");
	  		bookAuthor = input.nextLine();
	  			if(bookTitle.equals("")||validateName(bookTitle))
	  			{
	  				System.out.println("you entered invalid author");
	  				thecondition=true;
	  			}
	  		}while(thecondition);
			
			
			boolean result=lmobj.issueTheBook(bookTitle, bookAuthor, nameOfStudent, studentID);
			
			      if(result)
			    	  System.out.println("book is suscessfully issued	");
			
			
			
		}

		/**  
		 * @return void 
		 * @see com.Sample.MyExample#calculate() 
		 * @since Ver  2.11
		 */
		private void doAddChangeBookEntry() {			
			String bookTitle,bookAuthor;
			String bookSubject;
			String bookPublisher;
			
			
			
			
			do{
	  			thecondition=false;
	  				System.out.println("Enter the title of the book");
	  				bookTitle = input.nextLine();
	  					
	  				if(bookTitle.equals("")||validateName(bookTitle))
	  						{
	  						System.out.println("you entered invalid title of book" +
	  											"it must only contain letter and space");
	  						thecondition=true;
	  						}
	  		}while(thecondition);
			
			
			
			do{
	  			thecondition=false;
	  		System.out.println("Enter name of the author of the  book");
	  		bookAuthor = input.nextLine();
	  			if(bookTitle.equals("")||validateName(bookTitle))
	  			{
	  				System.out.println("you entered invalid author");
	  				thecondition=true;
	  			}
	  		}while(thecondition);
			
			
			
			
			
			do{
	  			thecondition=false;
	  				System.out.println("Enter subject of the book");
	  				bookSubject = input.nextLine();
	  					
	  				if(bookSubject.equals("")||validateName(bookSubject))
	  						{
	  						System.out.println("you entered invalid subject name");
	  						thecondition=true;
	  						}
	  		}while(thecondition);
			
					
			
			do{
	  			thecondition=false;
	  				System.out.println("Enter publisher of the book");
	  				bookPublisher= input.nextLine();
	  					
	  				if(bookPublisher.equals("")||validateName(bookSubject))
	  						{
	  						System.out.println("you entered invalid publisher");
	  						thecondition=true;
	  						}
	  		}while(thecondition);
			
		
			
			Book lme= lmobj.addOrChangeEntry(bookTitle, bookAuthor, bookSubject, bookPublisher,  0);
		
					System.out.println(
										 "the book  "+lme.getBookTitle()
										+"\nauthor  	"+lme.getBookAuthor()
										+"\nand subject	"+lme.getBookSubject()
										+"\nwith publisher	"+lme.getBookPublisher()
										+"\nentered into the library............\n"
									  );
		}
	
		
		private boolean doValidateUser() {
			int roll;
			System.out.println("Enter your Roll");
			roll=input.nextInt();
			return lmobj.validateUser(roll);
		}
}
