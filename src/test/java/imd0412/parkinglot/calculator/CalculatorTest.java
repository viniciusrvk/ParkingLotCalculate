package imd0412.parkinglot.calculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;

@RunWith(Parameterized.class)
public class CalculatorTest
{
	@Parameters(name = "{0}_{1}_ShouldReturn_{3}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
			{ "2017.11.30 10:30", "2017.11.30 11:00", ParkingLotType.ShortTerm, 8F},
			{ "2017.11.30 10:30", "2017.11.30 11:30", ParkingLotType.ShortTerm, 8F},
			{ "2018.01.12 10:30", "2018.01.12 11:31", ParkingLotType.ShortTerm, 10F},
			{ "2017.03.15 11:30", "2017.03.16 05:45", ParkingLotType.ShortTerm, 44F},		// 18 horas e 15 min = (8 + ((19-1)*2))
			{ "2018.01.12 10:30", "2018.01.13 10:30", ParkingLotType.ShortTerm, 54F},		// 24 horas (8 + ((24 - 1) *2) )
			{ "2018.01.12 10:30", "2018.01.13 10:31", ParkingLotType.ShortTerm, 106F},		// 24 horas e 1 min = (8 + (25-1)*2 + 50)
			{ "2019.01.12 10:30", "2019.01.16 07:15", ParkingLotType.ShortTerm, 342F},		// 3 dias, 20 horas e 45 minutos = (8 + ((93-1)*2) + 50 + 50 + 50)
			{ "2019.01.01 10:30", "2019.01.08 10:30", ParkingLotType.ShortTerm, 692F},		// 7 dias = 8 + ((168-1)*2) + 7 * 50
			{ "2019.01.01 10:30", "2019.01.08 10:31", ParkingLotType.ShortTerm, 694F},		// 7 dias e 1 minutos = (8 + ((169-1)*2) + 7*50)
			{ "2019.01.01 10:30", "2019.01.09 10:30", ParkingLotType.ShortTerm, 770F},		// 8 dias = (8 + ((169-1)*2) + 7*50 + 30)
		});
	}
	
	@Parameter(0)
	public String checkin;
	
	@Parameter(1)
	public String checkout;
	
	@Parameter(2)
	public ParkingLotType type;
	
	@Parameter(3)
	public Float value;
	
	@Test
	public void test() {		
		
		try {
			
			Calculator calulator = new Calculator();
			Float cost = calulator.calculateParkingCost(checkin, checkout, type);
			assertThat(value, is(equalTo(cost)));
			
		} catch (Exception e) {
			Assert.fail("Test Error.");
		}
		
	}
}
