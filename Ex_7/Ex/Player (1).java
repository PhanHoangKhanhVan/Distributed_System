import java.io.Serializable;

/**
 * Represents a single player. Must be Serializable so it can be transported
 * inside a JMS ObjectMessage.
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int number;
    private final String position;

    public Player(String name, int number, String position) {
        this.name = name;
        this.number = number;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "#" + number + " " + name + " (" + position + ")";
    }
}
