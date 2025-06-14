package ge.edu.btu.demoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarFilterController {
    private final CarFilterService carFilterService;

    @Autowired
    public CarFilterController(CarFilterService carFilterService) {
        this.carFilterService = carFilterService;
    }

    @PostMapping("/cars")
    public ApiResponse getCars(@RequestBody CarDto carDto) {
        return carFilterService.getCars(carDto);
    }
}
