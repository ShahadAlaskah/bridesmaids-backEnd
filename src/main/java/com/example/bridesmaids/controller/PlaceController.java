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
    // ALL
    @GetMapping("/get")
    public ResponseEntity<List<Place>> getPlaces(){
        List<Place> placeArrayList = placeService.getPlaces();
        return ResponseEntity.status(200).body(placeArrayList);
    }
    // none
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPlace(@RequestBody @Valid Place place){
        placeService.addPlace(place);
        return ResponseEntity.status(201).body(new ApiResponse("New place added !",201));
    }
    // none
    @PutMapping("/update/{placeId}")
    public ResponseEntity<ApiResponse> updatePlace(@RequestBody @Valid Place place,@PathVariable Integer placeId){
        placeService.updatePlace(place,placeId);
        return ResponseEntity.status(200).body(new ApiResponse("place updated !",201));
    }

    // none
    @DeleteMapping("/delete/{placeId}")
    public ResponseEntity<ApiResponse> deletePlace(@PathVariable Integer placeId){

        placeService.deletePlace(placeId);
        return ResponseEntity.status(200).body(new ApiResponse("place deleted",200));
    }
   // none
    @GetMapping("/getPlaceById/{placeId}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Integer placeId){
        Place place= placeService.getPlaceById(placeId);
        return ResponseEntity.status(200).body(place);
    }
    // all
    @GetMapping("/getPlaceByProductId/{productId}")
    public ResponseEntity<Place> getPlaceByProductId(@PathVariable Integer productId){
        Place place= placeService.getPlaceByProductId(productId);
        return ResponseEntity.status(200).body(place);
    }
}
