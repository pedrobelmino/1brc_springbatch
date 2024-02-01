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

            String chave = conteudoLinha.getNomeCidade();
            Double novoValor = conteudoLinha.getTemperatura();

            if (!mapa.getMapa().containsKey(conteudoLinha.getNomeCidade())) {
                Calculo novoCalculo = new Calculo();
                novoCalculo.setNumMin(novoValor);
                novoCalculo.setNumMax(novoValor);
                mapa.adicionarAoMapa(chave, novoCalculo);
            } else {
                Calculo calculo = mapa.getMapa().get(chave);
                calculo.setNumMax(Math.max(novoValor, calculo.getNumMax()));
                calculo.setNumMin(Math.min(novoValor, calculo.getNumMin()));
            }

            return mapa.getMapa();
        };

    }
}
