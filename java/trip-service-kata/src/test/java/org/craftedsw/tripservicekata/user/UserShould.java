package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserShould {

    @Test
    void return_false_if_not_friends_with_user() {
        User user = new User();

        assertFalse(user.isFriendsWith(new User()));
    }

    @Test
    void return_true_if_friends_with_user() {
        User user = new User();
        User friend = new User();

        user.addFriend(friend);

        assertTrue(user.isFriendsWith(friend));
    }

}
