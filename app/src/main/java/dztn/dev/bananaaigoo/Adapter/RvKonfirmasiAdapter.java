package dztn.dev.bananaaigoo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dztn.dev.bananaaigoo.Model.Cart;
import dztn.dev.bananaaigoo.R;

public class RvKonfirmasiAdapter extends RecyclerView.Adapter<RvKonfirmasiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cart> dataSet = new ArrayList<>();

    public RvKonfirmasiAdapter(Context context){
        this.context = context;
    }

    public void setDataSet(ArrayList<Cart> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_konfirmasi, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("CEKARRAY", dataSet.get(position).getNama());
        holder.jenis.setText(dataSet.get(position).getNama());
        holder.jumlah.setText(dataSet.get(position).getQty());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jenis;
        TextView jumlah;

        public ViewHolder(View itemView) {
            super(itemView);
            jenis = itemView.findViewById(R.id.jenis);
            jumlah = itemView.findViewById(R.id.jumlah);
        }
    }
}
