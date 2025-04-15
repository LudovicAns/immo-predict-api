package fr.ludovicanselin.immopredict.core.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public final class Prediction {

    private Identity id;
    private Double surfaceArea;
    private PropertyType propertyType;
    private String city;
    private Date createdAt;
    private User user;
    private PredictionResult result;

}
