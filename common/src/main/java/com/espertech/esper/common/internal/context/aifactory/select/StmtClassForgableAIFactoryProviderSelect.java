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
package com.espertech.esper.common.internal.context.aifactory.select;

import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenPackageScope;
import com.espertech.esper.common.internal.compile.stage3.StmtClassForgableAIFactoryProviderBase;
import com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol;
import com.espertech.esper.common.internal.context.aifactory.core.StatementAgentInstanceFactoryForge;
import com.espertech.esper.common.internal.context.module.EPStatementInitServices;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.localMethod;
import static com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol.REF_STMTINITSVC;

public class StmtClassForgableAIFactoryProviderSelect extends StmtClassForgableAIFactoryProviderBase {
    private final StatementAgentInstanceFactoryForge forge;

    public StmtClassForgableAIFactoryProviderSelect(String className, CodegenPackageScope packageScope, StatementAgentInstanceFactoryForge forge) {
        super(className, packageScope);
        this.forge = forge;
    }

    protected Class typeOfFactory() {
        return StatementAgentInstanceFactorySelect.class;
    }

    protected CodegenMethod codegenConstructorInit(CodegenMethodScope parent, CodegenClassScope classScope) {
        SAIFFInitializeSymbol saiffInitializeSymbol = new SAIFFInitializeSymbol();
        CodegenMethod method = parent.makeChildWithScope(typeOfFactory(), this.getClass(), saiffInitializeSymbol, classScope).addParam(EPStatementInitServices.class, REF_STMTINITSVC.getRef());
        method.getBlock().methodReturn(localMethod(forge.initializeCodegen(classScope, method, saiffInitializeSymbol)));
        return method;
    }
}
