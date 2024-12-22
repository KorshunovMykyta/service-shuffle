package serviceshuffle;


import serviceshuffle.client.LogClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import serviceshuffle.model.ShuffleResponse;
import serviceshuffle.service.ShuffleServiceImp;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShuffleServiceImplTest {

    @Mock
    private LogClient logClient;

    @InjectMocks
    private ShuffleServiceImp shuffleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLogRequestToLogService() {
        ShuffleResponse mockResponse = new ShuffleResponse(asList(1, 2, 3));
        ResponseEntity<ShuffleResponse> mockEntity = ResponseEntity.ok(mockResponse);

        when(logClient.log(mockResponse)).thenReturn(mockEntity);

        ResponseEntity<ShuffleResponse> result = shuffleService.logShuffleResponse(mockResponse);
        assertNotNull(result);
        assertEquals(mockEntity, result);
        verify(logClient, times(1)).log(mockResponse);
    }

    @Test
    void shouldCreateShuffledArray() {
        int value = 5;
        List<Integer> result = shuffleService.createShuffledArray(value);

        assertNotNull(result);
        assertEquals(value, result.size());
        for (int i = 1; i <= value; i++) {
            assertTrue(result.contains(i));
        }
        assertNotEquals(List.of(1, 2, 3, 4, 5), result); // Ensure the list is shuffled
    }
}
