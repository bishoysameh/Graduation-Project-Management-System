package com.graduationProject.gpManagementSystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage());
        }
    }
}



    //  ProposalRequest request = convertJsonToProposalObject(proposalJson);

    // public ProposalRequest convertJsonToProposalObject(String proposalJson) {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     ProposalRequest request;
    //     try {
    //         request = objectMapper.readValue(proposalJson, ProposalRequest.class);
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("Invalid JSON format" + e.getMessage());
    //     }
    //     return request;
    // }
    