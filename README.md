# Recept Na Svobodu

Textová adventura v Javě o útěku z věznice. Hráč se ujímá role pekaře **Karla**, jehož rodinný podnik se topí v dluzích. Rozhodl se vzít věci do vlastních rukou — a skončil za mřížemi. Teď má jediný cíl: **dostat se ven a zachránit rodinu**.

---

## 📖 Příběh a úvodní volba

Na začátku hry si Karel vybere cíl loupeže — ta určí **obtížnost** a **počet předmětů** v balíčku od rodiny, který bachař zapomněl prohledat:

| Volba | Obtížnost | Předmětů v balíčku |
|-------|-----------|---------------------|
| 🕊️ Zůstat poctivý | — | Hra okamžitě končí bankrotem |
| 💎 Vykrást klenotnictví | Lehká | 5 |
| 🎡 Vykrást zábavní park | Střední | 4 |
| 🏦 Vykrást banku | Těžká | 3 |

Z 10 dostupných předmětů si hráč vybere pouze daný počet — **každá kombinace vede k jiné strategii**.

---

## 🗺️ Lokace

Hra se odehrává ve vězeňském komplexu propojené místnosti, zdi, ventilační šachty a dveře:

| # | Lokace | Popis |
|---|--------|-------|
| 1 | **Tvoje cela** | Startovní bod. Balíček od rodiny, klíčová rozhodnutí. |
| 2 | **Jídelna** | Nezbytná pro 2 ze 3 konců hry (násilí + exploze). |
| 3 | **Hlavní chodba** | Rozcestí, 3 hlídači — doporučuje se obejít ventilací. |
| 4 | **Chodba do cel** | Přístupná přes pilník. Jeden strážný na překonání. |
| 5 | **Dílna s nářadím** | 3 strážní, ale po vítězství získáš **krumpáč**. |
| 6 | **Sprchy** | Scéna se šikanou, vězeň ti poradí o skladu zbraní. |
| 7 | **Blok s celami** | Tři vězni s informacemi (Vyklepanec, Panikař, Nemluva). |
| 8 | **Trezor banky** | Finální lokace — přístupná jen přes dynamit v jídelně. |
| 9 | **Velín** | Nejvíce střežená místnost (6 strážných). Poslední překážka. |
| 10 | **Sklad zbraní** | Klíčový pro Cestu násilí. Dostupný z jídelny prokopáním. |
| 11 | **Kancelář šéfa** | Dialog s ředitelem. S pistolí ho lze vzít jako rukojmí. |
| 12 | **Dvorek pro vězně** | Rozcestník s jižní posilovnou a severní dílnou. |
| 13 | **Venkovní posilovna** | Nasvalenec + Snílek — cenné informace výměnou za předměty. |

---

## 🚀 Tři cesty útěku

### 1. 🔫 Cesta rukojmí *(Pistole)*
Dostaň se do kanceláře šéfa → po dialogu tasni pistoli → vezmi ředitele jako rukojmí → projdi Velínem až na svobodu.

### 2. 💪 Cesta násilí *(Taser)*
Paralyzuj stráže v dílně → získej krumpáč → prokop zeď do skladu zbraní → plně se ozbroj → vystřílej si cestu Velínem ven.

### 3. 💥 Cesta exploze *(Dynamit + zapalovač)*
Zjisti od vězně polohu „slabé zdi" → dostaň se do jídelny → odpal dynamit u severní zdi → probourej se do trezoru banky → vyloupej banku a uteč i s penězi pro rodinu.

---

## 🎮 Příkazy

| Příkaz | Popis |
|--------|-------|
| `jdi <smer>` | Pohyb (sever / jih / vychod / zapad) |
| `seber <predmet>` | Seber předmět z místnosti |
| `zahod <predmet>` | Zahoď předmět z inventáře |
| `pouzij <predmet> [smer]` | Použij předmět (na zeď, mříže, ventilaci...) |
| `inventar` | Zobraz svůj inventář |
| `rozhledni` | Prohlédni místnost (předměty, osoby) |
| `mluv <osoba>` | Promluv s osobou v místnosti |
| `podej <osoba> <predmet>` | Dej předmět osobě |
| `zautoc <zbran>` | Zaútoč na nepřítele (nutné při boji!) |
| `zautoc <zbran> cigarety` | Útok se zdvojnásobenou silou |
| `mapa` | Zobraz mapu věznice |
| `pomoc` | Zobraz nápovědu příkazů |
| `napoveda` | Zobraz herní tipy |
| `konec` | Ukonči hru |

---

## 🎒 Předměty

| Předmět | Schopnost |
|---------|-----------|
| Pistole | Boj / zajmutí šéfa jako rukojmí (hlasité!) |
| Taser | Boj, tichý — klíč k dílně |
| Dynamit | Zničí zeď (hlasité! spustí alarm) |
| Pilník | Přepiluje mříže |
| Šroubovák | Odšroubuje ventilaci |
| Obušek | Boj zblízka |
| Samurajský meč | Tichý boj |
| Krumpáč | Ničení zdí (získaný v dílně) |
| Cigarety + zapalovač | Zdvojnásobí sílu útoku |
| Železná tyč | Vnikne jako vedlejší produkt pilování mříží |

> ⚠️ **Alarmový systém:** Použití pistole nebo dynamitu spustí alarm. Po 3 dalších příkazech se v místnosti objeví **ozbrojená ochranka** — musíš s ní bojovat!

---

## 👥 Postavy

**Vězni s informacemi:**
- **Vyklepanec** — Za cigaretu odhalí tajemství o dynamitu v jídelně (hint pro Cestu exploze)
- **Panikař** — Po osvobození dá plánek vězení a odláká stráže
- **Nemluva** ⚠️ — Past! Po osvobození na tebe zaútočí
- **Nasvalenec** — Výměnou za předmět dá mapu bytelnosti zdí
- **Snílek** — Poradí, kde je kancelář šéfa

**Nepřátelé:**
- **Strážní** — Různá síla dle lokace (1–6 na místnost)
- **Ozbrojená ochranka** — Spawnuje se po alarmu

---

## 🏗️ Struktura projektu

```
ReceptNaSvobodu/
├── src/
│   ├── Main.java               # Vstupní bod
│   ├── Hra.java                # Hlavní herní třída
│   ├── Command/                # Příkazy (Pohyb, Seber, Souboj, Podej...)
│   ├── Mapa/                   # Místnosti a zdi (Mistnost, Zed)
│   ├── Postavy/                # Hráč a NPC (Hrac, NPC)
│   ├── Predmety/               # Předměty a inventář
│   ├── Pribeh/                 # Volby a konce hry (Volba, Konec)
│   └── System/                 # Engine (ConsoleApp, DataHry)
└── res/
    ├── data.json               # Místnosti, předměty, NPC
    ├── pribeh.json             # Příběhové volby a úvod
    └── pomoc.txt               # Nápověda příkazů
```

---

## 🚀 Spuštění

Projekt vyžaduje **Java 17+** a knihovnu **Jackson** (deserializace JSON).

### V IntelliJ IDEA
1. Otevři složku `ReceptNaSvobodu` jako projekt
2. Ujisti se, že Jackson Databind je přidán jako závislost
3. Spusť `Main.java`

---

## 👨‍💻 Autor

Marek Šmíd — školní projekt, leden 2026
