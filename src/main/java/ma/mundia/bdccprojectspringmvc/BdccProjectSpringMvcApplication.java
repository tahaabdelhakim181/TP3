package ma.mundia.bdccprojectspringmvc;

import ma.mundia.bdccprojectspringmvc.Entites.Product;
import ma.mundia.bdccprojectspringmvc.respostory.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class BdccProjectSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdccProjectSpringMvcApplication.class, args);
    }
    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                            .name("Computer")
                            .price(5000)
                            .quantity(20)
                    .build());
            productRepository.save(Product.builder()
                    .name("Prinet")
                    .price(2000)
                    .quantity(40)
                    .build());
            productRepository.save(Product.builder()
                    .name("smartphone")
                    .price(4000)
                    .quantity(30)
                    .build());
             productRepository.findAll().forEach(p-> System.out.println(p.toString()));

        };
    }
}
