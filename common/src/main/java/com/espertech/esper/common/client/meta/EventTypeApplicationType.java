/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.common.client.meta;

/**
 * Application type.
 */
public enum EventTypeApplicationType {
    /**
     * Xml type.
     */
    XML,

    /**
     * Map type.
     */
    MAP,

    /**
     * Object Array type.
     */
    OBJECTARR,

    /**
     * Class type.
     */
    CLASS,

    /**
     * Avro type.
     */
    AVRO,

    /**
     * Wrapper type.
     */
    WRAPPER,

    /**
     * Variant type.
     */
    VARIANT
}
