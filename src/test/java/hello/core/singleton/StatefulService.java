package hello.core.singleton;

public class StatefulService {
    private int price;

    public void order(String userName, int price){
        System.out.println("userName = " + userName + " price = " + price);
        this.price = price; // FIXME: do not use stateful field
    }

    public int getPrice(){
        return price;
    }
}
