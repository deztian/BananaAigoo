package dztn.dev.bananaaigoo.Model;

public class User {
    private String NamaLengkap;
    private String NoTelp;
    private String Password;


    public User() {

    }

    public User(String namaLengkap, String noTelp, String password) {
        NamaLengkap = namaLengkap;
        NoTelp = noTelp;
        Password = password;
    }

    public String getNamaLengkap() {
        return NamaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        NamaLengkap = namaLengkap;
    }

    public String getNoTelp() {
        return NoTelp;
    }

    public void setNoTelp(String noTelp) {
        NoTelp = noTelp;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}