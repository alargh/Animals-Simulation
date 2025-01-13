package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.modes.MapType;
import agh.ics.oop.model.modes.MovinType;
import agh.ics.oop.model.modes.MutationType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

import java.util.List;
import java.util.Optional;


public class SimulationPresenter implements MapChangeListener {
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private int currentMapWidth;
    private int currentMapHeight;
    private WorldMap worldMap;

    private final int width = 25;
    private final int height = 25;

    private static final int MAPLIMITHIGHT = 100;
    private static final int MAPLIMITWIDTH = 100;


    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @FXML
    private Button start;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Spinner<Integer> widthSpinner;
    @FXML
    private Spinner<Integer> heightSpinner;
    @FXML
    private Spinner<Integer> equatorHeightSpinner;
    @FXML
    private Spinner<Integer> grassNumberSpinner;
    @FXML
    private Spinner<Integer> energyAdditionSpinner;
    @FXML
    // uzależnić od liczby zwierzakow i mapy
    private Spinner<Integer> plantRegenerationSpinner;
    @FXML
    private Spinner<Integer> numberOfAnimalsSpinner;
    @FXML
    private Spinner<Integer> startingAnimalEnergySpinner;
    @FXML
    private Spinner<Integer> energuNeededForReproductionSpinner;
    @FXML
    private Spinner<Integer> energyLosingWithReproductionSpinner;
    @FXML
    private Spinner<Integer> minGenMutationsSpinner;
    @FXML
    private Spinner<Integer> maxGenMutationsSpinner;
    @FXML
    private Spinner<Integer> genomLengthSpinner;
    @FXML
    private Spinner<MutationType> mutationTypeSpinner;
    @FXML
    private Spinner<Integer> maxEnergySpinner;
    @FXML
    private Spinner<MapType> mapTypeSpinner;
    @FXML
    private Spinner<Integer> moveEnergySpinner;
    @FXML
    private VBox configBox1;
    @FXML
    private VBox configBox2;
    @FXML
    private VBox configBox3;
    @FXML
    private HBox configBox4;


    private void drawMap() {
        updateBounds();
        xyLabel();
        columnsFunction();
        rowsFunction();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }
    @FXML
    public void onSimulationStartClicked(){

        int mapWidth = widthSpinner.getValue()-1;
        int mapHeight = heightSpinner.getValue()-1;
        int equatorHeight = equatorHeightSpinner.getValue();
        equatorHeight = Math.min(equatorHeight, mapWidth);
        int animalNumber = numberOfAnimalsSpinner.getValue();
        int grassNumber = grassNumberSpinner.getValue();
        int grassEnergy = energyAdditionSpinner.getValue();
        int dailySpawningGrass = plantRegenerationSpinner.getValue();
        int startEnergy = startingAnimalEnergySpinner.getValue();
        int maxEnergy = maxEnergySpinner.getValue();
        MovinType movingType = MovinType.DEFAULT;
        MutationType mutationType = mutationTypeSpinner.getValue();
        MapType mapType = mapTypeSpinner.getValue();
        int genesCount = genomLengthSpinner.getValue();
        int energyLevelNeededToReproduce = energuNeededForReproductionSpinner.getValue();
        int energyLevelToPassToChild = energyLosingWithReproductionSpinner.getValue();
        int moveEnergy = moveEnergySpinner.getValue();
        int minMutation = minGenMutationsSpinner.getValue();
        int maxMutation = maxGenMutationsSpinner.getValue();
        initialize();

        SimulationProperties simulationProperties = new SimulationProperties(mapWidth, mapHeight, equatorHeight, animalNumber, grassNumber,
                dailySpawningGrass, startEnergy, grassEnergy, maxEnergy,
                movingType,mutationType, mapType,  genesCount,
                energyLevelNeededToReproduce, energyLevelToPassToChild,moveEnergy,
                minMutation, maxMutation);
        AbstractWorldMap map1;
        try{
        if (mapType == MapType.GLOBE) {
            map1 = new GrassField(simulationProperties);
            map1.addObserver(this);
            Simulation simulation1 = new Simulation(map1, simulationProperties);
            SimulationEngine engine = new SimulationEngine(List.of(simulation1));
            //engine.runAsync();
            engine.runAsync();
        }
        else{map1 = new OwlBearMap(simulationProperties);
            map1.addObserver(this);
            Simulation simulation1 = new Simulation(map1, simulationProperties);
            SimulationEngine engine = new SimulationEngine(List.of(simulation1));
            //engine.runAsync();
            engine.runAsync();
        }} catch (Exception e) {
            throw new RuntimeException(e);
        }
        hideConfigurationElements();

    }

