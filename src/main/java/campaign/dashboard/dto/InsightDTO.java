package campaign.dashboard.dto;

public class InsightDTO {

    private String id;

    private String platformId;

    private String impressions;

    private String clicks;

    private String nanosScore;

    private String websiteVisits;

    private String costPerClick;

    private String clickThroughRate;

    private String advancedKpi1;

    private String advancedKpi2;

    public InsightDTO() {
    }

    public InsightDTO(String id, String platformId, String impressions, String clicks, String nanosScore, String websiteVisits,
                      String costPerClick, String clickThroughRate, String advancedKpi1, String advancedKpi2) {

        this.id = id;
        this.platformId = platformId;
        this.impressions = impressions;
        this.clicks = clicks;
        this.nanosScore = nanosScore;
        this.websiteVisits = websiteVisits;
        this.costPerClick = costPerClick;
        this.clickThroughRate = clickThroughRate;
        this.advancedKpi1 = advancedKpi1;
        this.advancedKpi2 = advancedKpi2;
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

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getNanosScore() {
        return nanosScore;
    }

    public void setNanosScore(String nanosScore) {
        this.nanosScore = nanosScore;
    }

    public String getWebsiteVisits() {
        return websiteVisits;
    }

    public void setWebsiteVisits(String websiteVisits) {
        this.websiteVisits = websiteVisits;
    }

    public String getCostPerClick() {
        return costPerClick;
    }

    public void setCostPerClick(String costPerClick) {
        this.costPerClick = costPerClick;
    }

    public String getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(String clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public String getAdvancedKpi1() {
        return advancedKpi1;
    }

    public void setAdvancedKpi1(String advancedKpi1) {
        this.advancedKpi1 = advancedKpi1;
    }

    public String getAdvancedKpi2() {
        return advancedKpi2;
    }

    public void setAdvancedKpi2(String advancedKpi2) {
        this.advancedKpi2 = advancedKpi2;
    }
}
