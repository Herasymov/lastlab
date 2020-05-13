package com.example.lastlab;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Pair<String, String>> mDataset;
    private LayoutInflater mInflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView myImageView;
        MyViewHolder(View v) {
            super(v);
            myImageView = v.findViewById(R.id.image);
            myTextView = v.findViewById(R.id.title);
        }
    }

    MyAdapter(Context context, List<Pair<String, String>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataset = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup gr, int viewType) {
        View view = mInflater.inflate(R.layout.rlist, gr, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String url = mDataset.get(position).first;
        Picasso.get().load(url).into(holder.myImageView);
        holder.myTextView.setText( mDataset.get(position).second);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
