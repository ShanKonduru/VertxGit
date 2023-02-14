package vertx.session.day2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class SenderVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		System.out.println("I am in SenderVerticle Start method..");

		JsonObject message = new JsonObject();
		message.put("IPL - IND vs SW", "India Won by 5 wickets");
		vertx.eventBus().send("BBC.NEWS.Sports", message);
	}

	@Override
	public void stop() throws Exception {
		System.out.println("I am in SenderVerticle Stop method..");
	}
}
