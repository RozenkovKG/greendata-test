package greendatatest.controller;

import greendatatest.dao.DepositDao;
import greendatatest.dto.DepositConverter;
import greendatatest.dto.DepositDto;
import greendatatest.dto.DepositListDto;
import greendatatest.entity.Deposit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deposit")
public class DepositController {

    private final Logger logger = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    DepositDao repository;

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getDeposits(
            @RequestParam(name = "clientId", required = false) Integer clientId,
            @RequestParam(name = "bankId", required = false) Integer bankId
    ) {
        logger.info("#getClients: get Client with clientId = " + clientId +
                " & bankId = " + bankId);

        DepositListDto depositListData = null;

        try {
            depositListData = DepositConverter.toListData(
                    repository.findAllWithFilters(
                            clientId,
                            bankId
                    )
            );
        } catch (Exception e) {
            logger.error("#getDeposits", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(depositListData);
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> setDeposit(@RequestBody DepositDto deposit) {
        logger.info("#setDeposit: " + deposit.toString());
        Deposit entity = null;
        try {
            entity = repository.save(DepositConverter.toEntity(deposit));
        } catch (Exception e) {
            logger.error("#setDeposit", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(DepositConverter.toData(entity));
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Integer id) {
        logger.info("#deleteDeposit: id = " + id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("#deleteDeposit", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
