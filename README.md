# Foxy Jumpscare Overlay

A Java-based jumpscare application that displays a fullscreen animated Foxy GIF over all windows, plays a sound, and can be triggered manually or automatically from the system tray.

## Features

* Fullscreen overlay with transparent GIF
* Plays custom sound with the jumpscare
* Runs always on top of other windows
* Supports automatic scheduled jumpscares using `ScheduledExecutorService`
* Tray icon integration for manual triggering and exit control
* Lightweight and packaged as a runnable JAR or Windows executable

## Requirements

* Java 21+ (JDK recommended)
* Swing/AWT support (built into standard JDK)

## Project Structure

```
jumpscare-inator/
├─ src/                   # Java source files
│  └─ src/Main.java
│  └─ src/FoxyWindow.java
│  └─ src/FullscreenGifPanel.java
├─ assets/                # Media assets
│  └─ fnaf-foxy.gif
│  └─ foxy-jumpscare.wav
├─ out/                   # Compiled classes (after javac)
├─ manifest.txt           # Specifies Main-Class for JAR
└─ README.md
```

## How to Build

### 1. Compile the project

```bash
javac -d out src/src/*.java
```

* `-d out` puts compiled `.class` files in the `out` folder
* Adjust source path if your package structure is different

### 2. Create JAR file

```bash
jar cfm FoxyJumpscare.jar manifest.txt -C out .
```

* The `manifest.txt` specifies the main class:

```
Main-Class: src.ChanceRunner
```

### 3. Optional: Build Windows Executable

* Use Launch4j
* Set Output file → `.exe`
* Set Jar file → `FoxyJumpscare.jar`
* Choose GUI application to hide console
* Configure icon and JRE version as needed
* Click Build Wrapper

## How to Run

### From JAR

```bash
java -jar FoxyJumpscare.jar
```

### From EXE

* Double-click the generated `.exe` file
* Tray icon will appear for manual triggering
* Scheduled jumpscare can run automatically if enabled

## Tray Controls

* **Left-click** or **Open Foxy** → triggers jumpscare overlay
* **Exit** → closes the application

## Scheduling Jumpscares

The app uses `ScheduledExecutorService` to trigger jumpscares automatically:

```java
scheduler.scheduleAtFixedRate(() -> {
    SwingUtilities.invokeLater(() -> new FoxyWindow());
}, initialDelaySeconds, intervalSeconds, TimeUnit.SECONDS);
```

* Make sure to wrap GUI calls in `SwingUtilities.invokeLater` for thread safety

## Notes

* GIF must have a transparent background for correct overlay
* JFrame must be undecorated, always-on-top, and non-opaque
* Assets can be packaged inside the JAR or referenced via relative path

## License

* This repository is open-source, so you can do what you want (and enjoy jumpscares)...

---

This README covers:
* Features
* Requirements
* Build instructions (JAR + Launch4j)
* How to run
* Tray and scheduling behavior

---

## Inspired by [this guy](https://youtu.be/3ZsQNmVRz7A?si=nkJU59p0pMdQeLxT) (so I made this).

### Note: Sometimes it feels like forever and th-
