package vertx.session.day2;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class DeploySecondVerticle {
	public static void main(String[] arg) {
		System.out.println("I am in DeploySecondVerticle Main method..");
		Vertx vertx = Vertx.vertx();

		// Traditional Way
		DeploymentOptions deploymentOptions = new DeploymentOptions();
		deploymentOptions.setInstances(3);
		deploymentOptions.setWorker(true);

		// Fluent Way
		deploymentOptions = new DeploymentOptions().setInstances(3).setWorker(true);

		vertx.deployVerticle("vertx.session.day2.SecondVerticle", deploymentOptions, deploymentStatus -> {
			if (!deploymentStatus.succeeded()) {
				System.out.println("SecondVerticle Deployment Failed!!!");
				deploymentStatus.cause().printStackTrace();
			} else {
				String guid = deploymentStatus.result();
				System.out.println("SecondVerticle Deployment DONE!!! ID: " + guid);
				vertx.undeploy(guid);
			}
		});
	}
}