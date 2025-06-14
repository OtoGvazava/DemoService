package ge.edu.btu.demoservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarFilterService {
    private final static List<CarDto> cars = new ArrayList<>();

    static {
        cars.add(new CarDto("Mercedes Benz", 2017, "E 63 AMG", "Black"));
        cars.add(new CarDto("BMW", 2012, "330", "White"));
        cars.add(new CarDto("Audi", 2019, "RS 6", "Grey"));
        cars.add(new CarDto("Mercedes Benz", 2014, "C 63 AMG", "Yellow"));
        cars.add(new CarDto("Toyota", 2015, "Prius C", "Black"));
    }

    public ApiResponse getCars(CarDto filter) {
        var filteredCars = cars.stream().filter(item ->
                (filter.getManufacturer() == null || item.getManufacturer().equalsIgnoreCase(filter.getManufacturer())) &&
                        (filter.getModel() == null || item.getModel().equalsIgnoreCase(filter.getModel())) &&
                        (filter.getColor() == null || item.getColor().equalsIgnoreCase(filter.getColor())) &&
                        (filter.getYear() == null || item.getYear().equals(filter.getYear()))
        ).toList();


        var response = new ApiResponse();

        if (filteredCars.isEmpty()) {
            response.addError("empty", "No cars found");
        } else {
            response.addData("cars", filteredCars);
        }

        return response;
    }
}
