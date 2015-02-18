
/**
 * @author Vikas Kumar Thakur 
 * @author brijesh kumar
 * @author dileep kumar  jaiswal
 * @version 2.11 
 * @serial or @serialField or @serialData) 
 */
import java.io.*;
import java.util.Calendar;
public class Librarian implements LibraryManagement{
	/*  
	 *  there are the array list one for maintaining book record
	 *  other for   managing issued book
	 *  and one is used for sorting purpose  
	 */
	private int size=0;
	private int issueSize=0;
	private boolean modified=false;
	private boolean issueModified=false;
	private Book[] bookList=new Book[INITIAL_CAPACITY];
	private Student[] issuedBookList=new  Student[INITIAL_CAPACITY];
	private int capacity=INITIAL_CAPACITY;
	private int issuedCapacity=INITIAL_CAPACITY;
	
	Backaned db=new Backaned();


	/** 
	 * @param  title of the book
	 * @param  author of the book
	 * @param  subject of the book
	 * @param  publisher of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	public Book addOrChangeEntry
	(String bookTitle,String bookAuthor, String bookSubject, String bookPublisher,int numberOfTimes) {
				int index=find(bookTitle,bookAuthor);
				if(index>-1)
					bookList[index].setBookInformation(bookSubject, bookPublisher, numberOfTimes);
				else
					addBookList(bookTitle,bookAuthor,bookSubject,bookPublisher,numberOfTimes);
				modified=true;
				return bookList[size-1];
	}

	/**  
	 * @param bokkTitle for title of the book
	 * @param bookAuthor for author of the book
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */	
	public Book removeBookEntry(String bookTitle, String bookAuthor){
		int index=find(bookTitle,bookAuthor);
		if(index < size && index > -1){
			Book returnValue=bookList[index];
			remove(index);
			modified=true;
			return returnValue;
		}
		
		int issueIndex=searchIssueBookList(bookTitle);
		
				if(issueIndex > -1)
					{
					removeIssuBookListEntry(issueIndex);
					}
	return null;
	}

	/**  
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	
	public void saveBookRecord() {
		
		if(modified)
		{
				try{
					PrintWriter fileWriter=new PrintWriter
							(new FileWriter(" "));
					for(int i=0;i<size;i++)
					{
						
						fileWriter.print(bookList[i].getBookTitle());
						fileWriter.print("\t"+bookList[i].getBookAuthor());
						fileWriter.print("\t"+bookList[i].getBookSubject());
						fileWriter.print("\t"+bookList[i].getBookPublisher());
						fileWriter.print("\t"+bookList[i].getNumberOfTimes()+"\t\n");
					
					}
					
					fileWriter.close();
					modified=false;
				}catch(FileNotFoundException fne){
					
					return;
				}catch(IOException ioe){
					System.out.println(failedWritingProcess);
					ioe.printStackTrace();
				}
				System.out.println("saved to book record");
		}
				

	}

	/**  
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	public void saveIssueBookRecord() {				
				if(issueModified)
				{
						try{
							PrintWriter fileWriter=new PrintWriter
									(new FileWriter(" "));
							for(int i=0 ; i<issueSize ; i++)
							{
								
								fileWriter.print(issuedBookList[i].getBookTitle());
								fileWriter.print("\t"+issuedBookList[i].getNameOfPerson());
								fileWriter.print("\t"+issuedBookList[i].getPersonID());
								fileWriter.print("\t"+issuedBookList[i].getDateOfIssue()+"\t\n");
							
							}
							
							fileWriter.close();
							issueModified=false;
						}catch(FileNotFoundException fne){
							
							return;
						}catch(IOException ioe){
							System.out.println(failedWritingProcess);
							ioe.printStackTrace();
						}
						
						System.out.println("saved issue book record");
				}
			
			}
	
	/** 
	 * @param  title of the book
	 * @param  author of the book
	 * @param  subject of the book
	 * @param  publisher of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void addBookList(String bookTitle, String bookAuthor,
			String bookSubject, String bookPublisher, int numberOfTimes) {	
			if(size>=capacity)
				reallocate();
			bookList[size]=new Book
								(bookTitle,bookAuthor,bookSubject,bookPublisher,numberOfTimes);
			size++;
	}
	
	/** 
	 * @param  title of the book
	 * @param  name of the person
	 * @param  subject of the book
	 * @param  publisher of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void addIssuedBookList(String bookTitle, String nameOfPerson,
			String personID, String dateOfIssue) {
		if(issueSize>=issuedCapacity)
			reallocateIssue();
		
		issuedBookList[issueSize]=new Student
							(bookTitle,nameOfPerson,personID,dateOfIssue);
		issueSize++;
	}

	/** 
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	
	private void reallocateIssue() {
			
		issuedCapacity=2*issuedCapacity;
		Student[] newBookList=new Student[capacity];
		System.arraycopy(issuedBookList, 0, newBookList, 0, issuedBookList.length);
		issuedBookList=newBookList;	
		
	}
	/** 
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void reallocate() {
		capacity=2*capacity;
		Book[] newBookList=new Book[capacity];
		System.arraycopy(bookList, 0, newBookList, 0, bookList.length);
		bookList=newBookList;
		
	}

	/** 
	 * @param  title of the book
	 * @param  author of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private int find(String bookTitle, String bookAuthor) {
				
				for(int i=0;i < size;i++)
				{
					if(	(	bookList[i].getBookTitle().equalsIgnoreCase(bookTitle)	)
							
							&&(		bookList[i].getBookAuthor().equalsIgnoreCase(bookAuthor)	) )
					{
						return i;
					}
					
				}
		return -1;
	}

	/** 
	 * @param  index of book in arrayList
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	
	private void remove(int index) {
		
			for(int i=index;i<size-1;i++)
			{
				bookList[i]=bookList[i+1];
			}
			size--;
		
	}

	/** 
	 * @param  title of the book
	 * @param  author of the book
	 * @param  name of the student
	 * @param  id of the stodent given at the time of registration
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */

