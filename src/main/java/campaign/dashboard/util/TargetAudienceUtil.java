package campaign.dashboard.util;

import campaign.dashboard.dto.TargetAudienceDTO;
import campaign.dashboard.entity.*;
import campaign.dashboard.repository.PlatformRepository;
import campaign.dashboard.repository.TargetAudienceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TargetAudienceUtil {

    private static final Logger logger = LoggerFactory.getLogger(TargetAudienceUtil.class);

    private static PlatformRepository platformRepository;

    private static TargetAudienceRepository targetAudienceRepository;

    @Autowired
    private PlatformRepository platformRepository0;

    @Autowired
    private TargetAudienceRepository targetAudienceRepository0;

    @PostConstruct
    private void initStaticDao() {
        platformRepository = this.platformRepository0;
        targetAudienceRepository = this.targetAudienceRepository0;
    }

    public static TargetAudienceDTO fromEntity(TargetAudience targetAudience) {

        TargetAudienceDTO targetAudienceDTO = new TargetAudienceDTO();

        targetAudienceDTO.setId(targetAudience.getId().toString());

        // there's a better way for sure to perform the following operations using Java streams

        // age range
        AgeRange ageRange = targetAudience.getAgeRange();

        List<String> ageRangeList = new ArrayList();

        ageRangeList.add(String.valueOf(ageRange.getLowLimit()));
        ageRangeList.add(String.valueOf(ageRange.getHighLimit()));

        targetAudienceDTO.setAgeRange(ageRangeList);

        // genders
        Set<Gender> genders = targetAudience.getGenders();

        List<String> genderList = new ArrayList();

        for (Gender gender: genders){
            genderList.add(gender.getType().toString());
        }

        targetAudienceDTO.setGenders(genderList);

        // languages
        Set<Language> languages = targetAudience.getLanguages();

        List<String> languageList = new ArrayList();

        for (Language language: languages){
            languageList.add(language.getName());
        }

        targetAudienceDTO.setLanguages(languageList);

        // locations
        Set<Location> locations = targetAudience.getLocations();

        List<String> locationList = new ArrayList();

        for (Location location: locations){
            locationList.add(location.getName());
        }

        targetAudienceDTO.setLocations(locationList);

        // interests
        Set<Interest> interests = targetAudience.getInterests();

        List<String> interestList = new ArrayList();

        for (Interest interest: interests){
            interestList.add(interest.getName());
        }

        targetAudienceDTO.setInterests(interestList);

        // keywords
        Set<Keyword> keywords = targetAudience.getKeywords();

        List<String> keywordsList = new ArrayList();

        for (Keyword keyword: keywords){
            keywordsList.add(keyword.getName());
        }

        targetAudienceDTO.setKeywords(keywordsList);

        return targetAudienceDTO;
    }

    public static TargetAudience fromDTO(TargetAudienceDTO targetAudienceDTO) {

        TargetAudience entity = new TargetAudience();

        String id = targetAudienceDTO.getId();

        if (id != null) {
            entity.setId(Long.valueOf(id));
        }

        // age range
        List<String> ageRangeList = targetAudienceDTO.getAgeRange();

        AgeRange ageRange = new AgeRange();
        ageRange.setHighLimit(Integer.valueOf(ageRangeList.get(0)));
        ageRange.setLowLimit(Integer.valueOf(ageRangeList.get(1)));

        entity.setAgeRange(ageRange);

        // locations
        List<String> locationList = targetAudienceDTO.getAgeRange();

        Set<Location> locations = new HashSet<>();

        for (String location: locationList){
            Location record = new Location();

            record.setName(location);

            locations.add(record);
        }

        entity.setLocations(locations);

        // interest
        List<String> interestList = targetAudienceDTO.getInterests();

        Set<Interest> interests = new HashSet<>();

        for (String interest: interestList){
            Interest record = new Interest();

            record.setName(interest);

            interests.add(record);
        }

        entity.setInterests(interests);

        // gender
        List<String> genderList = targetAudienceDTO.getGenders();

        Set<Gender> genders = new HashSet<>();

        for (String gender: genderList){
            Gender record = new Gender();

            record.setType(Gender.Type.valueOf(gender));

            genders.add(record);
        }

        entity.setGenders(genders);

        // languages
        List<String> languageList = targetAudienceDTO.getLanguages();

        Set<Language> languages = new HashSet<>();

        for (String language: languageList){
            Language record = new Language();

            record.setName(language);

            languages.add(record);
        }

        entity.setLanguages(languages);

        // keywords
        List<String> keywordList = targetAudienceDTO.getKeywords();

        Set<Keyword> keywords = new HashSet<>();

        for (String keyword: keywordList){
            Keyword record = new Keyword();

            record.setName(keyword);

            keywords.add(record);
        }

        entity.setKeywords(keywords);

        return entity;
    }

}
