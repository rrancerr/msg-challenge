**MSG CHALLENGE - Readme**

###Hallo gutaussehender Beurteiler,


mein Name ist Marco Stadler, und möchte mit dieser Datei kurz beschreiben wie mein Programm funktioniert.


##Ausführungshinweise

Im Verzeichnis 

```sh
/out/artifacts/msg_challenge_jar
```
ist eine msg-challenge.jar Datei zu finden, die das fertige und kompilierte Programm enthält.

Man kann das Ganze starten, indem man entweder das Projekt in IntelliJ öffnet und ausführt, oder aber man nutzt die (Windows-) Kommandozeile, navigiert zu besagter Directory und führt die jar-Datei mit diesem Befehl aus:

```sh
java -cp msg-challenge.jar de.stadler.marco.challenge.Main
```
_(Natürlich unter der Vorraussetzung, dass Java installiert ist)_

##Gewählter Algorithmus

Ich habe mich für die Nearest Neighbor Heuristik entschieden, da diese nach meiner Rechnung und Auffassung des Problems am nähesten an der perfekten Lösung ist.

Ich bin von einem metrischen TSP ausgegangen, da im Normalfall die Distanzen nur irrelevant voneinander abweichen.

Demnach wäre die maximale Abweichung vom Lösungs Optimum   
```sh
(log(n)+1)/2 = log(21)+1/2 = 1,16
```
##Optimale Abfolge der Standorte

Ismaning -> Passau -> Chemnitz -> Görlitz -> Berlin -> Braunschweig -> Hannover -> Hamburg -> Schortens -> Lingen -> Münster -> Essen -> Düsseldorf -> Hürth -> Eschborn -> Walldorf -> Bretten -> Leinfelden-Echterdingen -> St. Georgen -> Nürnberg -> Ingolstadt -> Ismaning

Führt zu einer Gesamtdistanz von ca. 2.370 Kilometern (2370255.3062398825 Meter).

###Möge die Macht mit euch sein beim Bewerten meiner Lösung

![baby-yoda](https://media0.giphy.com/media/kI2hsMDS4zjK7Fbif8/giphy.gif?cid=ecf05e47e240d44c03f4ed6cfcbe80c0a5be95cdc84e25b0&rid=giphy.gif)