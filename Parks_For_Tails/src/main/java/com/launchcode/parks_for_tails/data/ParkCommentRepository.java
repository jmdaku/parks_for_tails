package com.launchcode.parks_for_tails.data;

import com.launchcode.parks_for_tails.models.ParkComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkCommentRepository extends JpaRepository<ParkComment, Integer> {
    List<ParkComment> findByParkId(Integer parkId);
}
