# Clean Architecture dans votre projet Spring Boot

## Introduction à la Clean Architecture

La Clean Architecture est une méthodologie d'organisation du code qui permet d'isoler les différentes responsabilités
d'une application en couches distinctes, avec des dépendances orientées vers l'intérieur. Elle favorise la testabilité,
la maintenabilité et la flexibilité en évitant les couplages forts entre les différentes briques de l'application.

L'approche se décompose en trois couches principales adaptées aux projets Spring Boot :

1. **Domain (le coeur métier)**
2. **Application (les cas d'usage)**
3. **Adapters ou Infrastructure (les interfaces avec le monde extérieur)**

Ces couches respectent une règle fondamentale : **les couches externes peuvent dépendre des couches internes, mais
jamais l'inverse**.

---

## Illustrations

### Diagramme général des couches

```
   |--------------------------------------------------|
   |                  Infrastructure                  |
   | [ Web, DB, Services externes, Adapters ]         |
   |--------------------------------------------------|
   |                   Application                    |
   | [ UseCases, Services ]                           |
   |--------------------------------------------------|
   |                      Domain                      |
   | [ Entities, Interfaces, Value Objects, Rules ]   |
   |--------------------------------------------------|
```

---

## Détails des couches

### 1. **Domain**

La couche Domain est le coeur de l'application où résident les règles métier. Elle est complètement indépendante de
frameworks ou de bibliothèques externes (comme Spring). Elle contient principalement :

- Les **Entités métier** : objets représentant des concepts métier fondamentaux.
- Les **Interfaces (Ports)** : abstractions des interactions avec des systèmes externes.
- Les **Règles métier / Value Objects** : logiques associées aux entités ou à leurs relations.

#### Exemple

```java
public class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
```

---

### 2. **Application**

La couche Application contient les cas d'usages de l'application. Elle orchestre les règles métier présentes dans la
couche Domain pour remplir les fonctionnalités demandées. On y trouve :

- Les **UseCases**
- Les **Services métier**
- Les ports (interfaces) qui seront implémentés par les adapters.

#### Exemple

Un **UseCase** illustrant la persistance d'un objet :

```java
public class SaveProductUseCase {
    private final ProductRepository productRepository;

    public SaveProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Product product) {
        productRepository.save(product);
    }
}
```

---

### 3. **Adapters / Infrastructure**

Les Adapters, aussi appelés Infrastructure, sont la couche la plus externe. Elle contient des implémentations des ports
définis dans les couches internes et interagit avec des technologies ou des frameworks spécifiques (comme Spring Boot,
Hibernate, ou des APIs externes).

On y trouve :

- Les contrôleurs (API REST, GraphQL, etc.)
- Les connecteurs aux bases de données (DAO, Repositories)
- Les implémentations spécifiques pour des services externes.

#### Exemple d'implémentation d'un repository Spring Data :

```java
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, String> {
}

@Repository
public class ProductRepositoryAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;

    public ProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = new ProductEntity(
            product.getId(),
            product.getName(),
            product.getPrice()
        );
        repository.save(entity);
    }
}
```

---

## Sources proposés pour enrichir vos connaissances

- [Uncle Bob — The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Documentation officielle Spring Boot](https://spring.io/projects/spring-boot)
- [Domain-Driven Design](https://martinfowler.com/tags/domain%20driven%20design.html)

---

En respectant cette architecture, vous garantissez une meilleure séparation des responsabilités et assurez une
adaptabilité accrue face aux futurs changements ou évolutions. Bon développement !