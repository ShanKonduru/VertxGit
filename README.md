# Vert.x
## Introduction to Vert.x

## Overview

In this article, we'll discuss Vert.x, cover its core concepts and create a simple RESTfull web service with it. We'll start by covering the foundation concepts about the toolkit, slowly move forward to an HTTP server and then build the RESTfull service.

## About Vert.x

Vert.x is an open source, reactive and polyglot software development toolkit from the developers of Eclipse.

Reactive programming is a programming paradigm, associated with asynchronous streams, which respond to any changes or events.

Similarly, Vert.x uses an event bus, to communicate with different parts of the application and passes events, asynchronously to handlers when they available.

We call it polyglot due to its support for multiple JVM and non-JVM languages like Java, Groovy, Ruby, Python, and JavaScript.

## Setup
To use Vert.x we need to add the following Maven dependency in pom.xml:
```xml
<dependencies>
    <dependency>
        <groupId>io.vertx</groupId>
        <artifactId>vertx-core</artifactId>
        <version>4.3.8</version>
    </dependency>
</dependencies>
```
The latest version of the dependency can be found here.

## Verticles
Verticles are pieces of code that Vert.x engine executes. The toolkit provides us many abstract verticle class, which can be extended, and implemented as we want to.

Being polyglot, verticles can be written in any of the supported languages. An application would typically be composed of multiple verticles running in the same Vert.x instance and communicate with each other using events via the event bus.

To create a verticle in JAVA, the class must implement io.vertx.core.Verticle interface, or any one of its subclasses.

## Simple Vert.x Application
Let's create a simple application with a verticle and deploy it using a vertx instance. To create our verticle, we'll extend the

To create our verticle, we'll extend the io.vertx.core.AbstractVerticle class and override the start() method:

```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class MyVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MyVerticle - START!!!");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MyVerticle - STOP!!!");	
	}

}
```
The start() method will be invoked by the vertx instance when the verticle is deployed. The method takes io.vertx.core.Future as a parameter, which can be used to discover the status of an asynchronous deployment of the verticle.

### Deployment of Vert.x Applications
Now let's deploy the verticle: there are multiple ways to deploy a Verticle.

#### 1. Deployment using Main method inside Verticle class
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class SecondVerticle extends AbstractVerticle {
	
	public static void main(String[] args) {
		System.out.println("In Main SecondVerticle - Deploying SecondVerticle");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new SecondVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In SecondVerticle Start");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In SecondVerticle Stop");	
	}
}
```

#### 2. Deployment using Verticle Object
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployMyVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] args) {
		System.out.println("In Deploy MyVerticle main!!!");
		Vertx vertx = Vertx.vertx();

		// Way #2
		System.out.println("Deploying MyVerticle - new Verticle Instance !!!");
		vertx.deployVerticle(new MyVerticle());
	}
}
```
#### 3. Deployment using fully qualified class path
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployMyVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] args) {
		System.out.println("In Deploy MyVerticle main!!!");
		Vertx vertx = Vertx.vertx();

		// Way #3
		System.out.println("Deploying MyVerticle - new Verticle Instance !!!");
		vertx.deployVerticle("MyVerticle");
	}
}
```

#### 4. Deployment using class name
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployMyVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] args) {
		System.out.println("In Deploy MyVerticle main!!!");
		Vertx vertx = Vertx.vertx();

		// Way #4
		System.out.println("Deploying MyVerticle - Verticle Class Name!!!");
		vertx.deployVerticle(MyVerticle.class.getName());
	}
}
```

#### 5. Deployment using Deployment Options
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployMyVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] args) {
		System.out.println("In Deploy MyVerticle main!!!");
		Vertx vertx = Vertx.vertx();

		// Way #5
		System.out.println("Deploying 10 instanced of MyVerticle - via
		DiploymentOptions!!!");
		DeploymentOptions diploymentOptions = new DeploymentOptions();
		diploymentOptions.setInstances(10);
		diploymentOptions.setWorker(false);
		vertx.deployVerticle("MyVerticle", diploymentOptions);
	}
}
```

#### 6. Deployment using Bootstrap Verticle

##### Steps to follow
* Create a Verticle to be Deployed 
* Create another Verticle called BootstrapVerticle 
* Implement Necessary Deployment Code
* Add Bootstrap in run configuration 

<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>

**Create a Verticle to be Deployed**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MySecondVerticle extends AbstractVerticle {
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MySecondVerticle - START!!!");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MySecondVerticle - STOP!!!");
	}
}
```
**Create another Verticle called BootstrapVerticle**
 ```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class BootstrapVerticle extends AbstractVerticle {
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In BootstrapVerticle - START!!!");
		System.out.println("Deploying instance of MySecondVerticle.");
		vertx.deployVerticle(new MySecondVerticle());
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In BootstrapVerticle - STOP!!!");	
	}
}
 ```

