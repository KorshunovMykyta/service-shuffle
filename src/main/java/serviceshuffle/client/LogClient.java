package serviceshuffle.client;

import serviceshuffle.model.ShuffleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "logServiceClient", url = "${log.service.url}")
public interface LogClient {

    @RequestMapping(method = RequestMethod.POST, value = "/log")
    ResponseEntity<ShuffleResponse> log(ShuffleResponse response);
}
