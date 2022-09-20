package com.example.bridesmaids.controller;


import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.TimeSlot;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timeSlot")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;


    @GetMapping("/get")
    public ResponseEntity<List<TimeSlot>> getTimeSlot(){
        List<TimeSlot> timeSlots=timeSlotService.getTimeSlot();
        return ResponseEntity.status(200).body(timeSlots);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTimeSlot(@RequestBody @Valid TimeSlot timeSlot, @AuthenticationPrincipal User user){
        timeSlotService.addTimeSlot(timeSlot,user);
        return ResponseEntity.status(201).body(new ApiResponse("Time slot Added Successfully!", 201));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTimeSlot(@PathVariable Integer id, @AuthenticationPrincipal User user){
        timeSlotService.deleteTimeSlot(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("Time Slot Deleted Successfully!",200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateTimeSlot(@PathVariable Integer id ,@RequestBody @Valid TimeSlot timeSlot, @AuthenticationPrincipal User user){
        timeSlotService.updateTimeSlot(id,timeSlot,user);
        return ResponseEntity.status(200).body(new ApiResponse("Time Slot Updated Successfully!",200));
    }

    @GetMapping("/byProduct/{id}")
    public ResponseEntity<List<TimeSlot>> getTimeSlotByProduct(@PathVariable Integer id){
        List<TimeSlot> timeSlots=timeSlotService.getTimeSlotByProduct(id);
        return ResponseEntity.status(200).body(timeSlots);
    }

}
