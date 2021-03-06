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
package com.espertech.esper.common.internal.epl.output.core;

import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionRef;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.ref;

public class OutputProcessViewCodegenNames {
    public final static String NAME_RESULTSETPROCESSOR = "resultSetProcessor";
    public final static String NAME_STATEMENTRESULTSVC = "statementResultService";
    public final static String NAME_PARENTVIEW = "parentView";
    public final static String NAME_JOINEXECSTRATEGY = "joinExecutionStrategy";
    public final static CodegenExpressionRef REF_CHILD = ref("child");

    public final static CodegenExpressionRef REF_RESULTSETPROCESSOR = new CodegenExpressionRef(NAME_RESULTSETPROCESSOR);
}
