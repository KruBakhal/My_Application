package com.example.myapplication.DayDreamSoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.myapplication.DayDreamSoft.Adapter.ChildAdapter;
import com.example.myapplication.DayDreamSoft.Adapter.ParentChildAdapter;
import com.example.myapplication.DayDreamSoft.Intermediate.ParentInterface;
import com.example.myapplication.DayDreamSoft.Model.ParentChildModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySecondBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private ArrayList<ParentChildModel> list_All;
    private int selectedParent = 0;
    private ChildAdapter childAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        selectedParent = getIntent().getIntExtra("pos", 0);
        String list = getIntent().getStringExtra("list");

        Type type = new TypeToken<ArrayList<ParentChildModel>>() {
        }.getType();

        list_All = new ArrayList<>();
        list_All.addAll(new Gson().fromJson(list, type));
        binding.DropDown.setText(list_All.get(selectedParent).getParent());

        childAdapter = new ChildAdapter(list_All.get(selectedParent).getChild(), new ParentInterface() {
            @Override
            public void onCLickItem(int position) {

            }
        });
        binding.recyclerView.setAdapter(childAdapter);

        binding.DropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup(SecondActivity.this, null);

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
                childAdapter.setList(list_All.get(selectedParent).getChild());
                popup.dismiss();
            }
        });
        recyclerView.setAdapter(parentChildAdapter);


    }

}