package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Conta;

public class CsvReaderService {
	
	String csvSeparadorCampo = ";";

	public List<Conta> getContasFromCSV(String csvArquivo){
		List<Conta> listaContas = new ArrayList<Conta>();
		
		BufferedReader conteudoCsv = null;
	    String linha = "";
	    
		try {
			conteudoCsv = new BufferedReader(new FileReader(csvArquivo));
			
			while ((linha = conteudoCsv.readLine()) != null) {
				String[] conta = linha.split(csvSeparadorCampo);
				
				listaContas.add(new Conta(conta[0], conta[1], Double.valueOf(conta[2]), conta[3], null));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaContas;
	}

}
