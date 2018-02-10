package com.controllers.registration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.objects.person.LogonDetails;
import com.objects.person.LogonSuccessVO;
import com.objects.person.Person;
import com.objects.person.UserRegistrationVO;

@RestController
@RequestMapping(value="/register")
public class RegistrationController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserRegistrationVO responseVO;
	
	@Autowired
	LogonSuccessVO logonVO;
	
	private String queryStr = "insert into hackathon_user(NAME,PASSWORD,CONFPWD,EMAILID,COUNTRY,CITY,STREET,PINCODE,MOBILENUMBER) values(?,?,?,?,?,?,?,?,?)";
	
	private String checkEmailQuery = "select * from hackathon_user where EMAILID=?";
	
	private String validateCredentailsQuery = "select * from hackathon_user where EMAILID=? and PASSWORD=?";
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public UserRegistrationVO registerPerson(@RequestBody Person person)
	{
		try {
			int status = 0;
			if(null != person.getPassword() && person.getPassword().equals(person.getConfpwd())) { 
				List<Map<String,Object>> existUserList = jdbcTemplate.queryForList(checkEmailQuery, person.getEmailId());
				if(existUserList.isEmpty() || existUserList == null) {         
					status = jdbcTemplate.update(queryStr, person.getName(),person.getPassword(),person.getConfpwd(),person.getEmailId(),person.getCountry(),person.getCity(),person.getStreet(),person.getPincode(),person.getMobileNumber());
					if(status == 1) {
						responseVO.setSuccess(true);
					}else {
						responseVO.setSuccess(false);
						responseVO.setErrorMsg("Error while inserting into DB");
					}
				} else {
					responseVO.setSuccess(false);
					responseVO.setErrorMsg("User with this emailId already exists");
				}
			} else{
				responseVO.setSuccess(false);
				responseVO.setErrorMsg("Both password and confirm password should be same");
			}
		} catch(Exception e) {
			responseVO.setSuccess(false);
			responseVO.setErrorMsg(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value="/validate",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public LogonSuccessVO validateLogonDetails(@RequestBody LogonDetails logonDetails)
	{
		if(null != logonDetails.getEmailId() && null != logonDetails.getPassword()) {
			Map<String,Object> userDetMap = jdbcTemplate.queryForMap(validateCredentailsQuery, logonDetails.getEmailId(),logonDetails.getPassword());
			if(!userDetMap.isEmpty() && null != userDetMap && logonDetails.getEmailId().equalsIgnoreCase(String.valueOf(userDetMap.get("EMAILID")))) {
				logonVO.setSuccess(true);
				logonVO.setUserId(String.valueOf(userDetMap.get("ID")));
				logonVO.setUserName(String.valueOf(userDetMap.get("NAME")));
			} else {
				logonVO.setSuccess(false);
				logonVO.setErrorMsg("Logon credentails doesn't match with our database. Please try again");
			}
		}
		return logonVO;
	}
	

}
