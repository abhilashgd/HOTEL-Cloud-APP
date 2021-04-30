package meal.repository;
import org.springframework.data.repository.CrudRepository;

import meal.domain.Meal;
public interface MealRepository extends CrudRepository<Meal, Long> {}
