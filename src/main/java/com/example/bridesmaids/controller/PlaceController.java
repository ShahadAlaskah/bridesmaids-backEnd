package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Place;
import com.example.bridesmaids.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
//@CrossOrigin(origins = "*")
public class PlaceController {

   private final PlaceService placeService;

    @GetMapping("/get")
    public ResponseEntity<List<Place>> getPlaces(){
        List<Place> placeArrayList = placeService.getPlaces();
        return ResponseEntity.status(200).body(placeArrayList);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPlace(@RequestBody @Valid Place place){
        placeService.addPlace(place);
        return ResponseEntity.status(201).body(new ApiResponse("New place added !",201));
    }

    @PutMapping("/update/{placeId}")
    public ResponseEntity<ApiResponse> updatePlace(@RequestBody @Valid Place place,@PathVariable Integer placeId){
        placeService.updatePlace(place,placeId);
        return ResponseEntity.status(200).body(new ApiResponse("place updated !",201));
    }


    @DeleteMapping("/delete/{placeId}")
    public ResponseEntity<ApiResponse> deletePlace(@PathVariable Integer placeId){

        placeService.deletePlace(placeId);
        return ResponseEntity.status(200).body(new ApiResponse("place deleted",200));
    }
}
