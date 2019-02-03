package greendatatest.dto;

import greendatatest.entity.LegalForm;

public class LegalFormConverter {

    public static LegalFormDto toData(LegalForm entity) {

        if (entity == null) return null;

        LegalFormDto data = new LegalFormDto();
        data.id = entity.getId();
        data.name = entity.getName();

        return data;
    }

    public static LegalForm toEntity(LegalFormDto data) {
        if (data == null) return null;

        LegalForm entity = new LegalForm();
        entity.setId(data.id);
        entity.setName(data.name);

        return entity;
    }

}
