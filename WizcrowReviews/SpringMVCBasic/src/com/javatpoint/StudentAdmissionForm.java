/*package com.javatpoint;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentAdmissionForm {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//binder.setDisallowedFields(new String[] { "studentMobile" });
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy**mm**dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateformat,false));
		binder.registerCustomEditor(String.class, "studentName", new StudentNameEditor());
	}

	@RequestMapping(value = "admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() throws Exception {

		
		//String exceptionOccured = "NULL_POINTER";
		
		if(exceptionOccured.equalsIgnoreCase("NULL_POINTER"))
		{
			throw new NullPointerException("Null Pointer Exception");
		}
		
		ModelAndView model = new ModelAndView("AdmissionForm");
		return model;
	}

	@ModelAttribute
	public void addCommonObjects(Model model) {

		model.addAttribute("message", "Welcome to college");
	}

	@RequestMapping(value = "submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(
			@Valid @ModelAttribute("student") Student student, BindingResult result) {

		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("AdmissionForm");
			return model;
		}

		
		 * Student student1 = new Student();
		 * 
		 * String name = reqVar.get("studentname");
		 * student1.setStudentName(name);
		 * 
		 * String hobby = reqVar.get("studenthobby");
		 * student1.setStudentHobby(hobby);
		 

		ModelAndView model = new ModelAndView("AdmissionSuccess");

		// model.addObject("student1",student1);

		return model;
	}
	

}
*/