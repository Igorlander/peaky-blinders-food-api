package com.peakyblinders.peakyblindersfood;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.services.KitchenService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PeakyBlindersFoodApiApplicationTests {

	@Autowired
	private KitchenService kitchenService;

	@Test
	public void whenRegistrationKitchenCorrectThenSuccessfully() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Chinesa");

		newKitchen = kitchenService.save(newKitchen);

		assertThat(newKitchen).isNotNull();
		assertThat(newKitchen.getId()).isNotNull();
	}
	@Test
	public void whenRegistrationKitchenIncorrectThenUnnamed(){


		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);
		try {
			newKitchen = kitchenService.save(newKitchen);
			Assertions.fail("Deveria dar erro");
		}catch (Exception e){
			assertThat(e.getClass()).isEqualTo(ConstraintViolationException.class);
		}
	}

	@Test
	public void shouldFailWhenDeleteKitchenInUse(){

		EntityInUseException inUseException = Assertions.assertThrows(EntityInUseException.class,() ->
				kitchenService.remove(1L));

		assertThat(inUseException).isNotNull();
	}
	/*@Test
	public void shouldFailWhenDeleteNonExistentKitchen(){
		EntityNotFoundException error =
						Assertions.assertThrows(EntityNotFoundException.class,() ->{
				kitchenService.remove(100L);
								});
		assertThat(error).isNotNull();
	}*/

}
