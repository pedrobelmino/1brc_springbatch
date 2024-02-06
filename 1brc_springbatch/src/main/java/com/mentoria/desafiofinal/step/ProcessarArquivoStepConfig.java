package com.mentoria.desafiofinal.step;

import com.mentoria.desafiofinal.impl.Calculo;
import com.mentoria.desafiofinal.impl.ConteudoLinha;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;


@Configuration
public class ProcessarArquivoStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step processarArquivoStep(FlatFileItemReader<ConteudoLinha> leituraArquivoTxt,
                                     ItemProcessor<ConteudoLinha, Map<String, Calculo>> processCalculoMedia,
                                     ItemWriter<Map<String, Calculo>> imprimeWriter) {
        return stepBuilderFactory
                .get("processarArquivoStep")
                .<ConteudoLinha, Map<String, Calculo>>chunk(1000000)
                .reader(leituraArquivoTxt)
                .processor(processCalculoMedia)
                .writer(imprimeWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(10);
        return executor;
    }

}

