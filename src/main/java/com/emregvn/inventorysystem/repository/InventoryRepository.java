package com.emregvn.inventorysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emregvn.inventorysystem.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	@Query("""
			select i from Inventory i inner join User u on i.user.id = u.id
			where u.id = :userId
			""")
	List<Inventory> findAllByUser(int userId);
	
}
