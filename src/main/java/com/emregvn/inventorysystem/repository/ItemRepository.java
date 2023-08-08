package com.emregvn.inventorysystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emregvn.inventorysystem.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	Optional<Item> findByName(String name);
	
	@Query("""
			select i from Item i inner join Category c on i.category.id = c.id
			where c.id = :categoryId
			""")
	List<Item> findAllByCategory(int categoryId);

}
