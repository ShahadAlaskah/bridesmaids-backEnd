package com.example.bridesmaids.controller;


import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Picture;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/picture")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @GetMapping("/get")
    public ResponseEntity<List<Picture>> getPictures(){
        List<Picture> pictures=pictureService.getPicture();
        return ResponseEntity.status(200).body(pictures);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPicture(@RequestBody @Valid Picture picture, @AuthenticationPrincipal User user){
        pictureService.addPicture(picture,user);
        return ResponseEntity.status(201).body(new ApiResponse("Picture Added Successfully!",201));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePicture(@PathVariable Integer id, @AuthenticationPrincipal User user){
        pictureService.deletePicture(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("Picture Deleted Successfully!",200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updatePicture(@PathVariable Integer id ,@RequestBody @Valid Picture picture, @AuthenticationPrincipal User user){
        pictureService.updatePicture(id,picture,user);
        return ResponseEntity.status(200).body(new ApiResponse("Picture Updated Successfully!" , 200));
    }

    @GetMapping("/byProduct/{id}")
    public ResponseEntity<List<Picture>> getPicturesByProduct(@PathVariable Integer id){
       List<Picture> pictures=pictureService.getPicturesByProduct(id);
        return ResponseEntity.status(200).body(pictures);
    }
}
