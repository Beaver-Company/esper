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
package com.espertech.esper.common.internal.epl.expression.agg.accessagg;

import com.espertech.esper.common.internal.epl.agg.core.AggregationTableReadDesc;
import com.espertech.esper.common.internal.epl.expression.core.ExprEnumerationForge;
import com.espertech.esper.common.internal.epl.expression.core.ExprValidationContext;
import com.espertech.esper.common.internal.epl.expression.core.ExprValidationException;
import com.espertech.esper.common.internal.epl.table.compiletime.TableMetaData;
import com.espertech.esper.common.internal.epl.table.compiletime.TableMetadataColumnAggregation;

public interface ExprAggMultiFunctionNode extends ExprEnumerationForge {

    void validatePositionals(ExprValidationContext validationContext) throws ExprValidationException;

    AggregationTableReadDesc validateAggregationTableRead(ExprValidationContext context, TableMetadataColumnAggregation tableAccessColumn, TableMetaData table)
            throws ExprValidationException;
}
