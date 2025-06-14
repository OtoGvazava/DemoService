package ge.edu.btu.demoservice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class ApiResponse {
    private final Map<String, Object> data = new HashMap<>();
    private final Map<String, Object> error = new HashMap<>();

    public ApiResponse(String key, Object value) {
        this.data.put(key, value);
    }

    public ApiResponse addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ApiResponse addError(String key, Object value) {
        this.error.put(key, value);
        return this;
    }
}
