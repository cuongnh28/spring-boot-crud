package com.example.springbootcrud.service;

import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.DoanhThuXeKhach;
import com.example.springbootcrud.model.XeKhach;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface XeKhachService {
    List<XeKhach> getAllXeKhach();
    void saveXeKhach(XeKhach xeKhach);
    void deleteXeKhach(Long id);
    Optional<XeKhach> getXeKhachById(Long id);
    List<XeKhach> searchXeKhachByKeyword(String keyword);
    boolean checkTonTai(String bienSo);
    List<DoanhThuXeKhach> doanhThuXeKhachs(Date startDate, Date endDate); //Tinh doanh thu xe khach.
}
