package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.data.ParkRepository;
import com.launchcode.parks_for_tails.models.Park;
import com.launchcode.parks_for_tails.models.dto.AddParkFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class ParkService {

    private ParkRepository parkRepository;


    @Autowired
    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public Park AddPark(Park park) {
        // check if required fields are present)
//        String name = park.getName();
//        park.setName(name);
//        String address = park.getAddress();
//        park.setAddress(address);
//
        return parkRepository.save(park);
    }



    public Optional<Object> getParkById(int parkId) {
        return Optional.of(parkRepository.findById(parkId));
    }
}

