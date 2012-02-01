/**
 * @author shilen
 * $Id$
 */
package edu.internet2.middleware.grouper.internal.dao;

import java.sql.Timestamp;
import java.util.Set;

import edu.internet2.middleware.grouper.Member;
import edu.internet2.middleware.grouper.pit.PITMember;

/**
 * 
 */
public interface PITMemberDAO extends GrouperDAO {

  /**
   * insert or update
   * @param pitMember
   */
  public void saveOrUpdate(PITMember pitMember);
  
  /**
   * insert or update
   * @param pitMembers
   */
  public void saveOrUpdate(Set<PITMember> pitMembers);
  
  /**
   * delete
   * @param pitMember
   */
  public void delete(PITMember pitMember);
  
  /**
   * @param id
   * @param exceptionIfNotFound 
   * @return PITMember
   */
  public PITMember findBySourceIdActive(String id, boolean exceptionIfNotFound);
  
  /**
   * @param id
   * @param exceptionIfNotFound 
   * @return PITMember
   */
  public PITMember findById(String id, boolean exceptionIfNotFound);
  
  /**
   * @param id
   * @param exceptionIfNotFound 
   * @return PITMember
   */
  public PITMember findBySourceIdUnique(String id, boolean exceptionIfNotFound);
  
  /**
   * @param id
   * @param exceptionIfNotFound 
   * @return set of PITMember
   */
  public Set<PITMember> findBySourceId(String id, boolean exceptionIfNotFound);
  
  /**
   * Delete records that ended before the given date.
   * @param time
   */
  public void deleteInactiveRecords(Timestamp time);
  
  /**
   * @param id
   * @param source
   * @param type
   * @return pit members
   */
  public Set<PITMember> findPITMembersBySubjectIdSourceAndType(String id, String source, String type);
  
  /**
   * @return active members that are missing in point in time
   */
  public Set<Member> findMissingActivePITMembers();
  
  /**
   * @return active point in time members that should be inactive
   */
  public Set<PITMember> findMissingInactivePITMembers();
}
