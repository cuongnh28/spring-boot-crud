package com.example.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springbootcrud.model.TuyenXe;

import java.util.List;

@Repository
public interface TuyenXeRepository extends JpaRepository<TuyenXe, Long> {
    @Query("SELECT tuyenXe FROM TuyenXe tuyenXe WHERE tuyenXe.diemDau LIKE %?1% OR tuyenXe.diemCuoi LIKE %?1%")
    public List<TuyenXe> searchTuyenXeByKeyWord(String keyword);
}
