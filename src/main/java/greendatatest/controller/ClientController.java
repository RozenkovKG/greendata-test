package greendatatest.controller;

import greendatatest.dao.ClientDao;
import greendatatest.dto.ClientConverter;
import greendatatest.dto.ClientDto;
import greendatatest.dto.ClientListDto;
import greendatatest.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientDao repository;

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getClients(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "shortName", defaultValue = "") String shortName,
            @RequestParam(name = "address", defaultValue = "") String address,
            @RequestParam(name = "legalFormId", required = false) Integer legalFormId
    ) {

        logger.info("#getClients: get Client with name = " + name +
                " & shortName = " + shortName +
                " & address = " + address +
                " & legalFormId = " + legalFormId);

        ClientListDto clientListData = null;

        try {
            clientListData = ClientConverter.toListData(repository.findAllWithFilters(
                    name,
                    shortName,
                    address,
                    legalFormId
            ));
        } catch (Exception e) {
            logger.error("#getClients", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(clientListData);
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> setClient(@RequestBody ClientDto client) {
        logger.info("#setClient: " + client.toString());
        Client entity = null;
        try {
            entity = repository.save(ClientConverter.toEntity(client));
        } catch (Exception e) {
            logger.error("#setClient", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ClientConverter.toData(entity));
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        logger.info("#deleteClient: id = " + id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("#deleteClient", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
