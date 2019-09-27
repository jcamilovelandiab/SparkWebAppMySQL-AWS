package edu.escuelaing.arem.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest{

	@Test
    public void squareTest1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("test1.txt"));
        String str = null;
        while((str = br.readLine()) != null) {
            Integer number = Integer.parseInt(str);
            assertTrue(MathServices.square(number).equals(new Integer(number*number)));
        }
        br.close();
    }
}