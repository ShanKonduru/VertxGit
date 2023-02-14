package vertx.session.day2;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class GenericVerticleDeployer {
	
	static String verticlesToDeploy = "";

	public static void main(String[] arg) {

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
				verticlesToDeploy = config.getString("VerticlesToDeploy");
			});

		} catch (Exception ex) {
			System.out.println("getMessage::" + ex.getMessage());
			System.out.println("getStackTrace::" + ex.getStackTrace().toString());
		}

		String[] stringarray = verticlesToDeploy.split(",");

		for (String toDeploy : stringarray) {
			vertx.deployVerticle(toDeploy.trim(), deploymentStatus -> {
				if (!deploymentStatus.succeeded()) {
					System.out.println(toDeploy + " Deployment Failed!!!");
					deploymentStatus.cause().printStackTrace();
				} else {
					String guid = deploymentStatus.result();
					System.out.println(toDeploy + " Deployment DONE!!! ID: " + guid);
				}
			});
		}

	}

}
