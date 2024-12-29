package org.dev.UserFinder.repository;

import org.dev.UserFinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE " +
            "(:id IS NULL OR u.id = :id) AND " +
            "(:name IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))) AND"+
            "(:lname IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lname, '%')))AND" +
            "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND" +
            "(:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')))")
    List<User> search(@Param("id") Integer id, @Param("name")String name, @Param("lname")String lname, @Param("email") String email, @Param("username") String username);

    @Query("Select u from User u where u.username=:username")
    Optional<User> findUserByName(@Param("username") String username);
}
