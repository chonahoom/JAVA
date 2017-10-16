package Eagle_Breed;

public class Money extends Bird{
	
	public int get_money(int score){
		money += score * levelValue *10;
		return money;
	}
	//새 객체의 공격력 만큼 증가. 모험 클래스에서 한번 성공 시마다 머니클래스의 메소드 실행.
}
