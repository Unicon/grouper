/*
  Copyright (C) 2004-2007 University Corporation for Advanced Internet Development, Inc.
  Copyright (C) 2004-2007 The University Of Chicago

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
import  java.util.Properties;

/**
 * XML Command Line Argument Processing.
 * <p/>
 * @author  blair christensen.
 * @version $Id: XmlArgs.java,v 1.7 2007-03-21 18:02:28 blair Exp $
 * @since   1.1.0
 */
class XmlArgs {

  // TODO 20070321 i could probably make this smarter if i required instantiation

  // PROTECTED CLASS CONSTANTS //
  protected static final String RC_EFILE      = "export.file";
  protected static final String RC_IFILE      = "import.file";
  protected static final String RC_NAME       = "owner.name";
  protected static final String RC_PARENT     = "mystery.parent";
  protected static final String RC_RELATIVE   = "mystery.relative";
  protected static final String RC_SUBJ       = "subject.identifier";
  protected static final String RC_UPROPS     = "properties.user";
  protected static final String RC_UPDATELIST = "update.list";
  protected static final String RC_UUID       = "owner.uuid";


  // PRIVATE CLASS CONSTANTS //
  private static final String E_INSUFFICIENT_ARGS = "insufficient arguments";
  private static final String E_NAME_AND_UUID     = "cannot specify uuid and name";
  private static final String E_TOO_MANY_ARGS     = "too many arguments: ";
  private static final String E_UNKNOWN_OPTION    = "unknown option: ";


  // PROTECTED CLASS METHODS //

  // @since   1.2.0
  protected static Properties internal_getXmlExportArgs(String args[])
    throws  IllegalArgumentException,
            IllegalStateException
  {
    // TODO 20070321 DRY `getXmlImportArgs()`
    Properties  rc        = new Properties();
    String      arg;
    int         inputPos  = 0;
    int         pos       = 0;
    while (pos < args.length) {
      arg = args[pos];
      if (arg.startsWith("-")) {
        if (_handleNamedArgUuid(rc, arg))       {
          rc.setProperty(RC_UUID, args[pos + 1]);
          pos += 2;
          continue;
        } 
        else if (_handleNamedArgName(rc, arg))  {
          rc.setProperty(RC_NAME, args[pos + 1]);
          pos += 2;
          continue;
        } 
        else if (arg.equals("-relative"))       {
          rc.setProperty(RC_RELATIVE, "true");
          pos++;
          continue;
        } 
        else if (arg.equalsIgnoreCase("-includeparent")) {
          rc.setProperty(RC_PARENT, "true");
          pos++;
          continue;
        } else {
          throw new IllegalArgumentException(E_UNKNOWN_OPTION + arg);
        }
      }
      _handlePositionalArg(rc, inputPos, arg, RC_EFILE);
      pos++;
      inputPos++;
    }
    _enoughArgs(inputPos);
    return rc;
  } // protected static Properties internal_getXmlExportArgs(args)

  // @since   1.2.0
  protected static Properties internal_getXmlImportArgs(String[] args) 
    throws  IllegalArgumentException,
            IllegalStateException
  {
    Properties  rc        = new Properties();
    String      arg;
    int         inputPos  = 0;
    int         pos       = 0;
    while (pos < args.length) {
      arg = args[pos];
      if (arg.startsWith("-")) {
        if      (_handleNamedArgUuid(rc, arg))  {
          rc.setProperty(RC_UUID, args[pos + 1]);
          pos += 2;
          continue;
        } 
        else if (_handleNamedArgName(rc, arg))  {
          rc.setProperty(RC_NAME, args[pos + 1]);
          pos += 2;
          continue;
        } 
        else if (arg.equals("-list"))           {
          rc.setProperty(RC_UPDATELIST, "true");
          pos++;
          continue;
        } 
        else {
          throw new IllegalArgumentException(E_UNKNOWN_OPTION + arg);
        }
      }
      _handlePositionalArg(rc, inputPos, arg, RC_IFILE);
      pos++;
      inputPos++;
    }
    _enoughArgs(inputPos);
    return rc;
  } // protected static Properties internal_getXmlImportArgs(args)

  // @since   1.2.0
  protected static boolean internal_wantsHelp(String[] args) {
    if (
      args.length == 0
      || 
      "--h --? /h /? --help /help ${cmd}".indexOf(args[0]) > -1
    ) 
    {
      return true;
    }
    return false;
  } // protected static void internal_wantsHelp(args)


  // PRIVATE CLASS METHODS //

  // @since   1.1.0
  private static void _enoughArgs(int inputPos) 
    throws  IllegalStateException
  {
    if (inputPos < 1) {
      throw new IllegalStateException(E_INSUFFICIENT_ARGS);
    }
  } // private static void _enoughArgs(inputPos)

  // @since   1.1.0
  private static boolean _handleNamedArgName(Properties rc, String arg)
    throws  IllegalArgumentException
  {
    boolean rv = false;
    if (arg.equals("-name")) {
      if (rc.getProperty(RC_UUID) != null) {
        throw new IllegalArgumentException(E_NAME_AND_UUID);
      }
      rv = true;
    }
    return rv;
  } // private static boolean _handleNamedArgName(rc, arg)

  // @since   1.1.0
  private static boolean _handleNamedArgUuid(Properties rc, String arg) 
    throws  IllegalArgumentException
  {
    boolean rv = false;
    if (arg.equals("-id")) {
      if (rc.getProperty(RC_NAME) != null) {
        throw new IllegalArgumentException(E_NAME_AND_UUID);
      }
      rv = true;
    }
    return rv;
  } // private static boolean _handleNamedArgUuid(rc, arg)

  // @since   1.1.0
  private static void _handlePositionalArg(Properties rc, int inputPos, String arg, String file) 
    throws  IllegalArgumentException
  {
    switch (inputPos) {
    case 0:
      rc.setProperty(RC_SUBJ, arg);
      break;
    case 1:
      rc.setProperty(file, arg);
      break;
    case 2:
      rc.setProperty(RC_UPROPS, arg);
      break;
    case 3:
      throw new IllegalArgumentException(E_TOO_MANY_ARGS + U.internal_q(arg));
    }
  } // private static void _handlePositionalArg(rc, inputPos, arg, file)

} // class XmlArgs

