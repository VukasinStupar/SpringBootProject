package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
	List<User> findByCompanyId(Long companyId);
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findUsersByRoleName(@Param("roleName") String roleName);

}

