package campaign.dashboard.dto;

public class CreativeDTO {

    private String id;

    private String platformId;

    private String header;

    private String header1;

    private String header2;

    private String description;

    private String url;

    private String image;

    public CreativeDTO() {
    }

    public CreativeDTO(String id, String platformId, String header, String header1, String header2, String description, String url,
                       String image) {

        this.id = id;
        this.platformId = platformId;
        this.header = header;
        this.header1 = header1;
        this.header2 = header2;
        this.description = description;
        this.url = url;
        this.image = image;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
