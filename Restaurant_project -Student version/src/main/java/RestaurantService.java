import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName){
          for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
            
        }
              return null; // Return null if no restaurant with the given name is found
            
       
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

     public double calculateOrderValue(String restaurantName, String... itemNames) throws restaurantNotFoundException {
        Restaurant restaurant = findRestaurantByName(restaurantName);

        if (restaurant == null) {
            throw new restaurantNotFoundException(restaurantName);
        }

        double totalOrderValue = 0.0;

        for (String itemName : itemNames) {
            Item item = restaurant.findItemByName(itemName);

            if (item != null) {
                totalOrderValue += item.getPrice();
            }
         
        }

        return totalOrderValue;
    }

}
