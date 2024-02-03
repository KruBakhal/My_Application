package com.example.myapplication.DayDreamSoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.myapplication.DayDreamSoft.Adapter.ParentChildAdapter;
import com.example.myapplication.DayDreamSoft.Intermediate.ParentInterface;
import com.example.myapplication.DayDreamSoft.Model.ParentChildModel;
import com.example.myapplication.DayDreamSoft.Utils.Constant;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFirstBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    int selectedParent = 0;
    private ActivityFirstBinding binding;
    private ArrayList<ParentChildModel> list_All;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first);

        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (list_All == null) {
            list_All = new ArrayList<>();
            list_All.add(new ParentChildModel("No Item Seleted", null));
            list_All.addAll(Constant.setUpModel());
        }

        binding.DropDown.setText(list_All.get(selectedParent).getParent());


//        binding.editText.
        binding.DropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(FirstActivity.this, null);
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = binding.editText.getText().toString();
                if (TextUtils.isEmpty(str) || str.equals(list_All.get(0).getParent())) {
                    Toast.makeText(FirstActivity.this, "Enter child name  ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (selectedParent != 0) {
                    ParentChildModel model = list_All.get(selectedParent);
                    ArrayList<String> list = model.getChild();
                    if (list == null || list.size() == 0) {
                        list = new ArrayList<>();
                    }
                    list.add(str);
                    model.setChild(list);
                    list_All.set(selectedParent, model);
                    Toast.makeText(FirstActivity.this, "Added to child list", Toast.LENGTH_SHORT).show();

                } else {

                    ParentChildModel model = new ParentChildModel(str, new ArrayList<>());
                    list_All.add(model);
                    selectedParent = list_All.size() - 1;
                    binding.DropDown.setText(list_All.get(selectedParent).getParent());
                    Toast.makeText(FirstActivity.this, "Added to Parent list", Toast.LENGTH_SHORT).show();

                }

            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedParent == 0) {
                    Toast.makeText(FirstActivity.this, "NO Parent Selected ", Toast.LENGTH_SHORT).show();
                    return;

                }

                startActivity(new Intent(FirstActivity.this, SecondActivity.class)
                        .putExtra("pos", selectedParent).
                                putExtra("list", new Gson().toJson(list_All)));


            }
        });


    }

    private void showPopup(final Activity context, Point p) {


        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, null);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);

        popup.setWidth(getWindowManager().getDefaultDisplay().getWidth() * 300 / 1080);
        popup.setHeight(getWindowManager().getDefaultDisplay().getHeight() * 500 / 1080);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background

        // Displaying the popup at the specified location, + offsets.
        popup.showAsDropDown(binding.DropDown, 0, 0);

        // Getting a reference to Close button, and close the popup when clicked.
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.rv_List);


        ParentChildAdapter parentChildAdapter = new ParentChildAdapter(list_All, new ParentInterface() {
            @Override
            public void onCLickItem(int position) {
                selectedParent = position;
                binding.editText.setText(list_All.get(position).getParent());
                binding.DropDown.setText(list_All.get(position).getParent());
                popup.dismiss();
            }
        });
        recyclerView.setAdapter(parentChildAdapter);


    }
}