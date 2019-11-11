package com.spacetech.moovme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spacetech.moovme.Points.RankingInPointTable;
import com.spacetech.moovme.R;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<RankingInPointTable> ranking;

    public RankingAdapter(Context context, ArrayList<RankingInPointTable> ranking){
        this.inflater = LayoutInflater.from(context);
        this.ranking = ranking;
    }

    public RankingHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.ranking_item, parent, false);
        return new RankingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingHolder holder, int position) {
        String name = ranking.get(position).getData().getName();
        int points = ranking.get(position).getPoints().getPointsinIntValue();

        String rank = (position+1)  + ". " + name + "                                    " + points + "pts.";
        holder.nameView.setText(rank);
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    public class RankingHolder extends  RecyclerView.ViewHolder{
        TextView nameView;

        RankingHolder(View itemView){
            super(itemView);
            nameView = itemView.findViewById(R.id.ranking_userView);
        }
    }
}
