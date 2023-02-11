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
 * The class Simple rest service extends abstract verticle
 */
public class SimpleRestService extends AbstractVerticle {

	/**
	 *
	 * Main Convenience method so you can run it in your IDE
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {

		Runner.runExample(SimpleRestService.class);
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

		// Configure THREE Routers

		// 1. List all Products
		router.get("/products").handler(this::handleListProducts);

		// 2. Get Individual Products
		router.get("/products/:productID").handler(this::handleGetProduct);

		// 3. Add New Product
		router.put("/products/:productID").handler(this::handleAddProduct);

		// 4. Delete A Product
		router.delete("/products/:productID").handler(this::handleDeleteProduct);

		// Create Request Handler listing to a Specific port
		vertx.createHttpServer().requestHandler(router).listen(8888);
	}

	/**
	 *
	 * Handle get product
	 * 
	 * Reads product code from request object Gets the product information from
	 * Product HashMap Returns Product Info as JSON object in the Response
	 * 
	 * @param routingContext the routing context
	 */
	private void handleGetProduct(RoutingContext routingContext) {

		String productID = routingContext.request().getParam("productID");
		HttpServerResponse response = routingContext.response();
		if (productID == null) {
			sendError(400, response);
		} else {
			JsonObject product = products.get(productID);
			if (product == null) {
				sendError(404, response);
			} else {
				response.putHeader("content-type", "application/json").end(product.encodePrettily());
			}
		}
	}

	/**
	 *
	 * Handle add product
	 * 
	 * Reads product code and Product information from request json object Adds the
	 * product information to Product HashMap
	 *
	 * @param routingContext the routing context
	 */
	private void handleAddProduct(RoutingContext routingContext) {

		String productID = routingContext.request().getParam("productID");
		HttpServerResponse response = routingContext.response();
		if (productID == null) {
			sendError(400, response);
		} else {
			JsonObject product = routingContext.getBodyAsJson();
			if (product == null) {
				sendError(400, response);
			} else {
				products.put(productID, product);
				response.end();
			}
		}
	}

	/**
	 *
	 * Handle delete product
	 * 
	 * Reads product code and Product information from request json object Delete
	 * the product information from Product HashMap
	 *
	 * @param routingContext the routing context
	 */
	private void handleDeleteProduct(RoutingContext routingContext) {

		String productID = routingContext.request().getParam("productID");
		HttpServerResponse response = routingContext.response();
		if (productID == null) {
			sendError(400, response);
		} else {
			products.remove(productID);
			response.end();
		}
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
	 * Send error
	 *
	 * @param statusCode the status code
	 * @param response   the response
	 */
	private void sendError(int statusCode, HttpServerResponse response) {

		response.setStatusCode(statusCode).end();
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