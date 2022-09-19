package com.example.bridesmaids.service;


import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Product;
import com.example.bridesmaids.model.TimeSlot;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.repository.ProductRepository;
import com.example.bridesmaids.repository.TimeSlotRepository;
import com.example.bridesmaids.repository.VendorRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final ProductRepository productRepository;
    private final VendorRepositry vendorRepositry;



    public List<TimeSlot> getTimeSlot() {
        return timeSlotRepository.findAll();
    }


    public void addTimeSlot(TimeSlot timeSlot, User user) {
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());
        Product product= productRepository.findProductById(timeSlot.getProductId());

        if(product==null){
            throw new ApiException("Wrong productId!");
        }
        if (product.getVendorId().equals(vendor.getId())){
            throw new ApiException("Sorry , You do not have the authority to add timeSlot to this product");
        }
        timeSlotRepository.save(timeSlot);
    }

    public void deleteTimeSlot(Integer id, User user) {
        TimeSlot timeSlot=timeSlotRepository.findTimeSlotById(id);
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());

        if(timeSlot==null){
            throw new ApiException("Wrong time slot ID!");
        }
        if(productRepository.findProductById(timeSlot.getProductId()).getVendorId().equals(vendor.getId())){
            throw new ApiException("Sorry , You do not have the authority to delete this time slot");
        }
        timeSlotRepository.delete(timeSlot);
    }

    public void updateTimeSlot(Integer id, TimeSlot timeSlot, User user) {
        TimeSlot timeSlot1=timeSlotRepository.findTimeSlotById(id);
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());

        if(timeSlot1==null){
            throw new ApiException("Wrong time slot ID!");
        }
        if(productRepository.findProductById(timeSlot.getProductId()).getVendorId().equals(vendor.getId())){
            throw new ApiException("Sorry , You do not have the authority to update this time slot");
        }

        timeSlot1.setYear(timeSlot.getYear());
        timeSlot1.setMonth(timeSlot.getMonth());
        timeSlot1.setDay(timeSlot.getDay());
        timeSlotRepository.save(timeSlot1);
    }

    public List<TimeSlot> getTimeSlotByProduct(Integer productId){
        if(productRepository.findProductById(productId)==null){
            throw new ApiException("Wrong productId!");
        }
        return timeSlotRepository.findAllByProductId(productId);
    }
}
