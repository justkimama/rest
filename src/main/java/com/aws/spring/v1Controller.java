package com.aws.spring;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class v1Controller {

    private final AllService allService;

//    private final RestExceptionHandler exception;

    // Update, Create Stocks
    @PostMapping(value = "/stocks")
    public ResponseEntity postStocks(@RequestBody StockRequest request, HttpServletRequest http) {
        HttpHeaders headers = new HttpHeaders();
        String address = http.getRemoteAddr();
        headers.add("Location", address + ":80/v1/stocks/" + request.getName());
        if (allService.searchName(request) == null){    //Check name exists
            allService.addInventory(request);
        } else {
            allService.updateInventory(request);
        }
        return ResponseEntity.ok().headers(headers).body(request);
    }

    //Check Stocks
    @GetMapping(value = "/stocks")
    public ResponseEntity getStocks() {
        return ResponseEntity.ok(allService.showInventoryAll());
    }

    @GetMapping(value = "/stocks/{name}")
    public ResponseEntity getStocksName(@PathVariable String name) {
        return ResponseEntity.ok(allService.showInventory(name));
    }

    //Sales
    @PostMapping(value = "/sales")
    public ResponseEntity<SellRequest> postSales(@RequestBody SellRequest request, HttpServletRequest http) {
        HttpHeaders headers = new HttpHeaders();
        String address = http.getRemoteAddr();
        headers.add("Location", address + ":80/v1/sales/" + request.getName());
        if (allService.searchName(request) == null) {
//            return exception.handleError();
            return ResponseEntity.ok(request);
        } else {
            allService.subtractInventory(request);
            return ResponseEntity.ok().headers(headers).body(request);
        }
    }

    //Check Sales
    @GetMapping(value = "/sales")
    public ResponseEntity getSales(){
        return ResponseEntity.ok(allService.showPrice().get(0));
    }

    //Delete All
    @DeleteMapping("/stocks")
    public void deleteStocks(){
        allService.deleteAll();
    }
}
