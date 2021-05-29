package com.example.myapplication.Practice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Practice.PostModel.PostData;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.UserViewHolder> implements Filterable {

    private final String imgUrl;
    Context context;
    List<PostData> list;
    List<PostData> alBookInfo;
    private RecordFilter fRecords;

    @Override
    public Filter getFilter() {
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }

    public interface CategoryListener {
        void onClickItem(int position);

    }

    CategoryListener categoryListener;

    public PostAdapter(Context context, List<PostData> category, String imgUrl) {
        this.context = context;
//        this.categoryListener = (CategoryListener) context;
        this.list = category;
        this.alBookInfo = category;
        this.imgUrl = imgUrl;
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_shop, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostAdapter.UserViewHolder holder, int position) {
        PostData category = list.get(position);
        holder.tv_name.setText("id:" + category.getId());
        holder.tv_name1.setText("user_id:" + category.getUserId());
        holder.tv_name2.setText(category.getTitle());
//        Glide.with(context).load(imgUrl + "/" + category.getShopPicture()).circleCrop().into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                categoryListener.onClickItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        View view;
        ConstraintLayout lay_item;
        TextView tv_name, tv_name1, tv_name2;
        ImageView image;
        Button btn;

        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
            lay_item = view.findViewById(R.id.lay_item);
            tv_name = view.findViewById(R.id.tv_name);
            tv_name1 = view.findViewById(R.id.tv_name1);
            tv_name2 = view.findViewById(R.id.tv_name2);
            btn = view.findViewById(R.id.btn);
            image = view.findViewById(R.id.image);
        }
    }

    private class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            //Implement filter logic
            // if edittext is null return the actual list
            if (constraint == null || constraint.length() == 0) {
                //No need for filter
                results.values = alBookInfo;
                results.count = alBookInfo.size();

            } else {
                //Need Filter
                // it matches the text  entered in the edittext and set the data in adapter list
                ArrayList<PostData> fRecords = new ArrayList<>();

                for (PostData datum : alBookInfo) {
                    if (datum.getTitle().toLowerCase().trim().contains(constraint.toString().toLowerCase().trim())) {
                        fRecords.add(datum);
                    }
                }
                results.values = fRecords;
                results.count = fRecords.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //it set the data from filter to adapter list and refresh the recyclerview adapter
            list = (ArrayList<PostData>) results.values;
            notifyDataSetChanged();
        }
    }
}

