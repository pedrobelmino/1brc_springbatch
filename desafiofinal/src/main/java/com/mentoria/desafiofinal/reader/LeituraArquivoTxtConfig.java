package com.mentoria.desafiofinal.reader;

import com.mentoria.desafiofinal.impl.ConteudoLinha;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class LeituraArquivoTxtConfig {
    @StepScope
    @Bean
    public FlatFileItemReader<ConteudoLinha> leituraArquivoTxt() {
        return new FlatFileItemReaderBuilder<ConteudoLinha>()
                .name("leituraArquivoTxtReader")
                .resource(new ClassPathResource("measurements1.txt"))
                .delimited()
                .delimiter(";")
                .names( new String[] {"nomeCidade", "temperatura"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(ConteudoLinha.class);
                }})
                .build();
    }


}




