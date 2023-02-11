import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MyHttpServerQueryExample extends AbstractVerticle {

	public static void main(String[] args) {
		System.out.println("In Main MyHttpServerQueryExample - Deploying MyHttpServerQueryExample");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MyHttpServerQueryExample(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MyHttpServerQueryExample Start");
		vertx.createHttpServer().requestHandler(req -> {
			String name = req.getParam("name");
			String message = "hello " + (name != null && !name.trim().isEmpty() ? name : "world") + "!";
			JsonObject json = new JsonObject().put("message", message);
			req.response().putHeader("Content-Type", "application/json; charset=UTF8").end(json.encodePrettily());
		}).listen(8899);
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MyHttpServerQueryExample Stop");
	}
}