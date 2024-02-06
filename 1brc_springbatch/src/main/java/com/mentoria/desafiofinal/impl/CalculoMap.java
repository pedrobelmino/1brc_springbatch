package com.mentoria.desafiofinal.impl;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
public class CalculoMap {

    private final Map<String, Calculo> mapa = new HashMap<>();

    public Map<String, Calculo> getMapa() {
        return mapa;
    }

    public void adicionarAoMapa(String chave, Calculo calculo) {
        mapa.put(chave, calculo);
    }
}
