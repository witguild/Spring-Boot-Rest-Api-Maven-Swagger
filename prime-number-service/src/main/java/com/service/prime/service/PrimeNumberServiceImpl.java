package com.service.prime.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    private final Logger log = LoggerFactory.getLogger(PrimeNumberServiceImpl.class);


    @Override
    public List<Long> getPrimeNumbers(long upperLimit) {
        return getPrimeNumbersUpToUpperLimit(upperLimit);
    }

    /**
     * find all the prime numbers
     * between 2 to upper limit
     *
     * @param upperLimit
     * @return
     */
    protected List<Long> getPrimeNumbersUpToUpperLimit(long upperLimit) {

        log.debug("Start calculating the prime numbers for %d", upperLimit);
        return LongStream.rangeClosed(2, upperLimit)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * check the number is prime number or not
     *
     * @param number
     * @return
     */
    protected boolean isPrime(long number) {

        // return false if the number is less than 2
        // because, it is the minimum prime number
        if (number < 2)
            return false;

        boolean isPrime = LongStream.rangeClosed(2, (long) (Math.sqrt(number)))
                .noneMatch(divider -> number % divider == 0);
        log.debug("%d is prime: %d", number, isPrime);
        return isPrime;
    }

}
