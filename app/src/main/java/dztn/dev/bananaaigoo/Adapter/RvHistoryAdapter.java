package dztn.dev.bananaaigoo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dztn.dev.bananaaigoo.Model.ItemCart;
import dztn.dev.bananaaigoo.R;

import java.util.ArrayList;

import dztn.dev.bananaaigoo.Model.Cart;

public class RvHistoryAdapter extends RecyclerView.Adapter<RvHistoryAdapter.ViewHolder> {


    private ArrayList<Cart> dataSet;
    private Context context;

    public RvHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int harga = (Integer.valueOf(dataSet.get(position).getHarga()) * Integer.valueOf(dataSet.get(position).getQty())) + toping(dataSet.get(position).getToping());
        holder.harga.setText("" + harga);
        holder.nama.setText(dataSet.get(position).getNama());
        holder.jumlah.setText(dataSet.get(position).getQty());
        holder.toping.setText(topingName(dataSet.get(position).getToping()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setDataSet(ArrayList<Cart> dataSet) {
        this.dataSet = dataSet;
    }

    private int toping(ArrayList<Boolean> toping) {
        int tot = 0;
        int[] harga = new int[]{3000, 4000, 6000, 5000, 5000};
        for (int i = 0; i < 5; i++) {
            if (toping.get(i).booleanValue() == true) {
                tot += harga[i];
            }
        }
        return tot;
    }

    private String topingName(ArrayList<Boolean> toping) {
        String top = "";
        int count = 1;
        String[] nama = new String[]{"Chocochip", "Milo", "Kitkat", "Oreo", "Cheese"};
        for (int i = 0; i < 5; i++) {
            if (toping.get(i).booleanValue() == true) {
                top += (count + ". " + nama[i] + "\n");
                count++;
            }
        }
        if (top.equals("")) {
            top += "-";
        }
        return top;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView harga;
        TextView nama;
        TextView jumlah;
        TextView toping;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.sub_total_harga);
            nama = itemView.findViewById(R.id.nama_item);
            jumlah = itemView.findViewById(R.id.jumlah);
            toping = itemView.findViewById(R.id.toping);
        }
    }
}
