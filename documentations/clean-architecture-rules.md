# Règles de Nommage pour l'Architecture Clean - Partie Application

## Conventions de nommage

1. **Nom des packages** :
    - Tous les noms des packages doivent être en **minuscules** et suivre la structure hiérarchique.
    - Package principal pour cette couche : `application`.
    - Sous-packages :
        - `usecase` pour les cas d'utilisation.
        - `dto` pour les Transfer Objects (Objets de Transfert de Données).
        - `port` pour les interfaces connectant la couche application aux autres couches.

2. **Cas d'utilisation (Use Cases)** :
    - Les classes représentant les cas d'utilisation doivent être **verbe-nom** dans une forme qui indique l'action et
      l'objet visé (exemple : `CreateUser`).
    - Suffixer par "UseCase" pour éviter toute ambiguïté (exemple : `CreateUserUseCase`).

3. **Interfaces** :
    - Interfaces pour les ports de communication (entrants et sortants) doivent commencer par un "I" majuscule suivi de
      leur rôle (exemple : `IUserRepository`, `INotificationService`).

4. **DTO** :
    - Les objets DTO doivent refléter les données pertinentes pour un cas spécifique d'utilisation avec le suffixe
      `DTO` (exemple : `UserDTO`).

## Exemple d'Arborescence de Projet

```
src/
└── application/
    ├── usecase/
    │   ├── CreateUserUseCase.java
    │   ├── UpdateUserUseCase.java
    │   └── DeleteUserUseCase.java
    ├── dto/
    │   ├── UserDTO.java
    │   └── ProductDTO.java
    └── port/
        ├── in/
        │   ├── IUserInputPort.java
        │   └── IAuthenticationService.java
        └── out/
            ├── IUserRepository.java
            └── IEmailService.java
```

|

## Rôles des Ports in et out

Dans l'architecture Clean, les ports définis dans les sous-dossiers `in` et `out` sous `application/port/` jouent un
rôle clé pour connecter la couche application aux autres couches de l'architecture.

### Port in (Entrants)

Les ports entrants définissent les interfaces qui permettent à la couche interface utilisateur (par exemple, les
controllers) d'interagir avec la couche application.

- **Rôle** : Exposer les fonctionnalités de la couche application (cas d'utilisation).
- **Exemple** :
  ```java
  public interface UserUseCase {
      void createUser(String name, String email);
      void updateUser(int userId, String name, String email);
  }
  ```
  Ici, un `UserController` pourrait appeler cette interface pour créer ou mettre à jour un utilisateur.

### Port out (Sortants)

Les ports sortants définissent les interfaces qui permettent à la couche application d'interagir avec des systèmes
externes (bases de données, services tiers, etc.).

- **Rôle** : Abstraire les dépendances externes nécessaires à la couche application.
- **Exemple** :

```java
public interface IUserRepository {
    void saveUser(User user);

    User getUserById(String userId);
}
```

Ici, une implémentation concrète de `IUserRepository` (dans la couche infrastructure) interagirait avec une base de
données pour sauvegarder ou récupérer des utilisateurs.

### Résumé des rôles

| Type de Port | Rôle                                                                  | Exemple d'utilisation                                                                                |
|--------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| Port in      | Définit les fonctionnalités exposées par la couche application.       | Appelé par les controllers pour exécuter des cas d'utilisation.                                      |
| Port out     | Définit les dépendances externes nécessaires à la couche application. | Utilisé par les cas d'utilisation pour interagir avec des bases de données ou des services externes. |