import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class ImgurService {

    @Value("${imgur.client-id}")
    private String clientId;

    private final RestTemplate restTemplate;

    public ImgurService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String uploadImage(byte[] imageBytes) {
        String url = "https://api.imgur.com/3/upload";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpHeaders multipartHeaders = new HttpHeaders();
        multipartHeaders.set("Authorization", "Client-ID " + clientId);

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(imageBytes, multipartHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();  // handle response appropriately
    }

    public String deleteImage(String imageHash) {
        String url = "https://api.imgur.com/3/image/" + imageHash;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }

    public String getImage(String imageHash) {
        String url = "https://api.imgur.com/3/image/" + imageHash;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}