package br.com.pedrobelmino.onebicsv.sbatch.reader;

import br.com.pedrobelmino.onebicsv.sbatch.impl.ConteudoLinha;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class LeituraArquivoConfig {
    @Bean
    public FlatFileItemReader<ConteudoLinha> leituraArquivo() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<ConteudoLinha>()
                .name("leituraArquivoTxtReader")
                .resource(new FileUrlResource("/home/user/dev/measurements.txt"))
                .delimited()
                .delimiter(";")
                .names( new String[] {"nomeCidade", "temperatura"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(ConteudoLinha.class);
                }})
                .build();
    }




}
