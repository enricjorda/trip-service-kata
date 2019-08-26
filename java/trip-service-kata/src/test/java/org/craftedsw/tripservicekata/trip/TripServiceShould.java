package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceShould {

    public static final User GUEST = null;
    private TripService tripService;
    private User user;
    private User loggedInUser;

    @BeforeEach
    void setUp() {
        loggedInUser = new User();
        tripService = new TripService(loggedInUser);
        user = new User();
    }

    @Test
    void throw_exception_if_user_not_logged_in() {
        loggedInUser = GUEST;
        tripService = new TripService(loggedInUser);

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(new User());
        });
    }

    @Test
    void return_and_empty_list_for_non_friend_logged_user() {
        assertTrue(tripService.getTripsByUser(this.user).isEmpty());
    }

    @Test
    void return_and_empty_list_if_the_logged_user_is_not_a_friend() {
        User friendOfUser = new User();
        user.addFriend(friendOfUser);

        assertTrue(tripService.getTripsByUser(user).isEmpty());
    }

    @Test
    void return_and_empty_list_if_the_logged_user_friend_dont_have_any_trips() {
        User friendOfUser = new User();
        user.addFriend(friendOfUser);
        user.addFriend(this.loggedInUser);

        assertTrue(tripService.getTripsByUser(user).isEmpty());
    }

    @Test
    void return_the_trips_of_a_friend() {

        User friendOfUser = new User();
        user.addFriend(friendOfUser);
        user.addFriend(this.loggedInUser);

        Trip trip = new Trip();
        user.addTrip(trip);

        assertEquals(trip, tripService.getTripsByUser(user).get(0));
    }


}
