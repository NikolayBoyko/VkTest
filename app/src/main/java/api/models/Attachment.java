package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import api.models.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {

    @JsonProperty("type")
    private String mType;

    @JsonProperty("doc")
    private Document mDocument;

    public String getmType() {
        return mType;
    }

    public Document getmDocument() {
        return mDocument;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "mType='" + mType + '\'' +
                ", mDocument=" + mDocument +
                '}';
    }

}