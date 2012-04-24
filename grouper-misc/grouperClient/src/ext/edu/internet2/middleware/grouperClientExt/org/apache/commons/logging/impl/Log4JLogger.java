/*******************************************************************************
 * Copyright 2012 Internet2
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
 ******************************************************************************/
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 


package edu.internet2.middleware.grouperClientExt.org.apache.commons.logging.impl;

import java.io.Serializable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import edu.internet2.middleware.grouperClientExt.org.apache.commons.logging.Log;

/**
 * Implementation of {@link Log} that maps directly to a
 * <strong>Logger</strong> for log4J version 1.2.
 * <p>
 * Initial configuration of the corresponding Logger instances should be done
 * in the usual manner, as outlined in the Log4J documentation.
 * <p>
 * The reason this logger is distinct from the 1.3 logger is that in version 1.2
 * of Log4J:
 * <ul>
 * <li>class Logger takes Priority parameters not Level parameters.
 * <li>class Level extends Priority
 * </ul>
 * Log4J1.3 is expected to change Level so it no longer extends Priority, which is
 * a non-binary-compatible change. The class generated by compiling this code against
 * log4j 1.2 will therefore not run against log4j 1.3.
 *
 * @author <a href="mailto:sanders@apache.org">Scott Sanders</a>
 * @author Rod Waldhoff
 * @author Robert Burrell Donkin
 * @version $Id: Log4JLogger.java,v 1.1 2008-11-30 10:57:27 mchyzer Exp $
 */

public class Log4JLogger implements Log, Serializable {

    // ------------------------------------------------------------- Attributes

    /** The fully qualified name of the Log4JLogger class. */
    private static final String FQCN = Log4JLogger.class.getName();
    
    /** Log to this logger */
    private transient Logger logger = null;

    /** Logger name */
    private String name = null;

    private static Priority traceLevel;
    
    // ------------------------------------------------------------
    // Static Initializer.
    //
    // Note that this must come after the static variable declarations
    // otherwise initialiser expressions associated with those variables
    // will override any settings done here.
    //
    // Verify that log4j is available, and that it is version 1.2.
    // If an ExceptionInInitializerError is generated, then LogFactoryImpl
    // will treat that as meaning that the appropriate underlying logging
    // library is just not present - if discovery is in progress then
    // discovery will continue.
    // ------------------------------------------------------------

    static {
        if (!Priority.class.isAssignableFrom(Level.class)) {
            // nope, this is log4j 1.3, so force an ExceptionInInitializerError
            throw new InstantiationError("Log4J 1.2 not available");
        }
        
        // Releases of log4j1.2 >= 1.2.12 have Priority.TRACE available, earlier
        // versions do not. If TRACE is not available, then we have to map
        // calls to Log.trace(...) onto the DEBUG level.
        
        try {
            traceLevel = (Priority) Level.class.getDeclaredField("TRACE").get(null);
        } catch(Exception ex) {
            // ok, trace not available
            traceLevel = Priority.DEBUG;
        }
    }

    
    // ------------------------------------------------------------ Constructor

    public Log4JLogger() {
    }


    /**
     * Base constructor.
     */
    public Log4JLogger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    /** 
     * For use with a log4j factory.
     */
    public Log4JLogger(Logger logger ) {
        if (logger == null) {
            throw new IllegalArgumentException(
                "Warning - null logger in constructor; possible log4j misconfiguration.");
        }
        this.name = logger.getName();
        this.logger=logger;
    }


    // --------------------------------------------------------- 
    // Implementation
    //
    // Note that in the methods below the Priority class is used to define
    // levels even though the Level class is supported in 1.2. This is done
    // so that at compile time the call definitely resolves to a call to
    // a method that takes a Priority rather than one that takes a Level.
    // 
    // The Category class (and hence its subclass Logger) in version 1.2 only
    // has methods that take Priority objects. The Category class (and hence
    // Logger class) in version 1.3 has methods that take both Priority and
    // Level objects. This means that if we use Level here, and compile
    // against log4j 1.3 then calls would be bound to the versions of
    // methods taking Level objects and then would fail to run against
    // version 1.2 of log4j.
    // --------------------------------------------------------- 


