package service;

import com.mits.core.client.Person;

import java.util.List;

public interface UserManager {

    List<Person> getPeople(int start, int count);
}
