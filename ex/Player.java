public class Player {
    private String name;
    private String position;
    private int age;

    public class Player implements Serializable {
       private String name;
       private String position;
       private int age;
    }

    public String getName()     { return name; }
    public String getPosition() { return position; }
    public int    getAge()      { return age; }
    public void   setAge(int age) { this.age = age; }

    @Override
    public String toString() {
    return name + " (" + position + ", age " + age + ")";
    }
}