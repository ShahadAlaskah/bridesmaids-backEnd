package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Request;
import com.example.bridesmaids.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
//@CrossOrigin(origins = "*")
public class RequestController {
    private final RequestService requestService;


    @GetMapping("/get")
    public ResponseEntity<List<Request>> getRequests(){
        List<Request> requestArrayList = requestService.getRequests();
        return ResponseEntity.status(200).body(requestArrayList);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRequest(@RequestBody @Valid Request request){
        requestService.addRequest(request);
        return ResponseEntity.status(201).body(new ApiResponse("New request added !",201));
    }

    @PutMapping("/update/{requestId}")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody @Valid Request request,@PathVariable Integer requestId){
        requestService.updateRequest(request,requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request updated !",201));
    }


    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable Integer requestId){

        requestService.deleteRequest(requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request deleted",200));
    }

    @GetMapping("/getRequestById/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer requestId){
        Request request = requestService.getRequestById(requestId);
        return ResponseEntity.status(200).body(request);
    }

    @GetMapping("/getAllByVendorId/{vendorId}")
    public ResponseEntity<List<Request>> getAllByVendorId(@PathVariable Integer vendorId){
        List<Request> requestArrayList = requestService.getAllByVendorId(vendorId);
        return ResponseEntity.status(200).body(requestArrayList);
    }

    @GetMapping("/getAllByVendorIdAndAndStatus/{vendorId}/{status}")
    public ResponseEntity<List<Request>> getAllByVendorIdAndAndStatus(@PathVariable Integer vendorId,@PathVariable String status){
        List<Request> requestArrayList = requestService.getAllByVendorIdAndAndStatus(vendorId,status);
        return ResponseEntity.status(200).body(requestArrayList);
    }

    @PutMapping("/changeRequestStatus/{requestId}/{status}")
    public ResponseEntity<ApiResponse> changeRequestStatus(@PathVariable Integer requestId,@PathVariable String status){
        requestService.changeRequestStatus(requestId,status);
        return ResponseEntity.status(200).body(new ApiResponse("request status updated !",201));
    }
}
