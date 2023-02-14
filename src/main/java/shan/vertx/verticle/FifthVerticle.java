package vertx.session.day2;

import io.vertx.core.AbstractVerticle;

public class FifthVerticle extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		System.out.println("I am in FifthVerticle Start method..");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("I am in FifthVerticle Stop method..");
	}
}

