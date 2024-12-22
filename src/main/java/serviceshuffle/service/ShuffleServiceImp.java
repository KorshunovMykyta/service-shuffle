package serviceshuffle.service;

import serviceshuffle.client.LogClient;
import serviceshuffle.model.ShuffleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShuffleServiceImp implements ShuffleService{

    private final LogClient logClient;

    @Override
    public ResponseEntity<ShuffleResponse> logShuffleResponse(ShuffleResponse response) {
       log.info("Making request to log service for response: {}", response);
       return logClient.log(response);
    }

    @Override
    public List<Integer> createShuffledArray(Integer value) {
        List<Integer> array = new ArrayList<>();
        for (int i = 1; i <= value; i++) {
            array.add(i);
        }
        //Collections.shuffle has an O(n) time complexity
        Collections.shuffle(array);
        return array;
    }
}
