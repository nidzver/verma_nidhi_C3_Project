import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // Refactor method to add a restaurant with menu items
    private void addRestaurantWithMenu(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        restaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // Refactor method to assert the size of the menu
    private void assertMenuSize(int expectedSize) {
        assertEquals(expectedSize, restaurant.getMenu().size());
    }

    @BeforeEach
    public void setUp() {
        // Reset the restaurant before each test
        restaurant = null;
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //added missing lines of test case
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        assertTrue(restaurant.isRestaurantOpen());  
    }
     
     //added missing lines of test case
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("12:00:00"));
        assertFalse(restaurant.isRestaurantOpen());
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    
    // >>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertMenuSize(initialMenuSize + 1);
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertMenuSize(initialMenuSize - 1);
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
