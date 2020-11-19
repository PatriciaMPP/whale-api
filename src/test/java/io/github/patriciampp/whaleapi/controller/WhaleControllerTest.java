package io.github.patriciampp.whaleapi.controller;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.service.WhaleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WhaleControllerTest {

    @MockBean
    private WhaleService whaleService;

    @Autowired
    private MockMvc mockMvc;

   /* @BeforeEach
    void setUp(){
        Whale whale01 = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");
        Whale whale02 = new Whale("Humpback Whale", "Megaptera novaeangliae", "80 to 90 year", "They have dark backs, light bellies, pleats on their throats, and a small hump in front of their dorsal fin.", "48 to 62.5 feet", "Up to 40 tons");

        ArrayList<Whale> whaleList = new ArrayList<>();
        whaleList.add(whale01);
        whaleList.add(whale02);

    }*/

    //Test method that checks whether all existing whales in the server are being fetched with success.
    @Test
    @DisplayName("Get /whales status success")
    void testFindAllAndReturnStatusOk() throws Exception{
        Whale whale01 = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");
        Whale whale02 = new Whale("Humpback Whale", "Megaptera novaeangliae", "80 to 90 year", "They have dark backs, light bellies, pleats on their throats, and a small hump in front of their dorsal fin.", "48 to 62.5 feet", "Up to 40 tons");

        ArrayList<Whale> whaleList = new ArrayList<>();
        whaleList.add(whale01);
        whaleList.add(whale02);

        doReturn(whaleList).when(whaleService).getAll();
        mockMvc.perform(get("/whale-api/whales/")).andExpect(status().isOk());
    }


    //Test method that checks for no content when fetching an empty list of whales from the server
    @Test
    @DisplayName("Get /whales status no content")
    void testFindAllAndReturnStatusNoContent() throws Exception {

        ArrayList<Whale> emptyWhaleList = new ArrayList<>();

        when(whaleService.getAll()).thenReturn(emptyWhaleList);
        mockMvc.perform(get("/whale-api/whales/")).andExpect(status().isNoContent());
    }


    //Test method that checks whether the content fetched from the server is a JSON and has the right size (right number of whales).
    @Test
    @DisplayName("Get /whales content is JSON and has right number of whales")
    void testFindAllHasRightSize() throws Exception {
        Whale whale01 = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");
        Whale whale02 = new Whale("Humpback Whale", "Megaptera novaeangliae", "80 to 90 year", "They have dark backs, light bellies, pleats on their throats, and a small hump in front of their dorsal fin.", "48 to 62.5 feet", "Up to 40 tons");

        ArrayList<Whale> whaleList = new ArrayList<>();
        whaleList.add(whale01);
        whaleList.add(whale02);

        when(whaleService.getAll()).thenReturn(whaleList);
        mockMvc.perform(get("/whale-api/whales/"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }


    //Test method that checks whether the content fetched from the server is a JSON and has the right content (right specieName).
    @Test
    @DisplayName("Get /whales content is JSON and has right content")
    void testFindAllHasTheRightContent() throws Exception {
        Whale whale01 = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");
        Whale whale02 = new Whale("Humpback Whale", "Megaptera novaeangliae", "80 to 90 year", "They have dark backs, light bellies, pleats on their throats, and a small hump in front of their dorsal fin.", "48 to 62.5 feet", "Up to 40 tons");

        ArrayList<Whale> whaleList = new ArrayList<>();
        whaleList.add(whale01);
        whaleList.add(whale02);

        when(whaleService.getAll()).thenReturn(whaleList);
        mockMvc.perform(get("/whale-api/whales/"))
                //.andExpect(jsonPath("$[0]"))
                .andExpect(jsonPath("$[*].specieName", containsInAnyOrder("Blue Whale","Humpback Whale")));
    }


    //Test method that checks whether the whale fetch by the ID is being made with success.
    @Test
    @DisplayName("Get /whales/{id} status success")
    void testFindByIdAndReturnStatusOk() throws Exception {
        int rightWhaleId = 1;
        Whale whale01 = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");


        when(whaleService.findById(rightWhaleId)).thenReturn(whale01);
        mockMvc.perform(get("/whale-api/whales/{id}", rightWhaleId)).andExpect(status().isOk());
    }


    //Test method that checks the correct catch of an NoSuchElementException
    @Test
    @DisplayName("Get /whales/{id} not found")
    void testFindByIdNotFound() throws Exception {
        int wrongWhaleId = 10;

        when(whaleService.findById(wrongWhaleId)).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("/whale-api/whales/{id}", wrongWhaleId)).andExpect(status().isNotFound());
    }

    //Test method that checks whether the content fetched from the server by the ID is a JSON and has the right content (right specieName).
    @Test
    @DisplayName("Get /whales/{id} content is JSON and has the right content")
    void testFindByIdHasTheRightContent() throws Exception {
        Whale whale = new Whale("Blue Whale", "Balaenoptera musculus", "80 to 90 years", "Blue whales are the largest animals ever to live on our planet.", "82 to 105 feet", "Up to 200 tons");
        int whaleId = 1;


        when(whaleService.findById(whaleId)).thenReturn(whale);
        mockMvc.perform(get("/whale-api/whales/{id}", whaleId)).andExpect(jsonPath("$.specieName", is("Blue Whale")));
    }


    @Test
    void deleteAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void findMinSize() {
    }
}