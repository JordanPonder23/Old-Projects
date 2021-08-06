package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/*Much like how Hibernate annotations implement JPA annotations....
 * Spring AOP creates an implementation of aspectj. It simplifies aspectj
 * BUT it reduces the functionality as a result.
 * Basically, Spring AOP abstracts aspectj from us.
 * 
 * 
 * An aspect is a modularization of cross-cutting concerns.
 * In java, it takes the form of a class. You can ALSO think of an aspect
 * as a collection of advice.
 */
@Component("myAspect")
@Aspect
public class AspectExample {
	/*
	 * What is an advice?
	 * 		An action taken by an aspect. In Spring AOP advice are triggered
	 * 		by a method being invoked.
	 * 
	 * What form does an advice take in java?
	 * 		A method.
	 * 
	 * What are some TYPES of advice/advice timings?
	 * 		Before-	before the method
	 * 		After-	after the method, regardless of success
	 * 		AfterReturning-	after the method returns successfully
	 * 		AfterThrowing-	after some object is thrown
	 * 		Around-  well...it's the most powerful
	 * 
	 * Pointcut expressions
	 * 	"*" is our wildcard for return types and method naming
	 * 	".." is our wildcard for our parameter list
	 * 	"*" is ALSO our wildcard for a SINGLE parameter in the parameter list
	 * 	and yes, you can specify an access modifier.
	 * 
	 * JoinPoint are the possible points in the runtime of the program WHERE
	 * an advice can be invoked. In spring AOP, these points are method calls.
	 * JoinPoints take the form of an object given to us by Spring AOP; this object
	 * contains information about the current state of runtime.
	 * 
	 * Pointcuts are expressions that are used to select SPECIFIC joinpoints.
	 * In other words, pointcuts target a subset of all joinpoints.
	 * Pointcuts take the form of a pointcut expression (a string that uses
	 * regular expression)
	 */
	@Before("execution(* *r*(..))") //<--THIS is a pointcut expression
	public void buildingEnthusiam(JoinPoint jp) {
		System.out.println("build enthusiasm...");
		System.out.println(jp.getSignature());
		System.out.println(jp.getArgs().length);
		//System.out.println(jp.getThis());
	}
	
	//@After("execution(protected * draw*(..))")
	//@After("execution(int draw*(..))")
	//@After("execution(* draw*(int, int))")
	//@After("execution(int draw*(*, *))")
	/*@After("execution(int draw*(*))")
	public void eatSnack(JoinPoint jp) {
		System.out.println("snack break!");
	}*/
	
	/*
	 * AROUND EXAMPLE
	 * around is the most powerful advice type. It can control variables
	 * inside the method. And choose how to act by what is happening inside
	 * of the method itself.
	 */
	/*@Around("execution(* scul*(..))")
	public void testAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Wetting the clay");
		pjp.proceed();
		System.out.println("clean up the mess");
	}*/
	
}
