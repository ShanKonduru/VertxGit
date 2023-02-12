import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class My Whisky
 */
public class MyWhisky {

	// Auto Increment number
	private static final AtomicInteger COUNTER = new AtomicInteger();

	// Id of the Product
	private final int id;

	// Name of the product 
	private String name;

	// Origin of the product
	private String origin;

	// Age of the product in years
	private long age;


	/**
	 *
	 * It is a constructor.
	 *
	 * @param name   the name
	 * @param origin the origin
	 */
	public MyWhisky(String name, String origin, long age) {

		this.id = COUNTER.getAndIncrement();
		this.name = name;
		this.origin = origin;
		this.age = age;
	}

	/**
	 *
	 * It is a constructor.
	 *
	 */
	public MyWhisky() {

		this.id = COUNTER.getAndIncrement();
	}

	/**
	 *
	 * Gets the name
	 *
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 *
	 * Gets the origin
	 *
	 * @return the origin
	 */
	public String getOrigin() {

		return origin;
	}

	/**
	 *
	 * Gets the identifier
	 *
	 * @return the identifier
	 */
	public int getId() {

		return id;
	}
	
	/**
	 *
	 * Gets the Age
	 *
	 * @return the age
	 */
	public long getAge() {

		return age;
	}

	/**
	 *
	 * Sets the name
	 *
	 * @param name the name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 *
	 * Sets the origin
	 *
	 * @param origin the origin
	 */
	public void setOrigin(String origin) {

		this.origin = origin;
	}
}