    /**
     * Logs a message with <code>org.apache.log4j.Priority.TRACE</code>.
     * When using a log4j version that does not support the <code>TRACE</code>
     * level, the message will be logged at the <code>DEBUG</code> level.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#trace(Object)
     */
    public void trace(Object message) {
        getLogger().log(FQCN, traceLevel, message, null );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.TRACE</code>.
     * When using a log4j version that does not support the <code>TRACE</code>
     * level, the message will be logged at the <code>DEBUG</code> level.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#trace(Object, Throwable)
     */
    public void trace(Object message, Throwable t) {
        getLogger().log(FQCN, traceLevel, message, t );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.DEBUG</code>.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#debug(Object)
     */
    public void debug(Object message) {
        getLogger().log(FQCN, Priority.DEBUG, message, null );
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.DEBUG</code>.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#debug(Object, Throwable)
     */
    public void debug(Object message, Throwable t) {
        getLogger().log(FQCN, Priority.DEBUG, message, t );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.INFO</code>.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#info(Object)
     */
    public void info(Object message) {
        getLogger().log(FQCN, Priority.INFO, message, null );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.INFO</code>.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#info(Object, Throwable)
     */
    public void info(Object message, Throwable t) {
        getLogger().log(FQCN, Priority.INFO, message, t );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.WARN</code>.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#warn(Object)
     */
    public void warn(Object message) {
        getLogger().log(FQCN, Priority.WARN, message, null );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.WARN</code>.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#warn(Object, Throwable)
     */
    public void warn(Object message, Throwable t) {
        getLogger().log(FQCN, Priority.WARN, message, t );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.ERROR</code>.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#error(Object)
     */
    public void error(Object message) {
        getLogger().log(FQCN, Priority.ERROR, message, null );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.ERROR</code>.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#error(Object, Throwable)
     */
    public void error(Object message, Throwable t) {
        getLogger().log(FQCN, Priority.ERROR, message, t );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.FATAL</code>.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#fatal(Object)
     */
    public void fatal(Object message) {
        getLogger().log(FQCN, Priority.FATAL, message, null );
    }


    /**
     * Logs a message with <code>org.apache.log4j.Priority.FATAL</code>.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#fatal(Object, Throwable)
     */
    public void fatal(Object message, Throwable t) {
        getLogger().log(FQCN, Priority.FATAL, message, t );
    }


    /**
     * Return the native Logger instance we are using.
     */
    public Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(name);
        }
        return (this.logger);
    }


    /**
     * Check whether the Log4j Logger used is enabled for <code>DEBUG</code> priority.
     */
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }


     /**
     * Check whether the Log4j Logger used is enabled for <code>ERROR</code> priority.
     */
    public boolean isErrorEnabled() {
        return getLogger().isEnabledFor(Priority.ERROR);
    }


    /**
     * Check whether the Log4j Logger used is enabled for <code>FATAL</code> priority.
     */
    public boolean isFatalEnabled() {
        return getLogger().isEnabledFor(Priority.FATAL);
    }


    /**
     * Check whether the Log4j Logger used is enabled for <code>INFO</code> priority.
     */
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }


    /**
     * Check whether the Log4j Logger used is enabled for <code>TRACE</code> priority.
     * When using a log4j version that does not support the TRACE level, this call
     * will report whether <code>DEBUG</code> is enabled or not.
     */
    public boolean isTraceEnabled() {
        return getLogger().isEnabledFor(traceLevel);
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>WARN</code> priority.
     */
    public boolean isWarnEnabled() {
        return getLogger().isEnabledFor(Priority.WARN);
    }
}
