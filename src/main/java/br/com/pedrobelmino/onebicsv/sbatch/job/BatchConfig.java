package br.com.pedrobelmino.onebicsv.sbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job processarArquivoDesafio(Step processarArquivoStep, Step imprimirStep) {
        return jobBuilderFactory.get("processarArquivo")
                .incrementer(new RunIdIncrementer())
                .start(processarArquivoStep)
                .next(imprimirStep)
                .build();
    }


}
