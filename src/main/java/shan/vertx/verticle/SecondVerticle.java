import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class SecondVerticle extends AbstractVerticle {
	
	public static void main(String[] args) {
		System.out.println("In Main SecondVerticle - Deploying SecondVerticle");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new SecondVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In SecondVerticle Start");
		vertx.setPeriodic(2000, handler -> {
			System.out.println("message from Sensor");
		});
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In SecondVerticle Stop");	
	}
}
