package campaign.dashboard.dto;

import java.util.List;

public class PlatformDTO {

    private String id;

    private String campaignId;

    private String platformType;

    private String platformTypeId;

    private String status;

    private String totalBudget;

    private String remainingBudget;

    private String startDate;

    private String endDate;

    private List<String> creatives;

    private List<String> insights;

    public PlatformDTO() {
    }

    public PlatformDTO(String id, String campaignId, String platformTypeId, String platformType, String status, String totalBudget,
                       String remainingBudget, String startDate, String endDate, List<String> creatives, List<String> insights) {

        this.id = id;
        this.campaignId = campaignId;
        this.platformTypeId = platformTypeId;
        this.platformType = platformType;
        this.status = status;
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creatives = creatives;
        this.insights = insights;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPlatformTypeId() {
        return platformTypeId;
    }

    public void setPlatformTypeId(String platformTypeId) {
        this.platformTypeId = platformTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(String remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<String> creatives) {
        this.creatives = creatives;
    }

    public List<String> getInsights() {
        return insights;
    }

    public void setInsights(List<String> insights) {
        this.insights = insights;
    }

}
