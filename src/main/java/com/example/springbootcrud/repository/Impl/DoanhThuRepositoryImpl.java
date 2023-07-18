package com.example.springbootcrud.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.DoanhThuXeKhach;
import com.example.springbootcrud.repository.DoanhThuRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
@Service
public class DoanhThuRepositoryImpl implements DoanhThuRepository {
    @Autowired
    EntityManager em;

    @Override
    public List<DoanhThuXeKhach> tinhDoanhThu(Date startDate, Date endDate) {
        Query query = em.createNativeQuery("select xe.xe_khach_id, xe.bien_so, " +
                " ifnull(sum(cx.so_khach * cx.gia_ve), 0) as " +
                " doanh_thu from xe_khach xe left join chuyen_xe cx on xe.xe_khach_id = cx.xe_khach_id " +
                " where cx.ngay_di between ?1 and " +
                " ?2 group by xe.xe_khach_id order by doanh_thu", "DoanhThuResult");
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }
}
