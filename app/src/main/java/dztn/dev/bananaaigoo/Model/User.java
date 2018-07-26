package dztn.dev.bananaaigoo.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String NamaLengkap;
    private String NoTelp;


    public User() {

    }

    public User(String namaLengkap, String noTelp) {
        NamaLengkap = namaLengkap;
        NoTelp = noTelp;
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
}