package com.br.mhubaccelerator.domain;

import java.io.Serializable;

/**
 * Created by Leeo on 16/05/2017.
 */

public class Acelerometro implements Serializable {

    private static final long serialVersionUID = 6093226637618022646L;

    private float eixoX;
    private float eixoY;
    private float eixoZ;

    public float getEixoX() {
        return eixoX;
    }

    public void setEixoX(float eixoX) {
        this.eixoX = eixoX;
    }

    public float getEixoY() {
        return eixoY;
    }

    public void setEixoY(float eixoY) {
        this.eixoY = eixoY;
    }

    public float getEixoZ() {
        return eixoZ;
    }

    public void setEixoZ(float eixoZ) {
        this.eixoZ = eixoZ;
    }
}
