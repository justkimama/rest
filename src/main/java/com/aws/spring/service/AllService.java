package com.aws.spring.service;

import com.aws.spring.entity.Inventory;
import com.aws.spring.entity.InventoryRepository;
import com.aws.spring.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllService {

    private final InventoryRepository inventoryRepository;

    public AddRequest changeForm(StockRequest stockRequest) {
        AddRequest request = new AddRequest();
        request.setName(stockRequest.getName());
        request.setAmount(Integer.parseInt(stockRequest.getAmount()));
        return request;
    }

    public String searchName(StockRequest request) {
        return inventoryRepository.findName(request.getName());
    }

    public String searchName(SellRequest request) {
        return inventoryRepository.findName(request.getName());
    }

    public Inventory addInventory(StockRequest request) {
        Inventory inventory = new Inventory();
        inventory.setName(request.getName());
        inventory.setPrice(0);
        if (request.getAmount() == null){
            inventory.setAmount(1);
        } else {
            inventory.setAmount(Integer.parseInt(request.getAmount()));
        }
        return inventoryRepository.save(inventory);
    }

    public int updateInventory(StockRequest request) {
        Inventory inventory = new Inventory();
        inventory.setName(request.getName());
        if (request.getAmount() == null){
            inventory.setAmount(1);
        } else {
            inventory.setAmount(Integer.parseInt(request.getAmount()));
        }
        return inventoryRepository.updateAmount(inventory.getAmount(),inventory.getName());
    }

    public Map<String, Integer> showInventory(String name) {
        List<Inventory> inventory = inventoryRepository.findAllAmountByName(name);
        return inventory.stream().map(SumRequest::new).collect(Collectors.toMap(SumRequest::getName,SumRequest::getAmount));
    }

    public Map<String, Integer> showInventoryAll() {
        List<Inventory> inventory = inventoryRepository.findAllAmount();
        return inventory.stream().map(SumRequest::new).collect(Collectors.toMap(SumRequest::getName,SumRequest::getAmount));
    }

    public List<PriceRequest> showPrice() {
        List<Inventory> inventory = inventoryRepository.findAllPrice();
        return inventory.stream().map(PriceRequest::new).collect(Collectors.toList());
    }

    public int subtractInventory (SellRequest request) {
        Inventory inventory = new Inventory();
        String name = request.getName();
        int amount = 1;
        int price;
        if (request.getAmount() != null){
            amount = Integer.parseInt(request.getAmount());
        }
        if(request.getPrice() != null) {   //Check price value exists or not
            price = Integer.parseInt(request.getPrice());
        } else {
            price = 0;
        }
        int total = price*amount;  //Price X Amount
        inventory.setName(name);
        inventory.setAmount(amount);
        inventory.setPrice(price);
        return inventoryRepository.updateAmountAndPrice(amount, total, name);
    }

    public void deleteAll() {
        inventoryRepository.deleteAll();
    }

}