import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class PublishVerticle extends AbstractVerticle {

	final Logger logger = LoggerFactory.getLogger(PublishVerticle.class);

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In PublishVerticle Start");
		logger.info("info - In PublishVerticle Start");
		
		// Construct Event Bug Object
		EventBus eb = vertx.eventBus();
		
		// Send a message every second
		vertx.setPeriodic(1000, handler -> {
			logger.info("info - In PublishEverySecond");
			eb.publish("news-feed", "Some news!");			
		});
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In PublishVerticle Stop");
		logger.info("info - In PublishVerticle Stop");	
	}
}