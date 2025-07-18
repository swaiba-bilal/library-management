package library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
// LibraryManagement class to manage the book collection
public class LibraryManagement {
    public static void main(String[] args) {
        // Step 1: Create a Scanner object for user input
        Scanner sc= new Scanner(System.in);
        // Step 2: Create an ArrayList to store Book objects
        ArrayList<Book> library= new ArrayList<>();
        library.add(new Book("Atomic Habits","James Clear",2018));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.add(new Book("1984", "George Orwell", 1949));
        // Step 3: Implement a menu-driven program with the following options:
     boolean   running=true;
       while(running){
           System.out.println("\n-----Library Management System-----");
           System.out.println(" Press 1 to add books");
           System.out.println("Press 2 to view all books");
           System.out.println("Press 3 to search for a book ");
           System.out.println("Press 4 to check out a book");
           System.out.println("Press 5 to return a book");
           System.out.println("Press 6 to sort books by title,author, or publication year");
           System.out.println("Press 7 to view available books");
           System.out.println("Press 8 to find book by title");
           System.out.println("Press 9 to find book by index");
           System.out.println("Press 10 to exit");
           int choice;
           try{
               choice= Integer.parseInt(sc.nextLine());
           }
           catch (NumberFormatException e){
               System.out.println("Invalid input. Please enter a number");
               continue;
           }
           switch (choice){
               case 1:
                   addBook(sc,library);
                   break;
               case 2:
                   viewAllBooks(library);
                   break;
               case 3:
                   searchBook(sc,library);
                   break;
               case 4:
                   checkOutBook(sc,library);
                   break;
               case 5:
                   returnBook(sc,library);
                   break;
               case 6:
                   sortBooks(sc,library);
                   break;
               case 7:
                   viewAvailableBooks(library);
                   break;
               case 8:
                   findBookByTitle(sc,library);
                   break;
               case 9:
                   findByIndex(sc, library);
                   break;
               case 10:
                   System.out.println("Thank you for using the Library Management System. Goodbye!");
                   running = false;
                   break;
               default:
                   System.out.println("Invalid choice. Please try again.");
           }
       }}
        // Step 4: Implement the addBook functionality
        public static void addBook(Scanner sc,ArrayList<Book> library){
            System.out.println("\nAdd book details");
            System.out.println("Enter Book title");
            String bookTitle= sc.nextLine();
            System.out.println("Enter name of the author");
            String author= sc.nextLine();
            int year=0;
            boolean validYear=false;
            while(!validYear){
                System.out.println("Enter publication year");
                try{
                    year=Integer.parseInt(sc.nextLine());
                    validYear=true;
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid year.Please enter a valid year");
                }
            }
            Book newBook=new Book(bookTitle,author,year);
            library.add(newBook);
            System.out.println("Book added successfully "+ newBook);
        }
        // Step 5: Implement the viewAllBooks functionality
        public static void viewAllBooks(ArrayList<Book> library){
            System.out.println("---- All books in library----");
            if(library.isEmpty()){
                System.out.println("Library is empty.");
                return;
            }
            for(Book b:library){
                System.out.println(b.toString());
            }
        }
        // Step 6: Implement the search functionality
        public static void searchBook(Scanner sc ,ArrayList<Book> library){
            System.out.println("Enter the search term(Title or author)");
            String search=sc.nextLine().toLowerCase();
            System.out.println("Search results:");
            boolean found=false;
            for(Book b:library){
                if(b.getBookTitle().toLowerCase().contains(search) || b.getAuthor().toLowerCase().contains(search)){
                    System.out.println(b);
                    found=true;
                }
            }
            if(!found){
                System.out.println("No book found named \""+search+"\"");
            }
        }
        // Step 7: Implement the checkOut functionality
        public static void checkOutBook(Scanner sc, ArrayList<Book>library){
            System.out.println("----Check out a book----");
            if(library.isEmpty()){
                System.out.println("Library is empty");
                return;
            }
            System.out.println("Check out books");
            boolean hasCheckedOut = false;
            for(int i=0;i< library.size();i++){
                Book book=library.get(i);
                if(book.isAvailable()){
                    System.out.println((i+1)+"."+book);
                    hasCheckedOut=true;
                }
            }
            if(!hasCheckedOut){
                System.out.println("No books are currently checked out.");
                return;
            }
            // get book selection
            System.out.println("Enter the number of the book to checkout");
            try{
                int bookNumber=Integer.parseInt(sc.nextLine());
                if(bookNumber<1||bookNumber>library.size()){
                    System.out.println("Invalid book number");
                    return;
                }
                Book selectedBook= library.get(bookNumber-1);
                if(selectedBook.checkOut()){
                    System.out.println("Book checked out successfully"+selectedBook);
                }
                else{
                    System.out.println("Book is already checked out ");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number");
            }

        }
        // Step 8: Implement the returnBook functionality
       public static void returnBook(Scanner scanner, ArrayList<Book> library) {
            System.out.println("\n----- Return a Book -----");

            if (library.isEmpty()) {
                System.out.println("The library is empty.");
                return;
            }

            // Show checked out books
            System.out.println("Checked out books:");
            boolean hasCheckedOut = false;

            for (int i = 0; i < library.size(); i++) {
                Book book = library.get(i);
                if (!book.isAvailable()) {
                    System.out.println((i + 1) + ". " + book);
                    hasCheckedOut = true;
                }
            }

            if (!hasCheckedOut) {
                System.out.println("No books are currently checked out.");
                return;
            }

            // Get book selection
            System.out.print("Enter the number of the book to return: ");
            try {
                int bookNumber = Integer.parseInt(scanner.nextLine());

                if (bookNumber < 1 || bookNumber > library.size()) {
                    System.out.println("Invalid book number.");
                    return;
                }

                Book selectedBook = library.get(bookNumber - 1);

                if (selectedBook.returnBook()) {
                    System.out.println("Book returned successfully: " + selectedBook);
                } else {
                    System.out.println("Book was not checked out.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

    // Step 9: Implement the sortBooks functionality
        public static void sortBooks(Scanner sc, ArrayList<Book>library){
        if(library.isEmpty()){
            System.out.println("Library is empty.");
            return;
        }
            System.out.println("Enter your choice");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. publication year");
            System.out.println("Enter your choice");
            try{
                int choice=Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1:
                        Collections.sort(library, new Comparator<Book>() {
                            @Override
                            public int compare(Book b1, Book b2) {
                                return b1.getBookTitle().compareToIgnoreCase(b2.getBookTitle());
                            }
                        });
                        System.out.println("Book sorted by title");
                        break;
                    case 2:
                        Collections.sort(library, new Comparator<Book>() {
                            @Override
                            public int compare(Book b1, Book b2) {
                                return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
                            }
                        });
                        System.out.println("Books sorted by author");
                        break;
                    case 3:
                        Collections.sort(library, new Comparator<Book>() {
                            @Override
                            public int compare(Book b1, Book b2) {
                                return Integer.compare(b1.getPublicationYear(), b2.getPublicationYear());
                            }
                        });
                        System.out.println("Books sorted by publication year.");
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                viewAllBooks(library);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a number");
            }
        }
        // Step 10: Implement the viewAvailableBooks functionality
        public static void viewAvailableBooks(ArrayList<Book> library) {
            System.out.println("\n----- Available Books -----");

            if (library.isEmpty()) {
                System.out.println("The library is empty.");
                return;
            }

            boolean found = false;
            for (int i = 0; i < library.size(); i++) {
                Book book = library.get(i);
                if (book.isAvailable()) {
                    System.out.println((i + 1) + ". " + book);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No books are currently available.");
            }
        }
    
    // Step 11: Create findBookByTitle()
    public static void findBookByTitle(Scanner sc,ArrayList<Book> library){
        String title;
            System.out.println("Enter the title of the book you want to search");
           title=sc.nextLine().trim();
           boolean hasFound=false;
           for(Book b:library){
               if(b.getBookTitle().equalsIgnoreCase(title)){
                   System.out.println("Book found successfully"+b.toString());
                   hasFound=true;
                   break;
               }
           }
           if(!hasFound){
               System.out.println("There is no book with this title in library"+title);
           }


    }
    // Step 12: Create findBookByIndex(),
    public static void findByIndex(Scanner sc, ArrayList<Book> library){
        System.out.println("Enter the index of the book you want to search");
        int index;
        try{
            index=Integer.parseInt(sc.nextLine());
            if(index>=0 &&index< library.size()){
                System.out.println("Book found successfully... "+ library.get(index));
            }
            else{
                System.out.println("There is no book matching your index");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Invalid number. Please enter a integer");
        }
}}
