package com.example.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootcrud.model.ChuyenXe;
import com.example.springbootcrud.model.DoanhThuXeKhach;
import com.example.springbootcrud.model.XeKhach;
import com.example.springbootcrud.service.XeKhachService;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class XeKhachController {
    @Autowired
    private XeKhachService xeKhachService;
    @Autowired
    public XeKhachController(XeKhachService xeKhachService){
        this.xeKhachService = xeKhachService;
    }

    //lay toan bo xe khach.
    @RequestMapping(value = "/xeKhach", method = RequestMethod.GET)
    public ResponseEntity<List<XeKhach>> getAllXeKhach(){
        List<XeKhach> listXeKhach = xeKhachService.getAllXeKhach();
        if(listXeKhach.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(listXeKhach, HttpStatus.OK);
        }
    }

    //Lay xe khach theo id.
    @RequestMapping(value = "/xeKhach/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<XeKhach> getXeKhachById(@PathVariable("id") Long id){
        Optional<XeKhach> xeKhach = xeKhachService.getXeKhachById(id);
        if(!xeKhach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(xeKhach.get(), HttpStatus.OK);
        }
    }

    //Tim kiem thong tin theo bien so.
    @RequestMapping(value = "/xeKhach/search/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<XeKhach>> getXeKhachByKeyword(@PathVariable("keyword") String keyword){
        List<XeKhach> listXeKhach = xeKhachService.searchXeKhachByKeyword(keyword);
        if(listXeKhach.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(listXeKhach, HttpStatus.OK);
        }
    }

    //Them xe khach.
    @RequestMapping(value = "/xeKhach/create", method = RequestMethod.POST)
    public ResponseEntity<XeKhach> createXeKhach(@RequestBody XeKhach xeKhach){
        if(xeKhachService.checkTonTai(xeKhach.getBienSo())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            Date ngayBdTiepTheo = xeKhach.addDays(xeKhach.getNgayBaoDuong(), 360);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayBaoDuongTiepTheo = dateFormat.format(ngayBdTiepTheo);
            if(ngayBdTiepTheo.compareTo(new Date(System.currentTimeMillis())) < 0){
                xeKhach.setNgayBaoDuongTiepTheo("Đã quá hạn bảo dưỡng");
            }
            else{
                xeKhach.setNgayBaoDuongTiepTheo(ngayBaoDuongTiepTheo);
            }
            if(xeKhach.getListChuyenXe() != null){
                for(ChuyenXe chuyenXe:xeKhach.getListChuyenXe()){
                    //Neu he so kho la 2 thi * 1.2 con neu la 3 thi * 1.5
                    int heSoKho = 100;
                    if(chuyenXe.getTuyenXe().getDoPhucTap() == 2){
                        heSoKho *= 1.2;
                    }
                    else if(chuyenXe.getTuyenXe().getDoPhucTap() == 1){
                        heSoKho *= 1.5;
                    }
                    //Ngay bao duong tiep theo se bang ngay dung han tru di quangduong/heSoKho.
                    ngayBdTiepTheo = xeKhach.subtractDays(ngayBdTiepTheo, chuyenXe.getTuyenXe().getQuangDuong()/heSoKho);
                }
                if(ngayBdTiepTheo.compareTo(new Date(System.currentTimeMillis())) < 0){
                    xeKhach.setNgayBaoDuongTiepTheo("Đã quá hạn bảo dưỡng");
                }
                else{
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    ngayBaoDuongTiepTheo = dateFormat.format(ngayBdTiepTheo);
                    xeKhach.setNgayBaoDuongTiepTheo(ngayBaoDuongTiepTheo);
                }
            }
            xeKhachService.saveXeKhach(xeKhach);
            return new ResponseEntity<>(xeKhach, HttpStatus.CREATED);
        }
    }

    //Sua xe khach.
    @RequestMapping(value = "/xeKhach/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<XeKhach> updateXeKhach(@RequestBody XeKhach xeKhach, @PathVariable("id") Long id){
        Optional<XeKhach> currentXeKhach = xeKhachService.getXeKhachById(id);
        if(!currentXeKhach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            currentXeKhach.get().setBienSo(xeKhach.getBienSo());
            currentXeKhach.get().setDoiXe(xeKhach.getDoiXe());
            currentXeKhach.get().setHangSanXuat(xeKhach.getHangSanXuat());
            currentXeKhach.get().setMauXe(xeKhach.getMauXe());
            currentXeKhach.get().setModel(xeKhach.getModel());
            currentXeKhach.get().setNgayBaoDuong(xeKhach.getNgayBaoDuong());
            currentXeKhach.get().setSoGhe(xeKhach.getSoGhe());
            currentXeKhach.get().setSoNamSuDung(xeKhach.getSoNamSuDung());

            Date ngayBdTiepTheo = xeKhach.addDays(xeKhach.getNgayBaoDuong(), 360);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayBaoDuongTiepTheo = dateFormat.format(ngayBdTiepTheo);
            if(ngayBdTiepTheo.compareTo(new Date(System.currentTimeMillis())) < 0){
                currentXeKhach.get().setNgayBaoDuongTiepTheo("Đã quá hạn ✖");
            }
            else{
                currentXeKhach.get().setNgayBaoDuongTiepTheo(ngayBaoDuongTiepTheo);
            }
            if(xeKhach.getListChuyenXe() != null){
                for(ChuyenXe chuyenXe:xeKhach.getListChuyenXe()){
                    //Neu he so kho la 2 thi * 1.2 con neu la 3 thi * 1.5
                    int heSoKho = 100;
                    if(chuyenXe.getTuyenXe().getDoPhucTap() == 2){
                        heSoKho *= 1.2;
                    }
                    else if(chuyenXe.getTuyenXe().getDoPhucTap() == 3){
                        heSoKho *= 1.5;
                    }
                    //Ngay bao duong tiep theo se bang ngay dung han tru di quangduong/heSoKho.
                    ngayBdTiepTheo = xeKhach.subtractDays(ngayBdTiepTheo, chuyenXe.getTuyenXe().getQuangDuong()/heSoKho);
                }
                if(ngayBdTiepTheo.compareTo(new Date(System.currentTimeMillis())) < 0){
                    currentXeKhach.get().setNgayBaoDuongTiepTheo("Đã quá hạn bảo dưỡng");
                }
                else{
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    ngayBaoDuongTiepTheo = dateFormat.format(ngayBdTiepTheo);
                    currentXeKhach.get().setNgayBaoDuongTiepTheo(ngayBaoDuongTiepTheo);
                }
            }
            xeKhachService.saveXeKhach(currentXeKhach.get());
            return new ResponseEntity<>(currentXeKhach.get(), HttpStatus.OK);
        }
    }

    //Xoa xe khach theo id.
    @RequestMapping(value = "/xeKhach/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteXeKhach(@PathVariable("id") Long id){
        Optional<XeKhach> xeKhach = xeKhachService.getXeKhachById(id);
        if(!xeKhach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            xeKhachService.deleteXeKhach(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //tinh doanh thu xe khach.
    @RequestMapping(value = "/xeKhach/doanhThu/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<List<DoanhThuXeKhach>> getDoanhThuXeKhach(@PathVariable("startDate") Date startDate, @PathVariable ("endDate") Date endDate){
        List<DoanhThuXeKhach> doanhThuXeKhach = xeKhachService.doanhThuXeKhachs(startDate, endDate);
        if(doanhThuXeKhach.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(doanhThuXeKhach, HttpStatus.OK);
        }
    }


//    @JsonIgnore
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public static Date addDays(Date date, int days) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DATE, days);
//        return new Date(c.getTimeInMillis());
//    }
//
//    //Ham tru ngay.
////    @JsonIgnore
//    @RequestMapping(value = "/test2", method = RequestMethod.GET)
//    public static Date subtractDays(Date date, int days) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DATE, -days);
//        return new Date(c.getTimeInMillis());
//    }
}
