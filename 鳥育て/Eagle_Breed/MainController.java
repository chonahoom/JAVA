package Eagle_Breed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import Eagle_Breed.LevelUpController.Level_Up;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainController  implements Initializable{
	@FXML Button btnLevelup;
	@FXML Button btnAdventure;
	@FXML Button loginbtn;
	@FXML Button ad_back;
	
	
	 Button Leftbtn;
	 Button Rightbtn;
	 Button back;
	 
	 public static long timeCount = 18000000000L;
	 public static long timeLimit = 20000000000L;
	public Stage primaryStage;
	FXMLLoader outLoader =null;
	Parent EaglePR = null;
	ImageView Bird_Img_var;
	ImageView img;
	ImageView Rimg;
	ProgressBar timeLimitBar;
	Label pointLabel;
	Label comboLabel;
	Thread monThread;
	Thread mytimeThread;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
			
		btnLevelup.setOnAction(event -> {
			primaryStage = (Stage) btnLevelup.getScene().getWindow();

			Platform.runLater(() -> {
				
				primaryStage.close();
				
				outLoader = new FXMLLoader(getClass().getResource("LevelupUI.fxml"));
				
				try {
					EaglePR = outLoader.load();
					
				} catch (IOException ex) {
				}
				
				
				Scene scene = new Scene(EaglePR);
				primaryStage.setScene(scene); // 화면이동
				primaryStage.show();
				
	
				Label levelup_Label = (Label) EaglePR.lookup("#levelup_money");
				levelup_Label.setText(new Integer(Bird.money).toString());
				
				Label levelup_level_Label = (Label) EaglePR.lookup("#levelup_level");
				levelup_level_Label.setText(new Integer(Bird.levelValue).toString());
				
				
				Level_Up startLevelUp = new Level_Up();
				
				Label levelup_levelpersent_Label = (Label) EaglePR.lookup("#levelup_persent");
				levelup_levelpersent_Label.setText(new Integer(startLevelUp.Level_persent_out).toString());
				
				Label levelup_levelPrice_Label = (Label) EaglePR.lookup("#levelupPrice_Lable");
				levelup_levelPrice_Label.setText(new Integer(startLevelUp.Level_up_price).toString());
				
				Bird_type mytype = new Bird_type();
				Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
				Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
				
			});	
		}); 
		
		
		loginbtn.setOnAction(event -> {
			primaryStage = (Stage) loginbtn.getScene().getWindow();

			Platform.runLater(() -> {
				
				primaryStage.close();
				
				outLoader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
				
					try {
						EaglePR = outLoader.load();
						
					} catch (IOException ex) {
					}				
				Scene scene = new Scene(EaglePR);
				
				primaryStage.setScene(scene); // 화면이동
				primaryStage.show();
			});
		}); 
		
		
		
		btnAdventure.setOnAction(event -> {
			
			primaryStage = (Stage) btnAdventure.getScene().getWindow();

			Platform.runLater(() -> {
				
				primaryStage.close();
				outLoader = new FXMLLoader(getClass().getResource("AdventureUI.fxml"));
				
				
			
				try {
					EaglePR = outLoader.load();
				} catch (IOException ex) {
				}
				
				back = (Button) EaglePR.lookup("#ad_back");
				
				
				
				
				
				back.setOnAction(backevent -> {						
					
					Platform.runLater(() -> {
						try {
							back.setDisable(false);
							primaryStage.close();
							
							outLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
							
							try {
								EaglePR = outLoader.load();
							} catch (IOException ex) {
							}
							Scene scene = new Scene(EaglePR);
							primaryStage.setScene(scene);
							primaryStage.show();
							
							Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
							main_pointLabel.setText(new Integer(Bird.levelValue).toString());
							
							
							Bird_type mytype = new Bird_type();
							Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
							Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
							
						
							
						} catch (Exception e) {
						}
					});
				});
				
				Adventure GameStart = new Adventure();
				Scene scene = new Scene(EaglePR);
				
				primaryStage.setScene(scene); // 화면이동			
				primaryStage.show();
				Label pointLabel = (Label) EaglePR.lookup("#ad_Score");
				Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
				
				Bird_type mytype = new Bird_type();
				Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
				Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
				
				 monThread = new Thread(new Runnable(){
					public void run(){
						while(true){
							
							Platform.runLater(() -> {	
								Task mytimeTask = new Task<Integer>() {
									protected Integer call() throws Exception {
									updateProgress(timeCount, timeLimit);
									
										return null;
									}
								};
								timeLimitBar.progressProperty().bind(mytimeTask.progressProperty());
								Thread mytimeThread = new Thread(mytimeTask);
								mytimeThread.start();
								
							});
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
							
								e.printStackTrace();
							}
					}
					}
				});		
				
				monThread.start();
			
				
			});
			
		}); 
		
	}


	public class Adventure extends Bird {
		
		
		String [] data = {"지렁이.JPG","warring.JPG"};
		ArrayList<String> game_data_left =new ArrayList<String>();
		ArrayList<String> game_data_right =new ArrayList<String>();
		
		int ComboCount = 0;
		int score = 0;
		boolean flag = false;
		Boolean flagN =true;
	     Boolean flagM =true;
		
		Thread threadStart = null;
		exit startThread= null;
		Scanner scan = new Scanner(System.in);
		Money moneycount = new Money();
		
		
		public Adventure(){
			 
			 for (int i = 0 ; i < 9; i++){
				
				int rand = (int)(Math.random()*data.length);									//게임이 시작하기전, 난수를 발생시켜 왼쪽 컬렉션아 값을 집이넣고,
				String sj_cnt = Integer.toString(i);												
				game_data_left.add(data[rand]);
				Platform.runLater(() -> {
					img = (ImageView) EaglePR.lookup("#laim"+sj_cnt );
					img.setImage(new Image(getClass().getResource(data[rand]).toString()));
					
				});
				input_right(i);																	//왼쪽 컬렉션이 값이 집어넣어질 때마다 왼쪽 컬랙션 값을 기준으로 오른쪽 컬렉션의 값이 설정됨.
				
				
			  }
			
			 System.out.println("게임을 시작합니다.");
			 
		
			 
			 Leftbtn = (Button) EaglePR.lookup("#Leftbtn");
			 Rightbtn = (Button) EaglePR.lookup("#Rightbtn");	
			
			 	 startThread = new exit();
			     threadStart = new Thread(startThread);
			     threadStart.start();
			     
			     timeCount = 18000000000L;
			     timeLimit = 20000000000L;
			     
			     
			     Leftbtn.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
				 
				 	 if (event.getCode() == KeyCode.N && flagN == true) {
				 		
				 		while(flagN){
				 			flagM=false;														//N버튼을 눌렀을 때 M 버튼이 함께 눌리는 것을 방지
					 		puss_n();
					 		Label pointLabel = (Label) EaglePR.lookup("#ad_Score");
					 		pointLabel.setText(new Integer(score*Bird.levelValue).toString());
					 		 						 		
					 		Label comboLabel = (Label) EaglePR.lookup("#combo_Label");
					 		comboLabel.setText(new Integer(ComboCount).toString());
					 	break;
				 		}
					 	
				 	 }
				 	 if(event.getCode() == KeyCode.M  && flagM == true) {
				 		while(flagM){
					 		flagN=false;														//M바튼을 눌렀을 때  N 버튼이 함께 눌리는 것을 방지
					        puss_m();
					        Label pointLabel = (Label) EaglePR.lookup("#ad_Score");
						 	pointLabel.setText(new Integer(score*Bird.levelValue).toString());
						 			 		
						 	Label comboLabel = (Label) EaglePR.lookup("#combo_Label");
					 		comboLabel.setText(new Integer(ComboCount).toString());
					 		break;
				 		}
				     }	
		 	
			});
			     Leftbtn.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
					 
				 	 if (event.getCode() == KeyCode.N && flagM == false) {						//버튼을 떼면, 다음버튼을 누를 수 있게 true 변환, 떼기 전까진 
				 		 flagN = true;															//다른 버튼 동시 입력 불가.
				 		 flagM = true;
				 	 }
				 	 if(event.getCode() == KeyCode.M) {
				 		flagN = true;
				 		 flagM = true;
				 	 }
				     
		 	
			});
			     
			     
			
			//while문이 끝나면 게임이 종료되었습니다. (money class에서 get_money() 메소드 호출
			//
		}
		
		public ArrayList<String> input_right(int i){
			
			
			Platform.runLater(() -> {
				timeLimitBar = (ProgressBar) EaglePR.lookup("#timeLimitBar");
				timeLimitBar.setStyle("-fx-accent: pink;");
				
				if(game_data_left.get(i)== "지렁이.JPG" ){
					game_data_right.add("warring.JPG");
					
					String sj_cnt = Integer.toString(i);
					Rimg = (ImageView) EaglePR.lookup("#raim"+sj_cnt);
					
					Rimg.setImage(new Image(getClass().getResource(game_data_right.get(i)).toString()));
					
				}else if(game_data_left.get(i)== "warring.JPG"){
					game_data_right.add("지렁이.JPG");
					String sj_cnt = Integer.toString(i);
					Rimg = (ImageView) EaglePR.lookup("#raim"+sj_cnt);
					
					Rimg.setImage(new Image(getClass().getResource(game_data_right.get(i)).toString()));
					
				}
				
					
			});
			
			return game_data_right;
			
		}

		public void puss_n(){
			if(game_data_left.get(0) == "지렁이.JPG"){
				score++;
				if(timeCount < timeLimit-250000000L)
				 	timeCount += 850000000L;
				ComboCount += 1;
				if(ComboCount > 100){
					score++;
				}if(ComboCount > 300){
					score++;
				}if(ComboCount > 500){
					score++;
				}
			}else{
				timeCount -= 300000000L;
				ComboCount = 0;
			}			
			
			
			remove();
			if(timeCount>0)  
			create_array();
		}
		
		public void puss_m(){
			if(game_data_right.get(0) == "지렁이.JPG"){		//지렁이를 누른거면 스코어 추가.
				score++;
				if(timeCount < timeLimit-250000000L)		//타임리미트 추가.
				 	timeCount += 850000000L;
				ComboCount += 1;							//콤보 추가
				if(ComboCount > 100){						//콤보 100 2배
					score++;
				}if(ComboCount > 300){						//콤보 300초과 3배
					score++;
				}if(ComboCount > 500){						//콤보 500초과 4배
					score++;
				}
			}else{
				timeCount -= 300000000L;					//경고를 눌렀을때 타임리미트 감소
				
				ComboCount = 0;								//콤보를 0으로 초기화.
			}
			
			remove();
			if(timeCount>0)  
			create_array();
		}
		public void create_array(){				//버튼 누를때마다 remove()실행 뒤  새로은 값을 생성하기 위한 메소드.
			for (int i = 0 ; i <= 7 ; i++){
				String sj_cnt = Integer.toString(i);
				img = (ImageView) EaglePR.lookup("#laim"+sj_cnt );
				img.setImage(new Image(getClass().getResource(game_data_left.get(i)).toString()));
				Rimg = (ImageView) EaglePR.lookup("#raim"+sj_cnt );
				Rimg.setImage(new Image(getClass().getResource(game_data_right.get(i)).toString()));
				//왼쪽 배열을 다시 처음부터 8까지 읽어들이고, img 다시 셋팅
				
			}
			 for (int i = 8 ; i <= 8; i++){		//난수를 발생하여 지렁이와, warring 이미지의 두개 중 하나른 랜덤으로 가져와 
					int rand = (int)(Math.random()*data.length);
					String sj_cnt = Integer.toString(i);
					game_data_left.add(data[rand]);		//왼쪽 컬렉션에 값을 담는다.
					img = (ImageView) EaglePR.lookup("#laim"+sj_cnt );
					img.setImage(new Image(getClass().getResource(data[rand]).toString()));
					
					input_right(i);						//왼쪽 컬렉션의 값을 기준으로 오른쪽 컬렉션의 값이 정해진다.
					
				  }
		}
		public void remove(){							//버튼이 누를때마다 실행, 첫번쨰 인덱스를 삭제시킨다.
			
			game_data_left.remove(0);
			game_data_right.remove(0);
			
			
		}
		
		
		public class exit implements Runnable{
		
		public void run(){
			
	
				while(true){
					if(score < 10000){
					timeCount -= 2L;
					if(timeCount < 100)
						timeCount = 5;
					
					
					}else if(score >= 10000 && score < 30000){
						timeCount -= 4;
					}else if(score >= 30000 && score < 60000){
						timeCount -= 5;
					}
					
					if(timeCount <= 10){
						LoginController savemethod = new LoginController();
						
						Platform.runLater(() -> {	
							try {
								primaryStage.close();
								
								outLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
								
								try {
									EaglePR = outLoader.load();
								} catch (IOException ex) {
								}
								
								Scene scene = new Scene(EaglePR);
								primaryStage.setScene(scene);
								primaryStage.show();
								moneycount.get_money(score);
								Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
								main_pointLabel.setText(new Integer(Bird.levelValue).toString());
								
								Bird_type mytype = new Bird_type();
								Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
								Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
								
								
								savemethod.saveDate();	// DB에 현재 스코어 저장
							} catch (Exception e) {
							}
						});
						threadStart.stop();
						monThread.stop();
						mytimeThread.stop();
						break;
						
					}
				
				}
			
				}	
			}
		
	}

	}


		//랜덤생성.
		//키보드이벤트를통한 이벤트처리


