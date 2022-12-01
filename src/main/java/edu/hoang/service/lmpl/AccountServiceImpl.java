package edu.hoang.service.lmpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hoang.entity.Account;

@Repository
public interface AccountServiceImpl extends JpaRepository<Account, String> {

}
