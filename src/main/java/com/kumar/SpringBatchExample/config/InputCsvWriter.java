package com.kumar.SpringBatchExample.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kumar.SpringBatchExample.model.User;
import com.kumar.SpringBatchExample.repository.UserRepository;

@Component
@Scope("prototype")
public class InputCsvWriter implements ItemWriter<User> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("User Saved : "+users);
		userRepository.saveAll(users);
		
	}

}
