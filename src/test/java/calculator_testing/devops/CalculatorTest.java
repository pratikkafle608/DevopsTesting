package calculator_testing.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest {
	private Calculator calculator;
	private static int count = 0;
	private static String userName ="pkafle";
	
	@BeforeAll
	static void beforeAllTest() {
		System.out.printf("[Before All] Calculator Test suite starting .... by %s\n", userName);
	}
	
	@BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		calculator = new Calculator();
		System.out.printf("[Before Each] Starting Test #%d: %s\n", ++count, testInfo.getDisplayName());
	}
	
	//Test method using MethodSource for add
	@ParameterizedTest(name="{0}+{1}={2}")
    @DisplayName("Add two numbers")
    @MethodSource("privateAddData")
    public void add_twoNumbers(int input1, int input2, int expected) {
        
        int result = calculator.add(input1, input2);
        
        assertEquals(expected, result);
    } 
	static Stream<Arguments> privateAddData() {
		
		return Stream.of(
				Arguments.of(100,2,102),
				Arguments.of(100,-2,98),
				Arguments.of(-100,2,-98),
				Arguments.of(-100,-2,-102)
				);
	}
	
	//Test method using CsvSource for substract
	@ParameterizedTest(name="{0}-{1}={2}")
    @DisplayName("Substract two numbers")
    @CsvSource({
    		"100, 2, 98",
    		"100,-2, 102",
    		"-100,2, -102",
    		"-100,-2,-98"
    }
    		)
    public void substract_twoNumbers(int input1, int input2, int expected) {
       
        
        int result = calculator.substract(input1, input2);
        
        assertEquals(expected, result);
    }
	
	
	//Test method using CsvFileSrouce for multiple
	@ParameterizedTest(name="{0}*{1}={2}")
    @DisplayName("Multiply two numbers")
    @CsvFileSource(resources = "/data/multiple.csv")
    public void multiple_twoNumbers(int input1, int input2, int expected) {
        
        
        int result = calculator.multiple(input1, input2);
        
        assertEquals(expected, result);
    }
	
	
//	@ParameterizedTest(name="{0}/{1}={2}")
  //  @DisplayName("Divide two numbers")
   // @CsvSource({
    //		"100, 2, 98",
    	//	"100,-2, 102",
    //		"-100,2, -102",
    //		"-100,-2,-98"
    //}
    	//	)
	
   // public void divi(int input1, int input2, int expected) {
     //   Calculator calculator = new Calculator();
        
       // assertThrows(IllegalArgumentException.class, () -> calculator.divide(10,0));
        
        //int result = calculator.divide(input1, input2);
        
       // assertEquals(expected, result);
    //}

	@Test
	public void divide_byZero() {
	   
	    assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
	}
	
	
	@AfterEach
	void afterEachTest(TestInfo testInfo) {
		System.out.printf("[After Each] Finished Test #%d: %s\n\n", count, testInfo.getDisplayName());
	}
	
	@AfterAll
	static void afterAllTests() {
		System.out.printf("[After All] completed %d test invocations by %s\n",count, userName);
	}
}

