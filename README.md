# Logistics Delivery — Factory Method + Abstract Factory

**Assignment 2** — ShP-2216 Software Design Patterns, Astana IT University (2026-2027)
**Author:** Zhalynuly Zhassyn, group SE-2527
**Submitted version:** git tag `assignment2-submission` (see the commit hash in the report).

## Purpose
One console application that combines two creational patterns:

* **Factory Method** — chooses the transport for road (`Truck`) or sea (`Ship`) delivery.
* **Abstract Factory** — creates a matching `Button` + `Checkbox` pair for Windows or macOS.

The two choices are independent, so all four combinations (ROAD/SEA × WINDOWS/MACOS) work
without editing the code. Everything runs on any OS: the "macOS" components only print text.

## Package structure
```
src/main/java/delivery/
├── factorymethod/     Transport, Truck, Ship, Logistics, RoadLogistics, SeaLogistics
├── abstractfactory/   Button, Checkbox, GUIFactory,
│                      WindowsButton, WindowsCheckbox, WindowsFactory,
│                      MacOSButton, MacOSCheckbox, MacOSFactory
└── app/               Main (startup + validation), DeliveryApplication (client)
docs/uml/              factory-method.puml/.png, abstract-factory.puml/.png
```

## Prerequisites
* JDK 17 or newer (`javac` must be on the PATH). No external libraries or build tool required.

## Build
From the repository root:

```bash
# Linux / macOS
mkdir -p out && javac --release 17 -d out $(find src -name '*.java')
```
```powershell
# Windows PowerShell
mkdir out -Force; javac --release 17 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

## Run
```bash
java -cp out delivery.app.Main <DELIVERY_MODE> <UI_PLATFORM>
```
Supported values (case-insensitive):

| Argument | Values |
|---|---|
| `DELIVERY_MODE` | `ROAD`, `SEA` |
| `UI_PLATFORM`   | `WINDOWS`, `MACOS` |

### Validation behaviour
* Wrong number of arguments (none, one, or more than two): message with usage, exit code 1.
* Unsupported or blank delivery mode / UI platform: a clear message on `stderr`, exit code 1.
* Validation happens before `DeliveryApplication` is created, so no UI component is built and
  nothing is delivered for an invalid choice. No default value is ever substituted.

## Sample run
```
$ java -cp out delivery.app.Main ROAD WINDOWS
Delivery mode: ROAD
UI platform: WINDOWS
Rendering Windows button
Rendering Windows checkbox
Truck delivers laboratory equipment to Aktau warehouse by road
```
