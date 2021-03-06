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
package com.espertech.esper.common.internal.settings;

import com.espertech.esper.common.internal.epl.expression.core.ExprForge;
import com.espertech.esper.common.internal.epl.expression.core.ExprNode;
import com.espertech.esper.common.internal.epl.expression.core.ExprValidationContext;
import com.espertech.esper.common.internal.epl.expression.core.ExprValidationException;
import com.espertech.esper.common.internal.epl.join.analyze.FilterExprAnalyzerAffector;
import com.espertech.esper.common.internal.filterspec.FilterSpecCompilerAdvIndexDesc;

public interface SettingsApplicationDotMethod {

    ExprNode validate(ExprValidationContext validationContext) throws ExprValidationException;

    FilterExprAnalyzerAffector getFilterExprAnalyzerAffector();

    FilterSpecCompilerAdvIndexDesc getFilterSpecCompilerAdvIndexDesc();

    ExprForge getForge();

    String getLhsName();

    ExprNode[] getLhs();

    String getDotMethodName();

    String getRhsName();

    ExprNode[] getRhs();
}
