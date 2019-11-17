package imd0412.parkinglot.calculator;

import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
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
			{ "2017.11.30 10:30", LocalDateTime.parse("2017.11.30 10:30", Constants.DATE_FORMATTER)},
			{ "1970.01.01 00:00", LocalDateTime.parse("1970.01.01 00:00", Constants.DATE_FORMATTER)},
			{ "1970.01.01 00:01", LocalDateTime.parse("1970.01.01 00:01", Constants.DATE_FORMATTER)},
			{ "2019.12.31 23:58", LocalDateTime.parse("2019.12.31 23:58", Constants.DATE_FORMATTER)},
			{ "2019.12.31 23:59", LocalDateTime.parse("2019.12.31 23:59", Constants.DATE_FORMATTER)},
			{ "2012.02.29 00:00", LocalDateTime.parse("2012.02.29 00:00", Constants.DATE_FORMATTER)},
			{ "2004.02.29 23:59", LocalDateTime.parse("2004.02.29 23:59", Constants.DATE_FORMATTER)},
		});
	}
	
	@Parameter(0)
	public String data;
	
	@Parameter(1)
	public LocalDateTime retorno;
	
	@Test
	public void test() {
		
		
		try {
			LocalDateTime dataTeste = ConversorDataService.converterString(data);
			assertTrue(dataTeste.equals(retorno));
		} catch (Exception e) {
			Assert.fail("Test Error.");
		}
		
		
	}

}
