import java.util.Random;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HeatSensorVerticle extends AbstractVerticle {
	private double heat = 20;
	private final Random rand = new Random();

	private double GetTempFromSensor() {
		heat = heat + rand.nextGaussian() / 2.0d;
		return heat;
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Start");

		vertx.setTimer(5000, this::DealyedTimerUpdateHeat);
		heat = GetTempFromSensor();
		vertx.setPeriodic(2000, handler -> {
			System.out.println("Temperature read from Sensor :" + heat);
		});

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Stop");
	}

	private void DealyedTimerUpdateHeat(Long id) {
		heat = GetTempFromSensor();
		System.out.println("Inside DealyedTimerUpdateHeat Temperature read from Sensor :" + heat);
	}
}
