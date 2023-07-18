package com.example.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springbootcrud.model.TaiXe;

import java.util.List;

@Repository
public interface TaiXeRepository extends JpaRepository<TaiXe, Long> {
    @Query("SELECT taiXe FROM TaiXe taiXe WHERE taiXe.ten LIKE %?1%")
    public List<TaiXe> searchTaiXeByKeyword(String keyword);

    //Neu ton tai cmt hoac maSoBangLai thi khong them vao.
    @Query("SELECT COUNT(taiXe)>0 FROM TaiXe taiXe WHERE taiXe.cmt = ?1 OR taiXe.maSoBangLai = ?2")
    public boolean checkTonTai(String cmt, String maSoBangLai);
}