   public boolean issueTheBook(String bookTitle,
			String bookAuthor,String nameOfStudent,String studentID) {
		
				int bookIndex=find(bookTitle , bookAuthor);
				int issueIndex=searchIssueBookList(bookTitle);
				String date=null;
				
				if( (issueIndex==-1) && (bookIndex==-1) )
				{
					System.out.println("The book title-->"
							           +bookTitle+"and author-->"+bookAuthor
							           +"	is neither in library nor issued to anyone");
							return false;
				}else if(issueIndex > -1){
					
					printIssuedMassage(issueIndex);
					
					return false;
				}else{
						
						Calendar cal=Calendar.getInstance();
					    date=cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DATE)+"/"+cal.get(Calendar.YEAR);
					    bookList[bookIndex].incrementNOFIssue();
					    addIssuedBookList(bookTitle,nameOfStudent,studentID,date);
					    issueModified=true;
	
					    
				}
				
				return true;
	}

   /** 
	 * @param  issue index
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void printIssuedMassage(int issueIndex) {		
		 			System.out.println("the book title--->"+issuedBookList[issueIndex].getBookTitle()
		 							  +"\nis issued to--->"+issuedBookList[issueIndex].getNameOfPerson()	
		 							  +"\nand date of issue is-->"+issuedBookList[issueIndex].getDateOfIssue()
		 							  );
		
	}

	/** 
	 * @param  title of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private int searchIssueBookList(String bookTitle) {
				
		for(int i=0;i < issueSize;i++)
		{
			if(issuedBookList[i].getBookTitle().equalsIgnoreCase(bookTitle)  )
			{
				return i;
			}
			
		}
		
		return -1;
	}

	/** 
	 * @param  title of the book
	 * @param  author of the book
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	public boolean returnTheBook(String bookTitle , String bookAuthor) {
			
				
		 			int issueIndex=searchIssueBookList( bookTitle);
		 
		 			if(issueIndex>-1)
		 			{
		 					removeIssuBookListEntry(issueIndex);
		 					return true;
		 			}
		 			return false;
	}

	/** 
	 * @param  issue index.
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void removeIssuBookListEntry(int issueIndex) {
		
	
				for(int i=issueIndex;i<issueSize-1;i++)
				{
						issuedBookList[i]=issuedBookList[i+1];
				}
				
				issueSize--;
				issueModified=true;
		
	}
	/** 
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	public boolean displayBookByTitle() {
					
					sortingArrayListElement();
					printingInSortedOrder();
					
					return true;
			}

	/** 
	 * @param  no of times this book is issued
	 * @return void 
	 * @see com.Sample.MyExample#calculate() 
	 * @since Ver  2.11
	 */
	private void printingInSortedOrder() {
				for(int i=0;i<size;i++)
				{
		
					
								System.out.println(bookList[i].getBookTitle()
													+"\t"+bookList[i].getBookAuthor()
													+"\t"+bookList[i].getBookSubject()
													+"\t"+bookList[i].getBookPublisher()
													+"\t"+bookList[i].getNumberOfTimes()
												);
						
				}
	
			}

	private void sortingArrayListElement() {
					int res;
					Book temp=null;
					System.out.println(size);
					for(int i=0;i<size-1;i++)
					{
						for(int j=i+1;j<size;j++)
						{
							res=(bookList[i].getBookTitle().compareToIgnoreCase (bookList[j].getBookTitle()));
							if(res<0)
							{
								//System.out.print("\n"+size);
								temp=bookList[i];
								bookList[i]=bookList[j];
								bookList[j]=temp;
							}
						}
					}
					
	
				}

	public boolean displayBookOnSubject(String bookSubject) {
				boolean printedDataCond=false;
				for(int i=0;i<size;i++)
				{
					if(bookList[i].getBookSubject().equalsIgnoreCase(bookSubject))
							{
								System.out.println(bookList[i].getBookTitle()
													+"\t"+bookList[i].getBookAuthor()
													+"\t"+bookList[i].getBookSubject()
													+"\t"+bookList[i].getBookPublisher()
													+"\t"+bookList[i].getNumberOfTimes()
								
								
												);
								printedDataCond=true;
							}
					
				}
				
				return printedDataCond;
			}
				

	@Override
	public boolean validateUser(int roll) {	
		return db.validateUser(roll);
	}

	@Override
	public void loadDataSet() {
		// TODO Auto-generated method stub
		db.initalize(bookList,issuedBookList);
	}
}