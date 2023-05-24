package br.com.krossft.SpringAngular;

import static org.junit.jupiter.api.Assertions.*;

import br.com.krossft.SpringAngular.controller.HelloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest
@WebMvcTest(controllers = HelloController.class)
public class SpringAngularApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

    }
    @Test
    public void testHello() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Olá teste de requisição"));

        final String contentAsString = this.mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andReturn().getResponse().getContentAsString();

        assertEquals ("Olá teste de requisição", contentAsString);


    }

}
