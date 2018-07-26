package dztn.dev.bananaaigoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.w3c.dom.Text;

import dztn.dev.bananaaigoo.Model.User;

public class SignUpActivity extends AppCompatActivity {

    MaterialEditText email, edtPhone, edtName, edtPassword;
    Button btnSignUp;

    //Init Firebase
    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDialog = new ProgressDialog(SignUpActivity.this);

        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        email = (MaterialEditText) findViewById(R.id.email);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                createUser();
            }
        });

    }

    private void createUser() {
        if (!TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(edtPassword.getText())
                && !TextUtils.isEmpty(edtName.getText()) && !TextUtils.isEmpty(edtPhone.getText())) {
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), edtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                addDataUser();
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void addDataUser() {
        User user = new User(edtName.getText().toString(), edtPhone.getText().toString());
        table_user.child(firebaseAuth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SignUpActivity.this, "Daftar sukses !", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                Intent intent = new Intent(SignUpActivity.this, SignActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
