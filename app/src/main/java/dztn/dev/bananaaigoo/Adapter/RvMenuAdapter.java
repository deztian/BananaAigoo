package dztn.dev.bananaaigoo.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dztn.dev.bananaaigoo.DialogBox;
import dztn.dev.bananaaigoo.R;

import java.util.ArrayList;

import dztn.dev.bananaaigoo.Model.Category;

public class RvMenuAdapter extends RecyclerView.Adapter<RvMenuAdapter.ViewHolder> {
    private ArrayList<Category> mDataset;
    private Context context;
    public RvMenuAdapter(Context context){
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.menu_image);
            card = itemView.findViewById(R.id.card);
        }
    }

    public void setmDataset(ArrayList<Category> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RvMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(mDataset.get(position).getImage()).into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox dialogBox = new DialogBox();
                dialogBox.showDialog(context, mDataset.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
