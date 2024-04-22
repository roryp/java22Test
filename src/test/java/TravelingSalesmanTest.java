import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.example.TravelingSalesman;
public class TravelingSalesmanTest {

    private TravelingSalesman travelingSalesman;
    private Map<String, Map<String, Integer>> cities;

    @BeforeEach
    public void setUp() {
        travelingSalesman = new TravelingSalesman();
        cities = Map.of(
                "San Francisco", Map.of("Los Angeles", 347, "Denver", 950, "New York", 2572, "Johannesburg", 16983, "Paris", 5583),
                "Los Angeles", Map.of("San Francisco", 347, "Denver", 830, "New York", 2445, "Johannesburg", 16713, "Paris", 5669),
                "Denver", Map.of("San Francisco", 950, "Los Angeles", 830, "New York", 1631, "Johannesburg", 15388, "Paris", 4880),
                "New York", Map.of("San Francisco", 2572, "Los Angeles", 2445, "Denver", 1631, "Johannesburg", 7969, "Paris", 3635),
                "Johannesburg", Map.of("San Francisco", 16983, "Los Angeles", 16713, "Denver", 15388, "New York", 7969, "Paris", 5410),
                "Paris", Map.of("San Francisco", 5583, "Los Angeles", 5669, "Denver", 4880, "New York", 3635, "Johannesburg", 5410)
        );
    }

    @Test
    public void calculateRouteReturnsCorrectRoute() {
        List<String> otherCities = new ArrayList<>(cities.keySet());
        otherCities.remove("San Francisco");
        List<String> route = travelingSalesman.calculateRoute("San Francisco", otherCities, cities, new Random());

        assertEquals("San Francisco", route.get(0));
        assertEquals("San Francisco", route.get(route.size() - 1));
        assertTrue(route.containsAll(cities.keySet()));
    }

    @Test
    public void calculateTotalDistanceReturnsCorrectDistance() {
        List<String> route = Arrays.asList("San Francisco", "Los Angeles", "Denver", "New York", "Johannesburg", "Paris", "San Francisco");
        int expectedDistance = 347 + 830 + 1631 + 7969 + 5410 + 5583;

        int actualDistance = travelingSalesman.calculateTotalDistance(route, cities);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void findRandomCityReturnsCityFromList() {
        List<String> citiesList = new ArrayList<>(cities.keySet());

        String city = travelingSalesman.findRandomCity(citiesList, new Random());

        assertTrue(citiesList.contains(city));
    }
}