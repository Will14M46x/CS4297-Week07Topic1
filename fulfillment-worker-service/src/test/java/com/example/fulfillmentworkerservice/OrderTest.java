package com.example.fulfillmentworkerservice;

import com.example.fulfillmentworkerservice.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void testSingleAndMultipleItems() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // JSON with a single item
        String jsonSingle = "{\"orderId\":\"123\", \"item\":\"Book\", \"amount\":10.0}";

        // JSON with multiple items
        String jsonMultiple = "{\"orderId\":\"124\", \"items\":[\"Pen\",\"Notebook\"], \"amount\":15.0}";

        Order singleOrder = mapper.readValue(jsonSingle, Order.class);
        Order multipleOrder = mapper.readValue(jsonMultiple, Order.class);

        assertEquals(List.of("Book"), singleOrder.getItems());
        assertEquals(List.of("Pen","Notebook"), multipleOrder.getItems());
    }
}
