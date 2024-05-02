package com.example.test_task_for_clearsolution.repository;

import com.example.test_task_for_clearsolution.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserStorage {

    private final List<User> storage = new ArrayList<>(Arrays.asList(
            User.builder().firstName("John").lastName("Doe").address("Byg").birthDate(LocalDate.now().minusYears(19)).email("john_doe@gmail.com").phoneNumber("0667492234").build()
    ));

    public User save(User user){
        storage.add(user);
        return user;
    }

    public List<User> getByBirthDate(LocalDate from, LocalDate to){
        return storage.stream()
                .filter(it-> it.getBirthDate().isAfter(from) && it.getBirthDate().isBefore(to))
                .collect(Collectors.toList());
    }

    public void fullUpdate(User user, String email){
        storage.add(findIndexOfEmail(email), user);
    }

    public void update(User user, String email){
        int indexOfUpdatableUser = findIndexOfEmail(email);
        User userWhichWillBeUpdate = storage.get(indexOfUpdatableUser);
        if(!user.getFirstName().isEmpty()) userWhichWillBeUpdate.setFirstName(user.getFirstName());
        if(!user.getLastName().isEmpty()) userWhichWillBeUpdate.setLastName(user.getLastName());
        if(!user.getAddress().isEmpty()) userWhichWillBeUpdate.setAddress(user.getAddress());
        if(!user.getPhoneNumber().isEmpty()) userWhichWillBeUpdate.setPhoneNumber(user.getPhoneNumber());
        storage.add(findIndexOfEmail(email), userWhichWillBeUpdate);
    }

    public boolean delete(String email){
        return storage.removeIf(it -> it.getEmail().equals(email));
    }

    public User getByEmail(String email){
        return storage.get(findIndexOfEmail(email));
    }

    private int findIndexOfEmail(String email) {
        int index = -1;
        for (User user : storage) {
            if (user.getEmail().equals(email)) {
                index = storage.indexOf(user);
                break;
            }
        }
        return index;
    }
}
