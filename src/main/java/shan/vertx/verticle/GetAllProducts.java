import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The class Get All Products extends abstract verticle
 */
public class GetAllProducts extends AbstractVerticle {

	// to store port number to be referenced
	private int portNumber = 8080;

	public GetAllProducts(int _port) {
		portNumber = _port;
	}

	/**
	 *
	 * Main Convenience method so you can run it in your IDE
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {

		Runner.runExample(GetAllProducts.class);
	}

	// Hash map to store all products
	// This Hash Map stores in Key Value Pairs
	// Key is Product ID, Value is Product Information
	private Map<String, JsonObject> products = new HashMap<>();

	@Override
	/**
	 *
	 * Start
	 *
	 */
	public void start() {

		// set up initial data items, this method adds all items to products Hash Map.
		setUpInitialData();

		// Creating a Router
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());

		// 1. List all Products
		router.get("/products").handler(this::handleListProducts);

		// Create Request Handler listing to a Specific port
		vertx.createHttpServer().requestHandler(router).listen(portNumber);
	}

	/**
	 *
	 * Handle list products
	 *
	 * Gets list of all Product in JSON format as a response
	 * 
	 * @param routingContext the routing context
	 */
	private void handleListProducts(RoutingContext routingContext) {

		JsonArray arr = new JsonArray();
		products.forEach((k, v) -> arr.add(v));
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	/**
	 *
	 * Sets the up initial data
	 *
	 */
	private void setUpInitialData() {
		addProduct(
				new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
		addProduct(
				new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
		addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
	}

	/**
	 *
	 * Add product
	 *
	 * @param product the product
	 */
	private void addProduct(JsonObject product) {
		products.put(product.getString("id"), product);
	}
}
