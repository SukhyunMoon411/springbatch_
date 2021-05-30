package com.example.batch.springbatch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("basicJob").start(step1()).build();
	}

	@Bean
	public Step step1(){
		return this.stepBuilderFactory.get("step1").tasklet((contribution,chunkContext)->{
			System.out.println("Hello, world!");
			return RepeatStatus.FINISHED;
		}).build();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

}
