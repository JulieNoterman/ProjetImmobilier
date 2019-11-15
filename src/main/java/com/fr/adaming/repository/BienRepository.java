package com.fr.adaming.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Bien;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long>   {
 
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "UPDATE Bien SET vendu = true where id like :id", nativeQuery = true)
	public void vente(@Param(value = "id") Long id); 
	
	
	@Query(value = "SELECT * FROM Bien WHERE vendu = false", nativeQuery = true)
	public List<Bien> listNonVendu();
}
