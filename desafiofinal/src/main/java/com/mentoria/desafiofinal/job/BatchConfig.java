package com.mentoria.desafiofinal.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfig {


    @Bean
    public Job processarArquivoDesafio(JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager,
                                       Step processarArquivoStep) {
        return new JobBuilder("processarArquivoDesafio", jobRepository)
                .start(processarArquivoStep)
                .build();
    }

}
