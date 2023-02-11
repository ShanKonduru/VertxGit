import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class FirstVerticle extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In FirstVerticle Start");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In FirstVerticle Stop");	
	}
}