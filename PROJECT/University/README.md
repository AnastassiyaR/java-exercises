# Projektiplaan

## Antud nõuded projektile
- Ülikoolis on ruumid
- Ruumil on tüüp - klassiruum/õpituba
- Ruumil on atribuudid:
   - Max osalejate arv
   - Nimekiri varustusest
   - Ruumi number
- Igal ruumil on erinev number ülikooli piires
- Iga ruum tohib olla vaid ühes koolis
- Ruume saavad broneerida selle kooliga seotud kasutajad:
   - Klassiruumi saavad broneerida vaid õppejõud
   - Õpitube võivad broneerida nii õppejõud kui ka tudengid
- Ruumil saab olla samal ajahetkel vaid üks broneering
- Õppejõud/tudeng saab ruumi broneerida kindlaks päevaks ja kellaajaks (kasuta näiteks LocalDateTime)
- Broneeringul on algusaeg ja lõpuaeg
- Õpitoa broneering ei saa olla pikem kui 4h
- Broneering peab jääma ajavahemikku 8.00-22.00
- Igal kasutajal on päevalimiit 6h
- Broneeringuid saab tühistada (erinevalt TalTechist, sest meil on hea süsteem)

- Kaks kasutaja tüüpi
  - Tudeng:
     - Saab broneerida vaid õpitube
     - Atribuudid:
        - Keskmine hinne
        - Nimi
        - Vanus
        - Kool
  - Õppejõud:
     - Saab broneerida kõiki ruume
     - Atribuudid:
        - Nimi
        - Vanus
        - Nimekiri koolidest
- Koolis saab otsida vabu ruume vastavalt tüübile ja kuupäevale
   - Boonuspunktid siis, kui suudad otsingu implementeerida nii, et mõne parameetri saab otsingust ära jätta, mispuhul otsing kasutab ainult olemasolevaid parameetreid!
- Koolil on ülevaade:
   - Ruumidest
   - Kasutajatest
   - Broneeringutest
- Kasutajal on ülevaade oma broneeringutest
- Koolis on meetod kasutajate järjestamiseks:
   - Eespool on kasutajad, kes on rohkem kumulatiivselt aega broneerinud ruumide kasutuseks
   - Kui aeg on sama, siis broneeringute arvu järgi


## Nõuete analüüsist tulenev plaan ja klasside ligikaudne disain

### **Room**
- **Atribuudid:**
   - `number` (ruumi number, unikaalne ülikooli piires)
   - `type` (enum: klassiruum, õpituba)
   - `maxParticipants` (max osalejate arv)
   - `equipmentList` (nimekiri varustusest)
   - `bookings` (list broneeringutest)
   - `universityBelongsTo` (kuulub ühele kindlale ülikoolile)

- **Meetodid:**
   - `getters`, `setters`, `adders`

---

### **User**
- **Atribuudid:**
   - `name` (kasutaja nimi)
   - `age` (kasutaja vanus)
   - `bookings` (list kasutaja broneeringutest)
   - `dailyLimit` (päevane broneeringute ajalimiit, 6h)

- **Meetodid:**
   - `bookRoom(room, LocalDateTime, LocalDateTime)`
      - Kontrollib, kas ruum on saadaval, kas broneering jääb lubatud ajavahemikku (8.00-22.00) ja kas kasutajal on piisavalt aega päevalimiti piires
      - Kui tingimused on täidetud, broneerib ruumi
   - `cancelBooking(Booking)`
      - Tühistab broneeringu ja vabastab aja
   - `getters`, `setters`, `adders`

---

### **Student**
- **Atribuudid:**
   - `averageGrade` (keskmine hinne)
   - `university` (tudengi ülikool)

- **Meetodid:**
   - `bookStudyRoom(room, LocalDateTime, LocalDateTime)`
      - Saab broneerida vaid õpituid
      - Kontrollib, kas broneering ei ületa 4 tundi
  - `getters`, `setters`, `adders`

---

### **Teacher**
- **Atribuudid:**
   - `universities` (nimekiri koolidest, kus õppejõud töötab)

- **Meetodid:**
   - `bookRoom(room, LocalDateTime, LocalDateTime)`
      - Saab broneerida nii klassiruume kui ka õpituid
  - `getters`, `setters`, `adders`

---

### **University**
- **Atribuudid:**
  - `name` (ülikooli nimi)
  - `rooms` (list ülikooli ruumidest)
  - `users` (list kasutajatest, kes on broneerinud ruume)
  - `bookings` (list kõigist broneeringutest)

- **Meetodid:**
   - `searchAvailableRooms(optional type, optional date)`
      - Otsib vabu ruume vastavalt tüübile ja/või kuupäevale
      - Võimalik otsida ainult tüübi või kuupäeva järgi
   - `sortUsersByUsage()`
      - Sorteerib kasutajad kumulatiivse broneeritud aja järgi
      - Kui aeg on sama, siis broneeringute arvu järgi

---

### **Booking**
- **Atribuudid:**
   - `user` (broneeringu teinud kasutaja)
   - `room` (broneeritud ruum)
   - `startTime` (broneeringu algusaeg)
   - `finishTime` (broneeringu lõpuaeg)

- **Meetodid:**
   - `getters`
   - `validateBooking`
     - Kontrollib et Booking oli tenhtud õigesti ja rikku reegleid
       - Broneering peab jääma ajavahemikku 8.00-22.00
       - Igal kasutajal on päevalimiit 6h
       - Õpitoa broneering ei saa olla pikem kui 4h
