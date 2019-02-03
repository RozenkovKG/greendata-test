package greendatatest.dto;

import greendatatest.entity.Client;
import greendatatest.entity.LegalForm;

import java.util.HashMap;
import java.util.Map;

public class ClientConverter {

    public static ClientDto toData(Client entity) {

        if (entity == null) return null;

        ClientDto data = new ClientDto();
        data.id = entity.getId();
        data.name = entity.getName();
        data.shortName = entity.getShortName();
        data.address = entity.getAddress();
        if (entity.getLegalForm() != null) {
            data.legalFormId = entity.getLegalForm().getId();
        }

        return data;
    }

    public static Client toEntity(ClientDto data) {
        if (data == null) return null;

        Client entity = new Client();
        entity.setId(data.id);
        entity.setName(data.name);
        entity.setShortName(data.shortName);
        entity.setAddress(data.address);
        if (data.legalFormId != null) {
            LegalForm legalForm = new LegalForm();
            legalForm.setId(data.legalFormId);
            entity.setLegalForm(legalForm);
        }

        return entity;
    }

    public static ClientListDto toListData(Iterable<Client> entities) {
        ClientListDto data = new ClientListDto();
        Map<Integer, LegalForm> legalFormMap = new HashMap<Integer, LegalForm>();

        for (Client c : entities) {
            data.clients.add(toData(c));
            if (!legalFormMap.containsKey(c.getLegalForm().getId())) {
                legalFormMap.put(
                        c.getLegalForm().getId(),
                        c.getLegalForm()
                );
            }
        }

        legalFormMap.values().forEach(d -> {
            data.legalForms.add(LegalFormConverter.toData(d));
        });

        return data;
    }

}
