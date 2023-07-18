package com.example.springbootcrud.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springbootcrud.model.DoanhThuXeKhach;
import com.example.springbootcrud.model.XeKhach;
import com.example.springbootcrud.repository.DoanhThuRepository;
import com.example.springbootcrud.repository.XeKhachRepository;
import com.example.springbootcrud.service.XeKhachService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class XeKhachServiceImpl implements XeKhachService {
    @Autowired
    private DoanhThuRepository doanhThuRepository;
    @Autowired
    private XeKhachRepository xeKhachRepo;

    @Override
    public List<XeKhach> getAllXeKhach() {
        return (List<XeKhach>) xeKhachRepo.findAll();
    }

    @Override
    public void saveXeKhach(XeKhach xeKhach) {
        xeKhachRepo.save(xeKhach);
    }

    @Override
    public void deleteXeKhach(Long id) {
        xeKhachRepo.deleteById(id);
    }

    @Override
    public Optional<XeKhach> getXeKhachById(Long id) {
        return xeKhachRepo.findById(id);
    }

    @Override
    public List<XeKhach> searchXeKhachByKeyword(String keyword) {
        return xeKhachRepo.searchXeKhachByKeyword(keyword);
    }

    @Override
    public boolean checkTonTai(String bienSo) {
        return xeKhachRepo.checkTonTai(bienSo);
    }

    @Override
    public List<DoanhThuXeKhach> doanhThuXeKhachs(Date startDate, Date endDate) {
        return doanhThuRepository.tinhDoanhThu(startDate, endDate);
    }
}
