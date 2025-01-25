# Darwin World Simulation

## Project Overview
**Darwin World** is an evolving ecosystem simulation game where animals and plants interact in a dynamic environment. The game focuses on the evolution of herbivorous animals as they move, consume plants, reproduce, and evolve over time. The simulation runs through generations, showcasing natural selection and genetic variation.

### Objective
Create a virtual world where animals evolve based on their genetic traits and environment. The goal is to simulate a self-sustaining ecosystem with animals that adapt and evolve through natural selection, interacting with their surroundings.

## Features

### 🌍 **Map & Terrain**
The game world is a rectangular grid composed of grasslands and jungles:
- **Grasslands:** Sparse plant life with fewer food sources.
- **Jungles:** Rich in plants, offering more food for animals.

### 🦁 **Animals & Energy**
Herbivorous animals have energy that decreases daily. They must consume plants to survive and reproduce. If an animal’s energy reaches zero, it dies.

### 🧬 **Genetics & Behavior**
Each animal has a genome controlling its movement behavior:
- Animals have a sequence of genes (numbers 0-7), determining their daily actions (move forward, rotate, etc.).
- Genetic traits evolve through reproduction and occasional mutations, altering animal behavior.

### 💕 **Reproduction**
- **Energy Requirement:** Animals need sufficient energy to reproduce.
- **Genetic Inheritance:** Offspring inherit genes from both parents, with random mutations that can change their behavior.
- **Mutation:** Introduces genetic diversity, allowing new traits to emerge.

### 🌱 **Environment Interaction**
The environment influences animal and plant life, with events like:
- **Fires:** Periodic fires consume plants and can kill animals.
- **Plant Growth:** Plants grow in specific areas based on environmental conditions.

## Simulation Flow

### Day Cycle:
1. **Remove dead animals** from the map.
2. **Animal movement & rotation** based on their current genetic code.
3. **Animal consumption:** Animals consume plants to regain energy.
4. **Reproduction:** Animals with sufficient energy reproduce, creating offspring.
5. **Plant growth:** New plants appear on the map according to defined rules.

### Simulation Parameters:
- **Map Size & Type:** Customize map dimensions and behaviors (e.g., Earth-like wrapping or polar boundaries).
- **Initial Conditions:** Set the starting number of plants and animals, along with their energy levels.
- **Plant Growth Rate & Energy:** Define how quickly plants grow and the energy animals gain from consuming them.
- **Reproduction & Mutation:** Adjust the rules for reproduction, mutation rates, and energy distribution between parents and offspring.

### Graphical Interface:
- Built using **JavaFX**, the GUI provides an interactive experience for managing simulations.
- **Multiple Simulations:** Run and manage multiple simulations in separate windows.
- **Animation:** Visual representation of animals, plants, and environmental changes.
- **Statistics:** Track simulation data, such as animal population, energy levels, and genetic traits.

## Key Configurations

### 🗺️ **Map Variants**
- **[Mandatory] Earth-like wrap-around**: Left and right edges wrap, while top and bottom represent polar regions.
- **[Optional] Fire Events**: Periodic fires that spread across the map, burning plants and killing animals.
- **[Optional] Tides**: Water areas expand and contract periodically, affecting animal movement.
- **[Optional] Wild Sowonbear**: A carnivorous predator that hunts herbivorous animals in a defined territory.

### 🧬 **Mutation Variants**
- **[Mandatory] Full Randomness**: Mutations change genes to any other possible gene.
- **[Optional] Light Adjustment**: Mutations shift genes by 1 step up or down.
- **[Optional] Gene Swap**: Two genes in the genome swap positions.

### 🦓 **Animal Behavior Variants**
- **[Mandatory] Full Predestination**: Animals follow their genes in a predetermined order.
- **[Optional] Some Madness**: Animals may skip to a random gene with a small chance after performing their usual gene action.
- **[Optional] Ageing**: Older animals move slower, sometimes skipping a turn as they age.

## Requirements
- **JavaFX**: For creating the graphical user interface.
- **Gradle**: For building and managing the project.
- **CSV Export**: Daily statistics are saved to a CSV file for analysis.

## Running the Simulation
1. **Select Configuration:** Choose or create a simulation configuration (map type, animal behavior, mutation rules).
2. **Start Simulation:** Launch the simulation in a new window and observe the animals’ evolution.
3. **Control Simulation:** Pause, resume, or stop the simulation at any time.
4. **Track Stats:** Monitor animal populations, energy levels, and genetic diversity.
5. **Export Data:** Optionally export daily simulation stats to a CSV file for further analysis.

## Conclusion
Darwin World provides an engaging platform to explore evolutionary principles through simulation. By adjusting key parameters, users can create unique ecosystems and observe how animals adapt to changing environments. The interactive nature of the game encourages learning about genetics, natural selection, and ecological dynamics in a fun and visual way.

---

**Developed by:** [Your Team Name]  
**License:** [Insert License]  
**GitHub:** [Insert Repository Link]
