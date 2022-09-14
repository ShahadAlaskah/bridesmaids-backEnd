package com.example.bridesmaids.service;


import com.example.bridesmaids.model.TimeSlot;
import com.example.bridesmaids.repository.ProductRepository;
import com.example.bridesmaids.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final ProductRepository productRepository;


    public List<TimeSlot> getTimeSlot() {
        return timeSlotRepository.findAll();
    }


    public void addTimeSlot(TimeSlot timeSlot/*, User user*/) {
//        if(productRepository.findProductById(timeSlot.getProductId())==null){
//            throw new ApiException("Wrong serviceId!");
//        }
        timeSlotRepository.save(timeSlot);
    }

    public void deleteTimeSlot(Integer id/*, User user*/) {
        TimeSlot timeSlot=timeSlotRepository.findTimeSlotById(id);
//        if(timeSlot==null){
//            throw new ApiException("Wrong time slot ID!");
//        }
//        if(productRepository.findProductById(timeSlot.getProductId()).getVenderId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to delete this time slot");
//        }
        timeSlotRepository.delete(timeSlot);
    }

    public void updateTimeSlot(Integer id, TimeSlot timeSlot/*, User user*/) {
        TimeSlot timeSlot1=timeSlotRepository.findTimeSlotById(id);
//        if(timeSlot1==null){
//            throw new ApiException("Wrong time slot ID!");
//        }
//        if(productRepository.findProductById(timeSlot.getProductId()).getVenderId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to update this time slot");
//        }
        timeSlot1.setTime(timeSlot.getTime());
        timeSlot1.setIsAvailable(timeSlot.getIsAvailable());
        timeSlotRepository.save(timeSlot1);
    }

    public List<TimeSlot> getTimeSlotByProduct(Integer productId){
//        if(productRepository.findProductById(productId)==null){
//            throw new ApiException("Wrong productId!");
//        }
        return timeSlotRepository.findAllByProductId(productId);
    }
}
