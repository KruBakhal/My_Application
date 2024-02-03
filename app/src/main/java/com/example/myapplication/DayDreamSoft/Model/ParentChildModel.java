package com.example.myapplication.DayDreamSoft.Model;

import java.util.ArrayList;

public class ParentChildModel {
    String parent;
    ArrayList<String> child;

    public ParentChildModel(String parent, ArrayList<String> child) {
        this.parent = parent;
        this.child = child;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public ArrayList<String> getChild() {
        return child;
    }

    public void setChild(ArrayList<String> child) {
        this.child = child;
    }
}
