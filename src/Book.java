
public class Book {

    private int bookID;
    private String name;
    private String description;
    private int authorID;
    private int genreID;

    public Book(int bookID, String name, String description, int authorID, int genreID){
        this.bookID = bookID;
        this.name = name;
        this.description = description;
        this.authorID = authorID;
        this.genreID = genreID;
    }

   @Override
   public String toString() {
        return bookID + " | " + name + " | " + description + " | " + authorID + " | " + genreID;
   }
}
