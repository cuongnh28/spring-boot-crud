package com.example.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootcrud.model.TuyenXe;
import com.example.springbootcrud.service.TuyenXeService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TuyenXeController {

    private TuyenXeService tuyenXeService;
    @Autowired
    public TuyenXeController(TuyenXeService tuyenXeService){
        this.tuyenXeService = tuyenXeService;
    }

    //Lay toan bo tuyen xe.
    @RequestMapping(value = "/tuyenXe", method = RequestMethod.GET)
    public ResponseEntity<List<TuyenXe>> getAllTuyenXe(){
        List<TuyenXe> listTuyenXe= tuyenXeService.getAllTuyenXe();
        if(listTuyenXe.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(listTuyenXe, HttpStatus.OK);
        }
    }

    //Lay tuyenxe theo Id.
    @RequestMapping(value = "/tuyenXe/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TuyenXe> getTaiXeById(@PathVariable("id") Long id){
        Optional<TuyenXe> tuyenXe = tuyenXeService.getTuyenXeById(id);
        if(!tuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(tuyenXe.get(), HttpStatus.OK);
        }
    }

    //Tim kiem theo diem dau hoac diem cuoi.
    @RequestMapping(value = "/tuyenXe/search/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TuyenXe>> getTuyenXeByKeyword(@PathVariable("keyword") String keyword){
        List<TuyenXe> listTuyenXe = tuyenXeService.searchTuyenXeByKeyWord(keyword);
        if(listTuyenXe.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(listTuyenXe, HttpStatus.OK);
        }
    }

    //Them tuyen xe.
    @RequestMapping(value = "/tuyenXe/create", method = RequestMethod.POST)
    public ResponseEntity<TuyenXe> createTuyenXe(@RequestBody TuyenXe tuyenXe){
        tuyenXeService.saveTuyenXe(tuyenXe);
        return new ResponseEntity<>(tuyenXe, HttpStatus.CREATED);
    }

    //Sua tuyen xe
    @RequestMapping(value = "/tuyenXe/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TuyenXe> updateTuyenXe(@PathVariable("id") Long id, @RequestBody TuyenXe tuyenXe){
        Optional<TuyenXe> currentTuyenXe = tuyenXeService.getTuyenXeById(id);

        if(!currentTuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentTuyenXe.get().setDiemDau(tuyenXe.getDiemDau());
        currentTuyenXe.get().setDiemCuoi(tuyenXe.getDiemCuoi());
        currentTuyenXe.get().setQuangDuong(tuyenXe.getQuangDuong());
        currentTuyenXe.get().setDoPhucTap(tuyenXe.getDoPhucTap());
        tuyenXeService.saveTuyenXe(currentTuyenXe.get());
        return new ResponseEntity<>(currentTuyenXe.get(), HttpStatus.OK);
    }

    //Xoa tuyen xe by Id.
    @RequestMapping(value = "/tuyenXe/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTuyenXeById(@PathVariable("id") Long id){
        Optional<TuyenXe> currentTuyenXe = tuyenXeService.getTuyenXeById(id);
        //Neu khong ton tai.
        if(!currentTuyenXe.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            tuyenXeService.deleteTuyenXe(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
