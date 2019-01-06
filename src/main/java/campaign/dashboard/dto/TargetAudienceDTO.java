package campaign.dashboard.dto;

import java.util.List;

public class TargetAudienceDTO {

    private String id;

    private String platformId;

    private List<String> languages;

    private List<String> genders;

    private List<String> ageRange;

    private List<String> locations;

    private List<String> interests;

    private List<String> keywords;

    public TargetAudienceDTO() {
    }

    public TargetAudienceDTO(String id, String platformId, List<String> languages, List<String> genders,
                             List<String> ageRange, List<String> locations, List<String> interests, List<String> keywords) {

        this.id = id;
        this.platformId = platformId;
        this.languages = languages;
        this.genders = genders;
        this.ageRange = ageRange;
        this.locations = locations;
        this.interests = interests;
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public List<String> getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(List<String> ageRange) {
        this.ageRange = ageRange;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

}
