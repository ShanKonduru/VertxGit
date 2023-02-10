import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class DeployVertcle {

	public static void main(String[] args) {
		System.out.println("In Main Program");
		Vertx vertx = Vertx.vertx();
		
		// Deploying First Verticle
		// System.out.println("In Main - Deploying First Verticle");
	    // vertx.deployVerticle(new FirstVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));		
	    
		// Deploying Heat Sensor Verticle
		// System.out.println("In Main - Deploying Heat Sensor Verticle");
		// vertx.deployVerticle(new HeatSensorVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));

		// Deploying Logging Verticle 
		System.out.println("In Main - Deploying Logging Verticle");
		vertx.deployVerticle(new LoggingVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
	}
}
