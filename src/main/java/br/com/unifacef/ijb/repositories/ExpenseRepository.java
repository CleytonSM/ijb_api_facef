package br.com.unifacef.ijb.repositories;

import br.com.unifacef.ijb.models.entities.Construction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Construction, Integer> {
}
