package com.mentoria.desafiofinal.impl;

import lombok.Data;

import java.text.DecimalFormat;

@Data
public class Calculo {

    Double numMin, numMax, media;

    public Calculo() {
    }

    public String toString() {
        return getNumMin() + "/" + getMedia() + "/" + getNumMax();
    }


    public Double getMedia() {
        this.media = (this.numMax + this.numMin) / 2.0;
        DecimalFormat formato = new DecimalFormat("##.##");
        return Double.valueOf(formato.format(media).replace(',', '.'));
    }
}
