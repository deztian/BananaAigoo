package dztn.dev.bananaaigoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dztn.dev.bananaaigoo.Adapter.RvMenuAdapter;
import dztn.dev.bananaaigoo.Common.Common;
import dztn.dev.bananaaigoo.Model.Category;
import dztn.dev.bananaaigoo.Model.ItemCart;
import dztn.dev.bananaaigoo.Model.User;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    User user;
    TextView textFullName;
    TextView total;
    ImageButton pesan;

    Integer totalBelanja = 0;

    private ArrayList<Category> items = new ArrayList<>();

    RecyclerView recycler_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        total = findViewById(R.id.total);
        pesan = findViewById(R.id.imageButton);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        //Set Name for user
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        textFullName = (TextView) headerView.findViewById(R.id.txtFullName);
        getUserData();

        category = database.getReference("Category");
        database.getReference().child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Category item = data.getValue(Category.class);
                    Log.d("CEKITEM", item.getNama());
                    items.add(item);
                }
                getCart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this, "Gagal cuy", Toast.LENGTH_SHORT).show();
            }
        });

        RvMenuAdapter adapter = new RvMenuAdapter(this);
        adapter.setmDataset(items);
        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_menu.setAdapter(adapter);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    private void getCart() {
        database.getReference().child("User").child(auth.getUid()).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                totalBelanja = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ItemCart item = data.getValue(ItemCart.class);
                    for (Category i : items) {
                        if (i.getNama().equals(item.getNama())) {
                            totalBelanja += (Integer.valueOf(i.getHarga()) * Integer.valueOf(item.getQty())) + toping(item);
                        }
                    }
                }
                total.setText("Total\n"+totalBelanja.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private int toping(ItemCart item) {
        int tot = 0;
        int[] harga = new int[]{3000,4000,6000,5000,5000};
        for (int i = 0; i < 5; i++) {
            if (item.getToping().get(i).booleanValue() == true){
                tot += harga[i];
            }
        }
        return tot;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_keranjang) {
            Intent intent = new Intent(this, KeranjangActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_riwayat){

        }else if (id == R.id.nav_keluar) {
            auth.signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getUserData() {
        database.getReference().child("User").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                textFullName.setText(user.getNamaLengkap());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
