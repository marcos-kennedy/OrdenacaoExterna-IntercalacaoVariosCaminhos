/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LeitorArquivo {
    int contPastas = 1;
    
    public void distribuir(String caminho){
        double linha;
        int contArquivos = 1;
        
        try {
            File arquivoTeste = new File(caminho);
            Scanner leitura = new Scanner(arquivoTeste);
            
            while(leitura.hasNextLine()){
                ArrayList array = new ArrayList();
                
                for (int i = 0; i < 100 && leitura.hasNextLine(); i++) {
                    linha = Double.parseDouble(leitura.nextLine());
                    array.add(linha);
                }
                
                Collections.sort(array);
           
                File arqTemporarios = new File("arqTemporarios");
                arqTemporarios.mkdir();
                
                File subArqTemporarios = new File(arqTemporarios, "arqTemporario"+contPastas);
                subArqTemporarios.mkdir();
                
                File arquivos = new File(subArqTemporarios,  "arq"+contArquivos);
                
                try (FileWriter fileWriter = new FileWriter(arquivos, false)) {
                    for(int i=0; i<array.size(); i++){
                        fileWriter.write(String.valueOf(array.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.flush();
                }
                contArquivos++;
            }
            contPastas++;
            
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public void intercalar(String diretorio) throws FileNotFoundException, IOException{  
        int contArquivos = 1;
        File[] subDiretorios = null;
        ArrayList<File> listaSubDiretorios = new ArrayList<>();
        File[] vetArquivos = null;
        File subArqTemporarios = null ;
        int cont = 1;
        
        try {
            File dir = new File(diretorio);
            if (dir.isDirectory()) {
                subDiretorios = dir.listFiles(File::isDirectory);
                listaSubDiretorios.addAll(Arrays.asList(subDiretorios));
            } else {
                System.out.println("O caminho fornecido não é um diretório válido.");
            }
            
            while(cont == 1 || vetArquivos.length > 1 ){
                if(cont == 1){
                    vetArquivos = listaSubDiretorios.get(listaSubDiretorios.size()-1).listFiles();
                }
            
                Arrays.sort(vetArquivos,Comparator.comparing(File::getName)); //ordena o vetor de arquivos pelo nome dos arq.
                ArrayList<Arquivo> arquivos = new ArrayList<>();
                Comparator<Arquivo> comparador = Comparator.comparingDouble(Arquivo::getValor);
                // 'comparador' construído para comparar os arquivos com base no valor retornado pelo método getValor().
                
                for(int i = 0; i<vetArquivos.length; i+=10){
                    for (int j = i; j < Math.min(i + 10, vetArquivos.length); j++) {
                        arquivos.add(new Arquivo(vetArquivos[j].getPath()));
                    }
                    
                    while(!arquivos.isEmpty()){
                        Collections.sort(arquivos, comparador); //valores ordenados na lista

                        File pasta = new File("arqTemporarios");

                        subArqTemporarios = new File(pasta, "arqTemporario"+contPastas);
                        subArqTemporarios.mkdir();
                        
                        File arquivo = new File(subArqTemporarios, "arqTemporario"+contArquivos+".txt");

                        try (FileWriter escrever = new FileWriter(arquivo, true)) {
                            if(arquivos.get(0).valor != -1){
                                DecimalFormat formato = new DecimalFormat("0.00000000000000000000");
                                String valorFormatado = formato.format(arquivos.get(0).valor);
                                escrever.write(valorFormatado + "\n");
                                escrever.flush();
                                arquivos.get(0).nextLine();
                                System.out.println(arquivos.get(0).valor);
                            }
                            
                        }
                        catch(IOException e) {
                            e.getMessage();
                        }
                    }
                    contArquivos++;
                }
                contPastas++;
                cont++;
                listaSubDiretorios.remove(0);
                listaSubDiretorios.add(subArqTemporarios);
                vetArquivos = listaSubDiretorios.get(listaSubDiretorios.size()-1).listFiles();
            } 
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
