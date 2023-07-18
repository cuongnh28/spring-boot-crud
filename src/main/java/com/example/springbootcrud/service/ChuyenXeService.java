package com.example.springbootcrud.service;

import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.ChuyenXe;

import java.util.List;
import java.util.Optional;
@Service
public interface ChuyenXeService {
    List<ChuyenXe> getAllChuyenXe();
    void saveChuyenXe(ChuyenXe chuyenXe);
    void deleteChuyenXe(Long id);
    Optional<ChuyenXe> getChuyenXeById(Long id);
}
