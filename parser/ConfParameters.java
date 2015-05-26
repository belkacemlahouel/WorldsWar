package parser;

public enum ConfParameters {
  ENV("ENVIRONMENT"),
  PORTAL("PORTAL"),
  TRIBE("TRIBE"),
  BODY("BODY"),
  RES("RESOURCE");
  
  private String name = "";
  
  /**
   * Basic constructor
   */
  ConfParameters(String name){
    this.name = name;
  }
  
  /**
   * Getter for the string value of an enum.
   * @return the string value corresponding to an enum.
   */
  public String toString(){
    return name;
  }
  
  /**
   * Create an enum thanks to a string value.
   * @param type, the string value used to create the enum.
   * @return the enum corresponding to the string value called "type".
   */
  public static ConfParameters getByValue(String type) {
	for (ConfParameters value : values()) {
	       String enumValue = value.toString();
	       if (enumValue.equalsIgnoreCase(type)) {
	            return value;
	       }
	   } 
	return null;
  }
}