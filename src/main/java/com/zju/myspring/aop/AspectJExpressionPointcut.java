package com.zju.myspring.aop;

import lombok.Data;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zcz
 * @CreateTime 2020/2/5 12:27
 */
@Data
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    private PointcutParser pointcutParser;

    private String expression;

    private PointcutExpression pointcutExpression;

    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();

    static{
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);

    }

    public AspectJExpressionPointcut(){
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    /**
     * TODO  这里是protected
     * 检查 pointcut表达式是否为空
     * 空  就根据expression 生成一个
     */
    private void checkReadyToMatch(){
        if(null == pointcutExpression){
            pointcutExpression = buildPointExpression();
        }
    }

    /**
     * 根据 expression  生成 PointcutExpression
     * @return
     */
    private PointcutExpression buildPointExpression(){
        return pointcutParser.parsePointcutExpression(expression);
    }
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public boolean matches(Class<?> targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();

        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()){
            return true;
        }
        else if(shadowMatch.neverMatches()){
            return false;
        }
        //TODO  还有其他情况   见org.springframework.aop.aspectj.RuntimeTestWalker
        return false;
    }
}
