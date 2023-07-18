package com.example.springbootcrud.dto;

import java.sql.Date;

public class ChuyenXeRequest {

    private float giaVe;
    private Date ngayDi;
    private int soKhach;
    private Long laiXeId;
    private Long phuXeId;
    private Long xeKhachId;
    private Long tuyenXeId;

    public ChuyenXeRequest() {
    }

    public ChuyenXeRequest(float giaVe, Date ngayDi, int soKhach, Long laiXeId, Long phuXeId, Long xeKhachId, Long tuyenXeId) {
        this.giaVe = giaVe;
        this.ngayDi = ngayDi;
        this.soKhach = soKhach;
        this.laiXeId = laiXeId;
        this.phuXeId = phuXeId;
        this.xeKhachId = xeKhachId;
        this.tuyenXeId = tuyenXeId;
    }

    public float getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(float giaVe) {
        this.giaVe = giaVe;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public int getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(int soKhach) {
        this.soKhach = soKhach;
    }

    public Long getLaiXeId() {
        return laiXeId;
    }

    public void setLaiXeId(Long laiXeId) {
        this.laiXeId = laiXeId;
    }

    public Long getPhuXeId() {
        return phuXeId;
    }

    public void setPhuXeId(Long phuXeId) {
        this.phuXeId = phuXeId;
    }

    public Long getXeKhachId() {
        return xeKhachId;
    }

    public void setXeKhachId(Long xeKhachId) {
        this.xeKhachId = xeKhachId;
    }

    public Long getTuyenXeId() {
        return tuyenXeId;
    }

    public void setTuyenXeId(Long tuyenXeId) {
        this.tuyenXeId = tuyenXeId;
    }
}
