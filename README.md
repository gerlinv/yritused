# Ürituste rakendus

Rakenduse loomisel on kasutatud 
* Backendis:
    * Java (ver 17, Spring Boot)
    * H2 andmebaasi
* Frontendis: 
    * Angular (ver 18.2.4)
    * Bootstrap

## Eeldused
Java JDK: Veenduge, et teie süsteemis on installitud Java Development Kit (JDK) versioon 11 (või sellest kõrgem).
Frontendi poole peal on kasutusel Angular ning seetõttu veenduge, et Node.js ja Angular CLI on installitud.
Veenduge, et pordid (8080 ja 4200) ei ole hõivatud teiste rakenduste poolt.

Rakendus on arendatud Windowsi keskkonnas, Linux/macOS võivad osutada oma eripärasi. 

## Sammud rakenduse paigaldamiseks

### Failide kopeerimine:

Laadige alla rakenduse allikas või distributsioonifailid (nt *.jar ja Angulari failid).
Kopeerige need soovitud kausta (nt C:\myapp).

### Käivitamine (Backend):

Avage cmd või PowerShell.
Navigeerige kausta, kuhu kopeerisite rakenduse failid:
```
cd C:\myapp
```
Käivitage Spring Boot rakendus, kasutades järgmist käsku:
```
cd backend
cd .\gradlew bootRun
```
Veenduge, et rakendus käivitub ilma vigadeta ja logis kuvatakse, et server kuulab pordil 8080.

### Käivitamine (Frontend):

Avage cmd või PowerShell.
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
* Teade kui kindla isikukoodiga isik on juba üritusele registreerunud
* Spetsiifilisem veahaldus
* Täpsem isikukoodi ja e-posti aadressi valideerimine
* Parem kuupäeva valimise metoodika