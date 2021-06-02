import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
  //  Restaurant restaurant =  new Restaurant("Subway", "Chennai", LocalTime.parse("08:00"),LocalTime.parse("19:00"));
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    Restaurant restaurantMock = Mockito.spy(restaurant);
    ArrayList <String> itemNames = new ArrayList <String> ();

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
                Mockito.when(restaurantMock.getCurrentTime()).thenReturn(LocalTime.parse("11:06"));
        assertEquals(true,restaurantMock.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(LocalTime.parse("23:01"));
        assertEquals(false,restaurantMock.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }


  /*  public void Order_Value_should_not_be_negative (){
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);
        ArrayList <Item> items = new ArrayList <Item>();
        items.add(new Item("Vegetable lasagne", 269));
        items.add(new Item("Sizzling brownie",319));

      //  assertEquals (false, 0>restaurant.getOrderValue(items));
    } */
    @Test
    public void should_return_Zero_if_no_item_provided ()
    {
        assertEquals(0, restaurant.getOrderValue(itemNames));
    }

    @Test
    public void Should_return_order_value_as_expected()
    {
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);

        itemNames.add("Vegetable lasagne");
        itemNames.add("Sizzling brownie");
        assertEquals(319+269, restaurant.getOrderValue(itemNames));
    }





    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}