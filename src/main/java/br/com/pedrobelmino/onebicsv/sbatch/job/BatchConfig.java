package br.com.pedrobelmino.onebicsv.sbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {


    @Bean
    public Job processarArquivoDesafio(JobRepository jobRepository, Step processarArquivoStep, Step imprimirStep) {
        return new JobBuilder("Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(processarArquivoStep)
                .next(imprimirStep)
                .build();
    }


}
