package parser;

public enum ConfParameters {
  ENV("ENVIRONMENT"),
  PORTAL("PORTAL"),
  TRIBE("TRIBE"),
  BODY("BODY"),
  RES("RESOURCE"),
  SPEC1("ANT"),
  SPEC2("TERMITE"),
  SPEC3("SPIDER"),
  FUNC1("QUEEN"),
  FUNC2("SOLDER"),
  FUNC3("NURSE"),
  RES1("MEAT"),
  RES2("SUGAR"),
  RES3("WOOD"),
  RES4("POISON");
  
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