package com.example.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springbootcrud.model.ChuyenXe;

@Repository
public interface ChuyenXeRepository extends JpaRepository<ChuyenXe, Long> {
}
