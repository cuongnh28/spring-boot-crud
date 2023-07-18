package com.example.springbootcrud.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.TuyenXe;
import com.example.springbootcrud.repository.TuyenXeRepository;
import com.example.springbootcrud.service.TuyenXeService;

import java.util.List;
import java.util.Optional;

@Service
public class TuyenXeServiceImpl implements TuyenXeService {
    private TuyenXeRepository tuyenXeRepo;

    @Autowired
    public TuyenXeServiceImpl(TuyenXeRepository tuyenXeRepo){
        this.tuyenXeRepo = tuyenXeRepo;
    }
    @Override
    public List<TuyenXe> getAllTuyenXe() {
        return (List<TuyenXe>) tuyenXeRepo.findAll();
    }

    @Override
    public void saveTuyenXe(TuyenXe tuyenXe) {
        tuyenXeRepo.save(tuyenXe);
    }

    @Override
    public void deleteTuyenXe(Long id) {
        tuyenXeRepo.deleteById(id);
    }

    @Override
    public Optional<TuyenXe> getTuyenXeById(Long id) {
        return tuyenXeRepo.findById(id);
    }

    @Override
    public List<TuyenXe> searchTuyenXeByKeyWord(String keyword) {
        return tuyenXeRepo.searchTuyenXeByKeyWord(keyword);
    }
}
