package com.example.springbootcrud.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootcrud.model.TaiXe;
import com.example.springbootcrud.repository.TaiXeRepository;
import com.example.springbootcrud.service.TaiXeService;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TaiXeServiceImpl implements TaiXeService {

    private TaiXeRepository taiXeRepo;
    @Autowired
    public TaiXeServiceImpl(TaiXeRepository taiXeRepo){
        this.taiXeRepo = taiXeRepo;
    }

    @Override
    public List<TaiXe> getAllTaiXe() {
        return (List<TaiXe>) taiXeRepo.findAll();
    }

    @Override
    public void saveTaiXe(TaiXe taiXe) {
        taiXeRepo.save(taiXe);
    }

    @Override
    public void deleteTaiXe(Long id) {
        taiXeRepo.deleteById(id);
    }

    @Override
    public Optional<TaiXe> getTaiXeById(Long id) {
        return taiXeRepo.findById(id);
    }

    @Override
    public List<TaiXe> searchTaiXeByKeyword(String keyword) {
        return taiXeRepo.searchTaiXeByKeyword(keyword);
    }

    @Override
    public boolean checkTonTai(String cmt, String maSoBangLai) {
        return taiXeRepo.checkTonTai(cmt, maSoBangLai);
    }

	@Override
	public List<TaiXe> getSalaryTaiXe(Date startDate, Date endDate) {
		List<TaiXe> listTaiXe= taiXeRepo.findAll();
      //List<TaiXe> listTaiXe = jsonArrayToObjectList(restTemplate.getForObject(REST_SERVICE_URI, List.class).toString(), tClass);
  	for(TaiXe i: listTaiXe) {
  		try {
				i.setSalary(startDate, endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	}
  	System.out.println(listTaiXe);
      return listTaiXe;
	}
    
//    @Override
//    public List<TaiXe> getSalaryTaiXe(Date startDate, Date endDate){
//    	
//    	List<TaiXe> listTaiXe= taiXeRepo.findAll();
//        //List<TaiXe> listTaiXe = jsonArrayToObjectList(restTemplate.getForObject(REST_SERVICE_URI, List.class).toString(), tClass);
//    	for(TaiXe i: listTaiXe) {
//    		try {
//				i.setSalary(startDate, endDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	}
//    	System.out.println(listTaiXe);
//        return listTaiXe;
//    }
}
