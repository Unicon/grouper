/**
 * Copyright 2014 Internet2
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
/*
  Copyright (C) 2007 University Corporation for Advanced Internet Development, Inc.
  Copyright (C) 2007 The University Of Chicago

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package edu.internet2.middleware.grouper.validator;
import edu.internet2.middleware.grouper.Stem;
import edu.internet2.middleware.grouper.attr.AttributeDefName;
import edu.internet2.middleware.grouper.attr.finder.AttributeDefNameFinder;
import edu.internet2.middleware.grouper.internal.util.U;

/** 
 * @author mchyzer
 * @version $Id: AddAttributeDefNameValidator.java,v 1.2 2009-09-28 05:06:46 mchyzer Exp $
 */
public class AddAttributeDefNameValidator extends GrouperValidator {

  /** */
  public static final String ATTRIBUTE_DEF_NAME_ALREADY_EXISTS_WITH_NAME_PREFIX = "attribute def name already exists with name: '";

  /**
   * 
   * @param parent
   * @param extn
   * @param dExtn
   * @return self for chaining
   */
  public static AddAttributeDefNameValidator validate(Stem parent, String extn) {
    AddAttributeDefNameValidator  v   = new AddAttributeDefNameValidator();
    NamingValidator   nv  = NamingValidator.validate(extn);
    if (nv.isInvalid()) {
      v.setErrorMessage( nv.getErrorMessage() );
      return v;
    }
    if ( parent.isRootStem() ) {
      v.setErrorMessage("cannot create attribute def names at root stem level");
      return v;
    }
    String attributeName = U.constructName( parent.getName(), extn );
    AttributeDefName attributeDefName = AttributeDefNameFinder.findByName( attributeName, false );
    if (attributeDefName != null) {
      v.setErrorMessage(ATTRIBUTE_DEF_NAME_ALREADY_EXISTS_WITH_NAME_PREFIX + attributeName + "'");
      return v;
    }
    v.setIsValid(true); // attributeDefName does not exist, which is what we want
    return v;
  }

}

