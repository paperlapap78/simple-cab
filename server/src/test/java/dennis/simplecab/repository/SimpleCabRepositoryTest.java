package dennis.simplecab.repository;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jodatime2.JodaTimePlugin;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitPlatform.class)
class SimpleCabRepositoryTest {

  private static Jdbi jdbi;
  private final static String medallionId = "D7D598CD99978BD012A87A76A7C891B7";
  private SimpleCabRepository store;

  @BeforeAll
  static void setupDB() {
    jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    jdbi.installPlugin(new JodaTimePlugin());
    jdbi.useHandle(handle -> {
      handle.execute("CREATE TABLE cab_trip_data (" +
          "medallion TEXT," +
          "hack_license TEXT," +
          "vendor_id TEXT," +
          "rate_code int(11) DEFAULT NULL," +
          "store_and_fwd_flag TEXT," +
          "pickup_datetime datetime DEFAULT NULL," +
          "dropoff_datetime datetime DEFAULT NULL," +
          "passenger_count int(11) DEFAULT NULL," +
          "trip_time_in_secs int(11) DEFAULT NULL," +
          "trip_distance double DEFAULT NULL," +
          "pickup_longitude double DEFAULT NULL," +
          "pickup_latitude double DEFAULT NULL," +
          "dropoff_longitude double DEFAULT NULL," +
          "dropoff_latitude double DEFAULT NULL)");


      handle.execute("INSERT INTO cab_trip_data(medallion, pickup_datetime) VALUES (?, ?)", medallionId, "2013-12-01 00:13:00");
    });
  }

  @BeforeEach
  void setup() {
    store = new SimpleCabRepository(jdbi);
  }

  @DisplayName("Should get a result from DB")
  @Test
  void shouldReturnTripCount() {
    Long trips = store.getCountByMedallionAndPickupDatetime(medallionId, new DateTime("2013-12-01"));
    assertThat(trips, is(equalTo(1L)));
  }

  @DisplayName("Should return 0 trips when no entries found")
  @Test
  void shouldReturnZeroNoEntries() {
    Long trips = store.getCountByMedallionAndPickupDatetime(medallionId, new DateTime("2018-04-04"));
    assertThat(trips, is(equalTo(0L)));
  }

}