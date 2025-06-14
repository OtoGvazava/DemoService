package ge.edu.btu.demoservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDto {
    private String manufacturer;
    private Integer year;
    private String model;
    private String color;
}
