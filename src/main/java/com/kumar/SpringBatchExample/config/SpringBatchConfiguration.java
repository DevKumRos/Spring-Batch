package com.kumar.SpringBatchExample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kumar.SpringBatchExample.model.User;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private InputCsvReader inputCsvReader;
    
    @Autowired
    private InputCsvWriter inputCsvWriter;
    
    @Autowired
    private InputCsvProcessor inputCsvProcessor;
    
	/*@Value("/users.csv")
    private Resource inputResource;*/
 
    

	@Bean
	public Job csvJob() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception{
		
		return jobBuilderFactory.get("csv-job")
				.incrementer(new RunIdIncrementer())
				.start(readStep()).build();
	}

	private Step readStep() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		
		return stepBuilderFactory.get("read-step")
				.<User, User>chunk(10)
				.reader(inputCsvReader.read())
				.processor(inputCsvProcessor)
				.writer(inputCsvWriter)
				.build();
	}
	
}
