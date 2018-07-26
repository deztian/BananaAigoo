package dztn.dev.bananaaigoo.Model;

public class Category {
    private String Nama;
    private String Image;
    private String ImageDialog;
    private String Harga;

    public Category() {
    }

    public Category(String name, String image, String harga, String imageDialog) {
        Nama = name;
        Image = image;
        Harga = harga;
        ImageDialog = imageDialog;
    }

    public String getImageDialog() {
        return ImageDialog;
    }

    public void setImageDialog(String imageDialog) {
        ImageDialog = imageDialog;
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

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }
}
