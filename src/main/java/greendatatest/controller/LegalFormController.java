package greendatatest.controller;

import greendatatest.dao.LegalFormDao;
import greendatatest.dto.LegalFormConverter;
import greendatatest.dto.LegalFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("legal_form")
public class LegalFormController {

    private final Logger logger = LoggerFactory.getLogger(LegalFormController.class);

    @Autowired
    LegalFormDao repository;

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getLegalForms() {

        logger.info("#getLegalForms: get all legal forms");

        List<LegalFormDto> legalForms = new ArrayList<LegalFormDto>();

        try {
            repository.findAll().forEach(d -> {
                legalForms.add(LegalFormConverter.toData(d));
            });
        } catch (Exception e) {
            logger.error("#getLegalForms", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(legalForms);
    }

}
