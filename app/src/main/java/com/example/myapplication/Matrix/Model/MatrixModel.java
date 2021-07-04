package com.example.myapplication.Matrix.Model;

public class MatrixModel {

    int row, column;

    public MatrixModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "MatrixModel{" +
                "" + row +
                ",=" + column +
                '}';
    }
}
