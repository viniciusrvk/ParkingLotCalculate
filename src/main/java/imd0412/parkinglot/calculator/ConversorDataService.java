package imd0412.parkinglot.calculator;

import java.time.LocalDateTime;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;

public class ConversorDataService {
	
	public static final String FORMTO_INVALIDO = "Formato da data não suportado";
	public static final String DATA_NAO_EXISTE = "Data inexistente";
	
	public static LocalDateTime converterString(String dataString) throws DateFormatException, InvalidDataException {
		
		LocalDateTime data = null;
		
		dataValida(dataString);
		data = LocalDateTime.parse(dataString, Constants.DATE_FORMATTER);
		
		return data;

	}
	
	public static void dataValida(String dataString) throws InvalidDataException, DateFormatException {
		
		estaNoFomato(dataString);
		valoresValidos(dataString);
		verificarLimites(dataString);
        
    }

	private static void estaNoFomato(String dataString) throws DateFormatException, InvalidDataException {
		
		if (dataString.length() != 16) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		if (dataString.split(" ").length != 2) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		String data = dataString.split(" ")[0];
		String hora = dataString.split(" ")[1];
		
		verificadarValoresDataSaoDigitos(data);
		verificarValoresHoraMinutoSaoDigitos(hora);
		
	}

	private static void verificarValoresHoraMinutoSaoDigitos(String horaMinuto) throws DateFormatException {
		
		if (horaMinuto.split(":").length != 2) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		String horas = horaMinuto.split(":")[0];
		String minutos = horaMinuto.split(":")[1];
		
		if (!horas.chars().allMatch(Character::isDigit) ) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		if (!minutos.chars().allMatch(Character::isDigit) ) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
	}

	private static void verificadarValoresDataSaoDigitos(String data) throws DateFormatException, InvalidDataException {
		
		if (data.split("\\.").length != 3) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		String ano = data.split("\\.")[0];
		String mes = data.split("\\.")[1];
		String dia = data.split("\\.")[2];
		
		if (!ano.chars().allMatch(Character::isDigit) ) {
			throw new InvalidDataException(InvalidDataType.InvalidYear.toString(), InvalidDataType.InvalidYear);
		}
		
		if (!mes.chars().allMatch(Character::isDigit) ) {
			throw new InvalidDataException(InvalidDataType.InvalidMonth.toString(), InvalidDataType.InvalidMonth);
		}
		
		if (!dia.chars().allMatch(Character::isDigit) ) {
			throw new InvalidDataException(InvalidDataType.InvalidDay.toString(), InvalidDataType.InvalidDay);
		}
	}

	private static void verificarLimites(String dataTeste) throws InvalidDataException, DateFormatException {
		
		String data = dataTeste.split(" ")[0];
		String hora = dataTeste.split(" ")[1];
		
		int ano = Integer.parseInt(data.split("\\.")[0]);
		int mes = Integer.parseInt(data.split("\\.")[1]);
		int dia = Integer.parseInt(data.split("\\.")[2]);
		
		int horas = Integer.parseInt(hora.split(":")[0]);
		int minutos = Integer.parseInt(hora.split(":")[1]);
		
		if (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			throw new InvalidDataException(DATA_NAO_EXISTE, InvalidDataType.NonexistentDate);
		}
		
		if (anoBissexto(ano)) {
			if (mes == 2 && dia > 29) {
				throw new InvalidDataException(DATA_NAO_EXISTE, InvalidDataType.NonexistentDate);
			}
		} else {
			if (mes == 2 && dia > 28) {
				throw new InvalidDataException(DATA_NAO_EXISTE, InvalidDataType.NonexistentDate);
			}
		}
		
		if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
			throw new DateFormatException(FORMTO_INVALIDO);
		}
		
		
	}



	private static void valoresValidos(String dataTeste) throws InvalidDataException {
		
		String data = dataTeste.split(" ")[0];
		
		int ano = Integer.parseInt(data.split("\\.")[0]);
		int mes = Integer.parseInt(data.split("\\.")[1]);
		int dia = Integer.parseInt(data.split("\\.")[2]);
		
		if (ano < 1970 || ano > 2019) {
			throw new InvalidDataException(InvalidDataType.InvalidYear.toString(), InvalidDataType.InvalidYear);
		}
		
		if (mes < 1 || mes > 12) {
			throw new InvalidDataException(DATA_NAO_EXISTE, InvalidDataType.NonexistentDate);
		}
		
		if (dia < 1 || dia > 31) {
			throw new InvalidDataException(DATA_NAO_EXISTE, InvalidDataType.NonexistentDate);
		}
	}



	private static boolean anoBissexto(int ano) {
		
		if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
			return true;
		}
		return false;
	}
	
	

}
