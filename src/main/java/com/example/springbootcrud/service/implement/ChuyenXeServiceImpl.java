package com.example.springbootcrud.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.ChuyenXe;
import com.example.springbootcrud.repository.ChuyenXeRepository;
import com.example.springbootcrud.service.ChuyenXeService;

import java.util.List;
import java.util.Optional;

@Service
public class ChuyenXeServiceImpl implements ChuyenXeService {

    private ChuyenXeRepository chuyenXeRepo;
    @Autowired
    public ChuyenXeServiceImpl(ChuyenXeRepository chuyenXeRepo){ this.chuyenXeRepo = chuyenXeRepo; }
    @Override
    public List<ChuyenXe> getAllChuyenXe() {
        return chuyenXeRepo.findAll();
    }

    @Override
    public void saveChuyenXe(ChuyenXe chuyenXe) {
        chuyenXeRepo.save(chuyenXe);
    }

    @Override
    public void deleteChuyenXe(Long id) {
        chuyenXeRepo.deleteById(id);
    }

    @Override
    public Optional<ChuyenXe> getChuyenXeById(Long id) {
        return chuyenXeRepo.findById(id);
    }
}
