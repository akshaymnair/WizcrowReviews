package com.javatpoint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Constraint(validatedBy = HobbyValidator.class)
@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby {

	String listOfValidHobbies() default "Music|Football|Cricket|Hobby";
	
	String message() default "Please provide a valid hobby;" +
				"accepted hobbies are = Music,Football, Cricket, Hockey (Choose anyone)";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
