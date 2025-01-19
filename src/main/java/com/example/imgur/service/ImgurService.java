package com.example.imgur.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ImgurService {

//    @Value("${imgur.client-id}")
//    private String clientId;

    private final RestTemplate restTemplate = new RestTemplate();

    public String uploadImage(byte[] imageData) {
        String uploadUrl = "https://api.imgur.com/3/image";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uploadUrl)
                .queryParam("image", imageData);
        String response = restTemplate.postForObject(builder.toUriString(), imageData, String.class);
        return response; // Parse and extract image URL from Imgur's response
    }

    public String deleteImage(String imageHash) {
        String deleteUrl = "https://api.imgur.com/3/image/" + imageHash;
        restTemplate.delete(deleteUrl);
        return "Image deleted successfully.";
    }
}