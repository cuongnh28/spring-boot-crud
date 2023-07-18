package com.example.springbootcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class TuyenXe implements Serializable {
	@Id
	@Column(name="tuyenXeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tuyenXeId;
	@NotNull
	private String diemDau;
	@NotNull
	private String diemCuoi;
	@NotNull
	private int quangDuong;
	@NotNull
	@Range(min = 1, max =3)
	private int doPhucTap;
	@OneToMany(mappedBy = "tuyenXe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ChuyenXe> listChuyenXe; //list Chuyen Xe co su dung tuyen xe nay.

	public TuyenXe(){}

	public TuyenXe(Long taiXeId, String diemDau, String diemCuoi, int quangDuong, int doPhucTap){
		this.tuyenXeId = taiXeId;
		this.diemDau = diemDau;
		this.diemCuoi = diemCuoi;
		this.quangDuong = quangDuong;
		this.doPhucTap = doPhucTap;
	}

	public Long getTuyenXeId() {
		return tuyenXeId;
	}

	public void setTuyenXeId(Long tuyenXeId) {
		this.tuyenXeId = tuyenXeId;
	}

	public String getDiemCuoi() {
		return diemCuoi;
	}

	public void setDiemCuoi(String diemCuoi) {
		this.diemCuoi = diemCuoi;
	}

	public String getDiemDau() {
		return diemDau;
	}

	public void setDiemDau(String diemDau) {
		this.diemDau = diemDau;
	}

	public int getQuangDuong() {
		return quangDuong;
	}

	public void setQuangDuong(int quangDuong) {
		this.quangDuong = quangDuong;
	}

	public int getDoPhucTap() {
		return doPhucTap;
	}

	public void setDoPhucTap(int doPhucTap) {
		this.doPhucTap = doPhucTap;
	}

	@JsonIgnore
	public List<ChuyenXe> getListChuyenXe() { return this.listChuyenXe; }

	@JsonIgnore
	public void setListChuyenXe(List<ChuyenXe> listChuyenXe) {
		this.listChuyenXe = listChuyenXe;
	}

}
