package serviceshuffle.service;

import serviceshuffle.model.ShuffleResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShuffleService {
    ResponseEntity<ShuffleResponse> logShuffleResponse(ShuffleResponse response);

    List<Integer> createShuffledArray(Integer value);
}
