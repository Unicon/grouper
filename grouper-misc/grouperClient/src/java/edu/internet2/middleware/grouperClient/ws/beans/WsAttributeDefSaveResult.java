/**
 * Copyright 2016 Internet2
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package edu.internet2.middleware.grouperClient.ws.beans;



/**
 * Result of one AttributeDef being saved.  The number of
 * these result objects will equal the number of AttributeDefs sent in to the method
 * to be saved
 * 
 * @author vsachdeva
 */
public class WsAttributeDefSaveResult implements ResultMetadataHolder {

  /** attribute def saved */
  private WsAttributeDef wsAttributeDef;
  
  /**
   * metadata about the result
   */
  private WsResultMeta resultMetadata = new WsResultMeta();

  /**
   * @return the resultMetadata
   */
  public WsResultMeta getResultMetadata() {
    return this.resultMetadata;
  }
  
  /**
   * @return the wsAttributeDef
   */
  public WsAttributeDef getWsAttributeDef() {
    return this.wsAttributeDef;
  }

  
  /**
   * @param wsAttributeDef1 the wsAttributeDef to set
   */
  public void setWsAttributeDef(WsAttributeDef wsAttributeDef1) {
    this.wsAttributeDef = wsAttributeDef1;
  }

  
  /**
   * @param resultMetadata1 the resultMetadata to set
   */
  public void setResultMetadata(WsResultMeta resultMetadata1) {
    this.resultMetadata = resultMetadata1;
  }

  /**
   * empty
   */
  public WsAttributeDefSaveResult() {
    //empty
  }
}
