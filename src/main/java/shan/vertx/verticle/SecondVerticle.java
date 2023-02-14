package vertx.session.day2;

import io.vertx.core.AbstractVerticle;

public class SecondVerticle extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		System.out.println("I am in SecondVerticle Start method..");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("I am in SecondVerticle Stop method..");
	}
}
