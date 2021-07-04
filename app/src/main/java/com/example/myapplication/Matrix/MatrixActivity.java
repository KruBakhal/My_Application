package com.example.myapplication.Matrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Matrix.Adapter.MatrixAdapter;
import com.example.myapplication.Matrix.Model.MatrixModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MatrixActivity extends AppCompatActivity {

    Context context;
    EditText editText;
    Button button;
    RecyclerView recyclerView;
    private int value = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        context = this;
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.btnOkay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(context, "enter value", Toast.LENGTH_SHORT).show();
                    return;
                }
                value = Integer.parseInt(s);
                List<MatrixModel> list = new ArrayList<MatrixModel>();
                for (int i = 0; i < value; i++) {

                    for (int j = 0; j < value; j++) {
                        list.add(new MatrixModel(i, j));
                    }

                }
                MatrixAdapter matrixAdapter = new MatrixAdapter(list,value);
                recyclerView.setLayoutManager(new GridLayoutManager(context, value));
                recyclerView.setAdapter(matrixAdapter);
            }
        });

    }
}