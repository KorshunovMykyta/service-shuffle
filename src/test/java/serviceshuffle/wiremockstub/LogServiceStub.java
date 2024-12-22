package serviceshuffle.wiremockstub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class LogServiceStub {
    public static void stubLogResponseSuccess() {
        stubFor(post(urlEqualTo("/api/log"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "shuffledArray": [1, 2, 3]
                                }
                        """))
        );
    }
}