**Add Bootstrap in run configuration**

* provide  io.vertx.core.Launcher under main class
* under arguments add run BootstrapVerticle


## Read Data from Configuration files

**Add necessary dependencies to pom.xml**
```xml
    <dependency>
        <groupId>io.vertx</groupId>
        <artifactId>vertx-config</artifactId>
        <version>4.3.8</version>
    </dependency>
```

**Add data file to your project**

let the name of the file is *my-config.hocon*
```json
{
	"DefaultNumberOfInstances" : 3,
	"DefaultEnableWorker" : false
}
```

**Add the following code to read these config entries**
```java
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DeployMyVerticle {

	public static int setInstances;
	public static boolean setWorker;

	public static void main(String[] args) {
		System.out.println("In Deploy MyVerticle main!!!");
		Vertx vertx = Vertx.vertx();
		
		// Reading Data From Config file
		System.out.println("Reading Data From Config file!!!");

		System.out.println("buildng File ConfigStoreOptions !!!");
		ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setOptional(true)
				.setConfig(new JsonObject().put("path",
						"./my-config.hocon"));

		System.out.println("buildng Sys ConfigStoreOptions  !!!");
		ConfigStoreOptions sysStore = new ConfigStoreOptions().setType("sys");

		System.out.println("buildng ConfigStoreOptions  !!!");
		ConfigRetrieverOptions configRetrieverOptions = new ConfigRetrieverOptions().addStore(fileStore)
				.addStore(sysStore);
		
		ConfigRetriever configRetriever = null;
		try {
			System.out.println("buildng ConfigRetriever!!!");
			configRetriever = ConfigRetriever.create(vertx, configRetrieverOptions);
		} catch (Exception ex) {
			System.out.println("getMessage::" + ex.getMessage());
			System.out.println("getStackTrace::" + ex.getStackTrace().toString());
		}

		System.out.println("Reading Config data using ConfigRetriever!!!");
		configRetriever.getConfig(json -> {
			JsonObject config = json.result();
			setInstances = config.getInteger("DefaultNumberOfInstances");
			setWorker = config.getBoolean("DefaultEnableWorker");
		});

		System.out.println("Deploying n instanced of MyVerticle - via DiploymentOptions!!!");
		DeploymentOptions newDiploymentOptions = new DeploymentOptions();
		newDiploymentOptions.setInstances(setInstances);
		newDiploymentOptions.setWorker(setWorker);
		vertx.deployVerticle(new MyVerticle(), newDiploymentOptions);
	}
}
```

## Event Bus
It is the nerve system of any Vert.x application.

Being reactive, verticles remain dormant until they receive a message or event. Verticles communicate with each other through the event bus. The message can be anything from a string to a complex object.

Message handling is ideally asynchronous, messages are queued to the event bus, and control is returned to the sender. Later it's dequeued to the listening verticle. The response is sent using Future and callback methods.

Both send and publish are used to send a message to an event bus address. However there are some differences between the two.

By using publish:
* A message is sent to one or multiple listeners
* All handlers listening against the address will be notified
* No answer is expected from handlers

By using send:
* A message is sent to one and only one handler registered against the event bus address.
* If multiple handlers are registered, only one will be notified. The receiver will be selected by a "round-robin algorithm" as per the docs.
* The receiver can answer the message, this answer can be empty or contain a response body. A response timeout can also be specified.

In practical usage, publish is quite useful to inform that an event has occured, whereas send is quite handy for asking a treatment where the response matters.

Conceptually, publish uses the publish/subscribe pattern whereas send uses the request/response pattern.

### Steps to follow 
* Create a Producer Verticle
* Create a Consumer Verticle - call it Consumer One Verticle
* Create Boot Strap Verticle
* Configure Bootstrap in Run Configuration

