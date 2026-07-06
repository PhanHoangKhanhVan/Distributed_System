import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

pubic class TeamInterface extends UnicastRemoteObject implements PlayerInterface{
    public Item(String name, String position, double Age) throws RemoteException{
        super(); goi lenh chuan bi he thong mang cua java
        this.name = name;
        this.price = price;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public String getPosition(){
        return this.position;
    }

    public double getAge(){
        return this.age;
    }

    public void changeAge(double newAge){
        this.age = newAge;
    }
}