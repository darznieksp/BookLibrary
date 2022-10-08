import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void terminalMenu(Connection conn){

        boolean terminalWorking = true;

        while(terminalWorking){
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Add book");
            System.out.println("2. Edit book");
            System.out.println("3. Show books");
            System.out.println("4. Delete book");
            System.out.println("5. Add multiple books");
            System.out.println("6. Exit");
            System.out.print("Enter number: ");
            String input = sc.nextLine();

            int bookID;
            String name;
            String description;
            int author;
            int genre;

            switch (input){
                case "1":
                    System.out.println("---------------");
                    System.out.print("Name of the book: ");
                    name = sc.nextLine();
                    System.out.print("Description of book: ");
                    description = sc.nextLine();
                    System.out.print("Author of the book: ");
                    author = sc.nextInt();
                    System.out.print("Genre of the book: ");
                    genre = sc.nextInt();

                    Queries.addBook(conn, name, description, author, genre);
                    break;
                case "2":
                    String strInput;
                    System.out.print("Enter BookID: ");
                    bookID = sc.nextInt();

                    System.out.println("1. Edit book name");
                    System.out.println("2. Edit description");
                    System.out.println("3. Edit author");
                    System.out.println("4. Edit genre");
                    System.out.print("Enter number: ");
                    strInput = sc.nextLine();
                    Queries.editBook(conn, bookID, strInput);
                case "3":
                    showBooks(conn);
                    break;
                case "4":
                    System.out.print("Enter book ID: ");
                    bookID = sc.nextInt();
                    Queries.deleteBook(conn, bookID);
                    break;
                case "5":
                    int x = 0;
                    System.out.print("How many books will be added? ");
                    int intInput = sc.nextInt();
                    sc.nextLine();
                    while(x < intInput){
                        System.out.println("---------------");
                        System.out.print("Name of the book: ");
                        name = sc.nextLine();
                        System.out.print("Description of book: ");
                        description = sc.nextLine();
                        System.out.print("Author of the book: ");
                        author = sc.nextInt();
                        System.out.print("Genre of the book: ");
                        genre = sc.nextInt();
                        sc.nextLine();

                        Queries.addBook(conn, name, description, author, genre);

                        x++;
                    }
                    break;
                case "6":
                    System.out.println("---------------");
                    System.out.println("Exiting...");
                    terminalWorking = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
            System.out.println();
        }
    }

    private static void showBooks(Connection conn){

        List<Book> bookList = Queries.getBooks(conn);
        for (Book book : bookList){
            System.out.println(book);
        }
    }
}
