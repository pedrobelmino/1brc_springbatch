package br.com.pedrobelmino.onebicsv.sbatch.step;

import br.com.pedrobelmino.onebicsv.sbatch.impl.Calculo;
import br.com.pedrobelmino.onebicsv.sbatch.impl.CalculoMap;
import br.com.pedrobelmino.onebicsv.sbatch.impl.ConteudoLinha;
import lombok.Data;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;


@Configuration
public class ProcessarArquivoStepConfig {

    @Bean
    public Step processarArquivoStep(JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager,
                                     FlatFileItemReader<ConteudoLinha> leituraArquivoTxt,
                                     ItemProcessor<ConteudoLinha, Map.Entry<String, Calculo>> processCalculoMedia,
                                     ItemWriter<Map.Entry<String, Calculo>> imprimeWriter, AsyncTaskExecutor asyncTaskExecutor) {
        return new StepBuilder("step", jobRepository)
                .<ConteudoLinha, Map.Entry<String, Calculo>>chunk(1000000, transactionManager)
                .reader(leituraArquivoTxt)
                .processor(processCalculoMedia)
                .writer(imprimeWriter)
                .taskExecutor(asyncTaskExecutor)
                .build();
    }


    @Bean
    public Step imprimirStep(CalculoMap calculoMap, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("imprimirStep", jobRepository )
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println( calculoMap.getMapa());
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
    }




}

