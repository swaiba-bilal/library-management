package library;
// Book class to represent a library book
 class Book {
    // Step 1: Declare private variables for bookTitle, author, publicationYear, and isAvailable
    private String bookTitle;
    private String author;
    private int publicationYear;
    private boolean isAvailable;
    // Step 2: Create a constructor that accepts bookTitle, author, and publicationYear
    Book(String bookTitle,String author,int publicationYear){
        this.bookTitle=bookTitle;
        this.author=author;
        this.publicationYear=publicationYear;
    }

    // Step 3: Create public getter methods for each variable
    public String getBookTitle(){
        return bookTitle;
    }
    public String getAuthor(){
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    // Step 4: Create public setter methods for relevant variables
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    // Step 5: Create methods to check out and return a book
    // checkOut() method should set isAvailable to false and return true if the book was available
    // returnBook() method should set isAvailable to true and return true if the book was checked out
    public boolean checkOut(){
        if(isAvailable){
            isAvailable=false;
            return true;
        }
        return false;
    }
    public boolean returnBook(){
        if(!isAvailable){
            isAvailable=true;
            return true;
        }
        return false;
    }
    // Step 6: Override toString() method to display book details
    public String toString(){
        return "\""+bookTitle+"\" by"+author+"("+publicationYear+") -"+(isAvailable?"Available":"Checked out");
    }
}
