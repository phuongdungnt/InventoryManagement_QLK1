package com.ntpd.inventorymanagement_qlk.module;

import java.io.Serializable;

public class Goods implements Serializable {

    String etCodeG;
    String etNameG;
    String etTime;
    String etDate;
    String etMadeIn;
    String etNote;
    int etPrice;
    int etSL;
    int tongtien;

    //them sp trong creategooodactivity


    public Goods(String etCodeG, String etNameG, String etTime, String etDate, String etMadeIn, String etNote, int etPrice, int etSL, int tongtien) {
        this.etCodeG = etCodeG;
        this.etNameG = etNameG;
        this.etTime = etTime;
        this.etDate = etDate;
        this.etMadeIn = etMadeIn;
        this.etNote = etNote;
        this.etPrice = etPrice;
        this.etSL = etSL;
        this.tongtien = tongtien;
    }
    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int thanhtien) {
        this.tongtien = thanhtien;
    }

    public Goods(String code, String name, String time, String date, String madein, String sl, String note, String price, int tongtien){

    }

    public String getEtCodeG() {
        return etCodeG;
    }

    public void setEtCodeG(String etCodeG) {
        this.etCodeG = etCodeG;
    }

    public String getEtNameG() {
        return etNameG;
    }

    public void setEtNameG(String etNameG) {
        this.etNameG = etNameG;
    }

    public String getEtTime() {
        return etTime;
    }

    public void setEtTime(String etTime) {
        this.etTime = etTime;
    }

    public String getEtDate() {
        return etDate;
    }

    public void setEtDate(String etDate) {
        this.etDate = etDate;
    }

    public String getEtMadeIn() {
        return etMadeIn;
    }

    public void setEtMadeIn(String etMadeIn) {
        this.etMadeIn = etMadeIn;
    }

    public String getEtNote() {
        return etNote;
    }

    public void setEtNote(String etNote) {
        this.etNote = etNote;
    }

    public int getEtPrice() {
        return etPrice;
    }

    public void setEtPrice(int etPrice) {
        this.etPrice = etPrice;
    }

    public int getEtSL() {
        return etSL;
    }

    public void setEtSL(int etSL) {
        this.etSL = etSL;
    }

    @Override
    public String toString() {
        return "Good{" +
                "etCodeG='" + etCodeG + '\'' +
                ", etNameG='" + etNameG + '\'' +
                ", etTime='" + etTime + '\'' +
                ", etDate='" + etDate + '\'' +
                ", etMadeIn='" + etMadeIn + '\'' +
                ", etNote='" + etNote + '\'' +
                ", etPrice=" + etPrice +
                ", etSL=" + etSL +
                ", tongtien=" + tongtien +
                '}';
    }
}
