package Eagle_Breed;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public static String userLogID = "";
	private Stage primaryStage;	
	Parent EaglePR = null;
	TextField userId = null;
	PasswordField userPw = null;
	FXMLLoader outLoader =null;
	ImageView Bird_Img_var;
	
	ResultSet			rs 		= null;
	Connection 			conn 	= null;
	PreparedStatement 	ps 		= null;
	
	String url="jdbc:mysql://localhost:3309/nh";
	String id = "root";
	String password="iammanager";
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	
	@FXML Button btnGameStart;
	
	@SuppressWarnings("resource")
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		
		btnGameStart.setOnAction(event -> {
			
			
			
			primaryStage = (Stage) btnGameStart.getScene().getWindow();
			outLoader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
			try {
				EaglePR = outLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			userId = (TextField) EaglePR.lookup("#UserID");
			userPw = (PasswordField) EaglePR.lookup("#UserPW");
	
			userLogID = userId.getText();
			
			
			Platform.runLater(() -> {
			
				try{
					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(url,id,password);
					
					
					String sqlID_check = "select * from BIRD_INFO where USER_ID = ?";	//ID만체크
					String sqlID_create = "";											//ID없으면 생성
					String sqlPW_check = ""; 											//PW만체크
					
					ps = conn.prepareStatement(sqlID_check);
					ps.setString(1,userId.getText());			
					
			
					rs = ps.executeQuery();
					
					if(rs.next()){
						
						sqlPW_check = "select * from BIRD_INFO where  USER_ID = ? and USER_PASSWD = ?";
						
						ps = conn.prepareStatement(sqlPW_check);
						ps.setString(1,userId.getText());
						ps.setString(2,userPw.getText());
						
						rs = ps.executeQuery();
						if(rs.next()){													//ID/PASSWD다 만족 시키면 저장된 값을 불러옴.
							
							Bird.levelValue = rs.getShort("LEVEL");
							Bird.money 		= rs.getShort("MONEY");
							Bird.Bird_type 	= rs.getString("TYPE");
							System.out.println("가져오기 성공");
						}else{
							System.out.println("비밀번호 오류");
						}
					}else{																//없는 아이디면 생성함.
						
						sqlID_create = "insert into bird_info("+"USER_ID,USER_PASSWD,LEVEL,MONEY,TYPE"+")"+"values(?,?,1,0,'참새')";
						
						ps = conn.prepareStatement(sqlID_create);
						
						ps.setString(1,userId.getText());	
						ps.setString(2,userPw.getText());
						
						
						ps.executeUpdate();
						
						System.out.println("아이디가 생성되었습니다.");
					}
					Bird.user_Id = userLogID;
					System.out.println("데이터베이스와 연결성공");
					
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch(Exception e2){
					e2.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
							System.out.println("데이터베이스와 연결 종료");
						}
					}catch(Exception ex){};
				}				
			
				
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
				Bird_type mytype = new Bird_type();
				Bird_Img_var = (ImageView) EaglePR.lookup("#Bird_Img");
				Bird_Img_var.setImage(new Image(getClass().getResource(Bird.Bird_type+".png").toString()));
				
				Label main_pointLabel = (Label) EaglePR.lookup("#main_levelValueLabel");
				main_pointLabel.setText(new Integer(Bird.levelValue).toString());
				
			});	
			
		}); 
		
		
}
public void saveDate(){
	
		try {
			conn = DriverManager.getConnection(url,id,password);
			
			String save_Query = "update bird_info set LEVEL= ? ,MONEY=?,TYPE=? where USER_ID = ?;";

			ps = conn.prepareStatement(save_Query);
			
			
			
			
			ps.setInt(1,Bird.levelValue);	
			ps.setInt(2,Bird.money);	
			ps.setString(3,Bird.Bird_type);	
			ps.setString(4,Bird.user_Id);	
			
			System.out.println("DB저장 성공!");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
