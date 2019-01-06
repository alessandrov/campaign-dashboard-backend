package campaign.dashboard.controller.test;

import campaign.dashboard.dto.CampaignDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import campaign.dashboard.entity.Campaign;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import util.TestUtil;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CampaignControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private JacksonTester<CampaignDTO> jsonTester;

    private CampaignDTO campaignDTO;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        campaignDTO = new CampaignDTO("1", "campaign-id", "name-1", "goal-1",
                "12", Campaign.Status.SCHEDULED.toString());
    }

    @Test
    public void crudOperations() throws Exception {

        String campaignDTOJson = jsonTester.write(campaignDTO).getJson();

        AtomicReference<String> idReference = new AtomicReference(campaignDTO.getId());

        // POST
        mockMvc.perform(post("/campaign/")
               .content(campaignDTOJson)
               .contentType(APPLICATION_JSON))
               .andDo(print())
               .andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    CampaignDTO campaignDTO = (CampaignDTO) TestUtil.convertJSONStringToObject(json, CampaignDTO.class);

                    idReference.set(campaignDTO.getId().toString());
                })
               .andExpect(status().isCreated());

        final String newGoal = "new-goal";

        // PUT
        campaignDTO.setGoal(newGoal);

        campaignDTOJson = jsonTester.write(campaignDTO).getJson();

        mockMvc.perform(put("/campaign/{id}", idReference)
               .contentType(MediaType.APPLICATION_JSON)
               .content(campaignDTOJson))
               .andDo(print())
               .andDo(mvcResult -> {
                   String json = mvcResult.getResponse().getContentAsString();

                   org.springframework.boot.json.JsonParser springParser = JsonParserFactory.getJsonParser();
                   Map<String, Object> result = springParser.parseMap(json);

                   // checking if the update was successful
                   assertThat(result.get("goal")).isEqualTo(newGoal);
               })
               .andExpect(status().isOk());

        // GET
        mockMvc.perform(get("/campaign/{id}", idReference.get()))
               .andDo(print())
               .andExpect(status().isOk());

        // DELETE
        mockMvc.perform(delete("/campaign/{id}", idReference.get()))
               .andDo(print())
               .andExpect(status().isOk());

        // 404
        mockMvc.perform(get("/campaign/{id}", idReference.get() + 12))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

}
