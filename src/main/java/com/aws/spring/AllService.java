package com.aws.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllService {

    private final InventoryRepository inventoryRepository;

    public String searchName(StockRequest request) {    // search name
        return inventoryRepository.findName(request.getName());
    }

    public String searchName(SellRequest request) {
        return inventoryRepository.findName(request.getName());
    }

    public Inventory addInventory(StockRequest request) {   // create name, price, amount
        Inventory inventory = new Inventory();
        inventory.setName(request.getName());
        inventory.setPrice(0);  // default 0
        if (request.getAmount() == null){   // check amount exists
            inventory.setAmount(1); // default 1
        } else {
            inventory.setAmount(Integer.parseInt(request.getAmount()));
        }
        return inventoryRepository.save(inventory);
    }

    public int updateInventory(StockRequest request) {  //update price, amount
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
        return inventory.stream().map(SumRequest::new).collect(Collectors.toMap(SumRequest::getName,SumRequest::getAmount));    // entity to dto
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
        if(request.getPrice() != null) {   // check price value exists or not
            price = Integer.parseInt(request.getPrice());
        } else {
            price = 0;
        }
        int total = price*amount;  // price x amount
        inventory.setName(name);
        inventory.setAmount(amount);
        inventory.setPrice(price);
        return inventoryRepository.updateAmountAndPrice(amount, total, name);
    }

    public void deleteAll() { // delete all
        inventoryRepository.deleteAll();
    }

}