package com.example.myapplication.DayDreamSoft.Utils;

import com.example.myapplication.DayDreamSoft.Model.ParentChildModel;

import java.util.ArrayList;

public class Constant {
    public static ArrayList<ParentChildModel> listAll = new ArrayList<>();

    public static ArrayList<ParentChildModel> setUpModel() {

        String object[] = {"A", "B", "C", "D"};

        for (int i = 0; i < object.length; i++) {

            listAll.add(new ParentChildModel(object[i], getChild()));
        }

        return listAll;

    }

    private static ArrayList<String> getChild() {
        String object1[] = {"A_1", "B_1", "C_1", "D_1"};
        ArrayList<String> listChild = new ArrayList<>();

        for (int i = 0; i < object1.length; i++) {
            listChild.add(object1[i]);
        }

        return listChild;
    }


}
