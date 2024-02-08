package br.com.pedrobelmino.onebicsv.sbatch.writer;

import br.com.pedrobelmino.onebicsv.sbatch.impl.Calculo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ImprimeWriterConfig {

    AtomicInteger atomicInteger = new AtomicInteger(0);
    long tempoInicial = System.currentTimeMillis();

    @Bean
    public ItemWriter<Map.Entry<String, Calculo>> imprimeWriter() {

        return items -> {
            int chunk = atomicInteger.incrementAndGet();
            int quant = chunk*1000000;
            if(quant%4000000==0){
                System.out.println("Thread "+Thread.currentThread().getName()+ " | quantidade "+(quant)+" | tempo em segundos "+(System.currentTimeMillis()-tempoInicial)/1000);
            }

        };
    }

}


