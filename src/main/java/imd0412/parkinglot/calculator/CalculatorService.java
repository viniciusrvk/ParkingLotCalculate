package imd0412.parkinglot.calculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;

public class CalculatorService {
	
	String formatoEntrada = "yyyy.MM.dd HH:mm";
	
	public LocalDate converterString(String dataString) throws DateFormatException, InvalidDataException {
		
		dataValida(dataString);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoEntrada);
		LocalDate data = LocalDate.parse(dataString, formatter);
		
		return data;
	}
	
	public void dataValida(String dataString) throws InvalidDataException, DateFormatException {
		
		estaNoFomato(dataString);
		valoresValidos(dataString);
		verificarDiasdosMes(dataString);
        
    }

	private void estaNoFomato(String dataString) throws DateFormatException {
		
		try {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoEntrada);
			LocalDate dataTeste = LocalDate.parse(dataString, formatter);
		
		} catch (DateTimeParseException dataException) {
			throw new DateFormatException();
		}
		
	}

	private void verificarDiasdosMes(String dataTeste) throws InvalidDataException {
		
		String data = dataTeste.split(" ")[0];
		
		int ano = Integer.parseInt(data.split("\\.")[0]);
		int mes = Integer.parseInt(data.split("\\.")[1]);
		int dia = Integer.parseInt(data.split("\\.")[2]);
		
		if (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			throw new InvalidDataException(InvalidDataType.NonexistentDate);
		}
		
		if (anoBissexto(ano)) {
			if (mes == 2 && dia > 29) {
				throw new InvalidDataException(InvalidDataType.NonexistentDate);
			}
		} else {
			if (mes == 2 && dia > 28) {
				throw new InvalidDataException(InvalidDataType.NonexistentDate);
			}
		}
		
	}



	private void valoresValidos(String dataTeste) throws InvalidDataException {
		
		String data = dataTeste.split(" ")[0];
		
		int ano = Integer.parseInt(data.split("\\.")[0]);
		int mes = Integer.parseInt(data.split("\\.")[1]);
		int dia = Integer.parseInt(data.split("\\.")[2]);
		
		if (ano < 1970 || ano > 2019) {
			throw new InvalidDataException(InvalidDataType.InvalidYear);
		}
		
		if (mes < 1 || mes > 12) {
			throw new InvalidDataException(InvalidDataType.InvalidMonth);
		}
		
		if (dia < 1 || dia > 31) {
			throw new InvalidDataException(InvalidDataType.InvalidDay);
		}
	}



	private boolean anoBissexto(int ano) {
		
		if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
			return true;
		}
		return false;
	}
	
	

}
