/*
 * Krossby Adhemar Camacho Alviz
 *
 */

package br.com.krossft.SpringAngular.controller;

import br.com.krossft.SpringAngular.dto.HeroDto;
import br.com.krossft.SpringAngular.models.Hero;
import br.com.krossft.SpringAngular.models.Power;
import br.com.krossft.SpringAngular.service.InterfaceHeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = HeroController.class)
public class HeroControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private InterfaceHeroService interfaceHeroService;

  @BeforeEach
  public void setup() {
    final Power power= new Power();
    power.setId(1);
    power.setPower("Super Força");

    final HeroDto dto = new HeroDto();
    dto.setName("Teste de Hero");
    dto.setPower(1);
    dto.setEmail("testHero@gmail.com");

    final Hero mockHero = new Hero((long) 99, "Teste de Hero",power, "testHero@gmail.com");
    final Optional<Hero> mockOptionalHero = Optional.ofNullable(mockHero);

    Mockito.when(interfaceHeroService.findOne(mockHero.getId())).thenReturn(mockOptionalHero);
    Mockito.when(interfaceHeroService.ifIdExits(mockHero.getId())).thenReturn(true);

  }

  @Test
  public void createTest() throws Exception {
    final Power power= new Power();
    power.setId(1);
    power.setPower("Super Força");

    Hero mockHero = new Hero((long) 99, "Teste de Hero",power, "testHero@gmail.com");

    Mockito.when(interfaceHeroService.create(Mockito.any(Hero.class))).thenReturn(mockHero);
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String mockHeroJSON = ow.writeValueAsString(mockHero);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/heroes")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mockHeroJSON))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json(mockHeroJSON));
  }

  @Test
  public void createNewHeroTest() throws Exception {
    final Power power= new Power();
    power.setId(1);
    power.setPower("Super Força");

    HeroDto mockHeroDto = new HeroDto();
    mockHeroDto.setName("Teste de Hero");
    mockHeroDto.setPower(1);
    mockHeroDto.setEmail("testHero@gmail.com");

    Hero mockHero = new Hero(99L, "Teste de Hero", power, "testHero@gmail.com");

    Mockito.when(interfaceHeroService.create(Mockito.any(Hero.class))).thenReturn(mockHero);
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String mockHeroJSON = ow.writeValueAsString(mockHero);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/heroes")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mockHeroJSON))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json(mockHeroJSON));
  }

  @Test
  public void listAllTest() throws Exception {
    final Power power= new Power();
    power.setId(1);
    power.setPower("Super Força");

    List<Hero> mockListAll = new ArrayList<>();
    Hero mockHero1 = new Hero(1L, "Hero1",power,"hero1@gmail.com");
    Hero mockHero2 = new Hero(2L, "Hero2",power,"hero2@gmail.com");
    Hero mockHero3 = new Hero(3L, "Hero3",power,"hero3@gmail.com");
    mockListAll.add(mockHero1);
    mockListAll.add(mockHero2);
    mockListAll.add(mockHero3);

    Mockito.when(interfaceHeroService.findAll()).thenReturn(mockListAll);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String mockHeroListJSON = ow.writeValueAsString(mockListAll);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/heroes")
            .content(mockHeroListJSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(mockHeroListJSON));

  }

  @Test
  public void removeTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/heroes/delete-hero/"+"{id}", new Long(99)))
        .andExpect(MockMvcResultMatchers.status().is(204));
  }

  @Test
  public void updateTest() throws Exception {
    final Power power= new Power();
    power.setId(1);
    power.setPower("Super Força");

    Hero mockUpdateHero =
        new Hero(99L, "Teste update de Hero", power, "testHero@gmail.com");

    Mockito.when(interfaceHeroService.update(Mockito.any(Hero.class))).thenReturn(mockUpdateHero);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String mockHeroJSON = ow.writeValueAsString(mockUpdateHero);

    mockMvc.perform(MockMvcRequestBuilders.put("/heroes/update-hero/{id}", new Long(99), mockUpdateHero)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mockHeroJSON))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.content().json(mockHeroJSON));
  }
}
