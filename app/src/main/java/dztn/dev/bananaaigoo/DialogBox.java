package dztn.dev.bananaaigoo;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import dztn.dev.bananaaigoo.Model.Category;
import dztn.dev.bananaaigoo.Model.ItemCart;

public class DialogBox {
    private ImageView imageView;
    private Dialog dialog;
    private CheckBox choco;
    private CheckBox milo;
    private CheckBox kitkat;
    private CheckBox oreo;
    private CheckBox cheese;
    private TextView pesan;
    private EditText qty;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private ArrayList<Boolean> toping = new ArrayList<>();

    public void showDialog(final Context context, final Category item) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.costumdialog);

        choco = dialog.findViewById(R.id.choco);
        milo = dialog.findViewById(R.id.milo);
        kitkat = dialog.findViewById(R.id.kitkat);
        oreo = dialog.findViewById(R.id.oreo);
        cheese = dialog.findViewById(R.id.cheese);

        pesan = dialog.findViewById(R.id.btn_pesan);
        qty = dialog.findViewById(R.id.qty);

        imageView = dialog.findViewById(R.id.image);
        Glide.with(context).load(item.getImageDialog()).into(imageView);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toping.add(false);
                toping.add(false);
                toping.add(false);
                toping.add(false);
                toping.add(false);

                onCheckboxClicked();
                UUID id = UUID.randomUUID();
                if (!TextUtils.isEmpty(qty.getText())) {
                    if (Integer.valueOf(qty.getText().toString()) > 0) {
                        db.child("User").child(auth.getUid()).child("Cart").child(id.toString()).setValue(new ItemCart(item.getNama(),qty.getText().toString(), toping));
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Jumlah Tidak Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Jumlah Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public void onCheckboxClicked() {

        if (choco.isChecked()) {
            toping.set(0, true);
        }
        if (milo.isChecked()) {
            toping.set(1, true);
        }
        if (kitkat.isChecked()) {
            toping.set(2, true);
        }
        if (oreo.isChecked()) {
            toping.set(3, true);
        }
        if (cheese.isChecked()) {
            toping.set(4, true);
        }
    }
}
