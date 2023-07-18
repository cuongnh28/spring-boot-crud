package com.example.springbootcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class ChuyenXe implements Serializable {

	@Id
	@Column(name="chuyenXeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long chuyenXeId;
	@NonNull
	private int soKhach;
	@NonNull
	private float giaVe;
	@NonNull
	private Date ngayDi;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "laiXeId")
	private TaiXe laiXe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phuXeId")
	private TaiXe phuXe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "xeKhachId")
	private XeKhach xeKhach;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tuyenXeId")
	private TuyenXe tuyenXe;

	public ChuyenXe(){}

	public ChuyenXe(Long chuyenXeId, int soKhach, int giaVe, Date ngayDi, TaiXe laiXe, TaiXe phuXe, TuyenXe tuyenXe, XeKhach xeKhach){
		this.chuyenXeId = chuyenXeId;
		this.soKhach = soKhach;
		this.giaVe = giaVe;
		this.ngayDi = ngayDi;
		this.laiXe = laiXe;
		this.phuXe = phuXe;
		this.tuyenXe = tuyenXe;
		this.xeKhach = xeKhach;
	}

	public Long getChuyenXeId() {
		return chuyenXeId;
	}

	public void setChuyenXeId(Long chuyenXeId) {
		this.chuyenXeId = chuyenXeId;
	}

	public int getSoKhach() {
		return soKhach;
	}

	public void setSoKhach(int soKhach) {
		this.soKhach = soKhach;
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

	@JsonIgnore
	public TaiXe getLaiXe() {
		return laiXe;
	}

	public void setLaiXe(TaiXe laiXe) {
		this.laiXe = laiXe;
	}

	@JsonIgnore
	public TaiXe getPhuXe() {
		return phuXe;
	}

	public void setPhuXe(TaiXe phuXe) {
		this.phuXe = phuXe;
	}

	@JsonIgnore
	public XeKhach getXeKhach() {
		return xeKhach;
	}

	public void setXeKhach(XeKhach xeKhach) {
		this.xeKhach = xeKhach;
	}

	@JsonIgnore
	public TuyenXe getTuyenXe() {
		return tuyenXe;
	}

	public void setTuyenXe(TuyenXe tuyenXe) {
		this.tuyenXe = tuyenXe;
	}

	public Long getLaiXeId() {
		return laiXe.getTaiXeId();
	}

	public Long getPhuXeId() {
		return phuXe.getTaiXeId();
	}

	public Long getXeKhachId() {
		return xeKhach.getXeKhachId();
	}

	public Long getTuyenXeId() {
		return tuyenXe.getTuyenXeId();
	}

	public String getTenLaiXe(){ return laiXe.getTen(); }

	public String getTenPhuXe() { return phuXe.getTen(); }

	public String getBienSoXe() { return xeKhach.getBienSo(); }

}
