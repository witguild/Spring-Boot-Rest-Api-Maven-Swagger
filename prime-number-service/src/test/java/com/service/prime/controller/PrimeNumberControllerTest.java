// Copyright AB Trav och Galopp (556180-4161)

package com.service.prime.controller;

import com.service.prime.model.ServiceResponse;
import com.service.prime.service.PrimeNumberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.service.prime.util.TestUtil.getResourceFileAsString;
import static com.service.prime.util.TestUtil.jsonStringToServiceResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrimeNumberControllerTest {

    @Mock
    private PrimeNumberServiceImpl primeNumberService;

    @InjectMocks
    private PrimeNumberController primeNumberController;

    private ServiceResponse expectedResponse;

    private List<Long> primeNumbers = new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L, 11L));

    @Before
    public void before() {
        String RES_RESOURCE_FILE = "json/res_payload_success.json";
        String responsePayloadSting = getResourceFileAsString(RES_RESOURCE_FILE);
        expectedResponse = jsonStringToServiceResponse(responsePayloadSting);
    }

    @Test
    public void testGetPrimeNumbers() {
        when(primeNumberService.getPrimeNumbers(11)).thenReturn(primeNumbers);

        ResponseEntity responseEntity = primeNumberController.getPrimeNumbers(11);

        ServiceResponse actualResponse = (ServiceResponse) responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert actualResponse != null;
        assertEquals(expectedResponse.getPrimeNumbers(), actualResponse.getPrimeNumbers());
    }

    @Test
    public void testGetPrimeNumbersWithUpperLimitLessThanTwo() {

        ResponseEntity responseEntity = primeNumberController.getPrimeNumbers(-1);

        ServiceResponse actualResponse = (ServiceResponse) responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assert actualResponse != null;
        assertTrue(actualResponse.getPrimeNumbers().isEmpty());
    }

}
