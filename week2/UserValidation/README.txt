USER VALIDATION

IntelliJ IDEA
1. Open this folder in IntelliJ IDEA.
2. If asked, select an installed JDK (17 or newer) under File > Project Structure.
3. Open Main.java and run main(), or select the supplied Main run configuration.
4. The ValidationTest run configuration runs the small dependency-free test suite.

Astah
Open UserValidation.asta. It contains Class Diagram, Use Case Diagram and Activity Diagram.
These are editable Astah models and diagrams, created using the installed Astah Professional API.

Configuration
In Main.java, add the required rules with validator.addRule(...).
Omit a rule to disable it. PasswordValidation's arguments are:
spacesAllowed, specialCharacterRequired, numberRequired, lowercaseRequired, uppercaseRequired.
The minimum age is passed to MinimumAgeValidation.

Behavior
Validation stops at the first error. Only users that pass every selected rule are stored.
No selected rules means no optional validation. Usernames are case-sensitive.
Storage is an in-memory list for a single-threaded classroom demonstration.
Email checks a common ASCII format, not whether a mailbox exists.
Password spaces include whitespace; no extra minimum password length is imposed.
Age uses completed calendar years (February 29 birthdays advance on March 1 in non-leap years).
Null account fields are not permitted. Passwords remain in memory in this teaching example.

The class diagram shows the eight main design types and their important operations.
Main, getters and some constructors are omitted to keep it readable.

References
Supplied Assignment_UserValidation_INT.pdf and lecture slides.
https://github.com/NHL-Stenden-Emmen/coding-conventions
https://github.com/Sir-Fenrir/Kingdom_and_Quests
