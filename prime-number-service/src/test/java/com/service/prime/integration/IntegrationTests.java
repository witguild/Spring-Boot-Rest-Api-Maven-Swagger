
package com.service.prime.integration;


import com.service.prime.Application;
import com.service.prime.model.ServiceResponse;
import com.service.prime.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private String PRIME_URL = "/list/";

    @Test
    public void testGetPrimeNumbersBadRequestWithValidUpperLimit() throws Exception {
        mockMvc.perform(get(PRIME_URL+"2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPrimeNumbersValidateResponse() throws Exception {
        MvcResult result = mockMvc.perform(get(PRIME_URL+"11")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String RES_RESOURCE_FILE = "json/res_payload_success.json";

        assertEquals("Checking game list after sorting", TestUtil.getResourceFileAsString(RES_RESOURCE_FILE), result.getResponse().getContentAsString());
    }

    @Test
    public void testGetPrimeNumbersBadRequestInvalidPathParam() throws Exception {
        mockMvc.perform(get(PRIME_URL+"String")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPrimeNumbersBadRequestWithUpperLimitLessThanTwo() throws Exception {
        MvcResult result = mockMvc.perform(get(PRIME_URL+"-1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        ServiceResponse serviceResponse = TestUtil.jsonStringToServiceResponse(result.getResponse().getContentAsString());

        assertEquals("Validating response 400 status code", HttpStatus.BAD_REQUEST.value(), serviceResponse.getStatusCode());
        assertEquals("Validating response message", "-1 upper limit in request is less than minimum required limit 2", serviceResponse.getMessage());
        assertTrue("Validating response response", serviceResponse.getPrimeNumbers().isEmpty());
    }
}
