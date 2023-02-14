package vertx.session.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class FourthVerticle extends AbstractVerticle {
	
	final static Logger logger = LoggerFactory.getLogger(FourthVerticle.class);
	
	public static void main(String[] args)	{
		logger.info("info - In FourthVerticle Main method");
		Vertx vertx = Vertx.vertx();
		logger.info("About to Deploy FourthVerticle from Main method");
		vertx.deployVerticle(new FourthVerticle());
	}
	
	@Override
	public void start() throws Exception {
		System.out.println("I am in FourthVerticle Start method..");
		logger.debug("debug - In FourthVerticle Start");
		logger.trace("trace - In FourthVerticle Start");
		logger.debug("debug - In FourthVerticle Start");
		logger.info("info - In FourthVerticle Start");
		logger.warn("warning - In FourthVerticle Start");
		logger.error("error - In FourthVerticle Start");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("I am in FourthVerticle Stop method..");
		logger.debug("debug - In FourthVerticle Stop");
		logger.trace("trace - In FourthVerticle Stop");
		logger.debug("debug - In FourthVerticle Stop");
		logger.info("info - In FourthVerticle Stop");
		logger.warn("warning - In FourthVerticle Stop");
		logger.error("error - In FourthVerticle Stop");
	}
}
