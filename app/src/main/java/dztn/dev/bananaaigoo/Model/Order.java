package dztn.dev.bananaaigoo.Model;

import java.util.ArrayList;

public class Order {
    private String uid;
    private String date;
    private ArrayList<Cart> item;

    public Order(String uid, String date, ArrayList<Cart> item){
        this.uid = uid;
        this.date = date;
        this.item = item;
    }

    public Order(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Cart> getItem() {
        return item;
    }

    public void setItem(ArrayList<Cart> item) {
        this.item = item;
    }
}
