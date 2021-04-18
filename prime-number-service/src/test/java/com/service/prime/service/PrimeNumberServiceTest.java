package com.service.prime.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PrimeNumberServiceTest {

    @InjectMocks
    private PrimeNumberServiceImpl primeNumberService;

    @Test
    public void testIsPrimeForPrimeNumber() {
        boolean isPrime = primeNumberService.isPrime(5);

        assertTrue(isPrime);
    }

    @Test
    public void testIsPrimeForNotPrimeNumber() {
        boolean isPrime = primeNumberService.isPrime(6);

        assertFalse(isPrime);
    }

    @Test
    public void testIsPrimeForLessThanTwo() {
        boolean isPrime = primeNumberService.isPrime(-1);

        assertFalse(isPrime);
    }

    @Test
    public void testGetPrimeNumbersUpToUpperLimitEqualsToTwo() {
        List<Long> primeNumbers = primeNumberService.getPrimeNumbersUpToUpperLimit(2);

        assertEquals(1, primeNumbers.size());
    }

    @Test
    public void testGetPrimeNumbersUpToUpperLimitMoreThanTwo() {
        List<Long> primeNumbers = primeNumberService.getPrimeNumbersUpToUpperLimit(11);

        assertEquals(5, primeNumbers.size());
    }

    @Test
    public void testGetPrimeNumbersUpToUpperLimitLessThanTwo() {
        List<Long> primeNumbers = primeNumberService.getPrimeNumbersUpToUpperLimit(1);

        assertTrue(primeNumbers.isEmpty());
    }

    @Test
    public void testGetPrimeNumbersUpToUpperLimitAsNegativeNumber() {
        List<Long> primeNumbers = primeNumberService.getPrimeNumbersUpToUpperLimit(-1);

        assertTrue(primeNumbers.isEmpty());
    }

    @Test
    public void testGetPrimeNumbersWithUpperLimitMoreThanTwo() {
        List<Long> actualPrimeNumbers = primeNumberService.getPrimeNumbers(11);

        List<Long> expectedPrimeNumbers = new ArrayList<>(Arrays.asList(2l, 3l, 5l, 7l, 11l));

        assertEquals(expectedPrimeNumbers, actualPrimeNumbers);

    }

    @Test
    public void testGetPrimeNumbersWithUpperLimitLessThanTwo() {
        List<Long> actualPrimeNumbers = primeNumberService.getPrimeNumbers(1);
        assertTrue(actualPrimeNumbers.isEmpty());
    }
}
