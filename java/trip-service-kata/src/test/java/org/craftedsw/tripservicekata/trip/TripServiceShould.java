package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripServiceShould {

    @Test
    void throw_exception_if_user_not_logged() {

        User user = new User();
        TripService tripService = new FakeNotLoggedTripService();


        Assertions.assertThrows(UserNotLoggedInException.class,()->{tripService.getTripsByUser(user);});
    }

    class FakeNotLoggedTripService extends TripService{

        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}
