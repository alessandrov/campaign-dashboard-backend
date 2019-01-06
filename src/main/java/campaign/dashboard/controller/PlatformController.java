package campaign.dashboard.controller;

import campaign.dashboard.dto.InsightDTO;
import campaign.dashboard.dto.TargetAudienceDTO;
import campaign.dashboard.entity.Creative;
import campaign.dashboard.entity.Insight;
import campaign.dashboard.entity.Platform;
import campaign.dashboard.entity.TargetAudience;
import campaign.dashboard.service.InsightService;
import campaign.dashboard.service.TargetAudienceService;
import campaign.dashboard.util.InsightUtil;
import campaign.dashboard.util.TargetAudienceUtil;
import campaign.dashboard.dto.CreativeDTO;
import campaign.dashboard.dto.PlatformDTO;
import campaign.dashboard.service.CreativeService;
import campaign.dashboard.service.PlatformService;
import campaign.dashboard.util.CreativeUtil;
import campaign.dashboard.util.PlatformUtil;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);

    @Autowired
    private InsightService insightService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private TargetAudienceService targetAudienceService;

    @Autowired
    private CreativeService creativeService;


    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(
            value = "/{platformId}/creative/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("platformId") String platformId) throws IOException {

        Creative creative = creativeService.findByPlatform(Long.valueOf(platformId));

        String imagePath = creative.getImage();

        FileSystemResource imageFile = new FileSystemResource(imagePath);

        return new ResponseEntity<>(IOUtils.toByteArray(imageFile.getInputStream()), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(value = "/{platformId}/target-audience")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<TargetAudienceDTO> getTargetAudienceByPlatformId(@PathVariable("platformId") String platformId) {

        logger.info("TargetAudience by Platform fetch started");

        TargetAudience targetAudience = targetAudienceService.findByPlatform(Long.valueOf(platformId));

        if (targetAudience == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        TargetAudienceDTO result = TargetAudienceUtil.fromEntity(targetAudience);

        logger.info("TargetAudience by Platform fetch ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(value = "/{platformId}/creative")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<CreativeDTO> getCreativeByPlatformId(@PathVariable("platformId") String platformId) {

        logger.info("Creative by Platform fetch started");

        Creative creative = creativeService.findByPlatform(Long.valueOf(platformId));

        if (creative == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        CreativeDTO result = CreativeUtil.fromEntity(creative);

        logger.info("Creative by Platform fetch ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(value = "/{platformId}/insight")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<InsightDTO> getInsightByPlatformId(@PathVariable("platformId") String platformId) {

        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAInsight by Platform fetch started");

        Insight insight = insightService.findByPlatform(Long.valueOf(platformId));

        if (insight == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        InsightDTO result = InsightUtil.fromEntity(insight);

        logger.info("Insight by Platform fetch ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Crea un {@link Platform}.
     *
     * @param dto
     */
    @Transactional(rollbackOn = Exception.class)
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<PlatformDTO> create(@Valid @RequestBody PlatformDTO dto) {

        logger.info("Platform creation started");

        Platform platform = PlatformUtil.fromDTO(dto);

        platformService.save(platform);

        PlatformDTO response = PlatformUtil.fromEntity(platform);

        logger.info("Platform creation ended");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<PlatformDTO> update(@PathVariable("id") String id, @Valid @RequestBody PlatformDTO dto) {

        Platform originalPlatform = platformService.find(Long.valueOf(id));

        if (originalPlatform == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Platform updatedPlatform = PlatformUtil.merge(originalPlatform, dto);

        platformService.save(updatedPlatform);

        PlatformDTO response = PlatformUtil.fromEntity(updatedPlatform);

        logger.info("Platform update ended");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {

        logger.info("Platform deletion started");

        platformService.delete(Long.valueOf(id));

        logger.info("Platform deletion ended");
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<PlatformDTO>> getAll() {

        logger.info("Platform list retrieval started");

        List<Platform> platforms = platformService.findAll();

        if (platforms.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<PlatformDTO> result = PlatformUtil.fromEntities(platforms);

        logger.info("Platform list retrieval ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
    @GetMapping(value = "/campaign/{campaignId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<PlatformDTO>> getByCampaignId(@PathVariable("campaignId") String campaignId) {

        logger.info("Platforms by Campaign fetch started");

        List<Platform> platforms = platformService.findByCampaign(Long.valueOf(campaignId));

        if (platforms == null || platforms.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<PlatformDTO> result = PlatformUtil.fromEntities(platforms);

        logger.info("Platforms by Campaign fetch ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<PlatformDTO> get(@PathVariable("id") String id) {

        logger.info("Platform fetch started");

        Platform platform = platformService.find(Long.valueOf(id));

        if (platform == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        PlatformDTO result = PlatformUtil.fromEntity(platform);

        logger.info("Platform retrieval ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
