package com.group10.se452_g10.payment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {


//    List<PaymentMethod> findAll(Sort sort);

    PaymentMethod findById(long id);

    void deleteAll();

    void delete(PaymentMethod entity);

    void deleteAllById(Iterable<? extends Long> longs);

    long count();

    boolean existsById(Long aLong);

}
