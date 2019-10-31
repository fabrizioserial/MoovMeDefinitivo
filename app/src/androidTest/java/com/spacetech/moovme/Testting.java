package com.spacetech.moovme;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.spacetech.moovme.Repository.RepositoryUser;
import com.spacetech.moovme.exceptions.Mooveme;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.exceptions.RepositoryUser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Testting {
    @Test
    public void useAppContext() {
        Mooveme mooveme = new Mooveme(new RepositoryUser());
        String name = "Fabrizio";
        String number = "2223";
        PhoneNumber phoneNumberofFabrizio = new PhoneNumber(number);
        Mooveme.register(name,phoneNumberofFabrizio);

        Assert.assertTrue(Mooveme.login(phoneNumberofFabrizio));

    }
}
