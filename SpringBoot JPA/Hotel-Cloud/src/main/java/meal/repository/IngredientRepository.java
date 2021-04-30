package meal.repository;
import org.springframework.data.repository.CrudRepository;
import meal.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
}
