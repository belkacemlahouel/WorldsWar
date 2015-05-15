package env2.pheromones;

public enum PheromonsType {
  DANGER("DANGER"),
  FIGHT("FIGHT"),
  FOOD("FOOD");
  
  private String name = "";
  
  /**
   * Basic constructor
   */
  PheromonsType(String name){
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
  public static PheromonsType getByValue(String type) {
	for (PheromonsType value : values()) {
	       String enumValue = value.toString();
	       if (enumValue.equalsIgnoreCase(type)) {
	            return value;
	       }
	   } 
	return null;
  }
}