package dztn.dev.bananaaigoo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

import dztn.dev.bananaaigoo.Adapter.RvKonfirmasiAdapter;
import dztn.dev.bananaaigoo.Model.Cart;
import dztn.dev.bananaaigoo.Model.Order;
import dztn.dev.bananaaigoo.Model.User;

public class PesanDetailActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private RvKonfirmasiAdapter adapter;

    private ArrayList<Cart> cart = new ArrayList<>();
    private User user;
    private String totalBelanja;

    private TextView nama;
    private RecyclerView recyclerView;
    private TextView total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_detail);
        getSupportActionBar().setTitle("Daftar Pemesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUserData();

        nama = findViewById(R.id.atas_nama);
        recyclerView = findViewById(R.id.recycler_view);
        total = findViewById(R.id.total);

        cart = (ArrayList<Cart>) getIntent().getSerializableExtra("CART");
        totalBelanja = getIntent().getStringExtra("TOTAL");

        adapter = new RvKonfirmasiAdapter(this);
        adapter.setDataSet(cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void getUserData() {
        database.child("User").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);

                nama.setText(user.getNamaLengkap());
                total.setText("Rp. "+totalBelanja);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateData(){
        UUID uuid = UUID.randomUUID();
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        database.child("Order").child(uuid.toString()).setValue(new Order(auth.getUid(),ts,cart));
        database.child("User").child(auth.getUid()).child("Cart").removeValue();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            updateData();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateData();
    }
}
