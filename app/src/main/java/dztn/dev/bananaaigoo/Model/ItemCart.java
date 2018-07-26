package dztn.dev.bananaaigoo.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemCart {
    private String nama;
    private String qty;
    private ArrayList<Boolean> toping;

    public ItemCart(){}

    public ItemCart(String nama, String qty, ArrayList<Boolean> toping){
        this.nama = nama;
        this.qty = qty;
        this.toping = toping;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ArrayList<Boolean> getToping() {
        return toping;
    }

    public void setToping(ArrayList<Boolean> toping) {
        this.toping = toping;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
