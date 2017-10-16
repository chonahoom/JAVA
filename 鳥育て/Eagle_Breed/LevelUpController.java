package Eagle_Breed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.*;
import java.util.Random;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LevelUpController  implements Initializable{
	@FXML Button btnLevelBack;
	@FXML Button btnlevelup;
	
	ImageView Bird_Img_var;
	Label moneylabel;
	
	public Stage primaryStage;
	@Override
	
	public void initialize(URL location, ResourceBundle resources) {
		LoginController savemethod = new LoginController();
		
		btnLevelBack.setOnAction(event -> {
			primaryStage = (Stage) btnLevelBack.getScene().getWindow();

			Platform.runLater(() -> {
				
				primaryStage.close();

				FXMLLoader outLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
				Parent EaglePR = null;
				try {
					EaglePR = outLoader.load();
				} catch (IOException ex) {
				}
				Scene scene = new Scene(EaglePR);
				primaryStage.setScene(scene); // 화면이동
				primaryStage.show();
				Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
				main_pointLabel.setText(new Integer(Bird.levelValue).toString());
				savemethod.saveDate(); 	 						//진화하고 남은 돈 ,레벨 저장
				
				Bird_type mytype = new Bird_type();
				Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
				Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
			});	
		}); 
		
		btnlevelup.setOnAction(event -> {
			primaryStage = (Stage) btnlevelup.getScene().getWindow();

			Platform.runLater(() -> {
				
				primaryStage.close();

				FXMLLoader outLoader = new FXMLLoader(getClass().getResource("LevelupUI.fxml"));
				Parent EaglePR = null;
				try {
					EaglePR = outLoader.load();
				} catch (IOException ex) {
				}
				Level_Up startLevelUp = new Level_Up();
				startLevelUp.Level_Up();
				Scene scene = new Scene(EaglePR);
				primaryStage.setScene(scene); // 화면이동
				primaryStage.show();
				
				Label levelup_Label = (Label) EaglePR.lookup("#levelup_money");
				levelup_Label.setText(new Integer(Bird.money).toString());
				
				Label levelup_level_Label = (Label) EaglePR.lookup("#levelup_level");
				levelup_level_Label.setText(new Integer(Bird.levelValue).toString());
				
				Label levelup_levelpersent_Label = (Label) EaglePR.lookup("#levelup_persent");
				levelup_levelpersent_Label.setText(new Integer(startLevelUp.Level_persent_out).toString());
				
				Label levelup_levelPrice_Label = (Label) EaglePR.lookup("#levelupPrice_Lable");
				levelup_levelPrice_Label.setText(new Integer(startLevelUp.Level_up_price).toString());
				
				Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
				Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
					System.out.println(Bird_Img_var);
					
				Bird levelCheck = new Bird();
				if(levelCheck.levelValue == 100){
					Label levelup_Legendpersent_Label = (Label) EaglePR.lookup("#levelup_persent");
					levelup_Legendpersent_Label.setText(new Double(startLevelUp.Level_persent_Legend_out).toString());
				
				
				
				}  
				
			});	
		}); 
	}
	
	public static class Level_Up extends Bird implements probability_interface{
	
		
		public int Level_up_price = 200 * levelValue;											//진화하는데 필요한 비용 계산법
		
		public int Level_persent = (int) (Math.random()* Level_up_probability);					//항상 0~100까지의 난수를 발생시킨다.
		public int Level_persent_Legend = (int) (Math.random()* (Level_up_probability*10));		//레전드 진화시 진화확률은  0.1%
		
		public int Level_persent_out = Level_up_probability-levelValue; 						//레벨이 오를때마다 확률은 1% 감소
		public double Level_persent_Legend_out = 0.1;
		
		public int Level_Up(){	
			if (levelValue == Level_up_probability 
					&& Level_persent_Legend == 777){											//난수가 0~1000까지 발생되는데 그중 777이라는 숫자가 걸리면 진화성공
				money -= Level_up_price;
				
				System.out.println("레전드진화 성공!!");
				return levelValue++;
				                                                                             
			}else if (levelValue == Level_up_probability)    {
				
				System.out.println("★레전드 강화★에 실패하였습니다");
				System.out.println("진화에 필요한 돈은 " + Level_up_price + "이며,");
				System.out.println("진화에 성공 확률은 0.1 입니다.");
				money -= Level_up_price;
				
			}
			if((this.Level_persent <= Level_up_probability - levelValue) 
					&& (money >= Level_up_price)
					&& (levelValue < Level_up_probability) ){	//레벨이 1 증가 할 수록 if문을 충족할 확률이 1줄어든다.
				
				money -= Level_up_price;
				 
				System.out.println("진화 성공!!");
				Bird_type mytype = new Bird_type();
				return levelValue++;
			
			}else if(levelValue < Level_up_probability){
				 if(money >= Level_up_price ){                                              
					 money -= Level_up_price;
				 }                         
				 
				
				System.out.println("가진 돈이 부족하거나 진화에 실패했습니다ㅠㅠ..쟌넨..ㅠ");
				System.out.println("진화에 필요한 돈은 " + Level_up_price + "이며,");
				System.out.println("진화에 성공 확률은" + (Level_up_probability - levelValue) + "입니다.");
				
				
				
			}
			Bird_type mytype = new Bird_type();
			return levelValue;
		}
		
		
		
	}


		
}