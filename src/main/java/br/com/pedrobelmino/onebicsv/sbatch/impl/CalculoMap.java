package br.com.pedrobelmino.onebicsv.sbatch.impl;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CalculoMap {

    private final Map<String, Calculo> mapa = new ConcurrentHashMap<>();

    public Map<String, Calculo> getMapa() {
        return mapa;
    }

}
