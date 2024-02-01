package com.mentoria.desafiofinal.impl;

import java.text.DecimalFormat;

public class Calculo {

    Double numMin, numMax, media;

    public Calculo() {
    }

    public String toString() {
        return getNumMin() + "/" + getMedia() + "/" + getNumMax();
    }

    public Double getNumMin() {
        return this.numMin;
    }

    public void setNumMin(Double numMin) {
        this.numMin = numMin;
    }

    public Double getNumMax() {
        return this.numMax;
    }

    public void setNumMax(Double numMax) {
        this.numMax = numMax;
    }

    public Double getMedia() {
        this.media = (this.numMax + this.numMin) / 2.0;
        DecimalFormat formato = new DecimalFormat("##.##");
        return Double.valueOf(formato.format(media).replace(',', '.'));
    }
}
