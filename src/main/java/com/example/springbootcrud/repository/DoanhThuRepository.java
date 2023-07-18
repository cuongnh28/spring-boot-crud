package com.example.springbootcrud.repository;

import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.DoanhThuXeKhach;

import java.sql.Date;
import java.util.List;
@Service
public interface DoanhThuRepository {
    List<DoanhThuXeKhach> tinhDoanhThu(Date startDate, Date endDate);
}
