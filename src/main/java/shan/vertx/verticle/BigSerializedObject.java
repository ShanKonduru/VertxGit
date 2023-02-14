package vertx.session.day2;

import java.util.UUID;

public class BigSerializedObject {
	private String message;

	@Override
	public String toString() {
		return message;
	}

	public BigSerializedObject() {
		this.message = "BigSerializedObject";
	}
}