    private void hideConfigurationElements() {
        configBox1.setVisible(false); // Ukryj cały kontener
        configBox2.setVisible(false);
        configBox3.setVisible(false);
        configBox4.setVisible(false);
        configBox1.setManaged(false);
        configBox2.setManaged(false);
        configBox3.setManaged(false);
        configBox4.setManaged(false);

        mapGrid.requestLayout();
    }
    public void xyLabel(){
        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }
    public void updateBounds(){
        xMin = worldMap.getCurrentBounds().lowerLeft().getX();
        yMin = worldMap.getCurrentBounds().lowerLeft().getY();
        xMax = worldMap.getCurrentBounds().upperRight().getX();
        yMax = worldMap.getCurrentBounds().upperRight().getY();
        currentMapWidth = xMax - xMin + 1;
        currentMapHeight = yMax - yMin + 1;
    }
    public void columnsFunction(){
        for(int i = 0; i< currentMapWidth; i++){
            Label label = new Label(Integer.toString(i+xMin));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
            mapGrid.add(label, i+1, 0);
        }
    }
    public void rowsFunction(){
        for(int i=0; i<currentMapHeight; i++){
            Label label = new Label(Integer.toString(yMax-i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            mapGrid.add(label, 0, i+1);
        }
    }

    public void addElements(){
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Optional<WorldElement> optionalElement = worldMap.objectAt(new Vector2d(i, j));
                String labelText = optionalElement.isPresent() ? optionalElement.get().toString() : " ";
                // Zmieniamy sposób dodawania etykiet i obrazków:
                if (optionalElement.isPresent()) {
                    // Dodajemy tylko obrazek (WorldElementBox) w odpowiednie miejsce
                    mapGrid.add(new WorldElementBox(optionalElement.get()), i - xMin + 1, yMax - j + 1);
                } else {
                    // Dodajemy puste miejsce, jeśli brak elementu
                    mapGrid.add(new Label(" "), i - xMin + 1, yMax - j + 1);
                }
                GridPane.setHalignment(mapGrid.getChildren().getLast(), HPos.CENTER);
            }
        }
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
        });
    }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst());
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @FXML
    public void initialize() {
        // Create StringConverter for MapType
        StringConverter<MapType> mapTypeConverter = new StringConverter<>() {
            @Override
            public String toString(MapType mapType) {
                if (mapType == null) return "";
                return mapType.name();
            }

            @Override
            public MapType fromString(String string) {
                if (string == null || string.trim().isEmpty()) return MapType.OWLBEAR;
                return MapType.valueOf(string.trim());
            }
        };

        // Create StringConverter for MutationType
        StringConverter<MutationType> mutationTypeConverter = new StringConverter<>() {
            @Override
            public String toString(MutationType mutationType) {
                if (mutationType == null) return "";
                return mutationType.name();
            }

            @Override
            public MutationType fromString(String string) {
                if (string == null || string.trim().isEmpty()) return MutationType.values()[0];
                return MutationType.valueOf(string.trim());
            }
        };

        // Initialize MapType spinner
        SpinnerValueFactory<MapType> mapTypeFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(
                FXCollections.observableArrayList(MapType.OWLBEAR, MapType.GLOBE)
        );
        mapTypeFactory.setConverter(mapTypeConverter);
        mapTypeSpinner.setValueFactory(mapTypeFactory);
        mapTypeFactory.setValue(MapType.OWLBEAR); // Set default value

        // Initialize MutationType spinner
        SpinnerValueFactory<MutationType> mutationTypeFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(
                FXCollections.observableArrayList(MutationType.values())
        );
        mutationTypeFactory.setConverter(mutationTypeConverter);
        mutationTypeSpinner.setValueFactory(mutationTypeFactory);
        mutationTypeFactory.setValue(MutationType.values()[0]); // Set default value

        // Disable editing to prevent invalid input
        mapTypeSpinner.setEditable(false);
        mutationTypeSpinner.setEditable(false);
    }

    private void updateArea(){
        int height = heightSpinner.getValue();
        int width = widthSpinner.getValue();
        int maxArea = height * width;
        // do dokonczenia po zmienia spinnera na valuefactory
        //if ( grassNumberSpinner.getValue() > maxArea){
        //   grassNumberSpinner.setValueFactory(maxArea);
        //}

    }
}
