package com.example.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springbootcrud.model.XeKhach;

import java.util.List;

@Repository
public interface XeKhachRepository extends JpaRepository<XeKhach, Long> {

    //Tim kiem boi bien so.
    @Query("SELECT xeKhach FROM XeKhach xeKhach WHERE xeKhach.bienSo LIKE %?1%")
    public List<XeKhach> searchXeKhachByKeyword(String keyword);

    //Check trung bien so truoc khi insert.
    @Query("SELECT COUNT(xeKhach) > 0 FROM XeKhach xeKhach WHERE xeKhach.bienSo = ?1")
    public boolean checkTonTai(String bienSo);

    //Tinh doanh du cua cac xe khach.
//    @Query(value = "use transportationmanagementsystem; select xe.xe_khach_id, xe.bien_so, ifnull(sum(cx.so_khach * cx.gia_ve), 0) as doanhThu " +
//            "from xe_khach xe " +
//            "left join chuyen_xe cx on xe.xe_khach_id = cx.xe_khach_id where cx.ngay_di between '2000-01-01' and  '2020-12-10'" +
//            "group by xe.xe_khach_id order by doanhThu;", nativeQuery = true)
//    List<DoanhThuXeKhach> doanhThuXeKhach();

//    Query q = em.

}
