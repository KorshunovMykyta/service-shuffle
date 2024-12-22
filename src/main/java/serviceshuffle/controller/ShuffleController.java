package serviceshuffle.controller;

import serviceshuffle.model.ShuffleRequest;
import serviceshuffle.model.ShuffleResponse;
import serviceshuffle.service.ShuffleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shuffle")
public class ShuffleController {

    private final ShuffleService shuffleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity shuffle(@RequestBody ShuffleRequest request) {
        int number = request.number();

        if (number < 1 || number > 1000) {
            return ResponseEntity.badRequest().body("Number must be between 1 and 1000.");
        }

        var shuffleArray = shuffleService.createShuffledArray(number);
        var loggedResponse =
                shuffleService.logShuffleResponse(new ShuffleResponse(shuffleArray));
        return ResponseEntity.ok(loggedResponse);
    }
}
