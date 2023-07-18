package com.example.springbootcrud.model;

public class DoanhThuXeKhach{
    private Long xeKhachId;
    private String bienSo;
    private float doanhThu;

    public DoanhThuXeKhach(){}

    public DoanhThuXeKhach(Long xeKhachId, String bienSo, float doanhThu) {
        this.xeKhachId = xeKhachId;
        this.bienSo = bienSo;
        this.doanhThu = doanhThu;
    }

    public Long getXeKhachId() {
        return xeKhachId;
    }

    public String getBienSo() {
        return bienSo;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }
}
