package br.com.pedrobelmino.onebicsv.sbatch.step;

import br.com.pedrobelmino.onebicsv.sbatch.impl.Calculo;
import br.com.pedrobelmino.onebicsv.sbatch.impl.CalculoMap;
import br.com.pedrobelmino.onebicsv.sbatch.impl.ConteudoLinha;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;


@Configuration
public class ProcessarArquivoStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step processarArquivoStep(FlatFileItemReader<ConteudoLinha> leituraArquivoTxt,
                                     ItemProcessor<ConteudoLinha, Map.Entry<String, Calculo>> processCalculoMedia,
                                     ItemWriter<Map.Entry<String, Calculo>> imprimeWriter) {
        return stepBuilderFactory
                .get("processarArquivoStep")
                .<ConteudoLinha, Map.Entry<String, Calculo>>chunk(1000000)
                .reader(leituraArquivoTxt)
                .processor(processCalculoMedia)
                .writer(imprimeWriter)
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public Step imprimirStep(CalculoMap calculoMap) {
        return stepBuilderFactory
                .get("imprimirStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println( calculoMap.getMapa());
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }



    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("SimpleTask");
    }

}

