package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Request;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
//@CrossOrigin(origins = "*")
public class RequestController {
    private final RequestService requestService;

    // ADMIN
    @GetMapping("/get")
    public ResponseEntity<List<Request>> getRequests(){
        List<Request> requestArrayList = requestService.getRequests();
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // CUSTOMER
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRequest(@AuthenticationPrincipal User user ,@RequestBody @Valid Request request){
        requestService.addRequest(user.getId(),request);
        return ResponseEntity.status(201).body(new ApiResponse("New request added !",201));
    }
    // none
    @PutMapping("/update/{requestId}")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody @Valid Request request,@PathVariable Integer requestId){
        requestService.updateRequest(request,requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request updated !",201));
    }

    // none
    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable Integer requestId){

        requestService.deleteRequest(requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request deleted",200));
    }
    // معرف
    @GetMapping("/getRequestById/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer requestId){
        Request request = requestService.getRequestById(requestId);
        return ResponseEntity.status(200).body(request);
    }
    // ADMIN (new-underNegotiation-confirmedByVendor-confirmedByCustomer-rejected)
    @GetMapping("/getAllByStatus/{status}")
    public ResponseEntity<List<Request>> getAllByStatus(@PathVariable String status){
        List<Request> requestArrayList = requestService.getAllByStatus(status);
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // VENDOR
    @GetMapping("/getAllByVendorId")
    public ResponseEntity<List<Request>> getAllByVendorId(@AuthenticationPrincipal User user){
        List<Request> requestArrayList = requestService.getAllByVendorId(user.getId());
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // CUSTOMER
    @GetMapping("/getAllByUserId")
    public ResponseEntity<List<Request>> getAllByUserId(@AuthenticationPrincipal User user){
        List<Request> requestArrayList = requestService.getAllByUserId(user.getId());
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // VENDOR (new-underNegotiation-confirmedByVendor-confirmedByCustomer-rejected)
    @GetMapping("/getAllByVendorIdAndAndStatus/{status}")
    public ResponseEntity<List<Request>> getAllByVendorIdAndAndStatus(@AuthenticationPrincipal User user,@PathVariable String status){
        List<Request> requestArrayList = requestService.getAllByVendorIdAndAndStatus(user.getId(), status);
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // CUSTOMER (new-underNegotiation-confirmedByVendor-confirmedByCustomer-rejected)
    @GetMapping("/getAllByUserIdAndStatus/{status}")
    public ResponseEntity<List<Request>> getAllByUserIdAndStatus(@AuthenticationPrincipal User user,@PathVariable String status){
        List<Request> requestArrayList = requestService.getAllByUserIdAndStatus(user.getId(), status);
        return ResponseEntity.status(200).body(requestArrayList);
    }
    // VENDOR CUSTOMER
    @PutMapping("/changeRequestStatus/{requestId}/{status}")
    public ResponseEntity<ApiResponse> changeRequestStatus(@PathVariable Integer requestId,@PathVariable String status){
        requestService.changeRequestStatus(requestId,status);
        return ResponseEntity.status(200).body(new ApiResponse("request status updated !",201));
    }
}
