package com.aws.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class v1Controller {

    private final AllService allService;

    private final RestExceptionHandler exception;

    // Update, Create Stocks
    @PostMapping(value = "/inventory")
    public ResponseEntity postStocks(@RequestBody StockRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "00.00.00.00:80/rest/inventory/" + request.getName());  //header: Location with elastic ip
        if (allService.searchName(request) == null){    //Check name exists
            allService.addInventory(request);
        } else {
            allService.updateInventory(request);
        }
        return ResponseEntity.ok().headers(headers).body(request);
    }

    //Check Stocks
    @GetMapping(value = "/inventory")
    public ResponseEntity getStocks() {
        return ResponseEntity.ok(allService.showInventoryAll());
    }

    @GetMapping(value = "/inventory/{name}")   //get path
    public ResponseEntity getStocksName(@PathVariable String name) {
        return ResponseEntity.ok(allService.showInventory(name));
    }

    //Sales
    @PostMapping(value = "/sell")
    public ResponseEntity<SellRequest> postSales(@RequestBody SellRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "00.00.00.00:80/rest/sell/" + request.getName());
        if (allService.searchName(request) == null) {
            return exception.handleError(); //if name doesn't exist, return error
        } else {
            allService.subtractInventory(request);
            return ResponseEntity.ok().headers(headers).body(request);
        }
    }

    //Check Sales
    @GetMapping(value = "/sell")
    public ResponseEntity getSales(){
        return ResponseEntity.ok(allService.showPrice().get(0));
    }   //get index 0

    //Delete All
    @DeleteMapping("/inventory")
    public void deleteStocks(){
        allService.deleteAll();
    }
}
