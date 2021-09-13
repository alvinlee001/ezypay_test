package com.ezypay.alvin.subscription.api;

import com.ezypay.alvin.subscription.service.DateRangeService;
import com.ezypay.alvin.subscription.service.DateRangeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SubscriptionApi.class)
@ContextConfiguration(classes = { TestConfig.class })
public class SubscriptionApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DateRangeService dateRangeService;


    @Test
    void whenInvalidDayOfMonth_thenReturns400() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"MONTHLY\",\n" +
                                "    \"dayOfMonth\": 32,\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"13/10/21\"    \n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidDayOfWeek_thenReturns400() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"WEEKLY\",\n" +
                                "    \"dayOfWeek\": \"MOONDAY\",\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"13/10/21\"    \n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidSubscriptionType_thenReturns400() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"YEARLY\",\n" +
                                "    \"dayOfWeek\": \"MONDAY\",\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"13/10/21\"    \n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testMonthly_whenValidDayOfMonth_thenReturns200_and_correct_results() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"MONTHLY\",\n" +
                                "    \"dayOfMonth\": 13,\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"13/10/21\"    \n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"amount\":10.00,\"subscriptionType\":\"MONTHLY\",\"invoiceDates\":[\"13/09/0021\",\"13/10/0021\"]}"));
    }

    @Test
    void testWeekly_whenValidDayOfWeek_thenReturns200_and_correct_results() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"WEEKLY\",\n" +
                                "    \"dayOfWeek\": \"MONDAY\",\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"11/10/21\"    \n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"amount\":10.00,\"subscriptionType\":\"WEEKLY\",\"invoiceDates\":[\"13/09/0021\",\"20/09/0021\",\"27/09/0021\",\"04/10/0021\",\"11/10/0021\"]}"));
    }

    @Test
    void testDaily_whenValid_thenReturns200_and_correct_results() throws Exception {

        mockMvc.perform(post("/subscription/create")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"amount\": 10.00,\n" +
                                "    \"subscriptionType\": \"DAILY\",\n" +
                                "    \"startDate\": \"13/09/21\",\n" +
                                "    \"endDate\": \"11/10/21\"    \n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"amount\":10.00,\"subscriptionType\":\"DAILY\",\"invoiceDates\":[\"13/09/0021\",\"14/09/0021\",\"15/09/0021\",\"16/09/0021\",\"17/09/0021\",\"18/09/0021\",\"19/09/0021\",\"20/09/0021\",\"21/09/0021\",\"22/09/0021\",\"23/09/0021\",\"24/09/0021\",\"25/09/0021\",\"26/09/0021\",\"27/09/0021\",\"28/09/0021\",\"29/09/0021\",\"30/09/0021\",\"01/10/0021\",\"02/10/0021\",\"03/10/0021\",\"04/10/0021\",\"05/10/0021\",\"06/10/0021\",\"07/10/0021\",\"08/10/0021\",\"09/10/0021\",\"10/10/0021\",\"11/10/0021\"]}"
    ));
    }
}

@TestConfiguration
class TestConfig {
    @Bean
    public DateRangeService dateRangeService (){
        return new DateRangeServiceImpl();
    }
}
