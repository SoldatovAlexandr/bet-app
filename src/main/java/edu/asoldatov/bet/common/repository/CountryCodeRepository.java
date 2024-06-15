package edu.asoldatov.bet.common.repository;

import edu.asoldatov.bet.common.model.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {

    Optional<CountryCode> findByName(String countryCode);

}
