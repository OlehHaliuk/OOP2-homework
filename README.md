# Object-Oriented Programming 2 — Java Assignments

Weekly individual assignments for the **OOP 2** course of the BSc Information Technology (HBO-ICT) programme at **NHL Stenden University of Applied Sciences** (Sep – Oct 2026).

Each assignment models a small domain in Java, focusing on clean class design, inheritance, interfaces, exceptions and unit testing.

**At a glance:** 6 projects · ~3,900 lines of Java · 51 JUnit tests · UML diagrams (Astah) for 4 projects

## Projects

| Week | Project | What it models | Key OOP concepts | Tests |
|---|---|---|---|---|
| 1 | [Kingdom Quests](week1/KingdomsQeusts) | RPG characters, items and quests | Abstract class `Character` with `Warrior`, `Mage` and `Archer` subclasses; `SpecialQuest` extends `Quest` | — |
| 2 | [User Validation](week2/UserValidation) | User registration with configurable rules | **Strategy pattern**: every rule implements `ValidationRule`, so rules can be added or removed without changing `UserValidator` | Dependency-free test suite |
| 3 | [App Store](week3) | Apple and Google app stores, purchases and currencies | Abstract `AppStore` with two platform subclasses; enum `Currency`; custom `DownloadNotAllowedException` | 16 |
| 4 | [Flight Booking](week4/FlightBooking) | Flights, passengers, tickets and luggage | Abstract `Airplane` (commercial / private); booking service; enums for airports, seat types, luggage and flight status | 13 |
| 6 | [Vegetable Garden](week6/vegetablegarden) | Garden, greenhouse and vegetable market | Abstract `Vegetable` hierarchy; `Greenhouse` extends `Garden`; packages per domain; `TooManyVegetablesException` | 13 |
| 7 | [Smart BBQ](week7/smartBBQ) | A BBQ that grills food from a fridge over time | `Measurable` interface shared by food, fridge and BBQ; two-level hierarchy (`Food` → `Meat` / `Vegetable`); scheduled grilling with `TimerTask`; custom exceptions | 9 |

## Design highlights

- **Program to interfaces.** `ValidationRule` (week 2) and `Measurable` (week 7) let unrelated classes share behaviour without inheritance.
- **Abstract base classes** hold shared state and logic, while subclasses only define what differs (characters, app stores, airplanes, vegetables, food).
- **Checked exceptions** model invalid states explicitly, for example a blocked download, a full garden or an empty fridge.
- **Enums** replace magic strings for fixed sets such as currencies, airports, seat types and heat levels.
- **Unit tests** cover normal behaviour and edge cases, using JUnit 5.

## Diagrams

The UML models are made in [Astah](https://astah.net/) and are stored next to the code:

- `week1/UseCase and Class Diagram For KingdomQuest.asta`
- `week2/UserValidation.asta` (class, use case and activity diagrams)
- `week3/diagrams/AppStore.asta`
- `week4/FlightBooking/diagrams/FlightBooking.asta`

## How to run

1. Open a week's project folder in **IntelliJ IDEA**.
2. Select a JDK (17 or newer) under *File → Project Structure*, if IntelliJ asks for one.
3. Run `Main.java` for a demo (weeks 1, 2 and 7), or right-click the `test` folder and choose *Run All Tests* (weeks 3, 4, 6 and 7).

## Tech

Java · JUnit 5 · IntelliJ IDEA · Astah UML

---

Author: **Oleh Haliuk** · [GitHub](https://github.com/OlehHaliuk) · [LinkedIn](https://www.linkedin.com/in/oleh-haliuk-b327171a6/)
