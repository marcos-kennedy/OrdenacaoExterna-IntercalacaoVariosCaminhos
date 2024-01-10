/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ordenacaoexterna;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OrdenacaoExterna {

    public static void main(String[] args) throws FileNotFoundException, IOException {
       LeitorArquivo leitor = new LeitorArquivo();
       leitor.distribuir("ordExt_teste.txt");
       leitor.intercalar("arqTemporarios");
    }
}
