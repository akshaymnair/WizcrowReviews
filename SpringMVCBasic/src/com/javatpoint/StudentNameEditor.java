package com.javatpoint;

import java.beans.PropertyEditorSupport;

public class StudentNameEditor extends PropertyEditorSupport {

	// When you will submit admission form
	// Spring MVC will run setAsText function of this class
	// Before performing data binding task for studentName property of student
	// object

	@Override
	public void setAsText(String studentName) throws IllegalArgumentException {

		if (studentName.contains("Mr.") || studentName.contains("Ms.")) {
			setValue(studentName);
		}

		else {
			studentName = "Ms." + studentName;
			setValue(studentName);
		}
	}

}
