package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<Button> buttonArrayList = new ArrayList<>();
    ArrayList<Character> signesArrayList = new ArrayList<>();
    ArrayList<Integer> integersArrayList = new ArrayList<>();
    ArrayList<Integer> indiceMultEtDiv = new ArrayList<>();
    ArrayList<Integer> indiceMultEtDivRemoved = new ArrayList<>();
    String afficheurText = "";
    boolean usedEqual = false;

    public static boolean isNumeric(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        }catch (Exception e){
            System.out.println("Que des types numerique ou des opérateurs");
        }
        return false;
    }

    public void effacerUnCharacter(Label label){
        afficheurText = afficheurText.substring(0,afficheurText.length()-1);
        label.setText(afficheurText);
    }

    public void interpreteur(String str){
        System.out.println("Str taille : " + str.length());
        System.out.println("Taille integersArrayList : " + integersArrayList.size());
        String nombreDuMoment = "";
        for(int i = 0; i < str.length(); i++){
            if((str.charAt(i) == '+')||(str.charAt(i) == '-')||(str.charAt(i) == '*')||(str.charAt(i) == '/')){
                integersArrayList.add(Integer.parseInt(nombreDuMoment));
                System.out.println("Taille integersArrayList : " + integersArrayList.size());
                signesArrayList.add(str.charAt(i));
                nombreDuMoment = "";
            }
            else{
                nombreDuMoment = nombreDuMoment + str.charAt(i);
            }
        }
        integersArrayList.add(Integer.parseInt(nombreDuMoment));
        System.out.println("Taille integersArrayList : " + integersArrayList.size());
    }

    void toutVider(){
        while(signesArrayList.size() > 0){
            signesArrayList.remove(0);
        }
        while (integersArrayList.size() > 0){
            integersArrayList.remove(0);
        }
        while(indiceMultEtDiv.size() > 0){
            indiceMultEtDiv.remove(0);
        }
    }

    public void permuteIntergers(int index1, int index2){
        int transi = index1;
        integersArrayList.set(index1,integersArrayList.get(index2));
        integersArrayList.set(index2,integersArrayList.get(transi));
    }

    /*public void trier(){
        for(int i = 0 ; i < indiceMultEtDiv.size(); i++){
            Character transi = signesArrayList.get(i);
            if(signesArrayList.size() >= indiceMultEtDiv.get(i)) {
                signesArrayList.set(i, signesArrayList.get(indiceMultEtDiv.get(i)));
                signesArrayList.set(signesArrayList.get(indiceMultEtDiv.get(i)), transi);
            }
            else{
                System.out.println("signes ArrayList.size() < indiceMultEtDiv.get(i)");
            }
            permuteIntergers(i,indiceMultEtDiv.get(i));
            permuteIntergers(i+1,indiceMultEtDiv.get(i+1));

        }
    }*/

    public void findIndexMultAndDiv(){
        for(int i = 0; i < signesArrayList.size(); i++){
            if((signesArrayList.get(i) == '*') || (signesArrayList.get(i) == '/')){
                indiceMultEtDiv.add(i);
            }
        }
    }

    public int multEtDivAddEtSous(){

        int j = 0;
        while(indiceMultEtDiv.size() > 0){
            int index = indiceMultEtDiv.get(0)-j;
            if(signesArrayList.get(index) == '*'){
                System.out.println(integersArrayList.size());
                for(Integer inte : integersArrayList){
                    System.out.println("La valeur est : " + inte);
                }
                integersArrayList.set(index,integersArrayList.get(index)*integersArrayList.get(index+1));
                signesArrayList.remove(index);
                integersArrayList.remove(index+1);
                indiceMultEtDivRemoved.add(index);
                indiceMultEtDiv.remove(0);
            }
            else{
                integersArrayList.set(index,integersArrayList.get(index)/integersArrayList.get(index+1));
                signesArrayList.remove(index);
                integersArrayList.remove(index+1);
                indiceMultEtDivRemoved.add(index);
                indiceMultEtDiv.remove(0);
            }
            j++;
        }

        while(integersArrayList.size() > 1){
            if(signesArrayList.get(0) == '+'){
                integersArrayList.set(0,integersArrayList.get(0)+integersArrayList.get(1));
                System.out.print(integersArrayList.get(0) + " + " + integersArrayList.get(1) + " = ");
                signesArrayList.remove(0);
                integersArrayList.remove(1);
                System.out.println(integersArrayList.get(0));
            }
            else{
                integersArrayList.set(0,integersArrayList.get(0)-integersArrayList.get(1));
                signesArrayList.remove(0);
                integersArrayList.remove(1);
            }
        }
        return integersArrayList.get(0);
    }

    /*public boolean belongTo(int index){
        for(Integer entier : indiceMultEtDiv){
            if(index == entier){
                return true;
            }
        }
        return false;
    }*/

    /*public int calculator(){
        int entier = 0;
        int interet;

        for(int i = 0; i < indiceMultEtDiv.size(); i++) {
                interet = indiceMultEtDiv.get(i);
                if(indiceMultEtDiv.get(interet) == '*'){
                    entier = integersArrayList.get(interet)*integersArrayList.get(interet+1);
                    integersArrayList.set(interet+1,entier);
                    signesArrayList.remove(interet);
                }
                else if(indiceMultEtDiv.get(interet) == '/'){
                    entier = integersArrayList.get(interet)/integersArrayList.get(interet+1);
                    integersArrayList.set(interet+1,entier);
                    signesArrayList.remove(interet);
                }
        }

        for(int j = 0; j < signesArrayList.size(); j++){
            if(signesArrayList.get(j) == '+'){
                System.out.println("Taille = " + signesArrayList.size());
                System.out.println("Taille Array Entier = " + integersArrayList.size());
                entier = integersArrayList.get(j) + integersArrayList.get(j+1);
                integersArrayList.set(integersArrayList.get(j),0);
                integersArrayList.set(integersArrayList.get(j+1),entier);
            }
            else if(signesArrayList.get(j) == '-'){
                entier = integersArrayList.get(j) - integersArrayList.get(j+1);
                integersArrayList.set(integersArrayList.get(j),0);
                integersArrayList.set(integersArrayList.get(j+1),entier);
            }
        }

        return entier;
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calculator");

        VBox principalVBox = new VBox();
        principalVBox.setAlignment(Pos.CENTER);
        StackPane afficheur = new StackPane();
        afficheur.setPadding(new Insets(0));
        Rectangle afficheurBackground = new Rectangle(260,60, Color.LIGHTBLUE);
        Label afficheurLabel = new Label("0");
        afficheur.getChildren().addAll(afficheurBackground,afficheurLabel);
        afficheurLabel.setAlignment(Pos.CENTER_RIGHT);
        afficheurLabel.setPadding(new Insets(0,30,0,0));
        afficheurLabel.setMaxSize(260,200);
        afficheurLabel.setMinHeight(50);
        afficheurLabel.setStyle("-fx-border-color: #000000;");
        GridPane paveNumerique = new GridPane();
        /*paveNumerique.setHgap(2);
        paveNumerique.setVgap(2);*/
        /*paveNumerique.setPadding(new Insets(0,5,5,5));*/
        paveNumerique.setMinSize(200,200);



        int i = 1;
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                buttonArrayList.add(new Button(Integer.toString(i)));
                buttonArrayList.get(i-1).setMinSize(65,50);
                paveNumerique.add(buttonArrayList.get(i-1),k,j);
                i++;
            }
        }

        Button buttonC = new Button("C");
        buttonC.setMinSize(65,50);
        paveNumerique.add(buttonC,0,3);

        Button button0 = new Button("0");
        button0.setMinSize(65,50);
        paveNumerique.add(button0,1,3);

        Button buttonEq = new Button("=");
        buttonEq.setMinSize(65,50);
        paveNumerique.add(buttonEq,2,3);

        Button buttonPlus = new Button("+");
        buttonPlus.setMinSize(65,50);
        paveNumerique.add(buttonPlus,3,0);

        Button buttonMin = new Button("-");
        buttonMin.setMinSize(65,50);
        paveNumerique.add(buttonMin,3,1);

        Button buttonTime = new Button("*");
        buttonTime.setMinSize(65,50);
        paveNumerique.add(buttonTime,3,2);

        Button buttonDiv = new Button("/");
        buttonDiv.setMinSize(65,50);
        paveNumerique.add(buttonDiv,3,3);


        int h = 0;

        principalVBox.getChildren().addAll(afficheur,paveNumerique);

        Scene seuleScene = new Scene(principalVBox);
        primaryStage.setScene(seuleScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Espace Clavier

        seuleScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Le code est : " + keyEvent.getCode() + "\n" + "Le Text est : " + keyEvent.getText());
                if(isNumeric(keyEvent.getText())){
                    if(usedEqual){
                        afficheurText = "";
                    }
                    afficheurText = afficheurText + keyEvent.getText();
                    afficheurLabel.setText(afficheurText);
                    usedEqual = false;
                }
                if((keyEvent.getCode() == KeyCode.DIVIDE)||(keyEvent.getCode() == KeyCode.MULTIPLY)||(keyEvent.getCode() == KeyCode.SUBTRACT)||(keyEvent.getCode() == KeyCode.ADD)){
                    afficheurText = afficheurText + keyEvent.getText();
                    afficheurLabel.setText(afficheurText);
                    usedEqual = false;
                }
                if(keyEvent.getCode() == KeyCode.BACK_SPACE){
                    effacerUnCharacter(afficheurLabel);
                }
                if(keyEvent.getCode() == KeyCode.EQUALS){
                    try {
                        interpreteur(afficheurText);
                        findIndexMultAndDiv();
                        afficheurText = Integer.toString(multEtDivAddEtSous());
                        afficheurLabel.setText(afficheurText);
                        toutVider();
                    }catch(Exception e){
                        e.printStackTrace();
                    System.out.println("Number format Exception : Veuillez rentrer des calculs de la forme " +
                            "a n b n c n d ... où a,b,c,d sont de nombres et n des opérations");
                    }
                    usedEqual = true;
                }
            }
        });

        //----------------------  ----------------END---------------------------------------------------------


        // Espace Boutons

        for(Button but : buttonArrayList){
            but.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(usedEqual){
                        afficheurText = "";
                    }
                    afficheurText = afficheurText + but.getText();
                    afficheurLabel.setText(afficheurText);
                    usedEqual = false;
                }
            });
        }

        buttonC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                afficheurText = "";
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });

        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(usedEqual){
                    afficheurText = "";
                }
                afficheurText = afficheurText + button0.getText();
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });

        buttonEq.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    interpreteur(afficheurText);
                    findIndexMultAndDiv();
                    afficheurText = Integer.toString(multEtDivAddEtSous());
                    afficheurLabel.setText(afficheurText);
                    toutVider();
                }catch(Exception e){
                    e.printStackTrace();
                    /*System.out.println("Number format Exception : Veuillez rentrer des calculs de la forme " +
                            "a n b n c n d ... où a,b,c,d sont de nombres et n des opérations");*/
                }
                usedEqual = true;
            }

        });

        buttonPlus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                afficheurText = afficheurText + buttonPlus.getText();
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });

        buttonMin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                afficheurText = afficheurText + buttonMin.getText();
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });

        buttonTime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                afficheurText = afficheurText + buttonTime.getText();
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });

        buttonDiv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                afficheurText = afficheurText + buttonDiv.getText();
                afficheurLabel.setText(afficheurText);
                usedEqual = false;
            }
        });


        //--------------------------------------END---------------------------------------------------------
    }


    public static void main(String[] args) {
        launch(args);
    }
}
