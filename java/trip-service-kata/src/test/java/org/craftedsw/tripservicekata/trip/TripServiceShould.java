package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceShould {

    public static final User GUEST = null;
    private FakeTripService fakeTripService;
    private User user;
    private User loggedInUser;

    @BeforeEach
    void setUp() {
        fakeTripService = new FakeTripService();
        loggedInUser = new User();
        user = new User();
    }

    @Test
    void throw_exception_if_user_not_logged_in() {
        loggedInUser = GUEST;
        assertThrows(UserNotLoggedInException.class, () -> {
            fakeTripService.getTripsByUser(new User());
        });
    }

    @Test
    void return_and_empty_list_for_non_friend_logged_user() {
        assertTrue(fakeTripService.getTripsByUser(this.user).isEmpty());
    }

    @Test
    void return_and_empty_list_if_the_logged_user_is_not_a_friend() {
        User friendOfUser = new User();
        user.addFriend(friendOfUser);

        assertTrue(fakeTripService.getTripsByUser(user).isEmpty());
    }

    @Test
    void return_and_empty_list_if_the_logged_user_friend_dont_have_any_trips() {
        User friendOfUser = new User();
        user.addFriend(friendOfUser);
        user.addFriend(this.loggedInUser);

        assertTrue(fakeTripService.getTripsByUser(user).isEmpty());
    }

    @Test
    void return_the_trips_of_a_friend() {

        User friendOfUser = new User();
        user.addFriend(friendOfUser);
        user.addFriend(this.loggedInUser);

        Trip trip = new Trip();
        user.addTrip(trip);

        assertEquals(trip,fakeTripService.getTripsByUser(user).get(0));
    }

    private class FakeTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> getTripsDAOByUser(User user) {
            return user.trips();
        }
    }
}
