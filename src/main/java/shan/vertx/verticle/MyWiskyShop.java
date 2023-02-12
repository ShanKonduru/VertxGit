import java.util.LinkedHashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MyWiskyShop extends AbstractVerticle {

	// Store our product
	private Map<Integer, MyWhisky> products = new LinkedHashMap<>();

	public static void main(String[] args) {
		Runner.runExample(MyWiskyShop.class);
	}

	// Create some product
	private void createSomeData() {
		MyWhisky bowmore = new MyWhisky("Bowmore 15 Years Laimrig", "Scotland, Islay", 150);
		products.put(bowmore.getId(), bowmore);
		MyWhisky talisker = new MyWhisky("Talisker 57° North", "Scotland, Island", 220);
		products.put(talisker.getId(), talisker);
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		createSomeData();

		// Create a router object.
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		
		router.get("/api/whiskies").handler(this::getAll);
		router.delete("/api/whiskies/:id").handler(this::deleteOne);

		// Create Request Handler listing to a Specific port
		vertx.createHttpServer().requestHandler(router).listen(8888);
	}

	private void deleteOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id);
			products.remove(idAsInteger);
		}
		routingContext.response().setStatusCode(204).end();
	}

	private void getAll(RoutingContext routingContext) {
		routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(products.values()));
	}
}