**Create a Producer Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class ProducerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In ProducerVerticle - START!!!");
		JsonObject message = new JsonObject();
		message.put("Name", "Value");
		
		EventBus bus = vertx.eventBus().send("SportsTopic", message, handler -> {
			if(handler.succeeded()) {
				System.out.println("Great!!! Got the acknowledgement from Consumer.");
				System.out.println("The Message is " + handler.result().body());
			}
			else {
				System.out.println("Offooo!!! Failed to Deliver message.");
			}
		});
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In ProducerVerticle - STOP!!!");	
	}
}
```
**Create a Consumer Verticle - call it Consumer One Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class ConsumerOneVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In ConsumerOneVerticle - START!!!");
		vertx.eventBus().consumer("SportsTopic", message ->{
			System.out.println("received message from Producer :" + message.body());
			JsonObject reply = new JsonObject();
			reply.put("message", "This is Consumer One - Received your message");
			message.reply(reply);
		});
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In ConsumerOneVerticle - STOP!!!");	
	}
}
```

**Create Boot Strap Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class BootstrapProdConsVerticle extends AbstractVerticle {
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In BootstrapVerticle - START!!!");
		
		System.out.println("Deploying 10 instances of ProducerVerticle ConsumerOneVerticle, ConsumerTwoVerticle- via DiploymentOptions!!!");
		DeploymentOptions diploymentOptions = new DeploymentOptions();
		diploymentOptions.setInstances(1);
		diploymentOptions.setWorker(false);
		
		System.out.println("Deploying instance of ProducerVerticle.");
		vertx.deployVerticle(new ProducerVerticle(), diploymentOptions);
		
		System.out.println("Deploying instance of ConsumerOneVerticle.");
		vertx.deployVerticle(new ConsumerOneVerticle(), diploymentOptions);
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In BootstrapVerticle - STOP!!!");	
	}
}

```

**Configure Bootstrap in Run Configuration**
```java
```
## Timers  Handlers

### Types of Timers 

* Delayed Timers - One time execution
* Recursive Timers - Periodic execution with intervals 

**Delayed Timers - One time execution**
```java
import java.util.Random;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HeatSensorVerticle extends AbstractVerticle {
	private double heat = 20;
	private final Random rand = new Random();

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Start");

		vertx.setTimer(5000, this::DealyedTimerUpdateHeat);
				
		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Stop");
	}

	private void DealyedTimerUpdateHeat(Long id) {
		heat = heat + rand.nextGaussian() / 2.0d;
		System.out.println("Inside DealyedTimerUpdateHeat Temperature read from Sensor :" + heat);
	}
}
```
**Recursive Timers - Periodic execution with intervals**
```java
import java.util.Random;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HeatSensorVerticle extends AbstractVerticle {
	private double heat = 20;
	private final Random rand = new Random();

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Start");
	
		vertx.setPeriodic(2000, handler -> {
			heat = heat + rand.nextGaussian() / 2.0d;
			System.out.println("Temperature read from Sensor :" + heat);
		});
		
		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In HeatSensorVerticle Stop");
	}
}

```
## Logging

**Logging Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class LoggingVerticle extends AbstractVerticle {

	final Logger logger = LoggerFactory.getLogger(LoggingVerticle.class);

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In LoggingVerticle Start");
		logger.debug("debug - In LoggingVerticle Start");
		logger.trace("trace - In LoggingVerticle Start");
		logger.debug("debug - In LoggingVerticle Start");
		logger.info("info - In LoggingVerticle Start");
		logger.warn("warning - In LoggingVerticle Start");
		logger.error("error - In LoggingVerticle Start");
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In LoggingVerticle Stop");
		logger.debug("Debug - In LoggingVerticle Stop");
		logger.trace("trace - In LoggingVerticle Stop");
		logger.debug("debug - In LoggingVerticle Stop");
		logger.info("info - In LoggingVerticle Stop");
		logger.warn("warning - In LoggingVerticle Stop");
		logger.error("error - In LoggingVerticle Stop");
	}
}
```
**Deploying Logging Verticle*
```java
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class DeployVertcle {

	public static void main(String[] args) {
		System.out.println("In Main Program");
		Vertx vertx = Vertx.vertx();
		
		// Deploying Logging Verticle 
		System.out.println("In Main - Deploying Logging Verticle");
		vertx.deployVerticle(new LoggingVerticle(), new DeploymentOptions().setInstances(1).setWorker(false));
	}
}
```

## HTTP Server

