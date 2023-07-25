package dev.hyunjae.springbatch.study.repository;

import dev.hyunjae.springbatch.study.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
