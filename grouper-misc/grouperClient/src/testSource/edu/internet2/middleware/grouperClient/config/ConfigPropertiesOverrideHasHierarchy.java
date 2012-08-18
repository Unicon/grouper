package edu.internet2.middleware.grouperClient.config;


/**
 * 
 * @author mchyzer
 *
 */
public class ConfigPropertiesOverrideHasHierarchy extends ConfigPropertiesCascadeBase {

  /**
   * this is used to tell engine where the default and example config is...
   */
  private static ConfigPropertiesOverrideHasHierarchy configSingleton = new ConfigPropertiesOverrideHasHierarchy();
  
  /**
   * retrieve a config from the config file or from cache
   * @return the config object
   */
  public static ConfigPropertiesOverrideHasHierarchy retrieveConfig() {
    return (ConfigPropertiesOverrideHasHierarchy)configSingleton.retrieveFromConfigFileOrCache();
  }

  /**
   * @see ConfigPropertiesCascadeBase#getMainConfigClasspath()
   */
  @Override
  protected String getMainConfigClasspath() {
    return "testCascadeConfig.properties";
  }

  /**
   * @see ConfigPropertiesCascadeBase#getMainExampleConfigClasspath()
   */
  @Override
  protected String getMainExampleConfigClasspath() {
    return "testCascadeConfig-example.properties";
  }

  /**
   * @see ConfigPropertiesCascadeBase#clearCachedCalculatedValues()
   */
  @Override
  public void clearCachedCalculatedValues() {
    
  }

  /**
   * @see ConfigPropertiesCascadeBase#getHierarchyConfigKey()
   */
  @Override
  protected String getHierarchyConfigKey() {
    return "config.hierarchy";
  }

  /**
   * @see ConfigPropertiesCascadeBase#getSecondsToCheckConfigKey
   */
  @Override
  protected String getSecondsToCheckConfigKey() {
    return "config.checkConfigEverySeconds";
  }
  
  
}
