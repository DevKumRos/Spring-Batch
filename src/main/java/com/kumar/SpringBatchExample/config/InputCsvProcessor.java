package com.kumar.SpringBatchExample.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kumar.SpringBatchExample.model.User;

@Component
@Scope("prototype")
public class InputCsvProcessor implements ItemProcessor<User, User> {
	
	Map<String, String> DEPT_NAME= new HashMap<>();
	public InputCsvProcessor() {
		DEPT_NAME.put("001", "BANKING");
		DEPT_NAME.put("002", "TESTING");
		DEPT_NAME.put("003", "DEVELOPER");
	}
	
	@Override
	public User process(User user) throws Exception {
		System.out.println("User Proccesed : "+user);
		user.setDept(DEPT_NAME.get(user.getDept()));
		return user;
	}

}
