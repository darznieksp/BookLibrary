import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void terminalMenu(Connection conn){

        boolean terminalWorking = true;

        while(terminalWorking){
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Add book");
            System.out.println("2. Show books");
            System.out.println("3. Delete book");
            System.out.println("4. Exit");
            System.out.print("Enter number: ");
            String input = sc.nextLine();

            switch (input){
                case "1":
                    Querries.addBook(conn);
                    break;
                case "2":
                    showBooks(conn);
                    break;
                case "3":
                    Querries.deleteBook(conn);
                    break;
                case "4":
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

        List<Book> bookList = Querries.getBooks(conn);
        for (Book book : bookList){
            System.out.println(book);
        }
    }
}
