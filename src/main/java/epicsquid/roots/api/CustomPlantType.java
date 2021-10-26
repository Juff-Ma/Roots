package epicsquid.roots.api;

import net.minecraftforge.common.PlantType;

public class CustomPlantType {

  /**
   * ID: 0
   */
  public static final PlantType ELEMENT_FIRE = PlantType.get("element_fire");
  /**
   * ID: 1
   */
  public static final PlantType ELEMENT_WATER = PlantType.get("element_water");
  /**
   * ID: 2
   */
  public static final PlantType ELEMENT_AIR = PlantType.get("element_air");
  /**
   * ID: 3
   */
  public static final PlantType ELEMENT_EARTH = PlantType.get("element_earth");

  public static PlantType getTypeFromId(int id) {
    switch (id) {
      case 0:
        return ELEMENT_FIRE;
      case 1:
        return ELEMENT_WATER;
      case 2:
        return ELEMENT_AIR;
      case 3:
        return ELEMENT_EARTH;
      default:
        return null;
    }
  }
}
