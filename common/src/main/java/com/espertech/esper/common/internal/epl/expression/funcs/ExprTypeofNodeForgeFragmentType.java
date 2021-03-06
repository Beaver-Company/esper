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
package com.espertech.esper.common.internal.epl.expression.funcs;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpression;
import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionRef;
import com.espertech.esper.common.internal.epl.expression.codegen.ExprForgeCodegenSymbol;
import com.espertech.esper.common.internal.epl.expression.core.ExprEvaluator;
import com.espertech.esper.common.internal.epl.expression.core.ExprEvaluatorContext;
import com.espertech.esper.common.internal.epl.expression.core.ExprForgeConstantType;
import com.espertech.esper.common.internal.epl.expression.core.ExprNode;
import com.espertech.esper.common.internal.event.core.EventPropertyGetterSPI;
import com.espertech.esper.common.internal.metrics.instrumentation.InstrumentationBuilderExpr;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.*;

public class ExprTypeofNodeForgeFragmentType extends ExprTypeofNodeForge implements ExprEvaluator {

    private final ExprTypeofNode parent;
    private final int streamId;
    private final EventPropertyGetterSPI getter;
    private final String fragmentType;

    public ExprTypeofNodeForgeFragmentType(ExprTypeofNode parent, int streamId, EventPropertyGetterSPI getter, String fragmentType) {
        this.parent = parent;
        this.streamId = streamId;
        this.getter = getter;
        this.fragmentType = fragmentType;
    }

    public ExprForgeConstantType getForgeConstantType() {
        return ExprForgeConstantType.NONCONST;
    }

    public Object evaluate(EventBean[] eventsPerStream, boolean isNewData, ExprEvaluatorContext context) {
        EventBean event = eventsPerStream[streamId];
        if (event == null) {
            return null;
        }
        Object fragment = getter.getFragment(event);
        if (fragment == null) {
            return null;
        }
        if (fragment instanceof EventBean) {
            EventBean bean = (EventBean) fragment;
            return bean.getEventType().getName();
        }
        if (fragment.getClass().isArray()) {
            String type = fragmentType + "[]";
            return type;
        }
        return null;
    }

    public CodegenExpression evaluateCodegen(Class requiredType, CodegenMethodScope codegenMethodScope, ExprForgeCodegenSymbol exprSymbol, CodegenClassScope codegenClassScope) {
        return new InstrumentationBuilderExpr(this.getClass(), this, "ExprTypeof", requiredType, codegenMethodScope, exprSymbol, codegenClassScope).build();
    }

    public CodegenExpression evaluateCodegenUninstrumented(Class requiredType, CodegenMethodScope codegenMethodScope, ExprForgeCodegenSymbol exprSymbol, CodegenClassScope codegenClassScope) {
        CodegenMethod methodNode = codegenMethodScope.makeChild(String.class, ExprTypeofNodeForgeFragmentType.class, codegenClassScope);

        CodegenExpressionRef refEPS = exprSymbol.getAddEPS(methodNode);
        methodNode.getBlock()
                .declareVar(EventBean.class, "event", arrayAtIndex(refEPS, constant(streamId)))
                .ifRefNullReturnNull("event")
                .declareVar(Object.class, "fragment", getter.eventBeanFragmentCodegen(ref("event"), methodNode, codegenClassScope))
                .ifRefNullReturnNull("fragment")
                .ifInstanceOf("fragment", EventBean.class)
                .blockReturn(exprDotMethodChain(cast(EventBean.class, ref("fragment"))).add("getEventType").add("getName"))
                .ifCondition(exprDotMethodChain(ref("fragment")).add("getClass").add("isArray"))
                .blockReturn(constant(fragmentType + "[]"))
                .methodReturn(constantNull());
        return localMethod(methodNode);
    }

    public ExprEvaluator getExprEvaluator() {
        return this;
    }

    public ExprNode getForgeRenderable() {
        return parent;
    }
}
