package vertx.session.day2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


@SuppressWarnings("deprecation")
public class ThirdVerticle extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(ThirdVerticle.class);
	
	public static void main(String[] args)	{
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new ThirdVerticle());
	}
	
	@Override
	public void start() throws Exception {
		System.out.println("I am in ThirdVerticle Start method..");
		logger.debug("debug - In ThirdVerticle Start");
		logger.trace("trace - In ThirdVerticle Start");
		logger.debug("debug - In ThirdVerticle Start");
		logger.info("info - In ThirdVerticle Start");
		logger.warn("warning - In ThirdVerticle Start");
		logger.error("error - In ThirdVerticle Start");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("I am in ThirdVerticle Stop method..");
		logger.debug("debug - In ThirdVerticle Stop");
		logger.trace("trace - In ThirdVerticle Stop");
		logger.debug("debug - In ThirdVerticle Stop");
		logger.info("info - In ThirdVerticle Stop");
		logger.warn("warning - In ThirdVerticle Stop");
		logger.error("error - In ThirdVerticle Stop");
	}
}
