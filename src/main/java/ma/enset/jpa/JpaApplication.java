package ma.enset.jpa;

import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;
import ma.enset.jpa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			User user=new User();
			user.setUserName("Medbel");
			user.setPassword("medbel123");
			userService.addNewUser(user);


			User user2=new User();
			user2.setUserName("admin");
			user2.setPassword("medbel123");
			userService.addNewUser(user2);

			Stream.of("ADMIN","STUDENT","USER").forEach(roleName->{
				Role role=new Role();
				role.setRoleName(roleName);
				role.setDescription("Description de "+roleName);
				userService.addNewRole(role);
			});

			userService.addRoleToUser("admin","ADMIN");
			userService.addRoleToUser("admin","USER");

			userService.addRoleToUser("Medbel","STUDENT");
			userService.addRoleToUser("Medbel","USER");


			try{
				User connectedUser = userService.authenticate("admin", "medbel123");
				System.out.println("Bienvenue "+connectedUser.getUserName());
				System.out.println("Vous avez les roles suivants:");
				connectedUser.getRoles().forEach(r->{
					System.out.println(r.getRoleName()+" : "+r.getDescription());
				});
			}catch (Exception e){
				System.out.println("Erreur d'authentification");
			}
		};
	}
	}
