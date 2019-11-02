# domus
Il definitivo (forse) progetto di domotica personale
## Architettura
Questo sistema è basato su una serie di moduli (o servizi) che permettono di gestire una serie di micro controller di tipo Arduino o Raspberry
che a loro volta controllano una serie di oggetti di basso livello come sensori o switch.
## Moduli
- Hub
Il modulo con cui creare l'interfaccia per API ReST verso le componenti interne (micro controllers come Arduino o Raspberry).
