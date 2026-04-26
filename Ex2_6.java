import java.util.Scanner;
import java.util.*;
import java.io.*;

// 1. Dinh nghia Interface Display (Sheet 2 - Ex 5) [cite: 6, 8]
interface Display {
    void print();
}

// 2. Class cha Publication phai co Serializable de ghi file duoc [cite: 7, 31]
class Publication implements Display, Serializable {
    private String title;
    private String language;
    private double price;

    public Publication(String title, String language, double price) {
        this.title = title;
        this.language = language;
        this.price = price;
    }

    public void print() {
        System.out.println("Title: " + title + ", Language: " + language + ", Price: " + price);
    }
}

// 3. Class Book ke thua Publication [cite: 11]
class Book extends Publication {
    private String author;
    private String isbn;

    public Book(String title, String language, double price, String author, String isbn) {
        super(title, language, price);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Author: " + author + ", ISBN: " + isbn);
    }
}

// 4. Class Car moi (Sheet 2 - Ex 5) 
class Car implements Display, Serializable {
    private String colour;
    private int horsepower;
    private double weight;

    public Car(String colour, int horsepower, double weight) {
        this.colour = colour;
        this.horsepower = horsepower;
        this.weight = weight;
    }

    public void print() {
        System.out.println("Car -> Colour: " + colour + ", HP: " + horsepower + ", Weight: " + weight);
    }
}

// 5. Main class cho Bai tap 6 
public class Ex2_6 {
    public static void main(String[] args) {
        String fileName = "objects.ser";
        
        // Tao mang chua cac doi tuong khac nhau (Polymorphism) [cite: 15, 16]
        Display[] myObjects = new Display[3];
        myObjects[0] = new Publication("Magazine", "English", 5.50);
        myObjects[1] = new Book("Java DistSys", "German", 45.0, "Klingemann", "ISBN-123");
        myObjects[2] = new Car("Midnight Blue", 190, 1450.5);

        // --- GIAI DOAN 1: GHI FILE (Serialization) ---
        System.out.println("Saving objects to disk...");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(myObjects);
            System.out.println("Successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }

        System.out.println("------------------------------------");

        // --- GIAI DOAN 2: DOC FILE (Deserialization) ---
        System.out.println("Reading objects from disk...");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Doc mang doi tuong va ép kieu ve Display[]
            Display[] loadedObjects = (Display[]) ois.readObject();
            
            for (Display d : loadedObjects) {
                d.print();
                System.out.println("---");
            }
        } catch (Exception e) {
            System.err.println("Error loading: " + e.getMessage());
        }
    }
}