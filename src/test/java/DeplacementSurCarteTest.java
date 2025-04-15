
import org.example.api.DeplacementApiApplication;
import org.example.model.SimulationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(classes = DeplacementApiApplication.class)
@AutoConfigureMockMvc
public class DeplacementSurCarteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String CARTE_V2 = "src/test/resources/carte_v2.txt";

    @Test
    void testSimulerDeplacementMvmValid() throws Exception {
        SimulationRequest request = new SimulationRequest();
        request.setCartePath(CARTE_V2);
        request.setInitialX(3);
        request.setInitialY(0);
        request.setDeplacements("E");

        mockMvc.perform(MockMvcRequestBuilders.post("/simuler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalX").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalY").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Simulation terminée."));
    }

    @Test
    void testSimulerDeplacementObstacle() throws Exception {
        SimulationRequest request = new SimulationRequest();
        request.setCartePath(CARTE_V2);
        request.setInitialX(1);
        request.setInitialY(1);
        request.setDeplacements("E");

        mockMvc.perform(MockMvcRequestBuilders.post("/simuler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalX").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalY").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Le personnage ne peut pas aller sur les cases occupées par les bois impénétrables."));
    }


}
