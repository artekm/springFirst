package pl.artur.springFirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringFirstApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("runner from main class");
    }

    @Bean
    CommandLineRunner createCLR() {
        return args -> System.out.println("runner from bean");
    }
}

@Component
class Runner implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("runner from runner class");

        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        System.out.println("Customers found with findAll():");
        repository.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("Customer found with findByLastName('Bauer'):");
        repository.findByLastName("Bauer").forEach(System.out::println);
        System.out.println();
    }
}
