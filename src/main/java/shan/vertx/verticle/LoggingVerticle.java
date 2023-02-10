import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class LoggingVerticle extends AbstractVerticle {

	final Logger logger = LoggerFactory.getLogger(LoggingVerticle.class);

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In LoggingVerticle Start");
		logger.debug("debug - In LoggingVerticle Start");
		logger.trace("trace - In LoggingVerticle Start");
		logger.debug("debug - In LoggingVerticle Start");
		logger.info("info - In LoggingVerticle Start");
		logger.warn("warning - In LoggingVerticle Start");
		logger.error("error - In LoggingVerticle Start");
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In LoggingVerticle Stop");
		logger.debug("Debug - In LoggingVerticle Stop");
		logger.trace("trace - In LoggingVerticle Stop");
		logger.debug("debug - In LoggingVerticle Stop");
		logger.info("info - In LoggingVerticle Stop");
		logger.warn("warning - In LoggingVerticle Stop");
		logger.error("error - In LoggingVerticle Stop");
	}
}
