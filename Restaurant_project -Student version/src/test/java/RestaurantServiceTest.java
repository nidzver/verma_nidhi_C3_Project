import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    // Refactor method to add a restaurant with menu items
    private void addRestaurantWithMenu(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        restaurant = service.addRestaurant(name, location, openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // Refactor method to assert the size of the restaurant list
    private void assertRestaurantListSize(int expectedSize) {
        assertEquals(expectedSize, service.getRestaurants().size());
    }

    // >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //added missing lines of test case 
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        Restaurant foundRestaurant = service.findRestaurantByName("Amelie's cafe");
        assertEquals("Amelie's cafe", foundRestaurant.getName());
    }
    
    //added missing lines of test case 
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() {
        assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Pantry"));
    }
    // <<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

     // <<<<<<<<<<<<<<<<<<<<<CALCULATE ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>

    //Failing test case for calculate order value
   @Test
    @DisplayName("Calculate order value for a non-existing restaurant should throw an exception")
    public void calculateOrderValueForNonExistingRestaurant() {
    assertThrows(restaurantNotFoundException.class, () -> service.calculateOrderValue("Pantry d'or", "Some Item"));
   }
     //Passing test case for calculate order value
    @Test
    @DisplayName("Calculate order value for an existing restaurant and items")
    public void calculateOrderValueForExistingRestaurantAndItems() throws restaurantNotFoundException {
    addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));

    // Assuming "Sweet corn soup" and "Vegetable lasagne" are items in the menu
    double orderValue = service.calculateOrderValue("Amelie's cafe", "Sweet corn soup", "Vegetable lasagne");

    assertEquals(388.0, orderValue);
   }

    // <<<<<<<<<<<<<<<<<<<<<CALCULATE ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertRestaurantListSize(initialNumberOfRestaurants - 1);
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() {
        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertRestaurantListSize(initialNumberOfRestaurants + 1);
    }
    // <<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
     
  
 }