Now let's spin up an HTTP server using a verticle:
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MyHttpServer extends AbstractVerticle {

	public static void main(String[] args) {
		System.out.println("In Main MyHttpServer - Deploying MyHttpServer");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MyHttpServer(), new DeploymentOptions().setInstances(1).setWorker(false));
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In MyHttpServer Start");
		vertx.createHttpServer()
		.requestHandler(r -> {
			r.response().end("Welcome to Vert.x Intro");
			}
		)
		.listen(config().getInteger("http.port", 9090), 
	        result -> {
	          if (result.succeeded()) {
	        	  startPromise.complete();
	          } else {
	        	  startPromise.fail(result.cause());
	          }
	      });
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In MyHttpServer Stop");	
	}
}
```

We have overridden the start() method to create an HTTP server and attached a request handler to it. The requestHandler() method is called every time the server receives a request.

Finally, the server is bound to a port, and an AsyncResult<HttpServer> handler is passed to the listen() method whether or not the connection or the server startup is succeeded using future.complete() or future.fail() in the case of any errors.

Note that: config.getInteger() method, is reading the value for HTTP port configuration which is being loaded from an external conf.json file.

Let's test our server:
```java
@Test
public void whenReceivedResponse_thenSuccess(TestContext testContext) {
    Async async = testContext.async();

    vertx.createHttpClient()
      .getNow(port, "localhost", "/", response -> {
        response.handler(responseBody -> {
          testContext.assertTrue(responseBody.toString().contains("Hello"));
          async.complete();
        });
      });
}
```
For the test, let's use vertx-unit along with JUnit.:
```xml
<dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-unit</artifactId>
    <version>3.4.1</version>
    <scope>test</scope>
</dependency>
```
We can get the latest version here.

The verticle is deployed and in a vertx instance in the setup() method of the unit test:
```java
@Before
public void setup(TestContext testContext) {
    vertx = Vertx.vertx();

    vertx.deployVerticle(SimpleServerVerticle.class.getName(), 
      testContext.asyncAssertSuccess());
}
```

Similarly, the vertx instance is closed in the @AfterClass tearDown() method:
```java
@After
public void tearDown(TestContext testContext) {
    vertx.close(testContext.asyncAssertSuccess());
}
```

Notice that the @BeforeClass setup() method takes an TestContext argument. This helps up in controlling and testing the asynchronous behavior of the test. For example, the verticle deployment is async, so basically we can't test anything unless it's deployed correctly.

We have a second parameter to the deployVerticle() method, testContext.asyncAssertSuccess(). This is used to know if the server is deployed correctly or any failures occurred. It waits for the future.complete() or future.fail() in the server verticle to be called. In the case of a failure, it fails the test.

## RESTful WebService 

Lets first design and Develop REST Services.

### Build a REST Service to get list of all products.
```java
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
public class GetAllProducts  extends AbstractVerticle {

	/**
	 *
	 * Main Convenience method so you can run it in your IDE
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {

		Runner.runExample(GetAllProducts .class);
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
		vertx.createHttpServer().requestHandler(router).listen(8888);
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
```

### Manually Test your REST Service

#### Manually Test GET List of Products
After Running the REST Service program, launch web browser and tyoe the following URL.
```url
http://localhost:8888/products
```
You would get the following output.
```json
[ {
  "id" : "prod7340",
  "name" : "Tea Cosy",
  "price" : 5.99,
  "weight" : 100
}, {
  "id" : "prod3568",
  "name" : "Egg Whisk",
  "price" : 3.99,
  "weight" : 150
}, {
  "id" : "prod8643",
  "name" : "Spatula",
  "price" : 1.0,
  "weight" : 80
} ]
```
#### Manually Test GET One Product by Product
After testing get all products, lets try to get individual product details. 
to get it use the following URLs.
```url
http://localhost:8888/products/prod7340
```

the output would be
```json
{
  "id" : "prod7340",
  "name" : "Tea Cosy",
  "price" : 5.99,
  "weight" : 100
}
```
for the following URL
```url
http://localhost:8888/products/prod3568
```
the output would be 
```json
{
  "id" : "prod3568",
  "name" : "Egg Whisk",
  "price" : 3.99,
  "weight" : 150
}
```
when use the following URL
```url
http://localhost:8888/products/prod8643
```
the output would be 
```json
{
  "id" : "prod8643",
  "name" : "Spatula",
  "price" : 1.0,
  "weight" : 80
}
```

#### To Add a New Product, you could  use postman
PUT
```url
http://localhost:8888/products/prod888
```
use the following json as request body, with this a new product gets added with ID: prod8888 into the system. 
```json
{
  "id" : "prod8888",
  "name" : "Tea Cup",
  "price" : 2.99,
  "weight" : 100
}
```

to see if the newly added product is added or not, you could call get all products or get individual product URL and test it out.

use the following URL in the browser for the same.
```url
http://localhost:8888/products
```
or use the following url in the browser.
```url
http://localhost:8888/products/prod8888
```
the output would be 
```json
{
  "id" : "prod8888",
  "name" : "Tea Cup",
  "price" : 2.99,
  "weight" : 100
}
```
