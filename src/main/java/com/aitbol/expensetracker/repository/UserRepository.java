package com.aitbol.expensetracker.repository;

import com.aitbol.expensetracker.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u WHERE u.lastName = :lastName")
    Collection<User> findUsersByLastName(String lastName);

    @Query(value = "SELECT * FROM user WHERE u.lastName = :lastName", nativeQuery = true)
    Collection<User> findUsersByLastNameNative(String lastName);

}
