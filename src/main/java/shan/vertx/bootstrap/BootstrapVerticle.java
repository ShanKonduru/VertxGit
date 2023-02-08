package shan.vertx.bootstrap;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import shan.vertx.utilities.ConfigProperties;
import shan.vertx.verticle.FirstVerticle;

public class BootstrapVerticle extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("i am in bootstrap start");
		
		// Way #1 – by giving complete qualified class name
		// vertx.deployVerticle("shan.vertx.verticle.FirstVerticle");
		
		// Way #2 – by qualified class name from class object
		// vertx.deployVerticle(FirstVerticle.class.getName());

		// Way #3 – by Class object instantiation
		// vertx.deployVerticle(new FirstVerticle());
		
		// Way #4 - Deployment via Deployment Options 
		ConfigProperties configProperties = new ConfigProperties(vertx);
		
		DeploymentOptions options = new DeploymentOptions();
		options.setInstances(configProperties.getInstances());
		options.setWorker(configProperties.getWorker());

		vertx.deployVerticle(FirstVerticle.class.getName(), options);
	}
}
