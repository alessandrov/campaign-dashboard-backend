package campaign.dashboard;

import campaign.dashboard.entity.*;
import campaign.dashboard.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final String imagesPath;

    @Autowired
    private AgeRangeRepository ageRangeRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private InsightRepository insightRepository;
    @Autowired
    private InterestRepository interestRepository;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private PlatformTypeRepository platformTypeRepository;
    @Autowired
    private TargetAudienceRepository targetAudienceRepository;

    @Autowired
    public DataLoader() {
        String workingDirectory = System.getProperty("user.dir");

        this.imagesPath = workingDirectory + File.separator + "images";

        logger.info("Local images path: " + imagesPath);
    }

    public void run(ApplicationArguments args) {
        logger.info("Starting database population");

        // Platform Types
        storePlatformTypes();

        // Languages
        storeLanguages();

        // Genders
        storeGenders();

        // Locations
        storeLocations();

        // Interests
        storeInterests();

        // Keywords
        storeKeywords();

        // Campaigns
        storeCampaign1();
        storeCampaign2();
        storeCampaign3();
    }

    private void storeKeywords() {
        Keyword[] values = new Keyword[] { new Keyword("Easy DevOps"), new Keyword("DevOps As A Service"),
                new Keyword("DevOps"), new Keyword("Google Cloud Platform"), new Keyword("DevOps Automation"),
                new Keyword("One Click DevOps"), new Keyword("NoOps") };

        Set<Keyword> keywords = new HashSet<>(Arrays.asList(values));

        keywordRepository.saveAll(keywords);
    }

    private void storeInterests() {
        Interest[] values = new Interest[] { new Interest("Ubuntu"), new Interest("Google Cloud Platform"),
                new Interest("DevOps"), new Interest("Docker"), new Interest("Kubernetes"),
                new Interest("Software Development"), new Interest("React Native"), new Interest("Angular"),
                new Interest("React"), new Interest("VueJS"), new Interest("Frontend Development"),
                new Interest("NodeJS"), new Interest("Facebook Developer"), new Interest("Wordpress"),
                new Interest("AWS")};

        Set<Interest> interests = new HashSet<>(Arrays.asList(values));

        interestRepository.saveAll(interests);
    }

    private void storeLocations() {
        Location[] values = new Location[] { new Location("France"), new Location("Germany"),
                new Location("Switzerland"), new Location("Italy"), new Location("Spain"),
                new Location("Belgium"), new Location("United Kingdom"), new Location("Poland")};

        Set<Location> locations = new HashSet<>(Arrays.asList(values));

        locationRepository.saveAll(locations);
    }

    private void storeGenders() {
        Gender maleGender = new Gender(Gender.Type.M);
        Gender femaleGender = new Gender(Gender.Type.F);

        Set<Gender> genders = new HashSet<>();
        genders.add(maleGender);
        genders.add(femaleGender);

        genderRepository.saveAll(genders);
    }

    private void storeLanguages() {
        Language[] values = new Language[] { new Language("France"), new Language("Germany"),
                new Language("Switzerland")};

        Set<Language> languages = new HashSet<>(Arrays.asList(values));

        languageRepository.saveAll(languages);
    }

    private void storePlatformTypes() {
        PlatformType[] values = new PlatformType[] { new PlatformType("Google"), new PlatformType("Facebook"),
                new PlatformType("Instagram")};

        Set<PlatformType> platformTypes = new HashSet<>(Arrays.asList(values));

        platformTypeRepository.saveAll(platformTypes);
    }

    private void storeCampaign3() {
        Campaign campaign = campaignRepository.save(new Campaign("100000003", "Test Ad 3", "Raise Awareness",
                new BigDecimal(90), Campaign.Status.SCHEDULED));

        // Facebook platform - Campaign #3

        LocalDateTime startDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime endDate = Instant.ofEpochMilli(1535580000000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType facebookPlatformType = platformTypeRepository.findByName("Facebook");

        Platform facebookPlatform = platformRepository.save(new Platform(campaign, facebookPlatformType, Platform.Status.SCHEDULED,
                new BigDecimal(40), new BigDecimal(40), startDate));

        facebookPlatform.setEndDate(endDate);

        platformRepository.save(facebookPlatform);

        // Facebook Target Audience - Campaign #3

        TargetAudience facebookTargetAudience = new TargetAudience(facebookPlatform);

        // Facebook Target Audience - Campaign #3 -Languages

        Set<Language> languages = new HashSet<>();
        languages.add(languageRepository.findByName("EN"));

        facebookTargetAudience.setLanguages(languages);

        // Facebook Target Audience - Campaign #3 - Genders

        Set<Gender> genders = new HashSet<>();
        genders.add(genderRepository.findByType(Gender.Type.M));
        genders.add(genderRepository.findByType(Gender.Type.F));

        facebookTargetAudience.setGenders(genders);

        // Facebook Target Audience - Campaign #3 -Age Range

        AgeRange ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 65);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 65);
            ageRangeRepository.save(ageRange);
        }

        facebookTargetAudience.setAgeRange(ageRange);

        // Facebook Target Audience - Campaign #3 -Locations

        Set<Location> locations = new HashSet<>();
        locations.add(locationRepository.findByName("Switzerland"));

        facebookTargetAudience.setLocations(locations);

        targetAudienceRepository.save(facebookTargetAudience);

        // Facebook Target Audience - Campaign #3 - Interests

        Interest[] values = new Interest[] { interestRepository.findByName("Software Development"),
                interestRepository.findByName("React Native"),
                interestRepository.findByName("Angular"),
                interestRepository.findByName("React"),
                interestRepository.findByName("VueJS"),
                interestRepository.findByName("Frontend Development")};

        Set<Interest> interests = new HashSet<>(Arrays.asList(values));

        facebookTargetAudience.setInterests(interests);

        targetAudienceRepository.save(facebookTargetAudience);

        // Facebook Creatives - Campaign #3

        Creative creative = new Creative(facebookPlatform, "Let us take care of your frontend needs today!",
                "https://example.io", imagesPath + File.separator + "img4.jpg");

        creative.setHeader("Need Help?");

        creativeRepository.save(creative);

        // Facebook Insights - Campaign #3

        Insight insight = new Insight(facebookPlatform, 0, 0, new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(0));

        insight.setNanosScore(new BigDecimal(5.9));
        insight.setAdvancedKpi2(new BigDecimal(0));

        insightRepository.save(insight);

        // Instagram platform - Campaign #3

        startDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        endDate = Instant.ofEpochMilli(1535580000000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType instagramPlatformType = platformTypeRepository.findByName("Instagram");

        Platform instagramPlatform = new Platform(campaign, instagramPlatformType, Platform.Status.SCHEDULED,
                new BigDecimal(50), new BigDecimal(40), startDate);

        instagramPlatform.setEndDate(endDate);

        platformRepository.save(instagramPlatform);

        // Instagram Target Audience - Campaign #3

        TargetAudience instagramTargetAudience = new TargetAudience(instagramPlatform);

        // Instagram Target Audience - Campaign #3 - Languages

        languages = new HashSet<>();
        languages.add(languageRepository.findByName("EN"));

        instagramTargetAudience.setLanguages(languages);

        // Instagram Target Audience - Campaign #3 - Genders

        genders = new HashSet<>();
        genders.add(genderRepository.findByType(Gender.Type.M));
        genders.add(genderRepository.findByType(Gender.Type.F));

        instagramTargetAudience.setGenders(genders);

        // Instagram Target Audience - Campaign #3 - Age Range

        ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 45);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 45);
            ageRangeRepository.save(ageRange);
        }

        instagramTargetAudience.setAgeRange(ageRange);

        // Instagram Target Audience - Campaign #3 - Locations

        locations = new HashSet<>();
        locations.add(locationRepository.findByName("Switzerland"));

        instagramTargetAudience.setLocations(locations);

        // Instagram Target Audience - Campaign #3 - Interests

        values = new Interest[] { interestRepository.findByName("Software Development"),
                interestRepository.findByName("React Native"),
                interestRepository.findByName("Angular"),
                interestRepository.findByName("React"),
                interestRepository.findByName("NodeJS"),
                interestRepository.findByName("Wordpress"),
                interestRepository.findByName("Facebook Developer"),
                interestRepository.findByName("Frontend Development")};

        interests = new HashSet<>(Arrays.asList(values));

        instagramTargetAudience.setInterests(interests);

        targetAudienceRepository.save(instagramTargetAudience);

        // Instagram Creatives - Campaign #3

        creative = new Creative(instagramPlatform, "Let us take care of your frontend needs today! " +
                "#Angular #React #React_native #VueJS #VueIsAwesome, #ReactForever #ILoveAngular #Frontend #Javascript",
                "https://example.io", imagesPath + File.separator + "img4.jpg");

        creative.setHeader("Need Help?");

        creativeRepository.save(creative);

        // Instagram Insights - Campaign #3

        insight = new Insight(instagramPlatform, 0, 0, new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(93));

        insight.setWebsiteVisits(new BigDecimal(0));
        insight.setNanosScore(new BigDecimal(8.2));
        insight.setAdvancedKpi2(new BigDecimal(0));

        insightRepository.save(insight);
    }

    private void storeCampaign2() {
        Campaign campaign = campaignRepository.save(
                new Campaign("100000002", "Test Ad 2", "Raise Awareness", new BigDecimal(360), Campaign.Status.ENDED));

        // Facebook platform - Campaign #2

        LocalDateTime startDate = Instant.ofEpochMilli(1513724400000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime endDate = Instant.ofEpochMilli(1514674800000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType facebookPlatformType = platformTypeRepository.findByName("Facebook");

        Platform facebookPlatform = platformRepository.save(new Platform(campaign, facebookPlatformType, Platform.Status.ENDED,
                new BigDecimal(180), new BigDecimal(12), startDate));

        facebookPlatform.setEndDate(endDate);

        platformRepository.save(facebookPlatform);

        // Facebook Target Audience - Campaign #2

        TargetAudience facebookTargetAudience = new TargetAudience(facebookPlatform);

        // Facebook Target Audience - Campaign #2 - Languages

        Set<Language> languages = new HashSet<>();
        languages.add(languageRepository.findByName("EN"));

        facebookTargetAudience.setLanguages(languages);

        // Facebook Target Audience - Campaign #2 - Genders

        Set<Gender> genders = new HashSet<>();
        genders.add(genderRepository.findByType(Gender.Type.M));
        genders.add(genderRepository.findByType(Gender.Type.F));

        genderRepository.saveAll(genders);

        facebookTargetAudience.setGenders(genders);

        // Facebook Target Audience - Campaign #2 - Age Range

        AgeRange ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 45);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 45);
            ageRangeRepository.save(ageRange);
        }

        facebookTargetAudience.setAgeRange(ageRange);

        // Facebook Target Audience Campaign #2 - Locations

        Set<Location> locations = new HashSet<>();
        locations.add(locationRepository.findByName("Switzerland"));

        facebookTargetAudience.setLocations(locations);

        targetAudienceRepository.save(facebookTargetAudience);

        // Facebook Target Audience - Campaign #2 - Interests

        Interest awsInterest = interestRepository.findByName("AWS");

        Interest[] values = new Interest[] { interestRepository.findByName("Docker"),
                interestRepository.findByName("Kubernetes"),
                interestRepository.findByName("DevOps"),
                interestRepository.findByName("AWS"),
                interestRepository.findByName("Google Cloud Platform"),
                interestRepository.findByName("Ubuntu")};

        Set<Interest> interests = new HashSet<>(Arrays.asList(values));

        facebookTargetAudience.setInterests(interests);

        targetAudienceRepository.save(facebookTargetAudience);

        // Facebook Creatives - Campaign #2

        Creative creative = new Creative(facebookPlatform, "Let us our smart AI DevOps Assistant help you!",
                "https://example.io", imagesPath + File.separator + "img4.jpg");

        creative.setHeader("Tired of doing DevOps?");

        creativeRepository.save(creative);

        // Facebook Insights - Campaign #2

        Insight insight = new Insight(facebookPlatform, 8293, 453, new BigDecimal(2.88), new BigDecimal(0.03),
                new BigDecimal(39.7));

        insight.setNanosScore(new BigDecimal(2.9));
        insight.setAdvancedKpi2(new BigDecimal(0.035));

        insightRepository.save(insight);

        // Instagram platform - Campaign #2

        startDate = Instant.ofEpochMilli(1513724400000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        endDate = Instant.ofEpochMilli(1514674800000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType instagramPlatformType = platformTypeRepository.findByName("Instagram");

        Platform instagramPlatform = new Platform(campaign, instagramPlatformType, Platform.Status.ENDED,
                new BigDecimal(180), new BigDecimal(0), startDate);

        instagramPlatform.setEndDate(endDate);

        platformRepository.save(instagramPlatform);

        // Instagram Target Audience - Campaign #2

        TargetAudience instagramTargetAudience = new TargetAudience(instagramPlatform);

        // Instagram Target Audience - Campaign #2 - Languages

        languages = new HashSet<>();
        languages.add(languageRepository.findByName("EN"));

        instagramTargetAudience.setLanguages(languages);

        // Instagram Target Audience - Campaign #2 - Genders

        genders = new HashSet<>();
        genders.add(genderRepository.findByType(Gender.Type.M));
        genders.add(genderRepository.findByType(Gender.Type.F));

        instagramTargetAudience.setGenders(genders);

        // Instagram Target Audience - Campaign #2 - Age Range

        ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 45);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 45);
            ageRangeRepository.save(ageRange);
        }

        instagramTargetAudience.setAgeRange(ageRange);

        // Instagram Target Audience - Campaign #2 - Locations

        locations = new HashSet<>();
        locations.add(locationRepository.findByName("Switzerland"));

        instagramTargetAudience.setLocations(locations);

        // Instagram Target Audience - Campaign #2 - Interests

        values = new Interest[] { interestRepository.findByName("Docker"),
                interestRepository.findByName("Kubernetes"),
                interestRepository.findByName("DevOps"),
                interestRepository.findByName("AWS"),
                interestRepository.findByName("Google Cloud Platform"),
                interestRepository.findByName("Ubuntu")};

        interests = new HashSet<>(Arrays.asList(values));

        instagramTargetAudience.setInterests(interests);

        targetAudienceRepository.save(instagramTargetAudience);

        // Instagram Creatives - Campaign #2

        creative = new Creative(instagramPlatform, "Let us our smart AI DevOps Assistant help you #AWS #GCP #DevOps #Docker #Kubernates",
                "https://example.io", imagesPath + File.separator + "img3.jpg");

        creative.setHeader("Tired of doing DevOps ?");

        creativeRepository.save(creative);

        // Instagram Insights - Campaign #2

        insight = new Insight(instagramPlatform, 9023, 1321, new BigDecimal(4.28), new BigDecimal(0.043),
                new BigDecimal(93));

        insight.setWebsiteVisits(new BigDecimal(943));
        insight.setNanosScore(new BigDecimal(8.2));
        insight.setAdvancedKpi2(new BigDecimal(0.08332));

        insightRepository.save(insight);
    }

    private void storeCampaign1() {
        Campaign campaign = campaignRepository.save(
                new Campaign("100000001", "Test Ad 1", "Increase Reach", new BigDecimal(120), Campaign.Status.DELIVERING));

        // Facebook platform - Campaign #1

        PlatformType facebookPlatformType = platformTypeRepository.findByName("Facebook");

        LocalDateTime startDate =
                Instant.ofEpochMilli(1530568800000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime endDate =
                Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        Platform facebookPlatform = new Platform(campaign, facebookPlatformType, Platform.Status.DELIVERING,
                new BigDecimal(40), new BigDecimal(12), startDate);

        facebookPlatform.setEndDate(endDate);

        platformRepository.save(facebookPlatform);

        // Facebook Target Audience - Campaign #1

        TargetAudience facebookTargetAudience = new TargetAudience(facebookPlatform);

        // Facebook Target Audience - Campaign #1 - Languages

        Language[] values = new Language[] { languageRepository.findByName("FR"),
                languageRepository.findByName("EN"),
                languageRepository.findByName("DE")};

        Set<Language> languages = new HashSet<>(Arrays.asList(values));

        facebookTargetAudience.setLanguages(languages);

        // Facebook Target Audience - Campaign #1 - Genders

        Gender maleGender = genderRepository.findByType(Gender.Type.M);
        Gender femaleGender = genderRepository.findByType(Gender.Type.F);

        Set<Gender> genders = new HashSet<>();
        genders.add(maleGender);
        genders.add(femaleGender);

        facebookTargetAudience.setGenders(genders);

        // Facebook Target Audience - Campaign #1 - Age Range

        AgeRange ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 66);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 66);
            ageRangeRepository.save(ageRange);
        }

        facebookTargetAudience.setAgeRange(ageRange);

        // Facebook Target Audience Campaign #1 - Locations

        Location[] locationValues = new Location[] { locationRepository.findByName("France"),
                locationRepository.findByName("Germany"),
                locationRepository.findByName("Switzerland")};

        Set<Location> locations = new HashSet<>(Arrays.asList(locationValues));

        facebookTargetAudience.setLocations(locations);

        // Facebook Target Audience - Campaign #1 - Interests

        Interest[] interestValues = new Interest[] { interestRepository.findByName("Docker"),
                interestRepository.findByName("Kubernetes"),
                interestRepository.findByName("DevOps"),
                interestRepository.findByName("Google Cloud Platform"),
                interestRepository.findByName("Ubuntu")};

        Set<Interest> interests = new HashSet<>(Arrays.asList(interestValues));

        facebookTargetAudience.setInterests(interests);

        targetAudienceRepository.save(facebookTargetAudience);

        // Facebook Creatives - Campaign #1

        Creative creative = new Creative(facebookPlatform, "DOP SuperHero is where all DevOps is going to happen in the future, " +
                "join the revolution today!", "https://example.io", imagesPath + File.separator + "img1.jpg");

        creative.setHeader("DevOps Made Easy, We Take care of the heavy lifting for you");

        creativeRepository.save(creative);

        // Facebook Insights - Campaign #1

        Insight insight = new Insight(facebookPlatform, 4503, 328, new BigDecimal(0.88), new BigDecimal(0.09),
                new BigDecimal(44.5));

        insight.setNanosScore(new BigDecimal(0.99));
        insight.setAdvancedKpi2(new BigDecimal(0.0023));

        insightRepository.save(insight);

        // Instagram platform - Campaign #1

        startDate = Instant.ofEpochMilli(1530568800000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        endDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType instagramPlatformType = platformTypeRepository.findByName("Instagram");

        Platform instagramPlatform = new Platform(campaign, instagramPlatformType, Platform.Status.DELIVERING,
                new BigDecimal(44), new BigDecimal(14), startDate);

        instagramPlatform.setEndDate(endDate);

        platformRepository.save(instagramPlatform);

        // Instagram Target Audience - Campaign #1

        TargetAudience instagramTargetAudience = new TargetAudience(instagramPlatform);

        // Instagram Target Audience - Campaign #1 - Languages

        values = new Language[] { languageRepository.findByName("FR"),
                languageRepository.findByName("EN"),
                languageRepository.findByName("DE")};

        languages = new HashSet<>(Arrays.asList(values));

        instagramTargetAudience.setLanguages(languages);

        // Instagram Target Audience - Campaign #1 - Genders

        genders = new HashSet<>();
        genders.add(maleGender);
        genders.add(femaleGender);

        instagramTargetAudience.setGenders(genders);

        ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 66);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 66);
            ageRangeRepository.save(ageRange);
        }

        instagramTargetAudience.setAgeRange(ageRange);

        // Instagram Target Audience Campaign #1 - Locations

        locationValues = new Location[] { locationRepository.findByName("France"),
                locationRepository.findByName("Germany"),
                locationRepository.findByName("Switzerland")};

        locations = new HashSet<>(Arrays.asList(locationValues));

        instagramTargetAudience.setLocations(locations);

        // Instagram Target Audience - Campaign #1 - Interests

        interestValues = new Interest[] { interestRepository.findByName("Docker"),
                interestRepository.findByName("Kubernetes"),
                interestRepository.findByName("Ubuntu"),
                interestRepository.findByName("Google Cloud Platform"),
                interestRepository.findByName("DevOps")};

        interests = new HashSet<>(Arrays.asList(interestValues));

        instagramTargetAudience.setInterests(interests);

        targetAudienceRepository.save(instagramTargetAudience);

        // Instagram Creatives - Campaign #1

        creative = new Creative(instagramPlatform, "DOP SuperHero is where all DevOps is going to happen in the future, " +
                "join the revolution today!", "https://example.io", imagesPath + File.separator + "img1.jpg");

        creative.setHeader("DevOps Made Easy, We Take care of the heavy lifting for you");

        creativeRepository.save(creative);

        // Instagram Insights - Campaign #1

        insight = new Insight(instagramPlatform, 436, 220, new BigDecimal(1.28), new BigDecimal(0.43),
                new BigDecimal(39));

        insight.setWebsiteVisits(new BigDecimal(178));
        insight.setNanosScore(new BigDecimal(5.2));
        insight.setAdvancedKpi2(new BigDecimal(0.00143));

        insightRepository.save(insight);

        // Google platform - Campaign #1

        startDate = Instant.ofEpochMilli(1530568800000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        endDate = Instant.ofEpochMilli(1532901600000L).atZone(ZoneId.systemDefault()).toLocalDateTime();

        PlatformType googlePlatformType = platformTypeRepository.findByName("Google");

        Platform googlePlatform = new Platform(campaign, googlePlatformType, Platform.Status.DELIVERING,
                new BigDecimal(36), new BigDecimal(24), startDate);

        googlePlatform.setEndDate(endDate);

        platformRepository.save(googlePlatform);

        // Google Target Audience - Campaign #1

        TargetAudience googleTargetAudience = new TargetAudience(googlePlatform);

        // Google Target Audience - Campaign #1 - Languages

        Language[] languageValues = new Language[] { languageRepository.findByName("FR"),
                languageRepository.findByName("EN"),
                languageRepository.findByName("DE")};

        languages = new HashSet<>(Arrays.asList(languageValues));

        googleTargetAudience.setLanguages(languages);

        // Google Target Audience - Campaign #1 - Genders

        genders = new HashSet<>();
        genders.add(maleGender);
        genders.add(femaleGender);

        googleTargetAudience.setGenders(genders);

        ageRange = ageRangeRepository.findByLowLimitAndHighLimit(20, 66);

        if (ageRange == null) {
            ageRange = new AgeRange(20, 66);
            ageRangeRepository.save(ageRange);
        }

        googleTargetAudience.setAgeRange(ageRange);

        // Google Target Audience Campaign #1 - Locations

        locationValues = new Location[] { locationRepository.findByName("Italy"),
                locationRepository.findByName("Spain"),
                locationRepository.findByName("Belgium"),
                locationRepository.findByName("United Kingdom"),
                locationRepository.findByName("France"),
                locationRepository.findByName("Germany"),
                locationRepository.findByName("Switzerland"),
                locationRepository.findByName("Poland")};

        locations = new HashSet<>(Arrays.asList(locationValues));

        googleTargetAudience.setLocations(locations);

        // Google Target Audience Campaign #1 - KeyWords

        Keyword[] keywordValues = new Keyword[] { keywordRepository.findByName("Easy DevOps"),
                keywordRepository.findByName("DevOps As A Service"),
                keywordRepository.findByName("DevOps"),
                keywordRepository.findByName("Google Cloud Platform"),
                keywordRepository.findByName("DevOps Automation"),
                keywordRepository.findByName("One Click DevOps"),
                keywordRepository.findByName("NoOps")};

        Set keywords = new HashSet<>(Arrays.asList(keywordValues));

        googleTargetAudience.setKeywords(keywords);

        targetAudienceRepository.save(googleTargetAudience);

        // Google Creatives - Campaign #1

        creative = new Creative(googlePlatform, "DOP SuperHero is where all DevOps is going to happen in the future, " +
                "join the revolution today!", "https://example.io", imagesPath + File.separator + "img2.jpg");

        creative.setHeader1("DevOps Made Easy");
        creative.setHeader2("We Take care of the heavy lifting for you");

        creativeRepository.save(creative);

        // Google Insights - Campaign #1

        insight = new Insight(googlePlatform, 20436, 420, new BigDecimal(0.99), new BigDecimal(0.013),
                new BigDecimal(3.9));

        insight.setWebsiteVisits(new BigDecimal(378));

        insightRepository.save(insight);
    }

}