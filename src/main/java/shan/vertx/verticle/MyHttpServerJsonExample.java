import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MyHttpServerJsonExample extends AbstractVerticle {

	public static void main(String[] args) {
		System.out.println("In Main MyHttpServerJsonExample - Deploying MyHttpServerJsonExample");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MyHttpServerJsonExample(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MyHttpServerJsonExample Start");
		vertx.createHttpServer()
        .requestHandler(req -> {
            JsonObject json = new JsonObject()
                .put("message", "hello there!!!");
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        })
        .listen(8899);
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MyHttpServerJsonExample Stop");	
	}
}
