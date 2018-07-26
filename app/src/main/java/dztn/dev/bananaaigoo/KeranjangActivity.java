package dztn.dev.bananaaigoo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dztn.dev.bananaaigoo.Adapter.RvHistoryAdapter;
import dztn.dev.bananaaigoo.Model.Cart;
import dztn.dev.bananaaigoo.Model.Category;
import dztn.dev.bananaaigoo.Model.ItemCart;

public class KeranjangActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Cart> dataSet = new ArrayList<>();
    private ArrayList<Category> category = new ArrayList<>();

    private RvHistoryAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        getSupportActionBar().setTitle("Keranjang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        getItem();

        adapter = new RvHistoryAdapter(this);
        adapter.setDataSet(dataSet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void getItem(){
        category.clear();
        database.child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Category item = data.getValue(Category.class);
                    Log.d("CEKITEM", item.getNama());
                    category.add(item);
                }
                getHistory();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KeranjangActivity.this, "Gagal cuy", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getHistory(){
        dataSet.clear();
        database.child("User").child(auth.getUid()).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ItemCart order = data.getValue(ItemCart.class);
                    for (Category i : category) {
                        if (i.getNama().equals(order.getNama())) {
                            dataSet.add(new Cart(i.getNama(),i.getImage(), i.getImageDialog(), i.getHarga(),order.getQty(),order.getToping()));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("error", databaseError.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
