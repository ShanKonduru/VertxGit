package vertx.session.day2;

import io.vertx.core.Vertx;

public class DeploySenderAndReceiver {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();

		vertx.deployVerticle(new ReceiverVerticle());
		vertx.deployVerticle(new SenderVerticle());
	}
}