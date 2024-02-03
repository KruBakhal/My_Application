package com.example.myapplication.TicTacTo.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Matrix.Model.MatrixModel;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TicTacAdapter extends RecyclerView.Adapter<TicTacAdapter.UserViewHolder> {

    List<MatrixModel> list;
    List<MatrixModel> listSelected;
    int value;

    public TicTacAdapter(List<MatrixModel> list, int value) {
        this.list = list;
        this.value = value;

    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_matrix_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TicTacAdapter.UserViewHolder holder, int position) {

        MatrixModel matrixModel = list.get(position);
        holder.button.setText(matrixModel.getRow() + "" + matrixModel.getColumn());
        boolean status = checkInSelectedList(matrixModel);
        if (status) {
            Log.d("TAG", "onBindViewHolder: " + matrixModel.getRow() + "" + matrixModel.getColumn());
            holder.button.setBackgroundColor(Color.GREEN);
            if (listSelected.get(0).getRow() == matrixModel.getRow() &&
                    listSelected.get(0).getColumn() == matrixModel.getColumn()) {
                holder.button.setBackgroundColor(Color.DKGRAY);
            }
        } else {

            holder.button.setBackgroundColor(Color.BLUE);

        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.button.setBackgroundColor(Color.DKGRAY);

                listSelected = new ArrayList<>();
                listSelected.add(matrixModel);

              /*  // to form Square
                for (int i = 0; i <= matrixModel.getRow(); i++) {
                    for (int j = 0; j <= matrixModel.getColumn(); j++) {
                        if (!checkInSelectedList(new MatrixModel(i, j)))
                            listSelected.add(new MatrixModel(i, j));
                    }
                }
*/

                {
                    // bottom left to top right
                    int v1 = matrixModel.getRow();
                    int v2 = matrixModel.getColumn();
//                    if (v1 > 0)
                    for (int i = matrixModel.getRow(); i < value; i++) {
                        for (int j = matrixModel.getColumn(); j < value; j++) {
                            v1 = v1 - 1;
                            v2 = v2 + 1;
                            if (!checkInSelectedList(new MatrixModel(v1, v2)))
                                listSelected.add(new MatrixModel(v1, v2));

                        }

                    }

                    Log.d("TAG", "\n onClick: " + listSelected.toString());

                    // top left to bottom right
                    v1 = matrixModel.getRow();
                    v2 = matrixModel.getColumn();
                    for (int i = v1; i >= 0 && i < value; i++) {
                        for (int j = v2; j >= 0 && j < value; j++) {
                            v1 = v1 + 1;
                            v2 = v2 + 1;
                            if (!checkInSelectedList(new MatrixModel(v1, v2)))
                                listSelected.add(new MatrixModel(v1, v2));
                        }
                    }

                    Log.d("TAG", "\n onClick: " + listSelected.toString());
                    // top right to bottom left
                    v1 = matrixModel.getRow();
                    v2 = matrixModel.getColumn();
                    for (int i = matrixModel.getRow(); i < value; i++) {
                        for (int j = matrixModel.getColumn(); j < value; j++) {
                            v1 = v1 + 1;
                            v2 = v2 - 1;

                            if (!checkInSelectedList(new MatrixModel(v1, v2)))
                                listSelected.add(new MatrixModel(v1, v2));

                        }
                    }
                    // bottom right to to top left
                    v1 = matrixModel.getRow();
                    v2 = matrixModel.getColumn();
                    for (int i = 0; i < value; i++) {
                        for (int j = 0; j < value; j++) {
                            v1 = v1 - 1;
                            v2 = v2 - 1;
                            if (!checkInSelectedList(new MatrixModel(v1, v2)))
                                listSelected.add(new MatrixModel(v1, v2));

                        }
                    }
                    Log.d("TAG", "\n onClick: " + listSelected.toString());

                }
                notifyDataSetChanged();

            }
        });

    }

    private boolean checkInSelectedList(MatrixModel matrixModel) {
        boolean status = false;
        if (listSelected != null && listSelected.size() > 0)
            for (int i = 0; i < listSelected.size(); i++) {
                MatrixModel model = listSelected.get(i);
                if (matrixModel.getRow() == model.getRow() && matrixModel.getColumn() == model.getColumn()) {
                    status = true;
                    break;
                }

            }
        return status;
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn);
        }
    }
}
