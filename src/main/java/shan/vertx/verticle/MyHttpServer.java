import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MyHttpServer extends AbstractVerticle {

	public static void main(String[] args) {
		System.out.println("In Main MyHttpServer - Deploying MyHttpServer");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MyHttpServer(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MyHttpServer Start");
		vertx.createHttpServer()
		.requestHandler(r -> {
			r.response().end("Welcome to Vert.x Intro");
			}
		)
		.listen(config().getInteger("http.port", 9090), 
	        result -> {
	          if (result.succeeded()) {
	        	  startPromise.complete();
	          } else {
	        	  startPromise.fail(result.cause());
	          }
	      });
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MyHttpServer Stop");	
	}
}
