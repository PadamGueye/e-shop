# E-shop Application

## Description

Cette application est un système de gestion d'un **e-commerce** (E-shop) développé avec **Spring Boot**. Elle permet la gestion des utilisateurs et des produits. L'application est sécurisée pour la gestion des accès et utilise des mécanismes de gestion des erreurs.

## Fonctionnalités déjà implémentées

### 1. **Gestion des utilisateurs**
   - Inscription des utilisateurs avec des informations de base.
   - Authentification des utilisateurs pour sécuriser les opérations.

### 2. **Gestion des produits**
   - Ajout, modification et suppression des produits.
   - Chaque produit possède des informations telles que : `nom`, `description`, `prix`, `quantiteStock`.
   - Recherche des produits avec des filtres par nom.

### 3. **Sécurité et gestion des erreurs**
   - Authentification des utilisateurs pour protéger les endpoints sensibles.
   - Gestion des erreurs avec des codes HTTP appropriés :
     - `401 Unauthorized` : pour les actions nécessitant une authentification.
     - `403 Forbidden` : pour les actions non autorisées.
     - `404 Not Found` : pour les entités inexistantes (produits, utilisateurs, etc.).
   - Gestion centralisée des erreurs à l'aide d'un mécanisme `@ControllerAdvice`.

## Fonctionnalités non encore implémentées

### 1. **Gestion des commentaires**
   - La fonctionnalité permettant aux utilisateurs connectés de publier des commentaires sur les produits est **non intégrée** pour des raisons de temps.
   - Cette fonctionnalité sera ajoutée dans de futures versions de l'application.

## Architecture du projet

L'architecture du projet suit une approche simple, organisée autour des éléments suivants :

1. **Modèle (Entity)** :
   - Entités JPA représentant les objets métier, telles que `Produit` et `User`.
   - Chaque entité est mappée à une table dans la base de données.

2. **DTO (Data Transfer Object)** :
   - Des DTOs sont utilisés pour transférer les données entre le client et le serveur sans exposer directement les entités.
   - Par exemple, `ProduitDTO` est utilisé pour la communication avec l'API.

3. **Mapper** :
   - Un mapper est utilisé pour convertir les entités en DTOs et vice versa, afin de maintenir une séparation claire entre la logique métier et la couche de présentation.

4. **Gestion des erreurs** :
   - Les erreurs sont gérées de manière centralisée grâce à un `@ControllerAdvice` qui intercepte les exceptions et envoie des réponses structurées et appropriées.

## Technologies utilisées

- **Spring Boot**
- **Spring Data JPA** pour la persistance des données.
- **H2** pour la base de données.
- **Spring Security** pour sécuriser les endpoints de l'application.
- **Maven** pour la gestion des dépendances.

## Installation

   ```bash
   git clone https://github.com/PadamGueye/e-shop
   cd <nom-du-projet>
   mvn install
   mvn spring-boot:run
 ```
L'application sera accessible sur http://localhost:8080.


# TEST avec POSTMAN
## Register User
![image](https://github.com/user-attachments/assets/6a954c23-272f-4d02-91d1-74a19e3822c7)


## Login User
![image](https://github.com/user-attachments/assets/63aa79c4-793f-41a9-895a-18d914c2ef90)

## Get User By Id
![image](https://github.com/user-attachments/assets/b7bbebd9-3269-482d-96d1-e1f163e207f4)


## Create Product
![image](https://github.com/user-attachments/assets/9301d097-7ea6-4531-a9a4-1d4192b90546)

## Get Product By Id
![image](https://github.com/user-attachments/assets/e98de772-aad5-497d-a868-d29facac1bdd)


