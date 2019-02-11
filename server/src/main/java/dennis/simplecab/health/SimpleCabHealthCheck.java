package dennis.simplecab.health;

import com.codahale.metrics.health.HealthCheck;

public class SimpleCabHealthCheck extends HealthCheck {
  private final String check;

  public SimpleCabHealthCheck(String check) {
    this.check = check;
  }

  @Override
  protected Result check() {
    final String saying = String.format(check, "OK");
    if(!saying.contains("OK")) {
      return Result.unhealthy("check doesn't include a name");
    }
    return Result.healthy();
  }
}
