package shan.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class FirstVerticle extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("i am in first verticle start");
	}
}
