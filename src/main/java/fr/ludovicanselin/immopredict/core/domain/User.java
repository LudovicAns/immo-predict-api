package fr.ludovicanselin.immopredict.core.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public final class User {

    private Identity id;
    private String username;
    private String email;
    private String password;
    private Role role;

}
