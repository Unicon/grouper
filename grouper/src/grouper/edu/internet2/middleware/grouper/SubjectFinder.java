/*
  Copyright 2004-2005 University Corporation for Advanced Internet Development, Inc.
  Copyright 2004-2005 The University Of Chicago

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

package edu.internet2.middleware.grouper;

import  edu.internet2.middleware.subject.*;
import  edu.internet2.middleware.subject.provider.*;
import  java.io.Serializable;
import  java.util.*;
import  org.apache.commons.logging.*;
import  org.apache.commons.logging.LogFactory;

/**
 * Class for finding I2MI Subjects.
 * <p />
 * @author  blair christensen.
 * @version $Id: SubjectFinder.java,v 1.1.2.4 2005-10-18 16:40:31 blair Exp $
 */
public class SubjectFinder implements Serializable {

  /*
   * PRIVATE CLASS VARIABLES
   */
  private static Log            log       = LogFactory.getLog(SubjectFinder.class);


  /*
   * PUBLIC CLASS METHODS 
   */

  /**
   * Get a subject by id.
   * <pre class="eg">
   * // Find the subject - within all sources - with the specified id.
   * try {
   *   Subject subj = SubjectFinder.findById(subjectID);
   * }
   * catch (SubjectNotFoundException e) {
   *   // Subject not found
   * }
   *  </pre>
   * @param   id      Subject ID
   * @return  A {@link Subject} object
   * @throws SubjectNotFoundException
   */
  public static Subject findById(String id) 
    throws SubjectNotFoundException
  {
    throw new RuntimeException("Not implemented");  
  }

  /**
   * Get a subject by id and the specified type.
   * <pre class="eg">
   * // Find the subject - within all sources - with the specified id
   * // and type.
   * try {
   *   Subject subj = SubjectFinder.findById(subjectID, type);
   * }
   * catch (SubjectNotFoundException e) {
   *   // Subject not found
   * }
   *  </pre>
   * @param   id      Subject ID
   * @param   type    Subject type.
   * @return  A {@link Subject} object
   * @throws SubjectNotFoundException
   */
  public static Subject findById(String id, String type) 
    throws SubjectNotFoundException
  {
    throw new RuntimeException("Not implemented");  
  }

  /**
   * Get a subject by a well-known identifier.
   * <pre class="eg">
   * // Find the subject - within all sources - with the well-known
   * // identifier.
   * try {
   *   Subject subj = SubjectFinder.findByIdentifier(identifier);
   * }
   * catch (SubjectNotFoundException e) {
   *   // Subject not found
   * }
   *  </pre>
   * @param   id      Subject identifier.
   * @return  A {@link Subject} object
   * @throws SubjectNotFoundException
   */
  public static Subject findByIdentifier(String id) 
    throws SubjectNotFoundException
  {
    throw new RuntimeException("Not implemented");  
  }

  /**
   * Get a subject by a well-known identifier and the specified type.
   * <pre class="eg">
   * // Find the subject - within all sources - with the specified id
   * // and type.
   * try {
   *   Subject subj = SubjectFinder.findByIdentifier(identifier, type);
   * }
   * catch (SubjectNotFoundException e) {
   *   // Subject not found
   * }
   *  </pre>
   * @param   id      Subject identifier.
   * @param   type    Subject type.
   * @return  A {@link Subject} object
   * @throws SubjectNotFoundException
   */
  public static Subject findByIdentifier(String id, String type) 
    throws SubjectNotFoundException
  {
    throw new RuntimeException("Not implemented");  
  }

  /**
   * Find all subjects matching the query.
   * <p>
   * The query string specification is currently unique to each subject
   * source adapter.  Queries may not work or may lead to erratic
   * results across different source adapters.  Consult the
   * documentation for each source adapter for more information on the
   * query language supported by each adapter.
   * </p>
   * <pre class="eg">
   * // Find all subjects matching the given query string.
   * Set subjects = SubjectFinder.find(query);
   * </pre>
   * @param   query     Subject query string.
   * @return  A {@link Set} of {@link Subject} objects.
   */
  public static Set find(String query) {
    throw new RuntimeException("Not implemented");
  }

}

