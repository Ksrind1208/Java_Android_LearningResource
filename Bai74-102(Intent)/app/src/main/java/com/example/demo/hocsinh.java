package com.example.demo;

import java.io.Serializable;

public class hocsinh implements Serializable {
    private String ten;
    private int namsinh;

    public hocsinh(String ten, int namsinh) {
        this.ten = ten;
        this.namsinh = namsinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }
}
