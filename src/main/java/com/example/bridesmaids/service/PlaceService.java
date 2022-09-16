package com.example.bridesmaids.service;

import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Place;
import com.example.bridesmaids.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public void addPlace(Place place) {
        placeRepository.save(place);
    }

    public void updatePlace(Place place, Integer placeId) {
        Place oldPlace =placeRepository.findPlaceById(placeId);

        if (oldPlace == null) {
            throw new ApiException("placeID not found");
        }
        oldPlace.setCapacity(place.getCapacity());
        oldPlace.setLocation(place.getLocation());
        oldPlace.setProductId(place.getProductId());
        oldPlace.setCity(place.getCity());
        oldPlace.setCountry(place.getCountry());

        placeRepository.save(oldPlace);


    }

    public void deletePlace(Integer placeId) {
        Place oldPlace = placeRepository.findPlaceById(placeId);
        if (oldPlace == null) {
            throw new ApiException("placeID not found");
        }
        placeRepository.delete(oldPlace);
    }

    public Place getPlaceById(Integer placeId) {
        Place place =placeRepository.findPlaceById(placeId);

        if (place == null) {
            throw new ApiException("placeID not found");
        }
        return place;
    }

    public Place getPlaceByProductId(Integer productId) {
        Place place =placeRepository.findPlaceByProductId(productId);

        if (place == null) {
            throw new ApiException("productId not found");
        }
        return place;


    }
}
