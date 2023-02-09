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
        <version>3.4.1</version>
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
public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> future) {
        System.out.println("Executing HelloVerticle!!!");
    }
}
```
The start() method will be invoked by the vertx instance when the verticle is deployed. The method takes io.vertx.core.Future as a parameter, which can be used to discover the status of an asynchronous deployment of the verticle.

### Deployment of Vert.x Applications
Now let's deploy the verticle: there are multiple ways to deploy a Verticle.

#### 1. Deployment using Verticle Object
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
public static void main(String[] args) {
    System.out.println("About to Deploy HelloVerticle!!!");
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloVerticle());
}
```
#### 2. Deployment using fully qualified class path
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("shan.vertx.HelloVerticle");
}
```

#### 3. Deployment using class name
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(HelloVerticle.class.getName());
}
```

#### 4. Deployment using Deployment Options
<code> <b><i>NOTE:</i></b><b>References to interface static methods are allowed only at source level 1.8 or above</b> </code>
```java
public static void main(String[] args) {
    DeploymentOptions deploymentOptions = new DeploymentOptions();
    deploymentOptions.setInstances(10); // to Deploy 10 instances of this Verticle
    deploymentOptions.setWorker(false); // Set whether the verticle(s) should be deployed as a worker verticle.
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloVerticle(), deploymentOptions);
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
import io.vertx.core.Future;

public class MySecondVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("In MySecondVerticle - START!!!");
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("In MySecondVerticle - STOP!!!");
	}
}
```
*Create another Verticle called BootstrapVerticle* 
 ```java
 import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class BootstrapVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("In BootstrapVerticle - START!!!");
		System.out.println("Deploying instance of MySecondVerticle.");
		vertx.deployVerticle(new MySecondVerticle());
	}
	
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("In BootstrapVerticle - STOP!!!");	
	}
}
 ```

*Add Bootstrap in run configuration*

* provide  io.vertx.core.Launcher under main class
* under arguments add run BootstrapVerticle



## Event Bus
It is the nerve system of any Vert.x application.

Being reactive, verticles remain dormant until they receive a message or event. Verticles communicate with each other through the event bus. The message can be anything from a string to a complex object.

Message handling is ideally asynchronous, messages are queued to the event bus, and control is returned to the sender. Later it's dequeued to the listening verticle. The response is sent using Future and callback methods.

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
