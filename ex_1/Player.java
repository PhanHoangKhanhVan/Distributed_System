package ex_1;

public class Player {
    private String name;
    private String position;
    private int age;

    public Player(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // Dùng để biến đối tượng thành chuỗi gửi qua Socket
    @Override
    public String toString() {
        return name + "," + position + "," + age;
    }
}