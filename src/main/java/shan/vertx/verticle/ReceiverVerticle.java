package vertx.session.day2;

import io.vertx.core.AbstractVerticle;

public class ReceiverVerticle extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		System.out.println("I am in ReceiverVerticle Start method..");
		vertx.eventBus().consumer("BBC.NEWS.Sports", (message) -> {
			System.out.println(message.body().toString());
			System.out.println(message.body().toString().length());
        });
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("I am in ReceiverVerticle Stop method..");
	}
}

