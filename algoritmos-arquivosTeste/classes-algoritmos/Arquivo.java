/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Arquivo {
    String valorString;
    double valor;
    Scanner leitor;
    
    public Arquivo(String caminho) throws FileNotFoundException{
        this.leitor = new Scanner(new File(caminho));
        this.valorString = this.leitor.nextLine();
        this.valorString = valorString.replace(",", ".");
        this.valor = Double.parseDouble(valorString);
    }   
    
    public double getValor(){
        return valor;
    }
    
    public void nextLine(){
        if(leitor.hasNextLine()){
            this.valorString = this.leitor.nextLine();
            this.valorString = valorString.replace(",", ".");
            this.valor = Double.parseDouble(valorString);
        }
        else{
            this.valor = -1;
        }
    }
    
    public void close(){
        this.leitor.close();
    }
}
