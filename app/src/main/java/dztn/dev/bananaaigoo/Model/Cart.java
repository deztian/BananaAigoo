package dztn.dev.bananaaigoo.Model;

import java.util.ArrayList;

public class Cart {
    private String Nama;
    private String Image;
    private String ImageDialog;
    private String Harga;
    private String qty;
    private ArrayList<Boolean> toping;

    public Cart() {
    }

    public Cart(String nama, String image, String imageDialog, String harga, String qty, ArrayList<Boolean> toping){
        Nama = nama;
        Image = image;
        ImageDialog = imageDialog;
        Harga = harga;
        this.qty = qty;
        this.toping = toping;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageDialog() {
        return ImageDialog;
    }

    public void setImageDialog(String imageDialog) {
        ImageDialog = imageDialog;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
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
}
