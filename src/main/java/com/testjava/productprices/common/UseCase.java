package com.testjava.productprices.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * The Interface UseCase.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	// Solo un alias para abstraernos de Spring framework
	@AliasFor(annotation = Component.class)
	String value() default "";

}
