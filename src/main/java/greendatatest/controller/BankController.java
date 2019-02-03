package greendatatest.controller;

import greendatatest.dao.BankDao;
import greendatatest.dto.BankConverter;
import greendatatest.dto.BankDto;
import greendatatest.entity.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bank")
public class BankController {

    @Autowired
    private BankDao repository;

    private final Logger logger = LoggerFactory.getLogger(BankController.class);

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getBanks(@RequestParam(name = "name", required = false) String name) {

        logger.info("#getBanks: get Bank with name = " + name);

        List<BankDto> banksList = new ArrayList<BankDto>();

        try {
            if (name == null) {
                repository.findAll().forEach(d -> {
                    banksList.add(BankConverter.toData(d));
                });
            } else {
                repository.findByNameContaining(name).forEach(d -> {
                    banksList.add(BankConverter.toData(d));
                });
            }
        } catch (Exception e) {
            logger.error("#getBanks", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(banksList);
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> setBank(@RequestBody BankDto bank) {
        logger.info("#setBank: " + bank.toString());
        Bank entity = null;
        try {
            entity = repository.save(BankConverter.toEntity(bank));
        } catch (Exception e) {
            logger.error("#setBank", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(BankConverter.toData(entity));
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBank(@PathVariable Integer id) {
        logger.info("#deleteBank: id = " + id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("#deleteBank", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
