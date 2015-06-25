package com.example.leonardotalero.clases;

import java.text.Normalizer;

/**
 * Created by leonardotalero on 6/22/15.
 */
public class PaisPrima {



    public  String nombre;
    public float prima;

    public  final static String continente="Europa";
    public final static PaisPrima paisreferencia=new PaisPrima("Alemania",0.0f);


    public PaisPrima(String nombrepais) {
        this.nombre = nombrepais;
        this.prima = 0.0f;
    }

    public PaisPrima(String nombrepais, float primapais) {
        this.nombre = nombrepais;
        this.prima = primapais;
    }


    public String nombreURL(){

      return Normalizer.normalize(this.nombre,Normalizer.Form.NFD);
    }

    @Override
    public String toString(){
        return nombre +"\n \t" +prima;
    }
}
