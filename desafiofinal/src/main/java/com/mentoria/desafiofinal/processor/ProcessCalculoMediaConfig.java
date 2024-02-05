package com.mentoria.desafiofinal.processor;

import com.mentoria.desafiofinal.impl.Calculo;
import com.mentoria.desafiofinal.impl.CalculoMap;
import com.mentoria.desafiofinal.impl.ConteudoLinha;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.TreeMap;

@Configuration
public class ProcessCalculoMediaConfig {

    @Autowired
    private CalculoMap mapa;

    @Bean
    public ItemProcessor<ConteudoLinha, Map<String, Calculo>> processCalculoMedia() {

        return conteudoLinha -> {

            if (!mapa.getMapa().containsKey(conteudoLinha.getNomeCidade())) {
                Calculo novoCalculo = new Calculo();
                novoCalculo.setNumMin(conteudoLinha.getTemperatura());
                novoCalculo.setNumMax(conteudoLinha.getTemperatura());
                mapa.adicionarAoMapa(conteudoLinha.getNomeCidade(), novoCalculo);
            } else {
                Calculo calculo = mapa.getMapa().get(conteudoLinha.getNomeCidade());
                calculo.setNumMax(Math.max(conteudoLinha.getTemperatura(), calculo.getNumMax()));
                calculo.setNumMin(Math.min(conteudoLinha.getTemperatura(), calculo.getNumMin()));
            }

            return mapa.getMapa();
        };

    }
}
