package imd0412.parkinglot.calculator;

import java.time.Duration;
import java.time.LocalDateTime;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;

public class Calculator {
	/**
	 * Calculates the staying cost in the parking lot.
	 * 
	 * @param checkin
	 *            String representing check-in date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param checkout
	 *            String representing check-out date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param type
	 * @return
	 * @throws InvalidDataException 
	 * @throws DateFormatException 
	 */
	Float calculateParkingCost(String checkin, String checkout,
			ParkingLotType type) throws DateFormatException, InvalidDataException {
		
		LocalDateTime entrada = ConversorDataService.converterString(checkin);
		LocalDateTime saida = ConversorDataService.converterString(checkout);
		
		verificarCheckinCheckout(entrada, saida);
		Float custo = calulcarCusto(entrada, saida, type);
		
		return custo;
	}

	private Float calulcarCusto(LocalDateTime entrada, LocalDateTime saida, ParkingLotType type) {
		
		Float custo = 0F;
		
		long horasEstacionado = horasEstacionado(entrada, saida);
		TermCostCalculator term = termCost(type);
		custo = term.calcular(horasEstacionado);
		
		return custo;
	}

	private TermCostCalculator termCost(ParkingLotType type) {
		
		TermCostCalculator term = null;
		switch (type) {
			case ShortTerm :
				term = new ShortTermCostCalculator();
				break;
			case LongTerm :
				term = new LongTermCostCalculator();
				break;
			case VIP :
				term = new VipTermCostCalculator();
				break;
		}
		
		return term;
	}

	private long minutosPassadoHoraAtual(LocalDateTime entrada, LocalDateTime saida) {
		return Duration.between(entrada, saida).toMinutes() %60;
	}

	private long horasEstacionado(LocalDateTime entrada, LocalDateTime saida) {

		long horas = Duration.between(entrada, saida).toMinutes() / 60;
		
		long minutosPassados = minutosPassadoHoraAtual(entrada, saida);
		if (minutosPassados > 0) {
			horas++;	// Já passou o 1 minutos desde que completou a hora, então deve ser paga
		}
		
		return horas;
	}

	private void verificarCheckinCheckout(LocalDateTime entrada, LocalDateTime saida) throws InvalidDataException {
		if (entrada.isAfter(saida)) {
			throw new InvalidDataException("", InvalidDataType.InvalidDay);
		}
			
	}
}
