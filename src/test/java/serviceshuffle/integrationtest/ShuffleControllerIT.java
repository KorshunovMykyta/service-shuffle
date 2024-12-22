package serviceshuffle.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import serviceshuffle.BaseTest;
import serviceshuffle.model.ShuffleRequest;
import serviceshuffle.model.ShuffleResponse;
import serviceshuffle.wiremockstub.LogServiceStub;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {"log.service.url=http://localhost:${wiremock.server.port}/api"})
public class ShuffleControllerIT extends BaseTest {

    @LocalServerPort
    private int appPort;

    @Test
    public void shouldLogRequestToLogService() {
        LogServiceStub.stubLogResponseSuccess();
        var expectedResponse = new ShuffleResponse(Arrays.asList(1, 2, 3));
        var shuffleRequest = new ShuffleRequest(2);

        var response = setup()
                .body(shuffleRequest)
                .post("http://localhost:" + appPort + SHUFFLE_URL)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("body", ShuffleResponse.class);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @ParameterizedTest
    @MethodSource("generateInvalidValuesForRequest")
    public void shouldAcceptArgumentsInRangeFromOneToOneThousand(Integer requestValue) {
        LogServiceStub.stubLogResponseSuccess();

        var shuffleRequest = new ShuffleRequest(requestValue);

         setup()
                .body(shuffleRequest)
                .post("http://localhost:" + appPort + SHUFFLE_URL)
                .then()
                .assertThat()
                .statusCode(400);
    }


    private static Stream<Integer> generateInvalidValuesForRequest() {
        return Stream.of(0, 1001);
    }
}
