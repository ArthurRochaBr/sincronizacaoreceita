package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import model.Conta;

public class CsvWriterService {

	private static final String CSV_SEPARATOR = ";";
	 
    public static void writeContaToCSV(List<Conta> listaContas, String csvArquivoSaida) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvArquivoSaida), "UTF-8"));
            for (Conta conta : listaContas){
            	//agencia;conta;saldo;status;resultado
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(conta.getAgencia());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(conta.getConta());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(conta.getSaldo());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(conta.getStatus());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(conta.getResultado());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
}
