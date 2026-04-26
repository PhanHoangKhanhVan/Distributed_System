public class Ex1_2{
     static class Publication {
        private String title;
        private String language;
        private double price;

        public Publication(String title, String language, double price){
            this.title = title;
            this.language = language;
            this.price = price;
        }

        public void print(){
            System.out.println("Title: " +title+ ",Language: "+ language+", Price: "+price);
        }
    }

    static class Book extends Publication {
        private String author;
        private String isbn;
        public Book(String title, String language, double price, String author, String isbn){
            super(title, language, price);
            this.author = author;
            this.isbn = isbn;
        }
        @Override
        public void print(){
            super.print();
            System.out.println("Author: " + author + ", ISBN: " + isbn);
        }
    }

    public static void main(String[] args){
        Publication[] publications = new Publication[2]; 
        publications[0] = new Publication("Generic News", "English", 2.99);
        publications[1] = new Book("Distributed Systems", "Vietnam", 3.0, "Klingemann", "123");
        for (Publication publication : publications) {
            publication.print();
        }
    }
}