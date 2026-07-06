public class Player{
    //tao cac attribute
    private String name;
    private String position;
    private double age;

    //Contructor- ham khoi tao- tao Player
    public Player(String name, String position, double age){
        this.name = name;
        this.position = position;
        this.age = age;
    }

    //getter_ hanh dong de xem
    public String getName(){
        return this.name;
    }

    public String getPosition(){
        return this.position;
    }

    public double getAge(){
        return this.age;
    }

    // change Age - use Set
    public void changeAge(double newAge){
        this.age = newAge;
    }
}