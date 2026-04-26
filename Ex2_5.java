// 1. Dinh nghia Interface
interface Display {
    void print();
}

// 2. Class Car thuc thi Interface
class Car implements Display {
    private String colour;
    private int horsepower;
    private double weight;

    public Car(String colour, int horsepower, double weight) {
        this.colour = colour;
        this.horsepower = horsepower;
        this.weight = weight;
    }

    public void print() {
        System.out.println("Car - Colour: " + colour + ", HP: " + horsepower + ", Weight: " + weight);
    }
}

// 3. Sua lai class Publication tu Sheet 1 de implement Display
class Publication implements Display {
    private String title;
    private String language;
    private double price;

    public Publication(String title, String language, double price) {
        this.title = title;
        this.language = language;
        this.price = price;
    }

    public void print() {
        System.out.println("Pub - Title: " + title + ", Price: " + price);
    }
}

// Class Book ke thua Publication nen cung tu dong la kieu Display
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
        System.out.println("Book - Author: " + author + ", ISBN: " + isbn);
    }
}

public class Ex2_5 {
    public static void main(String[] args) {
        // Tao mang kieu Display
        Display[] objects = new Display[3];
        objects[0] = new Publication("Mag", "EN", 5.0);
        objects[1] = new Book("Java", "DE", 30.0, "Klingemann", "987");
        objects[2] = new Car("Red", 150, 1200.0);

        for (Display d : objects) {
            d.print();
            System.out.println("---");
        }
    }
}