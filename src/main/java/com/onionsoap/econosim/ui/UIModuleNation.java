package com.onionsoap.econosim.ui;

import com.onionsoap.econosim.core.nation.Nation;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class UIModuleNation extends VBox {
    private final Nation nation;

    public UIModuleNation(Nation nation) {
        this.nation = nation;

        setStyles();
        fillData();
    }

    private void setStyles() {
        setBackground(
                new Background(
                        new BackgroundFill(
                            Paint.valueOf("#e5e5e5"),
                            new CornerRadii(5.0),
                            new Insets(5.0)
                        )
                )
        );

        setPadding(new Insets(50.0));
    }

    private void fillData() {
//        // label for nation name
//        var labelName = new Label(nation.getName());
//        getChildren().add(labelName);
//
//        // label for nation reserve
//        var labelReserve = new Label("$" + nation.getReserve());
//        getChildren().add(labelReserve);
//
//        // button for add reserve
//        var buttonReserveAdd = new Button("Add To Reserve");
//        buttonReserveAdd.setOnMouseClicked((event) -> {
//            this.nation.addReserve(5);
//            labelReserve.setText("$" + nation.getReserve());
//        });
//        getChildren().add(buttonReserveAdd);
    }
}
