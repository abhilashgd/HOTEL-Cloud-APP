package meal.domain;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("unused")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity

public class Ingredient {
	@Id
	private final String id;
	private final String name;
	
	 @Enumerated(EnumType.STRING)
	private final Type type;
	public static enum Type {STARTERS, SIDES, MAIN, DESSERTS, CHATS, ADDONS}
}