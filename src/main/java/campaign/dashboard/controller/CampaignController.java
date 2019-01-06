package campaign.dashboard.controller;

import campaign.dashboard.service.CampaignService;
import campaign.dashboard.util.CampaignUtil;
import campaign.dashboard.dto.CampaignDTO;
import campaign.dashboard.entity.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;


    @Transactional(rollbackOn = Exception.class)
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<CampaignDTO> create(@Valid @RequestBody CampaignDTO dto) {

        logger.info("Campaign creation started");

        Campaign campaign = CampaignUtil.fromDTO(dto);

        campaignService.save(campaign);

        CampaignDTO response = CampaignUtil.fromEntity(campaign);

        logger.info("Campaign creation ended");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<CampaignDTO> update(@PathVariable("id") String id, @Valid @RequestBody CampaignDTO dto) {

        logger.info("Campaign update started");

        Campaign originalCampaign = campaignService.find(Long.valueOf(id));

        if (originalCampaign == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Campaign updatedCampaign = CampaignUtil.merge(originalCampaign, dto);

        campaignService.save(updatedCampaign);

        CampaignDTO response = CampaignUtil.fromEntity(updatedCampaign);

        logger.info("Campaign update ended");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {

        logger.info("Campaign deletion started");

        campaignService.delete(Long.valueOf(id));

        logger.info("Campaign deletion ended");
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CampaignDTO>> getAll() {

        logger.info("Campaign list retrieval started");

        List<Campaign> campaigns = campaignService.findAll();

        if (campaigns.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<CampaignDTO> result = CampaignUtil.fromEntities(campaigns);

        logger.info("Campaign list retrieval ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<CampaignDTO> get(@PathVariable("id") String id) {

        logger.info("Campaign fetch started");

        Campaign campaign = campaignService.find(Long.valueOf(id));

        if (campaign == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        CampaignDTO result = CampaignUtil.fromEntity(campaign);

        logger.info("Campaign retrieval ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
