package dennis.simplecab;

import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(JUnitPlatform.class)
class SimpleCabClientTest {

  final OkHttpClient client = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();

  SimpleCabService service = new SimpleCabService(client, "localhost", 8080);

  @DisplayName("client test sending a medallion cab trip request")
  @Test
  @Disabled // not mocking - time constraint
  void testSendMedallioinsSummary() throws Exception {

    List<String> medallions = Lists.newArrayList("D7D598CD99978BD012A87A76A7C891B7");
    DateTime aDate = new DateTime("2013-12-01");

    String response = service.getMedallionsSummary(medallions, aDate, false);

    System.out.println("response: " + response);
  }

  @DisplayName("client test asking server to delete its cache")
  @Test
  @Disabled
  void testSendDeleteCache() throws Exception {
    service.deleteCache();
  }

}