package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceShould {

    private User user;
    @Test
    void throw_exception_if_user_not_logged() {
        FakeTripService fakeTripService = new FakeTripService();

        assertThrows(UserNotLoggedInException.class,()->{fakeTripService.getTripsByUser(user);});
    }

    @Test
    void return_and_empty_list_for_non_friend_logged_user() {
        FakeTripService fakeTripService = new FakeTripService();
        fakeTripService.setFakeUser(new User());

        assertIterableEquals(new ArrayList<>(), fakeTripService.getTripsByUser(new User()));
    }

    class FakeTripService extends TripService {

        private User fakeUser;

        public void setFakeUser(User fakeUser) {
            this.fakeUser = fakeUser;
        }

        @Override
        protected User getLoggedUser() {
            return this.fakeUser;
        }
    }
}
