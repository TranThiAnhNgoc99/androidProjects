package com.example.ngoctta999123.heath.models;

public class TV_TraVe {
    private int ID_TV;
    private int ID_BS_DaKhoa;
    private int ID_BS_ChuyenMon;
    private String TV_Trave;

    public TV_TraVe(int ID_TV, int ID_BS_DaKhoa, int ID_BS_ChuyenMon, String TV_Trave) {
        this.ID_TV = ID_TV;
        this.ID_BS_DaKhoa = ID_BS_DaKhoa;
        this.ID_BS_ChuyenMon = ID_BS_ChuyenMon;
        this.TV_Trave = TV_Trave;
    }

    public int getID_TV() {
        return ID_TV;
    }

    public void setID_TV(int ID_TV) {
        this.ID_TV = ID_TV;
    }

    public int getID_BS_DaKhoa() {
        return ID_BS_DaKhoa;
    }

    public void setID_BS_DaKhoa(int ID_BS_DaKhoa) {
        this.ID_BS_DaKhoa = ID_BS_DaKhoa;
    }

    public int getID_BS_ChuyenMon() {
        return ID_BS_ChuyenMon;
    }

    public void setID_BS_ChuyenMon(int ID_BS_ChuyenMon) {
        this.ID_BS_ChuyenMon = ID_BS_ChuyenMon;
    }

    public String getTV_Trave() {
        return TV_Trave;
    }

    public void setTV_Trave(String TV_Trave) {
        this.TV_Trave = TV_Trave;
    }
}
