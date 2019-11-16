package imd0412.parkinglot.calculator;

import java.time.LocalDate;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;

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
		
		CalculatorService service = new CalculatorService();
		
		LocalDate dataEntrada = service.converterString(checkin);
		LocalDate dataSaida = service.converterString(checkout);
		
		return null;
	}
}
