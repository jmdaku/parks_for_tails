package com.launchcode.parks_for_tails.data;

import com.launchcode.parks_for_tails.models.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Integer> {

    Park findByName(String name);
}
