package com.solera.wvpmanager.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.solera.wvpmanager.models.PartsModel;

@Repository
public interface PartsRepository extends JpaRepository<PartsModel, Integer> {
}