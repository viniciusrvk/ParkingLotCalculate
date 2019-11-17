package imd0412.parkinglot.calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;

@RunWith(Parameterized.class)
public class ConversorDataServiceExceptionTest { 

	@Parameters(name = "{0}_ShouldReturn_{1}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
				{ "2017/11/30 10:30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO },
				{ "2019.02.29 01:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "2016.02.30 10:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "201B.12.30 10:30", InvalidDataException.class, InvalidDataType.InvalidYear.toString() },
				{ "2018.a2.30 10:30", InvalidDataException.class, InvalidDataType.InvalidMonth.toString() },
				{ "2018.03.yy 05:30", InvalidDataException.class, InvalidDataType.InvalidDay.toString() },
				{ "201.12.30 10:30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO },
				{ "0201.12.30 10:30", InvalidDataException.class, InvalidDataType.InvalidYear.toString() },
				{ "2021.12.30 10:30", InvalidDataException.class, InvalidDataType.InvalidYear.toString() },
				{ "1969.12.31 23:59", InvalidDataException.class, InvalidDataType.InvalidYear.toString() },
				{ "2020.01.01 00:00", InvalidDataException.class, InvalidDataType.InvalidYear.toString() },
				{ "2019.13.30 10:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "2019.00.30 10:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "2019.05.00 23:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "1995.11.32 10:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "1995.04.31 10:30", InvalidDataException.class, ConversorDataService.DATA_NAO_EXISTE },
				{ "1995.04.3010:30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO },
				{ "1995.04.30 10 30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO },

				
				{ "1995.04.30 1a:30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO}, 
				{ "2016.07.12 00:3$", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO},
				{ "2016.07.12 00:60", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO},
				{ "2016.07.12 24:35", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO},
				

		});
	}

	@Parameter(0)
	public String data;

	@Parameter(1)
	public Class<? extends Exception> expectedExceptionType;

	@Parameter(2)
	public String expectedErrorMessage;

	@Test
	public void test() {

		try {
			ConversorDataService.converterString(data);
			Assert.fail("Expected a instance of " + expectedExceptionType.getClass());
		} catch (Exception e) {
			assertThat(e, instanceOf(expectedExceptionType));
			assertThat(e.getMessage(), is(expectedErrorMessage));
		}

	}

}
