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
 * The class Add One Product extends abstract verticle
 */
public class AddOneProduct extends AbstractVerticle {

	// to store port number to be referenced
	private int portNumber = 8080;
	
	/**
	 *
	 * Main Convenience method so you can run it in your IDE
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {

		Runner.runExample(AddOneProduct.class);
	}
	
	public AddOneProduct(int _port) {
		portNumber = _port;
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

		// Creating a Router
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());

		// Configure Routers

		// 3. Add New Product
		router.put("/products/:productID").handler(this::handleAddProduct);

		// Create Request Handler listing to a Specific port
		vertx.createHttpServer().requestHandler(router).listen(portNumber);
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
	 * Send error
	 *
	 * @param statusCode the status code
	 * @param response   the response
	 */
	private void sendError(int statusCode, HttpServerResponse response) {

		response.setStatusCode(statusCode).end();
	}
}