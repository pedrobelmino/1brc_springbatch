package com.mentoria.desafiofinal.writer;

import com.mentoria.desafiofinal.impl.Calculo;
import com.mentoria.desafiofinal.impl.CalculoMap;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ImprimeWriterConfig {


    @Autowired
    private CalculoMap mapa;

    @Bean
    public ItemWriter<Map<String, Calculo>> imprimeWriter() {
        AtomicInteger count = new AtomicInteger(0);
        return items -> {

            System.out.println(count.incrementAndGet());
            if (!items.isEmpty()) {
                System.out.println(mapa.getMapa());
            }
        };
    }

}


