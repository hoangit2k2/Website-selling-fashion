package edu.hoang.service.lmpl;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hoang.entity.Account;
import edu.hoang.entity.Authority;

@Repository
public interface AuthServiceImpl extends JpaRepository<Authority, Integer> {
	public Set<Authority> findByAccount(Account account);
}
