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

#### 1. Deployment using Verticle Object
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

		// Way #1
		System.out.println("Deploying MyVerticle - new Verticle Instance !!!");
		vertx.deployVerticle(new MyVerticle());
	}
}
```
#### 2. Deployment using fully qualified class path
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

		// Way #1
		System.out.println("Deploying MyVerticle - new Verticle Instance !!!");
		vertx.deployVerticle("MyVerticle");
	}
}
```

#### 3. Deployment using class name
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
		System.out.println("Deploying MyVerticle - Verticle Class Name!!!");
		vertx.deployVerticle(MyVerticle.class.getName());
	}
}
```

#### 4. Deployment using Deployment Options
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
		System.out.println("Deploying 10 instanced of MyVerticle - via
		DiploymentOptions!!!");
		DeploymentOptions diploymentOptions = new DeploymentOptions();
		diploymentOptions.setInstances(10);
		diploymentOptions.setWorker(false);
		vertx.deployVerticle("MyVerticle", diploymentOptions);
	}
}
```

#### 5. Deployment using Bootstrap Verticle

##### Steps to follow
* Create a Verticle to be Deployed 
* Create another Verticle called BootstrapVerticle 
* Implement Necessary Deployment Code
* Add Bootstrap in run configuration 

<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>

*Create a Verticle to be Deployed*
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
*Create another Verticle called BootstrapVerticle* 
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

*Add Bootstrap in run configuration*

* provide  io.vertx.core.Launcher under main class
* under arguments add run BootstrapVerticle


## Read Data from Configuration files

* Add necessary dependencies to pom.xml
```xml
    <dependency>
        <groupId>io.vertx</groupId>
        <artifactId>vertx-config</artifactId>
        <version>4.3.8</version>
    </dependency>
```

* Add data file to your project

let the name of the file is *my-config.hocon*
```json
{
	"DefaultNumberOfInstances" : 3,
	"DefaultEnableWorker" : false
}
```

* Add the following code to read these config entries
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

### Steps tp follow 
* Create a Producer Verticle
* Create a Consumer Verticle - call it Consumer One Verticle
* Create another Consumer Verticle - call it Consumer Two Verticle
* Create Boot Strap Verticle
* Configure Bootstrap in Run Configuration

**Create a Producer Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class ProducerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In ProducerVerticle - START!!!");
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

public class ConsumerOneVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In ConsumerOneVerticle - START!!!");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In ConsumerOneVerticle - STOP!!!");	
	}
}
```

**Create another Consumer Verticle - call it Consumer Two Verticle**
```java
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class ConsumerTwoVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("In ConsumerTwoVerticle - START!!!");
	}
	
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("In ConsumerTwoVerticle - STOP!!!");	
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
		
		System.out.println("Deploying instance of ProducerVerticle.");
		vertx.deployVerticle(new ProducerVerticle());
		
		System.out.println("Deploying instance of ConsumerOneVerticle.");
		vertx.deployVerticle(new ConsumerOneVerticle());

		System.out.println("Deploying instance of ConsumerTwoVerticle.");
		vertx.deployVerticle(new ConsumerTwoVerticle());
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

## HTTP Server

Now let's spin up an HTTP server using a verticle:
```java
@Override
public void start(Future<Void> future) {
    vertx.createHttpServer()
      .requestHandler(r -> r.response().end("Welcome to Vert.x Intro");
      })
      .listen(config().getInteger("http.port", 9090), 
        result -> {
          if (result.succeeded()) {
              future.complete();
          } else {
              future.fail(result.cause());
          }
      });
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

We have created an HTTP server, lets now use that to host an RESTfull WebService. In order do so we will need another Vert.x module called vertx-web. This gives a lot of additional features for web development on top of vertx-core.

Let's add the dependency to our pom.xml:
```xml
<dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-web</artifactId>
    <version>3.4.1</version>
</dependency>
```

### Router and Routes
Let's create a router for our WebService. This router will take a simple route of GET method, and handler method getArtilces():
```java
Router router = Router.router(vertx);
router.get("/api/baeldung/articles/article/:id")
  .handler(this::getArticles);
```
The getArticle() method is a simple method that returns new Article object:
```java
private void getArticles(RoutingContext routingContext) {
    String articleId = routingContext.request()
      .getParam("id");
    Article article = new Article(articleId, 
      "This is an intro to vertx", "baeldung", "01-02-2017", 1578);
    routingContext.response()
      .putHeader("content-type", "application/json")
      .setStatusCode(200)
      .end(Json.encodePrettily(article));
}
```
A Router, when receives a request, looks for the matching route, and passes the request further. The routes having a handler method associated with it to do sumthing with the request.

In our case, the handler invokes the getArticle() method. It receives the routingContext object as an argument. Derives the path parameter id, and creates an Article object with it.

In the last part of the method, let's invoke the response() method on the routingContext object and put the headers, set the HTTP response code, and end the response using the JSON encoded article object.

### Adding Router to Server
Now let's add the router, created in the previous section to the HTTP server:
```java
vertx.createHttpServer()
  .requestHandler(router::accept)
  .listen(config().getInteger("http.port", 8080), 
    result -> {
      if (result.succeeded()) {
          future.complete();
      } else {
          future.fail(result.cause());
      }
});
```
Notice that we have added requestHandler(router::accept) to the server. This instructs the server, to invoke the accept() of the router object when any request is received.

Now let's test our WebService:
```java
@Test
public void givenId_whenReceivedArticle_thenSuccess(TestContext testContext) {
    Async async = testContext.async();

    vertx.createHttpClient()
      .getNow(8080, "localhost", "/api/baeldung/articles/article/12345", 
        response -> {
            response.handler(responseBody -> {
            testContext.assertTrue(
              responseBody.toString().contains("\"id\" : \"12345\""));
            async.complete();
        });
      });
}
```
## Packaging Vert.x Application
To package the application as a deployable Java Archive (.jar) let's use Maven Shade plugin and the configurations in the execution tag:
```xml
<configuration>
    <transformers>
        <transformer 
          implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <manifestEntries>
                <Main-Class>io.vertx.core.Starter</Main-Class>
                <Main-Verticle>com.baeldung.SimpleServerVerticle</Main-Verticle>
            </manifestEntries>
        </transformer>
    </transformers>
    <artifactSet />
    <outputFile>
        ${project.build.directory}/${project.artifactId}-${project.version}-app.jar
    </outputFile>
</configuration>
```
In the manifestEntries, Main-Verticle indicates the starting point of the application and the Main-Class is a Vert.x class which, creates the vertx instance and deploys the Main-Verticle.

## Conclusion

In this introductory article, we discussed the Vert.x toolkit and its fundamental concepts. Saw how to create and HTTP server, with Vert.x and also an RESTFull WebService and showed how to test them using vertx-unit.

Finally packaged the application as an executable jar.
