package vertx.session.day2;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployFifthVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] arg) {
		System.out.println("I am in DeployFifthVerticle Main method..");
		Vertx vertx = Vertx.vertx();

		// Reading Data From Config file
		System.out.println("Reading Data From Config file!!!");
		ConfigRetriever configRetriever = null;

		try {

			System.out.println("buildng File ConfigStoreOptions !!!");
			ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setOptional(true)
					.setConfig(new JsonObject().put("path",
							"C:/Users/e001150/vertxsession/Vertx.Demo.1/src/main/java/vertx/session/day2/my-config.hocon"));

			System.out.println("buildng Sys ConfigStoreOptions  !!!");
			ConfigStoreOptions sysStore = new ConfigStoreOptions().setType("sys");

			System.out.println("buildng ConfigStoreOptions  !!!");
			ConfigRetrieverOptions configRetrieverOptions = new ConfigRetrieverOptions().addStore(fileStore)
					.addStore(sysStore);

			System.out.println("buildng ConfigRetriever!!!");
			configRetriever = ConfigRetriever.create(vertx, configRetrieverOptions);

			System.out.println("Reading Config data using ConfigRetriever!!!");
			configRetriever.getConfig(json -> {
				JsonObject config = json.result();
				setInstances = config.getInteger("DefaultNumberOfInstances");
				setWorker = config.getBoolean("DefaultEnableWorker");
			});

		} catch (Exception ex) {
			System.out.println("getMessage::" + ex.getMessage());
			System.out.println("getStackTrace::" + ex.getStackTrace().toString());
		}

		// Fluent Way
		DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(setInstances).setWorker(setWorker);

		vertx.deployVerticle(new FifthVerticle(), deploymentOptions, deploymentStatus -> {
			if (!deploymentStatus.succeeded()) {
				System.out.println("FifthVerticle Deployment Failed!!!");
				deploymentStatus.cause().printStackTrace();
			} else {
				String guid = deploymentStatus.result();
				System.out.println("FifthVerticle Deployment DONE!!! ID: " + guid);
			}
		});
	}
}