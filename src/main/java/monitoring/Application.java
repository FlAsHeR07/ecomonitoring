package monitoring;

import monitoring.model.*;
import monitoring.repository.EnterpriseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Разрешаем всех
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");

                System.out.println("*********** Completed ***************");
            }
        };
    }

    private Map<LocalDate, Double> generateMap(List<Double> input) {
        Map<LocalDate, Double> values = new HashMap<>();

        values.put(LocalDate.of(2024, 6, 1), input.get(0));
        values.put(LocalDate.of(2024, 7, 1), input.get(1));
        values.put(LocalDate.of(2024, 8, 1), input.get(2));
        values.put(LocalDate.of(2024, 9, 1), input.get(3));
        values.put(LocalDate.of(2024, 10, 1), input.get(4));
        values.put(LocalDate.of(2024, 11, 1), input.get(5));
        values.put(LocalDate.of(2024, 12, 1), input.get(6));
        values.put(LocalDate.of(2025, 1, 1), input.get(7));
        values.put(LocalDate.of(2025, 2, 1), input.get(8));
        values.put(LocalDate.of(2025, 3, 1), input.get(9));
        values.put(LocalDate.of(2025, 4, 1), input.get(10));
        values.put(LocalDate.of(2025, 5, 1), input.get(11));

        return values;
    }

}

