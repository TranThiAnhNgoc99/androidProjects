package com.example.ngoctta999123.heath.models;

public class Item {
    private int Ten;
    private int Hinh;

    public Item(int ten, int hinh) {
        Ten = ten;
        Hinh = hinh;
    }

    public int getTen() {
        return Ten;
    }

    public void setTen(int ten) {
        Ten = ten;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
