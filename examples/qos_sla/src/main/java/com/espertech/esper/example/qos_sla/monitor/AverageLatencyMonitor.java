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
package com.espertech.esper.example.qos_sla.monitor;

import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;

public class AverageLatencyMonitor {
    public AverageLatencyMonitor(EPRuntime runtime) {
        EPStatement statement = MonitorUtil.compileDeploy("select * from OperationMeasurement#groupwin(customerId, operationName)#length(100)#uni(latency)", runtime);
        statement.addListener(new AverageLatencyListener(10000));
    }
}
