package imd0412.parkinglot.calculator;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.Constants;


@RunWith(Parameterized.class)
public class ConversorDataServiceTest {

	@Parameters(name = "{0}_ShouldReturn_{1}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
			{ "2017.11.30 10:30", "Data valida"},
		});
	}
	
	@Parameter(0)
	public String data;
	
	@Parameter(1)
	public String retorno;
	
	@Test
	public void test() {
		
		LocalDateTime dataTeste = ConversorDataService.converterString(data);
		assertTrue(LocalDateTime.parse(data, Constants.DATE_FORMATTER).equals(dataTeste));
		
	}

}
