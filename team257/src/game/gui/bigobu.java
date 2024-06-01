package game.gui;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.PriorityQueue;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class bigobu extends Application {
	static Stage popupStage;
	
	
	Battle battle;
	
	AnchorPane lane1;
	AnchorPane lane2;
	AnchorPane lane3;
	AnchorPane lane4;
	AnchorPane lane5;
	
	GridPane gridlane1;
	GridPane gridlane2;
	GridPane gridlane3;
	GridPane gridlane4;
	GridPane gridlane5;
	
	Label w1;
	Label w2;
	Label w3;
	
	Label l1;
	Label l2;
	Label l3;
	
	Label ws;
	Label rs;
	Label sc;
	Label ph;
	Label tu;
	Label al;
	Label ps;
	
	static Button back= new Button("Return");
	int x=500;
    VBox wall1;
    VBox wall2;
    VBox wall3;
    VBox wall4;
    VBox wall5;
    HBox weaponshop;
    HBox score;
    HBox passbutton;
    HBox phase;
    HBox rescources;
    HBox turn;
    HBox Available_lanes;
    Label Current_score;
    Label Current_turn;
    Label Current_phase;
    Label Current_Resources;
    Label Weapon_shop;
    Button easy= new Button("Easy");
    Button hard = new Button("Hard");
    Label w5 = new Label();
    Label w4 = new Label();
    
    public static class PopupHelper {

        public static void showPopup(String message) {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Error");

            Label label = new Label(message);
            VBox vbox = new VBox(label);
            vbox.setStyle("-fx-padding: 20px");

            Scene scene = new Scene(vbox, 300, 100);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        }
    }
    public static class PopupHelperend {
    	static Stage popupStage;

        public static void showPopup(String message) {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Game Over GETGUUD");

            Label label = new Label(message);
            VBox vbox = new VBox(label);
            vbox.setStyle("-fx-padding: 20px");
            vbox.getChildren().add(back);
            

            Scene scene = new Scene(vbox, 400, 150);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        }
        public static void closePopup() {
	        if (popupStage != null) {
	        	popupStage.close();
	        }
	    }
		
    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	
    	

        Group root = new Group();
        Scene sky = new Scene(root, 1000, 800);
        primaryStage.setScene(sky);
        primaryStage.show();

        Button start = new Button("Start");
        start.setStyle("-fx-font-size: 36px; -fx-background-color: #D5D043;");
        start.setMinWidth(200);
        start.setAlignment(Pos.CENTER);

        Group buttonGroup = new Group(start);
        buttonGroup.layoutXProperty().bind(sky.widthProperty().subtract(buttonGroup.prefWidth(-1)).divide(2));
        buttonGroup.layoutYProperty().bind(sky.heightProperty().subtract(buttonGroup.prefHeight(-1)).divide(2));

        root.getChildren().add(buttonGroup);

        primaryStage.setScene(sky);
        primaryStage.show();
        easy.setOnMouseClicked(event->{
        	  try {
                  battle=new Battle(1,0,575,3,250);

                  primaryStage.setScene(getgudeasy());
                  
              
              
              }
                  
                catch (Exception e) {
                  e.printStackTrace();
           
      }
        	
        	
        });
        hard.setOnMouseClicked(event->{
      	  try {
              battle=new Battle(1,0,575,5,125);

                primaryStage.setScene(getgudhard());

                
            
            
            }
                
              catch (Exception e) {
                e.printStackTrace();
         
    }
      	
      	
      });
        start.setOnMouseClicked(event -> {
        		
            try {
                primaryStage.setScene(getNewScene());
                
            
            
            }
                
              catch (Exception e) {
                e.printStackTrace();
         
    } ;
    
        });
        back.setOnMouseClicked(event->{
        	bigobu b= new bigobu();
        	try {
        		
        		PopupHelperend.closePopup();
				b.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });   
    }


       

 

	private Scene getNewScene() throws FileNotFoundException {
        StackPane root = new StackPane();
        VBox box=new VBox();
        
        
        Label instructions = new Label("Objective: Defeat as many enemies as possible\n" +
        	    "Titan Movement: Move closer to the wall each turn.\n" +
        	    "Attack Actions: defeated targets are removed.\n" +
        	    "Titans Spawning & Battle Phase: Titans added based on battle phase.\n" +
        	    "Weapon Purchase: Buy and deploy weapons into active lanes.\n" +
        	    "Turn Actions: Purchase and deploy weapons or pass turn.");
        instructions.setStyle("-fx-font-size: 80px;-fx-font-family: 'Edwardian Script ITC'; -fx-text-fill: black;");
        
        Scene sky1 = new Scene(root, 1000, 800);
        
        

        easy.setStyle("-fx-font-size: 36px; -fx-background-color: #D5D043;"); 
        easy.setMinWidth(200); 
        easy.setAlignment(Pos.TOP_RIGHT);
        
        
        hard.setStyle("-fx-font-size: 36px; -fx-background-color: #D5D043;"); 
        hard.setMinWidth(200); 
        hard.setAlignment(Pos.TOP_LEFT);
       
        HBox hbomb=new HBox();
        hbomb.setAlignment(Pos.CENTER);
        hbomb.getChildren().addAll(hard,easy);
        HBox.setMargin(hard, new Insets(10, 10, 10, 10));

        box.getChildren().addAll(hbomb,instructions);
        box.setPrefHeight(100);
        box.setPrefWidth(100);
        box.setAlignment(Pos.CENTER);
        root.getChildren().add(box);
        return sky1;

    }
	private Scene getgudhard() {
	    ToggleGroup toggleGroup = new ToggleGroup();

	    Button llane1 = new Button("1");
	    llane1.setOnAction(event -> x = 0);

	    Button llane2 = new Button("2");
	    llane2.setOnAction(event -> x = 1);

	    Button llane3 = new Button("3");
	    llane3.setOnAction(event -> x = 2);

	    Button llane4 = new Button("4");
	    llane4.setOnAction(event -> x = 3);

	    Button llane5 = new Button("5");
	    llane5.setOnAction(event -> x = 4);

	    HBox caak = new HBox();
	    caak.setPrefSize(30, 60);
	    caak.getChildren().addAll(llane1, llane2, llane3, llane4, llane5);

	    Label w1 = new Label("Maria");
	    Label w2 = new Label("Rose");
	    Label w3 = new Label("Sina");
	    Label w4 = new Label("Utgard");
	    Label w5 = new Label("Azumabito");

	    ws = new Label("Weapon-Shop");
	    rs = new Label("Resources= " + battle.getResourcesGathered());
	    sc = new Label("Score= " + battle.getScore());
	    ph = new Label("Phase= " + battle.getBattlePhase());
	    tu = new Label("Turn= " + battle.getNumberOfTurns());
	    al = new Label("Available-Lanes= " + battle.getLanes().size());
	    ps = new Label("Pass");

	    Label a = new Label("Anti-Titan Shell\nDmg= 10\nPrice= 25");
	    Label b = new Label("Long Range Spear\nDmg= 35\nPrice= 25");
	    Label c = new Label("Wall Spread Cannon\nDmg= 5\nPrice= 100");
	    Label d = new Label("Proximity Trap\nDmg= 100\nPrice= 75");

	    Button buypc = new Button("Buy Piercing Cannon");
	    buypc.setOnAction(event -> handleWeaponPurchase(1, "Piercing Cannon"));

	    Button buysc = new Button("Buy Sniper Cannon");
	    buysc.setOnAction(event -> handleWeaponPurchase(2, "Sniper Cannon"));

	    Button buyvs = new Button("Buy Volley Spread");
	    buyvs.setOnAction(event -> handleWeaponPurchase(3, "Volley Spread"));

	    Button buywt = new Button("Buy Wall Trap");
	    buywt.setOnAction(event -> handleWeaponPurchase(4, "Wall Trap"));


	    VBox piercingCannonContainer = new VBox(buypc, a);
	    piercingCannonContainer.setAlignment(Pos.TOP_CENTER);

	    VBox sniperCannonContainer = new VBox(buysc, b);
	    sniperCannonContainer.setAlignment(Pos.TOP_CENTER);

	    VBox vollySpreadContainer = new VBox(buyvs, c);
	    vollySpreadContainer.setAlignment(Pos.TOP_CENTER);

	    VBox wallTrapContainer = new VBox(buywt, d);
	    wallTrapContainer.setAlignment(Pos.TOP_CENTER);

	    Button pass = new Button();
	    pass.setOnAction(event -> {
	        battle.passTurn();
	        updateallhrd();
	    });

	    wall1=new VBox();
		 wall1.getChildren().addAll(w1);
		 wall1.setStyle("-fx-background-color: grey;");
		 wall1.setPrefSize(75,200);
		 Tooltip m= new Tooltip("Maria\nHealth " + battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(0).getDangerLevel());
	     m.install(wall1, m);
		 
		 

		
		 wall2=new VBox();
		 wall2.getChildren().addAll(w2);
		wall2.setStyle("-fx-background-color: grey;");
		 wall2.setPrefSize(75,200);
		 Tooltip m1= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(1).getDangerLevel());
	     m1.install(wall2, m1);


		
		 wall3=new VBox();
		 wall3.getChildren().addAll(w3);
		 wall3.setStyle("-fx-background-color: grey;");
		 wall3.setPrefSize(75,200);
		 Tooltip m2= new Tooltip("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
	     m2.install(wall3, m2);

	    
	    
	    wall4 = new VBox();
	    wall4.setPrefSize(75, 100);
	    wall4.getChildren().addAll(w4);
	    wall4.setStyle("-fx-background-color: grey;");
	    Tooltip m3 =new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(3).getDangerLevel());
	     m3.install(wall4, m3);
	    
	    
	    wall5 = new VBox();
	    wall5.setPrefSize(75, 100);
	    wall5.getChildren().addAll(w5);
	    wall5.setStyle("-fx-background-color: grey;");
	    Tooltip m4= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(4).getDangerLevel());
	     m4.install(wall5, m4);
	    
	    
	    weaponshop = new HBox(piercingCannonContainer, sniperCannonContainer, vollySpreadContainer, wallTrapContainer);
	    weaponshop.setPrefSize(1000, 400);

	    score = new HBox(sc);
	    score.setPrefSize(125, 200);

	    passbutton = new HBox(ps, pass);
	    passbutton.setPrefSize(125, 200);

	    phase = new HBox(ph);
	    phase.setPrefSize(125, 200);

	    rescources = new HBox(rs);
	    rescources.setPrefSize(125, 200);

	    turn = new HBox(tu);
	    turn.setPrefSize(125, 200);

	    Available_lanes = new HBox(al, caak);
	    Available_lanes.setPrefSize(125, 200);

	    lane1 = new AnchorPane();
	    lane1.setPrefSize(700, 50);
	    lane1.setStyle("-fx-background-color: green;");
	    lane1.getChildren().add(wall1);

	    lane2 = new AnchorPane();
	    lane2.setPrefSize(700, 50);
	    lane2.setStyle("-fx-background-color: green;");
	    lane2.getChildren().add(wall2);

	    lane3 = new AnchorPane();
	    lane3.setPrefSize(700, 50);
	    lane3.setStyle("-fx-background-color: green;");
	    lane3.getChildren().add(wall3);

	    lane4 = new AnchorPane();
	    lane4.setPrefSize(700, 50);
	    lane4.setStyle("-fx-background-color: green;");
	    lane4.getChildren().add(wall4);

	    lane5 = new AnchorPane();
	    lane5.setPrefSize(700, 50);
	    lane5.setStyle("-fx-background-color: green;");
	    lane5.getChildren().add(wall5);

	    VBox garbg = new VBox(score, rescources, turn);
	    garbg.setPrefSize(1000, 200);

	    VBox garby = new VBox(Available_lanes, phase, passbutton);
	    garby.setPrefSize(1000, 200);

	    HBox rubbish = new HBox(garbg, garby);
	    rubbish.setPrefSize(1000, 400);

	    VBox x = new VBox(weaponshop, rubbish);
	    x.setPrefSize(500, 800);
	    HBox.setHgrow(x, Priority.ALWAYS);

	    VBox y = new VBox(lane1, lane2, lane3, lane4, lane5);
	    y.setPrefSize(500, 800);
	    HBox.setHgrow(y, Priority.ALWAYS);

	    HBox root = new HBox(x, y);
	    root.setAlignment(Pos.CENTER);

	    return new Scene(root, 1000, 800);
	}

	public void updateallhrd() {
		Tooltip m= new Tooltip("Maria\nHealth " + battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(0).getDangerLevel());
	     m.install(wall1, m);
		 
		 Tooltip m1= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(1).getDangerLevel());
	     m1.install(wall2, m1);

		 Tooltip m2= new Tooltip("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
	     m2.install(wall3, m2);
	     
		 Tooltip m3 =new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(3).getDangerLevel());
		 m3.install(wall4, m3);
		    
		 Tooltip m4= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(4).getDangerLevel());
		 m4.install(wall5, m4);
		
	    sc.setText("Score= " + battle.getScore());
	    rs.setText("Resources= " + battle.getResourcesGathered());
	    ph.setText("Phase= " + battle.getBattlePhase());
	    tu.setText("Turn= " + battle.getNumberOfTurns());

	    if (battle.isGameOver()) {
	    	PopupHelperend.showPopup("YOU LOSE \n YOUR SCORE WAS " + battle.getScore());
	    } else {
	        ATTL(battle.GetTitans(0), lane1, wall1);
	        ATTL(battle.GetTitans(1), lane2, wall2);
	        ATTL(battle.GetTitans(2), lane3, wall3);
	        ATTL(battle.GetTitans(3), lane4, wall4);
	        ATTL(battle.GetTitans(4), lane5, wall5);
	    }
	}



		private Scene getgudeasy() { 
			ToggleGroup toggleGroup = new ToggleGroup();

		
			
			Button llane1= new Button("1");
			llane1.setOnAction(event->{
				 x=0;
			});
			
			
			Button llane2= new Button("2");
			llane2.setOnAction(event->{
            	x=1;

			});
			

			Button llane3= new Button("3");
            llane3.setOnAction(event->{
            	x=2;
				
			});
            
            
           
            HBox caak= new HBox();
            caak.setPrefSize(30, 60);
            caak.getChildren().addAll(llane1,llane2,llane3);
			


		
             w1 = new Label("Maria");
			 w1.setVisible(true);
			 w2 = new Label("Rose");
			 w2.setVisible(true);
			 w3 = new Label("Sina");
			 w3.setVisible(true);
			 w4 = new Label("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
			 w4.setVisible(true);
			 w5 = new Label("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
			 w5.setVisible(true);
			
			Label l1 = new Label("1");
			l1.setVisible(true);
			Label l2 = new Label("2");
			l2.setVisible(true);
			Label l3 = new Label("3");
			l3.setVisible(true);
			Label l4 = new Label("4");
			l3.setVisible(true);
			Label l5 = new Label("5");
			l3.setVisible(true);
			
			 ws= new Label("Weapon-Shop");
			 ws.setVisible(true);
			 rs= new Label("Rescources= "+battle.getResourcesGathered());
			 rs.setVisible(true);
			 sc= new Label("score= "+ battle.getScore());
			 sc.setVisible(true);
			 ph= new Label("phase= "+ battle.getBattlePhase());
			 ph.setVisible(true);
			 tu= new Label("turn= "+ battle.getNumberOfTurns());
			 tu.setVisible(true);
			 al= new Label("Available-Lanes= "+battle.getLanes().size());
			 al.setVisible(true);
			 ps= new Label("pass");
			 ps.setVisible(true);
			
			
			Label a= new Label("Anti-Titan Shell\nDmg= 10\nPrice= 25");
			a.setVisible(true);
			Label b= new Label("Long Range Spear\nDmg= 35\nPrice= 25");
			b.setVisible(true);
			Label c= new Label("Wall Spread Cannon\nDmg= 5\nPrice= 100");
			c.setVisible(true);
			Label d= new Label("Proximity Trap \nDmg= 100 \nPrice= 75");
			d.setVisible(true);

			
			Button buypc = new Button();
			buypc.setText("Buy Piercing Cannon");
			
			buypc.setOnAction(event -> {
			   
			    
			    	
			        try {
			        	if(x!=0&x!=1&x!=2)
			        		PopupHelper.showPopup("you must pick a lane");
			        	else{
						battle.purchaseWeapon(1, battle.getOriginalLanes().get(x));
						if(x==0){
							Rectangle rec =new Rectangle(10,20);
							wall1.getChildren().add(rec);
							Tooltip w= new Tooltip("type: Piercing Cannon");
						     w.install(rec, w);
						}
						else if(x==1){
							Rectangle rec =new Rectangle(10,20);
							wall2.getChildren().add(rec);
							Tooltip w= new Tooltip("type: Piercing Cannon");
						     w.install(rec, w);
						}
						else if(x==2){
							Rectangle rec =new Rectangle(10,20);
							wall3.getChildren().add(rec);
							Tooltip w= new Tooltip("type: Piercing Cannon");
						     w.install(rec, w);
						}
						
						updateall();
			        	}
					}
			        catch ( InsufficientResourcesException e){
			        	PopupHelper.showPopup("no munnah");
					} 
			        catch(InvalidLaneException e){
			        	PopupHelper.showPopup("Lane not found");

					}
			});
			
			
			
			Button buysc=new Button();
			buysc.setText("Buy Sniper Cannon");
			
			
			
			buysc.setOnAction(event -> {
				   
			    
		    	
		        try {
		        	if(x!=0&x!=1&x!=2)
		        		PopupHelper.showPopup("you must pick a lane");
		        	else{
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(x));
					if(x==0){
						Rectangle rec =new Rectangle(10,20);
						wall1.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Sniper Cannon");
					     w.install(rec, w);
					}
					else if(x==1){
						Rectangle rec =new Rectangle(10,20);
						wall2.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Sniper Cannon");
					     w.install(rec, w);
					}
					else if(x==2){
						Rectangle rec =new Rectangle(10,20);
						wall3.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Sniper Cannon");
					     w.install(rec, w);
					}
			
					updateall();
		        	}
				}
		        catch ( InsufficientResourcesException e){
		        	PopupHelper.showPopup("no munnah");
				} 
		        catch(InvalidLaneException e){
		        	PopupHelper.showPopup("Lane not found");

				}
		    
		});


			Button buyvs=new Button();
			buyvs.setText("Buy Volley Spread");
			
			
			buyvs.setOnAction(event -> {
				   
			    
		    	
		        try {
		        	if(x!=0&x!=1&x!=2)
		        		PopupHelper.showPopup("you must pick a lane");
		        	else{
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(x));
					if(x==0){
						Rectangle rec =new Rectangle(10,20);
						wall1.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Volley Spread");
					     w.install(rec, w);
					}
					else if(x==1){
						Rectangle rec =new Rectangle(10,20);
						wall2.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Volley Spread");
					     w.install(rec, w);
					}
					else if(x==2){
						Rectangle rec =new Rectangle(10,20);
						wall3.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Volley Spread");
					     w.install(rec, w);
					}
					
					updateall();
		        	}
				}
		        catch ( InsufficientResourcesException e){
		        	PopupHelper.showPopup("no munnah");
				} 
		        catch(InvalidLaneException e){
		        	PopupHelper.showPopup("Lane not found");

				}
		    
		});
			
			
			
			

			
			Button buywt=new Button();
			buywt.setText("Buy Wall Trap");
			
			
			
			buywt.setOnAction(event -> {
				   
			    
		    	
		        try {
		        	if(x!=0&x!=1&x!=2)
		        		PopupHelper.showPopup("you must pick a lane");
		        	else{
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(x));
					if(x==0){
						Rectangle rec =new Rectangle(10,20);
						wall1.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Wall Trap");
					     w.install(rec, w);
					}
					else if(x==1){
						Rectangle rec =new Rectangle(10,20);
						wall2.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Wall Trap");
					     w.install(rec, w);
					}
					else if(x==2){
						Rectangle rec =new Rectangle(10,20);
						wall3.getChildren().add(rec);
						Tooltip w= new Tooltip("type: Wall Trap");
					     w.install(rec, w);
					}
					
					updateall();
		        	}
				}
		        catch ( InsufficientResourcesException e){
		        	PopupHelper.showPopup("no munnah");
				} 
		        catch(InvalidLaneException e){
		        	PopupHelper.showPopup("Lane not found");

				}
		    
		});
			
			VBox piercingCannonContainer = new VBox();
			piercingCannonContainer.getChildren().addAll(buypc, a);
			piercingCannonContainer.setAlignment(Pos.TOP_CENTER);

			VBox sniperCannonContainer = new VBox();
			sniperCannonContainer.getChildren().addAll(buysc, b);
			sniperCannonContainer.setAlignment(Pos.TOP_CENTER);

			VBox vollySpreadContainer = new VBox();
			vollySpreadContainer.getChildren().addAll(buyvs, c);
			vollySpreadContainer.setAlignment(Pos.TOP_CENTER);

			VBox wallTrapContainer = new VBox();
			wallTrapContainer.getChildren().addAll(buywt, d);
			wallTrapContainer.setAlignment(Pos.TOP_CENTER);
			
			lane1 = new AnchorPane();
			lane1.setMaxHeight(300);
			lane1.setMaxWidth(700);
			lane1.setStyle("-fx-background-color: green;");
			lane2 = new AnchorPane();
			lane2.setMaxHeight(300);
			lane2.setMaxWidth(700);
			lane2.setStyle("-fx-background-color: green;");
			lane3 = new AnchorPane();
			lane3.setMaxHeight(300);
			lane3.setMaxWidth(700);
			lane3.setStyle("-fx-background-color: green;");
			lane1.setTranslateX(-40);
			lane2.setTranslateX(-40);
			lane3.setTranslateX(-40);
			
			Button pass=new Button();
			 pass.setOnAction(event->{
				 battle.passTurn();
				 updateall();
			 });

			
			 wall1=new VBox();
			 wall1.getChildren().addAll(w1);
			 wall1.setStyle("-fx-background-color: grey;");
			 wall1.setPrefSize(75,200);
			 Tooltip m= new Tooltip("Maria\nHealth " + battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(0).getDangerLevel());
		     m.install(wall1, m);
			 
			 

			
			 wall2=new VBox();
			 wall2.getChildren().addAll(w2);
			wall2.setStyle("-fx-background-color: grey;");
			 wall2.setPrefSize(75,200);
			 Tooltip m1= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(1).getDangerLevel());
		     m1.install(wall2, m1);


			
			 wall3=new VBox();
			 wall3.getChildren().addAll(w3);
			 wall3.setStyle("-fx-background-color: grey;");
			 wall3.setPrefSize(75,200);
			 Tooltip m2= new Tooltip("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
		     m2.install(wall3, m2);



			
			 weaponshop=new HBox();
			 weaponshop.getChildren().addAll(piercingCannonContainer,sniperCannonContainer,vollySpreadContainer,wallTrapContainer);
			 weaponshop.setPrefSize(1000, 400);
			 //weaponshop.setStyle("-fx-background-color: red;");
			 
		     score=new HBox();
		     score.getChildren().add(sc);
		     score.setPrefSize(125, 200);
		     //score.setStyle("-fx-background-color: black;");

		     
		     passbutton=new HBox();
		     passbutton.getChildren().add(ps);
		     passbutton.setPrefSize(125, 200);
		     passbutton.getChildren().add(pass);
		     //passbutton.setStyle("-fx-background-color: white;");
		     
		     phase=new HBox();
		     phase.getChildren().add(ph);
		     phase.setPrefSize(125, 200);
		     //phase.setStyle("-fx-background-color: black;");
		     
		     rescources=new HBox();
		     rescources.getChildren().add(rs);
		     rescources.setPrefSize(125, 200);
		     
		     //rescources.setStyle("-fx-background-color: violet;");
		     
		     turn=new HBox();
		     turn.getChildren().addAll(tu);
		     turn.setPrefSize(125, 200);
		    // turn.setStyle("-fx-background-color: red;");

		     
		     Available_lanes=new HBox();
		     Available_lanes.getChildren().addAll(al,caak);
		     Available_lanes.setPrefSize(125, 200);
		     //Available_lanes.setStyle("-fx-background-color: cyan;");
		     lane1.getChildren().addAll(l1,wall1); // Add l1 to column 0, row 0
		     wall1.setTranslateX(0); // Add wall1 to column 1, row 0
		     wall1.setTranslateY(0);
		     // Add children to lane2 GridPane
		     lane2.getChildren().addAll(l2,wall2); // Add l1 to column 0, row 0
		     wall2.setTranslateX(0); // Add wall1 to column 1, row 0
		     wall2.setTranslateY(0);
		     // Add children to lane3 GridPane
		     lane3.getChildren().addAll(l3,wall3); // Add l1 to column 0, row 0
		     wall3.setTranslateX(0); // Add wall1 to column 1, row 0
		     wall3.setTranslateY(0);

			VBox garbg=new VBox(score,rescources,turn);
			garbg.setPrefSize(1000, 200);
			//garbg.setStyle("-fx-background-color: green;");
			VBox garby=new VBox(Available_lanes,phase,passbutton);
			garby.setPrefSize(1000, 200);
			//garby.setStyle("-fx-background-color: yellow;");

			HBox rubbish=new HBox();
			rubbish.getChildren().addAll(garbg,garby);
			//rubbish.setStyle("-fx-background-color: brown;");
			rubbish.setPrefSize(1000, 400);
			
			
			
			
			HBox root = new HBox();
			root.setAlignment(Pos.CENTER);
			VBox x=new VBox();
			//x.setStyle("-fx-background-color: red;");
			x.setPrefSize(500,800);
			HBox.setHgrow(x, javafx.scene.layout.Priority.ALWAYS);
			x.getChildren().addAll(weaponshop,rubbish);
			
			VBox y=new VBox();
				//y.setStyle("-fx-background-color: blue;");
				y.setPrefSize(500,800);
				HBox.setHgrow(y, javafx.scene.layout.Priority.ALWAYS);
				y.getChildren().addAll(lane1,lane2,lane3);
				
				
			 root.getChildren().addAll(x,y);
			Scene s = new Scene(root,1000,800);
			return s;
		}
		
		public  void updateall(){
			Tooltip m= new Tooltip("Maria\nHealth " + battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(0).getDangerLevel());
		     m.install(wall1, m);
			 
			 Tooltip m1= new Tooltip("Rose\nHealth " + battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(1).getDangerLevel());
		     m1.install(wall2, m1);

			 Tooltip m2= new Tooltip("Sina\nHealth " + battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() + "\nDanger LVL " + battle.getOriginalLanes().get(2).getDangerLevel());
		     m2.install(wall3, m2);
			
			 rs.setText("Rescources= "+battle.getResourcesGathered());
			 sc.setText("score= "+ battle.getScore());
			 ph.setText("phase= "+ battle.getBattlePhase());
			 tu.setText("turn= "+ battle.getNumberOfTurns());
			 al.setText("Available-Lanes= "+battle.getLanes().size());
			 if(battle.getLanes().size()==0){
				 PopupHelperend.showPopup( "Game Over YOU LOSE LOSER F's IN THE CHAT "+"\nSO BAD YOUR SCORE WAS "+battle.getScore());
				 
			 }else {
			ATTL(battle.GetTitans(0),lane1,wall1);
			ATTL(battle.GetTitans(1),lane2,wall2);
			ATTL(battle.GetTitans(2),lane3,wall3);
			 }
		}
		public void ATTL(ArrayList<Titan> H,AnchorPane lane,VBox v) {
			lane.getChildren().clear();
			lane.getChildren().add(v);
			for(Titan titan:H){
			 Rectangle titanShape = new Rectangle(20, titan.getHeightInMeters()/2);
		        titanShape.setFill(titan.getColor());
		     lane.getChildren().add(titanShape);
		     titanShape.setTranslateX(75+titan.getDistance()*1.0);
		     titanShape.setTranslateY(25);
		     
		     if(titan instanceof PureTitan){
		     Tooltip t= new Tooltip("type: PureTitan"+"\nhealth: "+titan.getCurrentHealth()+"\nhight: "+titan.getHeightInMeters());
		     t.install(titanShape, t);
		     }
		     else if(titan instanceof AbnormalTitan){
			     Tooltip t= new Tooltip("type: AbnormalTitan"+"\nhealth: "+titan.getCurrentHealth()+"\nhight: "+titan.getHeightInMeters());
			     t.install(titanShape, t);
			 }
		     else if(titan instanceof ArmoredTitan){
			     Tooltip t= new Tooltip("type: ArmoredTitan"+"\nhealth: "+titan.getCurrentHealth()+"\nhight: "+titan.getHeightInMeters());
			     t.install(titanShape, t);
			 }
		     else if(titan instanceof ColossalTitan){
			     Tooltip t= new Tooltip("type: ColossalTitan"+"\nhealth: "+titan.getCurrentHealth()+"\nhight: "+titan.getHeightInMeters());
			     t.install(titanShape, t);
			 }
		     
		    
			 }
	}
		private void purchaseWeapon(int weaponId) {
		    try {
		        battle.purchaseWeapon(weaponId, battle.getOriginalLanes().get(x));
		        System.out.println(battle.getResourcesGathered());
		        updateallhrd();
		    } catch (InsufficientResourcesException e) {
		        PopupHelper.showPopup("No munnah");
		    } catch (InvalidLaneException e) {
		        PopupHelper.showPopup("Lane not found");
		    }
		}
		private void handleWeaponPurchase(int weaponId, String weaponType) {
		    try {
		        if (x < 0 || x >= battle.getOriginalLanes().size()) {
		            PopupHelper.showPopup("You must pick a lane");
		        } else {
		            battle.purchaseWeapon(weaponId, battle.getOriginalLanes().get(x));
		            Rectangle rec = new Rectangle(10, 20);
		            Tooltip w = new Tooltip("Type: " + weaponType);

		            switch (x) {
		                case 0:
		                    wall1.getChildren().add(rec);
		                    w.install(rec, w);
		                    break;
		                case 1:
		                    wall2.getChildren().add(rec);
		                    w.install(rec, w);
		                    break;
		                case 2:
		                    wall3.getChildren().add(rec);
		                    w.install(rec, w);
		                    break;
		                case 3:
		                    wall4.getChildren().add(rec);
		                    w.install(rec, w);
		                    break;
		                case 4:
		                    wall5.getChildren().add(rec);
		                    w.install(rec, w);
		                    break;
		            }

		            updateallhrd();
		        }
		    } catch (InsufficientResourcesException e) {
		        PopupHelper.showPopup("No munnah");
		    } catch (InvalidLaneException e) {
		        PopupHelper.showPopup("Lane not found");
		    }
		}


		
		
		

    public static void main(String[] args) {
        launch(args);
    }
}