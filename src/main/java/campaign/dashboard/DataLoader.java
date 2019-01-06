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
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final String imagesPath;
    private AgeRangeRepository ageRangeRepository;
    private CampaignRepository campaignRepository;
    private CreativeRepository creativeRepository;
    private GenderRepository genderRepository;
    private InsightRepository insightRepository;
    private InterestRepository interestRepository;
    private KeywordRepository keywordRepository;
    private LanguageRepository languageRepository;
    private LocationRepository locationRepository;
    private PlatformRepository platformRepository;
    private PlatformTypeRepository platformTypeRepository;
    private TargetAudienceRepository targetAudienceRepository;

    @Autowired
    public DataLoader(AgeRangeRepository ageRangeRepository,
                      CampaignRepository campaignRepository,
                      CreativeRepository creativeRepository,
                      GenderRepository genderRepository,
                      InsightRepository insightRepository,
                      InterestRepository interestRepository,
                      KeywordRepository keywordRepository,
                      LanguageRepository languageRepository,
                      LocationRepository locationRepository,
                      PlatformRepository platformRepository,
                      PlatformTypeRepository platformTypeRepository,
                      TargetAudienceRepository targetAudienceRepository) {

        this.ageRangeRepository = ageRangeRepository;
        this.campaignRepository = campaignRepository;
        this.creativeRepository = creativeRepository;
        this.genderRepository = genderRepository;
        this.insightRepository = insightRepository;
        this.interestRepository = interestRepository;
        this.keywordRepository = keywordRepository;
        this.languageRepository = languageRepository;
        this.locationRepository = locationRepository;
        this.platformRepository = platformRepository;
        this.platformTypeRepository = platformTypeRepository;
        this.targetAudienceRepository = targetAudienceRepository;

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
        Keyword keyword1 = new Keyword("Easy DevOps");
        Keyword keyword2 = new Keyword("DevOps As A Service");
        Keyword keyword3 = new Keyword("DevOps");
        Keyword keyword4 = new Keyword("Google Cloud Platform");
        Keyword keyword5 = new Keyword("DevOps Automation");
        Keyword keyword6 = new Keyword("One Click DevOps");
        Keyword keyword7 = new Keyword("NoOps");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        keywords.add(keyword5);
        keywords.add(keyword6);
        keywords.add(keyword7);

        keywordRepository.saveAll(keywords);
    }

    private void storeInterests() {
        Interest ubuntuInterest = new Interest("Ubuntu");
        Interest gcpInterest = new Interest("Google Cloud Platform");
        Interest devopsInterest = new Interest("DevOps");
        Interest dockerInterest = new Interest("Docker");
        Interest kubernetesInterest = new Interest("Kubernetes");
        Interest swDevelopmentInterest = new Interest("Software Development");
        Interest reactNativeInterest = new Interest("React Native");
        Interest angularInterest = new Interest("Angular");
        Interest reactInterest = new Interest("React");
        Interest vueJsInterest = new Interest("VueJS");
        Interest frontendInterest = new Interest("Frontend Development");
        Interest nodeJsInterest = new Interest("NodeJS");
        Interest facebookDeveloperInterest = new Interest("Facebook Developer");
        Interest wordpressInterest = new Interest("Wordpress");
        Interest awsInterest = new Interest("AWS");

        Set<Interest> interests = new HashSet<>();
        interests.add(ubuntuInterest);
        interests.add(gcpInterest);
        interests.add(devopsInterest);
        interests.add(dockerInterest);
        interests.add(kubernetesInterest);
        interests.add(swDevelopmentInterest);
        interests.add(reactNativeInterest);
        interests.add(angularInterest);
        interests.add(reactInterest);
        interests.add(vueJsInterest);
        interests.add(frontendInterest);
        interests.add(nodeJsInterest);
        interests.add(facebookDeveloperInterest);
        interests.add(wordpressInterest);
        interests.add(awsInterest);

        interestRepository.saveAll(interests);
    }

    private void storeLocations() {
        Location franceLocation = new Location("France");
        Location germanyLocation = new Location("Germany");
        Location switzerlandLocation = new Location("Switzerland");
        Location italyLocation = new Location("Italy");
        Location spainLocation = new Location("Spain");
        Location belgiumLocation = new Location("Belgium");
        Location ukLocation = new Location("United Kingdom");
        Location polandLocation = new Location("Poland");

        Set<Location> locations = new HashSet<>();
        locations.add(franceLocation);
        locations.add(germanyLocation);
        locations.add(switzerlandLocation);
        locations.add(italyLocation);
        locations.add(spainLocation);
        locations.add(belgiumLocation);
        locations.add(ukLocation);
        locations.add(polandLocation);

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
        Language frenchLanguage = new Language("FR");
        Language englishLanguage = new Language("EN");
        Language germanLanguage = new Language("DE");

        Set<Language> languages = new HashSet<>();
        languages.add(frenchLanguage);
        languages.add(englishLanguage);
        languages.add(germanLanguage);

        languageRepository.saveAll(languages);
    }

    private void storePlatformTypes() {
        PlatformType googlePlatformType = new PlatformType("Google");
        PlatformType facebookPlatformType = new PlatformType("Facebook");
        PlatformType instagramPlatformType = new PlatformType("Instagram");

        Set<PlatformType> platformTypes = new HashSet<>();

        platformTypes.add(googlePlatformType);
        platformTypes.add(facebookPlatformType);
        platformTypes.add(instagramPlatformType);

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

        Set<Interest> interests = new HashSet<>();
        interests.add(interestRepository.findByName("Software Development"));
        interests.add(interestRepository.findByName("React Native"));
        interests.add(interestRepository.findByName("Angular"));
        interests.add(interestRepository.findByName("React"));
        interests.add(interestRepository.findByName("VueJS"));
        interests.add(interestRepository.findByName("Frontend Development"));

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

        interests = new HashSet<>();
        interests.add(interestRepository.findByName("Software Development"));
        interests.add(interestRepository.findByName("React Native"));
        interests.add(interestRepository.findByName("Angular"));
        interests.add(interestRepository.findByName("React"));
        interests.add(interestRepository.findByName("Frontend Development"));
        interests.add(interestRepository.findByName("NodeJS"));
        interests.add(interestRepository.findByName("Facebook Developer"));
        interests.add(interestRepository.findByName("Wordpress"));

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

        Set<Interest> interests = new HashSet<>();
        interests.add(interestRepository.findByName("Docker"));
        interests.add(interestRepository.findByName("Kubernetes"));
        interests.add(interestRepository.findByName("DevOps"));
        interests.add(interestRepository.findByName("AWS"));
        interests.add(interestRepository.findByName("Google Cloud Platform"));
        interests.add(interestRepository.findByName("Ubuntu"));

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

        interests = new HashSet<>();
        interests.add(interestRepository.findByName("Docker"));
        interests.add(interestRepository.findByName("Kubernetes"));
        interests.add(interestRepository.findByName("DevOps"));
        interests.add(awsInterest);
        interests.add(interestRepository.findByName("Google Cloud Platform"));
        interests.add(interestRepository.findByName("Ubuntu"));

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

        Language frenchLanguage = languageRepository.findByName("FR");
        Language englishLanguage = languageRepository.findByName("EN");
        Language germanLanguage = languageRepository.findByName("DE");

        Set<Language> languages = new HashSet<>();
        languages.add(frenchLanguage);
        languages.add(englishLanguage);
        languages.add(germanLanguage);

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

        Location franceLocation = locationRepository.findByName("France");
        Location germanyLocation = locationRepository.findByName("Germany");
        Location switzerlandLocation = locationRepository.findByName("Switzerland");

        Set<Location> locations = new HashSet<>();
        locations.add(franceLocation);
        locations.add(germanyLocation);
        locations.add(switzerlandLocation);

        facebookTargetAudience.setLocations(locations);

        // Facebook Target Audience - Campaign #1 - Interests

        Interest dockerInterest = interestRepository.findByName("Docker");
        Interest kubernetesInterest = interestRepository.findByName("Kubernetes");
        Interest devopsInterest = interestRepository.findByName("DevOps");
        Interest gcpInterest = interestRepository.findByName("Google Cloud Platform");
        Interest ubuntuInterest = interestRepository.findByName("Ubuntu");

        Set<Interest> interests = new HashSet<>();
        interests.add(dockerInterest);
        interests.add(kubernetesInterest);
        interests.add(devopsInterest);
        interests.add(gcpInterest);
        interests.add(ubuntuInterest);

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

        languages = new HashSet<>();
        languages.add(frenchLanguage);
        languages.add(englishLanguage);
        languages.add(germanLanguage);

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

        locations = new HashSet<>();
        locations.add(franceLocation);
        locations.add(germanyLocation);
        locations.add(switzerlandLocation);

        instagramTargetAudience.setLocations(locations);

        // Instagram Target Audience - Campaign #1 - Interests

        interests = new HashSet<>();
        interests.add(dockerInterest);
        interests.add(kubernetesInterest);
        interests.add(devopsInterest);
        interests.add(gcpInterest);
        interests.add(ubuntuInterest);

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

        languages = new HashSet<>();
        languages.add(frenchLanguage);
        languages.add(englishLanguage);
        languages.add(germanLanguage);

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

        Location italyLocation = locationRepository.findByName("Italy");
        Location spainLocation = locationRepository.findByName("Spain");
        Location belgiumLocation = locationRepository.findByName("Belgium");
        Location ukLocation = locationRepository.findByName("United Kingdom");
        Location polandLocation = locationRepository.findByName("Poland");

        locations = new HashSet<>();
        locations.add(franceLocation);
        locations.add(germanyLocation);
        locations.add(switzerlandLocation);
        locations.add(italyLocation);
        locations.add(spainLocation);
        locations.add(belgiumLocation);
        locations.add(ukLocation);
        locations.add(polandLocation);

        googleTargetAudience.setLocations(locations);

        // Google Target Audience Campaign #1 - KeyWords

        Keyword keyword1 = keywordRepository.findByName("Easy DevOps");
        Keyword keyword2 = keywordRepository.findByName("DevOps As A Service");
        Keyword keyword3 = keywordRepository.findByName("DevOps");
        Keyword keyword4 = keywordRepository.findByName("Google Cloud Platform");
        Keyword keyword5 = keywordRepository.findByName("DevOps Automation");
        Keyword keyword6 = keywordRepository.findByName("One Click DevOps");
        Keyword keyword7 = keywordRepository.findByName("NoOps");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        keywords.add(keyword5);
        keywords.add(keyword6);
        keywords.add(keyword7);

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