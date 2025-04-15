package fr.ludovicanselin.immopredict.core.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public final class EstimationResult {

    private Identity id;
    private Estimation estimation;
    private Double price;

}
