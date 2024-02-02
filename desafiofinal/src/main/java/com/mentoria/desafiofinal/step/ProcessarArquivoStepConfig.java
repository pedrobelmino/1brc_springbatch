package com.mentoria.desafiofinal.step;

import com.mentoria.desafiofinal.impl.Calculo;
import com.mentoria.desafiofinal.impl.ConteudoLinha;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;


@Configuration
public class ProcessarArquivoStepConfig {

    @Bean
    public Step processarArquivoStep(JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager,
                                     FlatFileItemReader<ConteudoLinha> leituraArquivoTxt,
                                     ItemProcessor<ConteudoLinha, Map<String, Calculo>> processCalculoMedia,
                                     ItemWriter<Map<String, Calculo>> imprimeWriter) {
        return new StepBuilder("processarArquivoStep", jobRepository)
                .<ConteudoLinha, Map<String, Calculo>>chunk(1000000, transactionManager)
                .reader(leituraArquivoTxt)
                .processor(processCalculoMedia)
                .writer(imprimeWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("Thread N -> :");
        return executor;
    }

}

