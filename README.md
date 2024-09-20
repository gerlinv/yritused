# Ürituste rakendus

Rakenduse loomisel on kasutatud 
* Backendis:
    * Java (ver 17, Spring Boot)
    * H2 andmebaas
* Frontendis: 
    * Angular (ver 18)
    * Bootstrap

## Eeldused

* Java JDK: Kontrollige, et teie süsteemis on installitud Java Development Kit (JDK).
* Node.js ja Angular CLI: Frontendi käivitamiseks on vajalikud Node.js ja Angular CLI.
* Portide saadavus: Veenduge, et pordid 8080 (backend) ja 4200 (frontend) ei oleks hõivatud teiste rakenduste poolt.
* Platvormi eripärad: Rakendus on arendatud Windowsi keskkonnas. Linuxi ja macOS-i kasutajad võivad kohtuda mõningate eripäradega. 

## Sammud rakenduse paigaldamiseks

### Failide kopeerimine:

Laadige alla rakenduse allikas, pakkige .zip fail lahti ning kopeerige failid soovitud kausta (nt C:\myapp).

### Käivitamine (Backend):

Navigeerige kausta, kuhu kopeerisite rakenduse failid:
```
cd C:\myapp
```
Käivitage Spring Boot rakendus, kasutades järgmist käsku:
```
cd backend
cd .\gradlew bootRun
```

* Kui rakendus töötab, peab toimima ka järgnev päring:
    * http://localhost:8080/api/event
* Vastuse staatus antud päringule on 204 No Content.

### Käivitamine (Frontend):

Navigeerige kausta, kuhu kopeerisite rakenduse failid:
```
cd C:\myapp
```
Käivitade Angulari rakendus, kasutades järgmist käsku:
```
cd frontend
ng serve
```

Avage veebibrauser ja minge aadressile http://localhost:4200.

#### Rakenduse admin kasutaja

Rakenduse admin e-posti aadress ja parool on toodud backendi application.properties failis.
* e-posti aadress: admin@example.com
* parool: admin

### Andmebaasi seadistamine
H2 andmebaas käivitatakse automaatselt koos Spring Boot rakendusega.

### Hetkel implementeerimata

* JWT tokeni aegumine
* Spetsiifilisem veahaldus
* Täpsem isikukoodi ja e-posti aadressi valideerimine
* Parem kuupäeva valimise metoodika