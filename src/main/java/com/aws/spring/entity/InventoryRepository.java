package com.aws.spring.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM aws_table a WHERE a.name = binary :name"    //For NativeQuery
            , nativeQuery = true)
    String findName(String name);

    @Query(value = "SELECT * FROM aws_table"
            , nativeQuery = true)
    List<Inventory> findAllAmount();

    @Query(value = "SELECT '' as name, '' as amount, sum(a.price) as price FROM aws_table a"
            , nativeQuery = true)
    List<Inventory> findAllPrice();

    @Query(value = "SELECT * FROM aws_table WHERE name = :name"
            , nativeQuery = true)
    List<Inventory> findAllAmountByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE aws_table a SET a.amount = a.amount + :amount WHERE a.name = :name"
            , nativeQuery = true)
    int updateAmount(int amount, String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE aws_table a SET a.amount = a.amount - :amount, a.price = a.price + :price WHERE a.name = :name"
            , nativeQuery = true)
    int updateAmountAndPrice(int amount, int price, String name);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE aws_table"
            ,nativeQuery = true)
    void deleteAll();
}
