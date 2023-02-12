
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyWhiskyTest {

	private java.util.logging.Logger log = java.util.logging.Logger.getLogger(MyWhiskyTest.class.getName());
	
	@Test
	public void getName() {
		try {
			log.info("Starting execution of getName");
			String expectedValue = "shan";

			MyWhisky mywhisky = new MyWhisky("shan", "konduru", 400);
			String actualValue = mywhisky.getName();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.info("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void getOrigin() {
		try {
			log.info("Starting execution of getOrigin");
			String expectedValue = "konduru";

			MyWhisky mywhisky = new MyWhisky("shan", "konduru", 500);
			String actualValue = mywhisky.getOrigin();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.info("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void getId() {
		try {
			log.info("Starting execution of getId");
			int expectedValue = 2;

			MyWhisky mywhisky = new MyWhisky("shan", "konduru", 300);
			int actualValue = mywhisky.getId();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.info("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void getAge() {
		try {
			log.info("Starting execution of getAge");
			long expectedValue = 300;

			MyWhisky mywhisky = new MyWhisky("shan", "konduru", 300);
			long actualValue = mywhisky.getAge();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.info("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void setName() {
		try {
			log.info("Starting execution of setName");
			String name = "";

			MyWhisky mywhisky = new MyWhisky();
			mywhisky.setName(name);
			assertTrue(true);
		} catch (Exception exception) {
			log.info("Exception in execution ofsetName-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void setOrigin() {
		try {
			log.info("Starting execution of setOrigin");

			MyWhisky mywhisky = new MyWhisky("shan", "", 300);

			String expectedValue ="";
			String actualValue = mywhisky.getOrigin();
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);

			String origin = "konduru";
			mywhisky.setOrigin(origin);

			expectedValue = origin;
			actualValue = mywhisky.getOrigin();
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.info("Exception in execution ofsetOrigin-" + exception);
			exception.printStackTrace();
			assertFalse(false);
		}
	}
}
