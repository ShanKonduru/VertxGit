import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class DeployVertcle {

	public static void main(String[] args) {
		System.out.println("In Main Program");
		
		// Deploying First Verticle
		// System.out.println("In Main - Deploying First Verticle");
		// Vertx vertx = Vertx.vertx();
	    // vertx.deployVerticle(new FirstVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));		
	    
		// Deploying Heat Sensor Verticle
		// System.out.println("In Main - Deploying Heat Sensor Verticle");
		// Vertx vertx = Vertx.vertx();
		// vertx.deployVerticle(new HeatSensorVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));

		// Deploying Logging Verticle 
		// System.out.println("In Main - Deploying Logging Verticle");
		// Vertx vertx = Vertx.vertx();
		// vertx.deployVerticle(new LoggingVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
		
		// Deploying Publish Verticle
		// System.out.println("In Main - Deploying Publish Verticle");
		// Vertx vertx = Vertx.vertx();
		// vertx.deployVerticle(new PublishVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
		
		// Deploying REST Services
		System.out.println("In Main - Deploying Rest Service GetAllProducts Verticle and AddOneProduct Verticle");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new GetAllProducts(8888), new DeploymentOptions().setInstances(1).setWorker(false));
		vertx.deployVerticle(new AddOneProduct(9999), new DeploymentOptions().setInstances(1).setWorker(false));
	}
}
