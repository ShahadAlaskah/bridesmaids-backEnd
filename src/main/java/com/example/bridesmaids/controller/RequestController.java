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

    @PutMapping("update/{requestId}")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody @Valid Request request,@PathVariable Integer requestId){
        requestService.updateRequest(request,requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request updated !",201));
    }


    @DeleteMapping("delete/{requestId}")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable Integer requestId){

        requestService.deleteRequest(requestId);
        return ResponseEntity.status(200).body(new ApiResponse("request deleted",200));
    }
}
