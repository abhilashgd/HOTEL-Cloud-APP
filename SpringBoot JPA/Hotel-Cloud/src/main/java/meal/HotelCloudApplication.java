package meal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import meal.domain.Ingredient;
import meal.domain.Ingredient.Type;
import meal.repository.IngredientRepository;

@SpringBootApplication
public class HotelCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelCloudApplication.class, args);
	}
	
	 @Bean
	  public CommandLineRunner dataLoader(IngredientRepository repo) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	    	  repo.save(new Ingredient("MANC", "MANCHURIAN", Type.STARTERS));
	    	  repo.save(new Ingredient("PAPD", "PAPAD", Type.STARTERS));
	    	  repo.save(new Ingredient("RICE", "RICE", Type.MAIN));
	    	  repo.save(new Ingredient("ROTI", "ROTI", Type.MAIN));
	    	  repo.save(new Ingredient("RASM", "RASSAM SAMBAR", Type.SIDES));
	    	  repo.save(new Ingredient("CURY", "GRAVY", Type.SIDES));
	    	  repo.save(new Ingredient("CURD", "CURD", Type.ADDONS));
	          repo.save(new Ingredient("SWET", "SWEETS", Type.ADDONS));
	    	  repo.save(new Ingredient("MASL", "MASALA PURI", Type.CHATS));
	    	  repo.save(new Ingredient("DAHI", "DAHI PURI", Type.CHATS));
	      }
	    };
	  }

}
