
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Querries {

    public static List<Book> getBooks (Connection connection){
        List<Book> bookList = new ArrayList<>();

        int bookID;
        String name;
        String description;
        int authorID;
        int genreID;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()){
                bookID = resultSet.getInt("BookId");
                name = resultSet.getString("BookName");
                description = resultSet.getString("Description");
                authorID = resultSet.getInt("AuthorId");
                genreID = resultSet.getInt("GenreID");

                bookList.add(new Book(bookID, name, description, authorID, genreID));
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return bookList;
    }

    public static void addBook (Connection connection){
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
            PreparedStatement stmt = connection.prepareStatement(qry);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, author);
            stmt.setInt(4, genre);
            stmt.executeUpdate();

            System.out.println("Book successfully added!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteBook (Connection connection){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter book ID: ");
        int bookID = sc.nextInt();

        try {
            String qry = "DELETE FROM books WHERE BookId = ?";
            PreparedStatement stmt = connection.prepareStatement(qry);
            stmt.setInt(1, bookID);
            stmt.executeUpdate();

            System.out.println("Book with ID: " + bookID + " successfully deleted!");
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
