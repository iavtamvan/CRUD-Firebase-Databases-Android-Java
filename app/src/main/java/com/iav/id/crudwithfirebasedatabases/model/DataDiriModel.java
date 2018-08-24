package com.iav.id.crudwithfirebasedatabases.model;

public class DataDiriModel {
    String nama;
    String alamat;
    String key;


    public DataDiriModel() {
    }

    public DataDiriModel(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
