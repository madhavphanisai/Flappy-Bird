# 🐤 Flappy Bird Clone in Java

A simple 2D Flappy Bird game clone built in **Java** using **Swing** and **AWT** libraries. This project demonstrates basic game loop mechanics, collision handling, image rendering, and keyboard input handling.

---

## 🎮 Game Features

- Smooth bird animation using a game loop (`Timer`)
- Gravity and jump mechanics via space bar
- Randomly generated top and bottom pipes
- Real-time rendering using `paintComponent`
- Keyboard input handling with `KeyListener`
- Background and sprite rendering using `ImageIcon`

---

## 🧰 Technologies Used

- Java SE 8+
- AWT & Swing
- Object-Oriented Programming

---

## 🖼️ Assets Required

Place the following image files in the **same directory** as your `.java` files or in the `resources` folder:

- `flappybirdbg.png` – background image  
- `flappybird.png` – bird sprite  
- `toppipe.png` – top pipe sprite  
- `bottompipe.png` – bottom pipe sprite  

Make sure they're accessible using:

```java
new ImageIcon(getClass().getResource("filename")).getImage();
