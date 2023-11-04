package com.example.test;

import com.example.test.controller.SortingLettersController;
import com.example.test.service.SortingService;
import com.example.test.service.SortingServiceImpl;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@WebMvcTest(SortingLettersController.class)
public class ControllerAndServiceTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private final SortingService sortingService = new SortingServiceImpl();

    private final String INPUT_LETTERS = "aaaaA bcccc";
    private final String SORTED_LETTERS = "\"a\":5, \"c\":4, \"b\":1";

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new SortingLettersController(sortingService)).build();
    }

    @Test
    @SneakyThrows
    public void sortByLettersTest() {
        Assertions.assertEquals(SORTED_LETTERS, sortingService.sortByLetters(INPUT_LETTERS));
    }

    @Test
    @SneakyThrows
    public void sortEmptyValueTest() {
        RuntimeException ex = Assertions.assertThrows(
                RuntimeException.class,
                () -> sortingService.sortByLetters(""));
        assertEquals("Cannot use an empty String or null value for the method sortByLetters.", ex.getMessage());
    }

    @SneakyThrows
    @Test
    public void controllerTest() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                .get("/sort/string/{letters}", INPUT_LETTERS)).andReturn().getResponse().getContentAsString();

        assertEquals(SORTED_LETTERS, responseString);
    }
}
