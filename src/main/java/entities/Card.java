package entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "desc",
        "due",
        "start",
        "dueComplete",
        "idList",
        "urlSource",
        "fileSource",
        "mimeType",
        "idCardSource",
        "keepFromSource",
        "address",
        "locationName",
        "coordinates"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    @JsonProperty("id") @Getter @Setter
    private String id;
    @JsonProperty("name") @Getter @Setter
    private String name;
    @JsonProperty("desc") @Getter @Setter
    private String desc;
    @JsonProperty("due") @Getter @Setter
    private String due;
    @JsonProperty("start") @Getter @Setter
    private String start;
    @JsonProperty("dueComplete") @Getter @Setter
    private boolean dueComplete;
    @JsonProperty("idList") @Getter @Setter
    private String idList;
    @JsonProperty("urlSource") @Getter @Setter
    private String urlSource;
    @JsonProperty("fileSource") @Getter @Setter
    private String fileSource;
    @JsonProperty("mimeType") @Getter @Setter
    private String mimeType;
    @JsonProperty("idCardSource") @Getter @Setter
    private String idCardSource;
    @JsonProperty("keepFromSource") @Getter @Setter
    private String keepFromSource;
    @JsonProperty("address") @Getter @Setter
    private String address;
    @JsonProperty("locationName") @Getter @Setter
    private String locationName;
    @JsonProperty("coordinates") @Getter @Setter
    private String coordinates;
}
