package com.example.SpringLaptop;

import com.example.SpringLaptop.Entities.Laptop;
import com.example.SpringLaptop.Repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringLaptopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringLaptopApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop HP = new Laptop(null,"Pavilon","240",16);
		Laptop Asus = new Laptop(null,"Racer","480",32);

		repository.save(HP);
		repository.save(Asus);



	}

}
