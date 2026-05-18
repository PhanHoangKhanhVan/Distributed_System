import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<String> books = new ArrayList<>();

    public Library(String name){
        this.name = name;
    }

    //Method 1: addBook
    public String addBook(String title){
        books.add(title);
        return title + "added to " + name;
    }

    //Method 2: removeBook
    public String removeBook(String title){
        if(books.remove(title)){
            return title + " removed from " + name;
        }
        return title + " not found in " + name;
    }

    //Method 3: getBooks()
    public String getBooks(){
        if (books.isEmpty()) return name + " no title.";
        return name + ": " + String.join(", ", books);
    }

    //Method 4: getName
    public String getName(){
        return name;
    }

}