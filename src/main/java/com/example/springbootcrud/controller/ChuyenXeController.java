package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.ChuyenXeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.springbootcrud.model.ChuyenXe;
import com.example.springbootcrud.model.TaiXe;
import com.example.springbootcrud.model.TuyenXe;
import com.example.springbootcrud.model.XeKhach;
import com.example.springbootcrud.service.ChuyenXeService;
import com.example.springbootcrud.service.TaiXeService;
import com.example.springbootcrud.service.TuyenXeService;
import com.example.springbootcrud.service.XeKhachService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ChuyenXeController {

    private ChuyenXeService chuyenXeService;
    private TuyenXeService tuyenXeService;
    private TaiXeService taiXeService;
    private XeKhachService xeKhachService;
    public ChuyenXeController(ChuyenXeService chuyenXeService, TaiXeService taiXeService, TuyenXeService tuyenXeService, XeKhachService xeKhachService){
        this.taiXeService = taiXeService;
        this.xeKhachService = xeKhachService;
        this.tuyenXeService = tuyenXeService;
        this.chuyenXeService = chuyenXeService;
    }

    //Lay toan bo danh sach chuyen xe.
    @RequestMapping(value = "/chuyenXe", method = RequestMethod.GET)
    public ResponseEntity<List<ChuyenXe>> getAllChuyenXe(){
        List<ChuyenXe> listChuyenXe = chuyenXeService.getAllChuyenXe();
//        if(listChuyenXe.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        else{
            return new ResponseEntity<>(listChuyenXe, HttpStatus.OK);
//        }
    }

    //Lay chuyen xe theo id.
    @RequestMapping(value = "/chuyenXe/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChuyenXe> getChuyenXeById(@PathVariable("id") Long id){
        Optional<ChuyenXe> chuyenXe = chuyenXeService.getChuyenXeById(id);
        if(!chuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(chuyenXe.get(), HttpStatus.OK);
        }
    }

    //Them chuyen xe.
    @RequestMapping(value = "/chuyenXe/create", method = RequestMethod.POST)
    public ResponseEntity<ChuyenXe> addChuyenXe(@RequestBody ChuyenXeRequest chuyenXeRequest, UriComponentsBuilder builder){
        Long laiXeId = chuyenXeRequest.getLaiXeId();
        Long phuXeId = chuyenXeRequest.getPhuXeId();
        Long tuyenXeId = chuyenXeRequest.getTuyenXeId();
        Long xeKhachId = chuyenXeRequest.getXeKhachId();
        Optional<XeKhach> xeKhach = xeKhachService.getXeKhachById(xeKhachId);
        Optional<TaiXe> laiXe = taiXeService.getTaiXeById(laiXeId);
        Optional<TaiXe> phuXe = taiXeService.getTaiXeById(phuXeId);
        Optional<TuyenXe> tuyenXe = tuyenXeService.getTuyenXeById(tuyenXeId);
        if(!xeKhach.isPresent() || !laiXe.isPresent() || !phuXe.isPresent() || !tuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Kiem tra so khach < so ghe - 2. Va lai xe voi phu xe phai khac nhau
        int soKhach = chuyenXeRequest.getSoKhach();
        int soGhe = xeKhach.get().getSoGhe();
        if(!checkBeforeInsert(laiXeId, phuXeId, soGhe, soKhach)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            ChuyenXe chuyenXe = new ChuyenXe();
            chuyenXe.setSoKhach(chuyenXeRequest.getSoKhach());
            chuyenXe.setNgayDi(chuyenXeRequest.getNgayDi());
            chuyenXe.setGiaVe(chuyenXeRequest.getGiaVe());
            chuyenXe.setXeKhach(xeKhach.get());
            chuyenXe.setTuyenXe(tuyenXe.get());
            chuyenXe.setLaiXe(laiXe.get());
            chuyenXe.setPhuXe(phuXe.get());
            chuyenXeService.saveChuyenXe(chuyenXe);
            return new ResponseEntity<>(chuyenXe, HttpStatus.OK);
        }
    }

    //Sua chuyen xe.
    @RequestMapping(value = "/chuyenXe/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ChuyenXe> updateChuyenXe(@PathVariable("id") Long id, @RequestBody ChuyenXeRequest chuyenXeRequest){
        Optional<ChuyenXe> currentChuyenXe = chuyenXeService.getChuyenXeById(id);
        if(!currentChuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            Long laiXeId = chuyenXeRequest.getLaiXeId();
            Long phuXeId = chuyenXeRequest.getPhuXeId();
            if(laiXeId == phuXeId){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Long tuyenXeId = chuyenXeRequest.getTuyenXeId();
            Long xeKhachId = chuyenXeRequest.getXeKhachId();
            Optional<XeKhach> xeKhach = xeKhachService.getXeKhachById(xeKhachId);
            Optional<TaiXe> laiXe = taiXeService.getTaiXeById(laiXeId);
            Optional<TaiXe> phuXe = taiXeService.getTaiXeById(phuXeId);
            Optional<TuyenXe> tuyenXe = tuyenXeService.getTuyenXeById(tuyenXeId);
            if(!xeKhach.isPresent() || !laiXe.isPresent() || !phuXe.isPresent() || !tuyenXe.isPresent()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                currentChuyenXe.get().setChuyenXeId(id);
                currentChuyenXe.get().setSoKhach(chuyenXeRequest.getSoKhach());
                currentChuyenXe.get().setNgayDi(chuyenXeRequest.getNgayDi());
                currentChuyenXe.get().setGiaVe(chuyenXeRequest.getGiaVe());
                currentChuyenXe.get().setXeKhach(xeKhach.get());
                currentChuyenXe.get().setTuyenXe(tuyenXe.get());
                currentChuyenXe.get().setLaiXe(laiXe.get());
                currentChuyenXe.get().setPhuXe(phuXe.get());
                chuyenXeService.saveChuyenXe(currentChuyenXe.get());
            }
            return new ResponseEntity<>(currentChuyenXe.get(), HttpStatus.OK);
        }
    }

    //Xoa chuyen xe.
    @RequestMapping(value = "/chuyenXe/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteChuyenXe(@PathVariable ("id") Long id){
        Optional<ChuyenXe> chuyenXe = chuyenXeService.getChuyenXeById(id);
        if(!chuyenXe.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else{
            chuyenXeService.deleteChuyenXe(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    //Kiem tra dieu kien truoc khi them hoac sua. So khach < so ghe - 2, va lai xe voi phu xe phai khac nhau
    private boolean checkBeforeInsert(Long laiXeId, Long phuXeId, int soGhe, int soKhach){
        if(laiXeId == phuXeId || soGhe <= soKhach + 2){
            return false;
        }
        return  true;
    }
}
