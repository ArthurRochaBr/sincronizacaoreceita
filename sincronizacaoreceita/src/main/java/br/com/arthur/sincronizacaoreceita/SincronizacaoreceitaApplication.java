package br.com.arthur.sincronizacaoreceita;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.Conta;
import service.CsvReaderService;
import service.CsvWriterService;
import service.ReceitaService;

@SpringBootApplication
public class SincronizacaoreceitaApplication {
	

	public static void main(String[] args) throws RuntimeException, InterruptedException {
		SpringApplication.run(SincronizacaoreceitaApplication.class, args);
		
	    validarContas("C:\\Users\\Arthur\\java\\arquivo.csv", "C:\\Users\\Arthur\\java\\resultado.csv");
	    validarContas("C:\\Users\\Arthur\\java\\arquivo2.csv", "C:\\Users\\Arthur\\java\\resultado.csv");
	    validarContas("C:\\Users\\Arthur\\java\\arquivo3.csv", "C:\\Users\\Arthur\\java\\resultado.csv");
	    validarContas("C:\\Users\\Arthur\\java\\arquivo4.csv", "C:\\Users\\Arthur\\java\\resultado.csv");
	    
	}
	
	public static void validarContas(String csvArquivoEntrada, String csvArquivoSaida) throws RuntimeException, InterruptedException {
		ReceitaService receitaService = new ReceitaService();
	    	    
	    CsvReaderService csvReaderService = new CsvReaderService();
	    List<Conta> listaContas = csvReaderService.getContasFromCSV(csvArquivoEntrada);

	    for(int i = 0; i < 5; i++) {
	    	Conta conta = listaContas.get(i);
	    	boolean resultado = receitaService.atualizarConta(conta.getAgencia(), conta.getConta().replace("-", ""), conta.getSaldo(), conta.getStatus());
			
	    	listaContas.get(i).setResultado(resultado ? "1" : "0");
	    }
	    
	    CsvWriterService csvWriterService = new CsvWriterService();
	    csvWriterService.writeContaToCSV(listaContas, csvArquivoSaida);
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS");
	    Calendar cal = Calendar.getInstance();
	    System.out.println(dateFormat.format(cal.getTime()) + " - Fim - " + csvArquivoEntrada);
	}

}