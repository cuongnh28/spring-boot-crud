package com.example.springbootcrud.service;

import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.TaiXe;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@Service
public interface TaiXeService {
    List<TaiXe> getAllTaiXe();
    void saveTaiXe(TaiXe taiXe);
    void deleteTaiXe(Long id);
    Optional<TaiXe> getTaiXeById(Long id);
    List<TaiXe> searchTaiXeByKeyword(String keyword);
    boolean checkTonTai(String cmt, String maSoBangLai);
    List<TaiXe> getSalaryTaiXe(Date startDate, Date endDate) ;
}
