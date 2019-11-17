package imd0412.parkinglot.calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;


@RunWith(Parameterized.class)
public class ConversorDataServiceExceptionTest {

	@Parameters(name = "{0}_ShouldReturn_{1}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
			{ "2017/11/30 10:30", DateFormatException.class, ConversorDataService.FORMTO_INVALIDO},
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
