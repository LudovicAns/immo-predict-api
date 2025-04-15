package fr.ludovicanselin.immopredict.core.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public final class PredictionResult {

    private Identity id;
    private Double price;
    private Prediction prediction;

}
