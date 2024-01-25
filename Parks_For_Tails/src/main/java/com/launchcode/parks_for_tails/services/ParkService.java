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

    public Park addPark(AddParkFormDTO parkForm) {
        // Convert AddParkFormDTO to Park entity and save to the database
        Park park = new Park();
        park.setName(parkForm.getName());
        park.setAddress(parkForm.getAddress());
        park.setLatitude(parkForm.getLatitude());
        park.setLongitude(parkForm.getLongitude());

        return parkRepository.save(park);
    }

}


