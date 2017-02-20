package betterLeJOSColors.model;

/**
 *
 * Color Class used to store a single color input
 * 
 * @author Nathan Beam 
 * @version Fall 2016
 */
public class Color {
	
	private String colorName;
	private float red;
	private float green;
	private float blue;
	
	/**
	 * Constructor for Color
	 * 
	 * @param red the Red input of the scan
	 * @param green the Green input of the scan
	 * @param blue the Blue input of the scan
	 */
	public Color(float red, float green, float blue) {
		this.colorName = "NO_NAME";
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Returns the Red value of this Color
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the Red value
	 */
	public float getRed() {
		return this.red;
	}

	/**
	 * Returns the Green value of this Color
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the Green value
	 */
	public float getGreen() {
		return this.green;
	}

	/**
	 * Returns the Blue value of this Color
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the Blue value
	 */
	public float getBlue() {
		return this.blue;
	}
	
	/**
	 * Gets the name of this Color as set by the User
	 *
	 * @precondition colorname is not null
	 * @postcondition none
	 * @return the name of this Color Object
	 */
	public String getColorName() {
		return this.colorName;
	}
	
	/**
	 * Sets the colorName to the specified name
	 *
	 * @precondition colorName != null
	 * @postcondition this.colorName = colorName
	 * @param colorName the new name of the color
	 */
	public void setColorName(String colorName) {
		if (colorName != null) {
			this.colorName = colorName;
		}
	}
}
