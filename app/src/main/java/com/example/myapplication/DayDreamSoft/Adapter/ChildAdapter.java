package com.example.myapplication.DayDreamSoft.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DayDreamSoft.Intermediate.ParentInterface;
import com.example.myapplication.DayDreamSoft.Model.ParentChildModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {

    private List<String> moviesList;
    ParentInterface parentInterface;

    public void setList(ArrayList<String> child) {
        moviesList = new ArrayList<>();
        moviesList.addAll(child);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvText);
        }
    }


    public ChildAdapter(List<String> moviesList, ParentInterface parentInterface) {
        this.moviesList = moviesList;
        this.parentInterface = parentInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String movie = moviesList.get(position);
        holder.title.setText(movie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                parentInterface.onCLickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}