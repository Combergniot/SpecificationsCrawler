package com.panamodels.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificationDTO {

    @NotNull
    private String model;
    private String specUrl;
    private String countryId;
    private Long productId;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecUrl() {
        return specUrl;
    }

    public void setSpecUrl(String specUrl) {
        this.specUrl = specUrl;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "SpecificationDTO{" +
                "model='" + model + '\'' +
                ", specUrl='" + specUrl + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }
}
