package campaign.dashboard.dto;

public class CampaignDTO {

    private String id;

    private String campaignUniqueId;

    private String name;

    private String goal;

    private String totalBudget;

    private String status;

    public CampaignDTO() {
    }

    public CampaignDTO(String id, String campaignUniqueId, String name, String goal, String totalBudget, String status) {
        this.id = id;
        this.campaignUniqueId = campaignUniqueId;
        this.name = name;
        this.goal = goal;
        this.totalBudget = totalBudget;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampaignUniqueId() {
        return campaignUniqueId;
    }

    public void setCampaignUniqueId(String campaignUniqueId) {
        this.campaignUniqueId = campaignUniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
