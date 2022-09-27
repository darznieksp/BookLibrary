
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Querries {

    public static List<Book> getBooks (Connection conn){
        List<Book> bookList = new ArrayList<>();

        int bookID;
        String name;
        String description;
        int authorID;
        int genreID;

        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");

            while (resultSet.next()){
                bookID = resultSet.getInt("BookId");
                name = resultSet.getString("BookName");
                description = resultSet.getString("Description");
                authorID = resultSet.getInt("AuthorId");
                genreID = resultSet.getInt("GenreID");

                bookList.add(new Book(bookID, name, description, authorID, genreID));
            }
            resultSet.close();
            stmt.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return bookList;
    }

    public static void addBook (Connection conn){
        Scanner sc = new Scanner(System.in);

        System.out.println("---------------");
        System.out.print("Name of the book: ");
        String name = sc.nextLine();
        System.out.print("Description of book: ");
        String description = sc.nextLine();
        System.out.print("Author of the book: ");
        int author = sc.nextInt();
        System.out.print("Genre of the book: ");
        int genre = sc.nextInt();

        try {
            String qry = "INSERT INTO books (BookName, Description, AuthorId, GenreID)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(qry);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, description);
            preparedStmt.setInt(3, author);
            preparedStmt.setInt(4, genre);
            preparedStmt.executeUpdate();

            System.out.println("Book successfully added!");

            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void editBook(Connection conn){
        Scanner sc = new Scanner(System.in);
        String strInput;
        int intInput;

        System.out.print("Enter BookID: ");
        int bookID = sc.nextInt();

        System.out.println("1. Edit book name");
        System.out.println("2. Edit description");
        System.out.println("3. Edit author");
        System.out.println("4. Edit genre");
        System.out.print("Enter number: ");
        strInput = sc.nextLine();

        switch(strInput){
            case "1":
                System.out.print("New book name: ");
                strInput = sc.nextLine();
                try{
                    String query = "UPDATE books SET BookName = ? WHERE BookId = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, strInput);
                    preparedStmt.setInt(2, bookID);

                    preparedStmt.close();
                    System.out.println("BookName updated!");
                } catch (Exception e){
                    System.out.println(e);
                }
                break;

            case "2":
                System.out.print("New description: ");
                strInput = sc.nextLine();
                try{
                    String query = "UPDATE books SET Description = ? WHERE BookId = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, strInput);
                    preparedStmt.setInt(2, bookID);

                    preparedStmt.close();
                    System.out.println("Description updated!");
                } catch (Exception e){
                    System.out.println(e);
                }
                break;

            case "3":
                System.out.print("New author ID: ");
                intInput = sc.nextInt();
                try{
                    String query = "UPDATE books SET AuthorId = ? WHERE BookId = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, intInput);
                    preparedStmt.setInt(2, bookID);

                    preparedStmt.close();
                    System.out.println("AuthorID updated!");
                } catch (Exception e){
                    System.out.println(e);
                }
                break;

            case "4":
                System.out.print("New genre ID: ");
                intInput = sc.nextInt();
                try{
                    String query = "UPDATE books SET GenreID = ? WHERE BookId = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, intInput);
                    preparedStmt.setInt(2, bookID);

                    preparedStmt.close();
                    System.out.println("GenreID updated!");
                } catch (Exception e){
                    System.out.println(e);
                }
                break;

            default:
                System.out.println("Wrong input!");
                break;
        }
    }

    public static void deleteBook (Connection conn){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter book ID: ");
        int bookID = sc.nextInt();
        try {
            String qry = "DELETE FROM books WHERE BookId = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(qry);
            preparedStmt.setInt(1, bookID);
            preparedStmt.executeUpdate();

            preparedStmt.close();
            System.out.println("Book with ID: " + bookID + " successfully deleted!");
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
