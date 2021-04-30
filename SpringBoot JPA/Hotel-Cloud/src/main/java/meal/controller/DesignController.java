package meal.controller;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import meal.domain.Ingredient;
import meal.domain.Meal;
import meal.domain.Order;
import meal.domain.Ingredient.Type;
import meal.repository.IngredientRepository;
import meal.repository.MealRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {
	
	private final IngredientRepository ingredientRepo;
	private MealRepository designRepo;
	@Autowired
	public DesignController(IngredientRepository ingredientRepo,
			MealRepository designRepo) {
	this.ingredientRepo = ingredientRepo;
	this.designRepo=designRepo;
	}
	
	@ModelAttribute(name = "order")
	  public Order order() {
	  return new Order();
	  }
	  @ModelAttribute(name = "design")
	  public Meal design() {
	  return new Meal();
	  }
	  
	
	@GetMapping()
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		/*
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("MANC", "MANCHURIAN", Type.STARTERS),
				new Ingredient("PAPD", "PAPAD", Type.STARTERS),
				new Ingredient("RICE", "RICE", Type.MAIN),
				new Ingredient("ROTI", "ROTI", Type.MAIN),
				new Ingredient("RASM", "RASSAM SAMBAR", Type.SIDES),
				new Ingredient("CURY", "GRAVY", Type.SIDES),
				new Ingredient("CURD", "CURD", Type.ADDONS),
				new Ingredient("SWET", "SWEETS", Type.ADDONS),
				new Ingredient("MASL", "MASALA PURI", Type.CHATS),
				new Ingredient("DAHI", "DAHI PURI", Type.CHATS)
				);
		*/
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		model.addAttribute(type.toString(),
		filterByType(ingredients, type));
		}
		model.addAttribute("design", new Meal());
		return "design";
	}
	
	  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

	        return ingredients.stream()
	                .filter(x -> x.getType().equals(type))
	                .collect(Collectors.toList());
	    }
	  
	  @PostMapping
	  public String processDesign(
	  @Valid Meal meal, Errors errors,
	  @ModelAttribute Order order) {
	  if (errors.hasErrors()) {
	  return "design";
	  }
	  Meal saved = designRepo.save(meal);
	  order.addDesign(saved);
	  return "redirect:/orders/current";
	  }
}
	