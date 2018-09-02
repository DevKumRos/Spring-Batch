package com.kumar.SpringBatchExample.config;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kumar.SpringBatchExample.model.User;

@Component
@Scope("prototype")
public class InputCsvReader implements ItemReader<FlatFileItemReader<User>>{
	
	@Value("$(inputFile)")
    private Resource inputResource;

	public InputCsvReader() {
		
	}
	@Override
	public FlatFileItemReader<User> read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		 FlatFileItemReader<User> itemReader = new FlatFileItemReader<User>();
		 itemReader.setResource(new ClassPathResource("users.csv"));
		 itemReader.setName("CSV-READER");
		 itemReader.setLinesToSkip(1);
		 itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<User> lineMapper() {
		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
		// READING FILE 
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
		delimitedLineTokenizer.setStrict(true);
		delimitedLineTokenizer.setNames(new String[] {"id", "name", "dept", "salary"});
		
		// POJO MAPPING
		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<User>();
		fieldSetMapper.setTargetType(User.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}
