package shan.vertx.main;

import io.vertx.core.Vertx;
import shan.vertx.verticle.FirstVerticle;

public class MainVerticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("i am in verticle main");
		System.out.println("About to Deploy first verticle ");

		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new FirstVerticle());
	}
}