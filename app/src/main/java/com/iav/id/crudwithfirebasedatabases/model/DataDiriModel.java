package com.iav.id.crudwithfirebasedatabases.model;

public class DataDiriModel {
    String nama;
    String alamat;
    String idKey;


    public DataDiriModel() {
    }

    public DataDiriModel(String nama, String alamat, String idKey) {
        this.nama = nama;
        this.alamat = alamat;
        this.idKey = idKey;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
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
