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
				primaryStage.setScene(scene); // ȭ���̵�
				primaryStage.show();
				Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
				main_pointLabel.setText(new Integer(Bird.levelValue).toString());
				savemethod.saveDate(); 	 						//��ȭ�ϰ� ���� �� ,���� ����
				
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
				primaryStage.setScene(scene); // ȭ���̵�
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
	
		
		public int Level_up_price = 200 * levelValue;											//��ȭ�ϴµ� �ʿ��� ��� ����
		
		public int Level_persent = (int) (Math.random()* Level_up_probability);					//�׻� 0~100������ ������ �߻���Ų��.
		public int Level_persent_Legend = (int) (Math.random()* (Level_up_probability*10));		//������ ��ȭ�� ��ȭȮ����  0.1%
		
		public int Level_persent_out = Level_up_probability-levelValue; 						//������ ���������� Ȯ���� 1% ����
		public double Level_persent_Legend_out = 0.1;
		
		public int Level_Up(){	
			if (levelValue == Level_up_probability 
					&& Level_persent_Legend == 777){											//������ 0~1000���� �߻��Ǵµ� ���� 777�̶�� ���ڰ� �ɸ��� ��ȭ����
				money -= Level_up_price;
				
				System.out.println("��������ȭ ����!!");
				return levelValue++;
				                                                                             
			}else if (levelValue == Level_up_probability)    {
				
				System.out.println("�ڷ����� ��ȭ�ڿ� �����Ͽ����ϴ�");
				System.out.println("��ȭ�� �ʿ��� ���� " + Level_up_price + "�̸�,");
				System.out.println("��ȭ�� ���� Ȯ���� 0.1 �Դϴ�.");
				money -= Level_up_price;
				
			}
			if((this.Level_persent <= Level_up_probability - levelValue) 
					&& (money >= Level_up_price)
					&& (levelValue < Level_up_probability) ){	//������ 1 ���� �� ���� if���� ������ Ȯ���� 1�پ���.
				
				money -= Level_up_price;
				 
				System.out.println("��ȭ ����!!");
				Bird_type mytype = new Bird_type();
				return levelValue++;
			
			}else if(levelValue < Level_up_probability){
				 if(money >= Level_up_price ){                                              
					 money -= Level_up_price;
				 }                         
				 
				
				System.out.println("���� ���� �����ϰų� ��ȭ�� �����߽��ϴ٤Ф�..���..��");
				System.out.println("��ȭ�� �ʿ��� ���� " + Level_up_price + "�̸�,");
				System.out.println("��ȭ�� ���� Ȯ����" + (Level_up_probability - levelValue) + "�Դϴ�.");
				
				
				
			}
			Bird_type mytype = new Bird_type();
			return levelValue;
		}
		
		
		
	}


		
}