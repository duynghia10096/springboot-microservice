package com.DDN.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.DDN.dto.FlightSearchEvent;
import com.DDN.entity.FLight;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/batchdb")
                .username("postgres")
                .password("Nghia1009@")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Job syncFlightJob(JobRepository jobRepository, Step syncFlighStep) {
        return new JobBuilder("syncFlightJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(syncFlighStep)
                .build();
    }

    @Bean
    public Step syncFlighStep(JobRepository jobRepository,
            ItemReader<FlightSearchEvent> flightItemReader,
            ItemProcessor<FlightSearchEvent, FLight> flightItemProcessor,
            ItemWriter<FLight> flightItemWriter,
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("syncFlightStep", jobRepository)
                .<FlightSearchEvent, FLight>chunk(100, transactionManager)
                .reader(flightItemReader)
                .processor(flightItemProcessor)
                .writer(flightItemWriter)
                .build();
    }

}
