package shan.vertx.utilities;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ConfigProperties {

	private int _setInstances;
	private boolean _setWorker;
	private ConfigRetriever _retriever;

	public ConfigProperties(Vertx _vertx) {
		ConfigStoreOptions fileStoreOptions = new ConfigStoreOptions().setType("file").setOptional(true)
				.setConfig(new JsonObject().put("path", "./data/my-config.hocon"));

		ConfigStoreOptions sysStoreOptions = new ConfigStoreOptions().setType("sys");

		ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions().addStore(fileStoreOptions)
				.addStore(sysStoreOptions);

		_retriever = ConfigRetriever.create(_vertx, retrieverOptions);
	}

	public int getInstances() {
		_retriever.getConfig(json -> {
			if (json.failed()) {
				// Failed to retrieve the configuration
				System.out.println("Failed to retrieve 'setInstances' value from the configuration");
			} else {
				JsonObject config = json.result();
				_setInstances = config.getInteger("setInstances");
			}
		});
		return _setInstances;
	}
	
	public boolean getWorker() {
		_retriever.getConfig(json -> {
			if (json.failed()) {
				// Failed to retrieve the configuration
				System.out.println("Failed to retrieve 'setWorker' value from the configuration");
			} else {
				JsonObject config = json.result();
				_setWorker = config.getBoolean("setWorker");
			}
		});
		return _setWorker;
	}

}
