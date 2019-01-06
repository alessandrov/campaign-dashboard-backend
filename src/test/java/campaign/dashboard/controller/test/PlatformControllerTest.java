package campaign.dashboard.controller.test;

import campaign.dashboard.entity.Campaign;
import campaign.dashboard.entity.Platform;
import campaign.dashboard.service.CampaignService;
import campaign.dashboard.service.PlatformTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import campaign.dashboard.dto.PlatformDTO;
import campaign.dashboard.entity.PlatformType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import util.TestUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlatformControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(PlatformControllerTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlatformTypeService platformTypeService;

    @Autowired
    private CampaignService campaignService;

    private JacksonTester<PlatformDTO> jsonTester;

    private PlatformDTO platformDTO;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);

        PlatformType platformType = new PlatformType();
        platformType.setName("plt");

        platformTypeService.save(platformType);

        Campaign campaign = new Campaign();

        campaign.setCampaignUniqueId("aaaaaa");
        campaign.setGoal("aaaaaa");
        campaign.setName("aaaaaa");
        campaign.setTotalBudget(new BigDecimal(12));
        campaign.setStatus(Campaign.Status.SCHEDULED);

        campaignService.save(campaign);

        LocalDateTime startDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        platformDTO = new PlatformDTO("4", campaign.getId().toString(), platformType.getId().toString(), platformType.getName(),
                "ENDED", "12", "11", startDate.toString(), endDate.toString(), null, null);
    }

    @Test
    public void crudOperations() throws Exception {

        String platformDTOJson = jsonTester.write(platformDTO).getJson();

        AtomicReference<String> idReference = new AtomicReference(4);

        // POST
        mockMvc.perform(post("/platform/")
                .content(platformDTOJson)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andDo(mvcResult -> {

                    String json = mvcResult.getResponse().getContentAsString();

                    PlatformDTO platformDTO2 = (PlatformDTO) TestUtil.convertJSONStringToObject(json, PlatformDTO.class);

                    //idReference.set(platformDTO2.getId());

                })
                .andExpect(status().isCreated());

        final String newStatus = Platform.Status.DELIVERING.toString();
        platformDTO.setStatus(newStatus);

        // PUT
        platformDTOJson = jsonTester.write(platformDTO).getJson();

        mockMvc.perform(put("/platform/{id}", idReference)
                .contentType(MediaType.APPLICATION_JSON)
                .content(platformDTOJson))
                .andDo(print())
                .andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();

                    PlatformDTO platformDTO = (PlatformDTO) TestUtil.convertJSONStringToObject(json, PlatformDTO.class);

                    assertThat(platformDTO.getStatus()).isEqualTo(newStatus);
                })
                .andExpect(status().isOk());

        // GET
        mockMvc.perform(get("/platform/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk());

        // DELETE
        mockMvc.perform(delete("/platform/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk());

        // 404
        mockMvc.perform(get("/platform/{id}", 123))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
