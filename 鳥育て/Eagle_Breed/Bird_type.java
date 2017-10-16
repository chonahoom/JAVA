package Eagle_Breed;

public class Bird_type extends Bird{
	
	public Bird_type(){
		
		if(levelValue <10){
			Bird_type=	"SPARROW";
		}else if(levelValue <20){
			Bird_type=	"ROBIN";
		}else if(levelValue <30){
			Bird_type=	"TOMILI";
		}else if(levelValue <40){
			Bird_type=	"SWALLOW";
		}else if(levelValue <50){
			Bird_type=	"SISKIN";
		}else if(levelValue <60){
			Bird_type=	"GOLDFINCH";
		}else if(levelValue <70){
			Bird_type=	"BULLFINCH";
		}else if(levelValue <75){
			Bird_type=	"MAGPIE";
		}else if(levelValue <80){
			Bird_type=	"CROW";
		}else if(levelValue <85){
			Bird_type=	"WAXWING";
		}else if(levelValue <90){
			Bird_type=	"BLUEJAY";
		}else if(levelValue <95){
			Bird_type=	"CARDINAL";
		}else if(levelValue <= 100){
			Bird_type=	"Eagle_100";
		}else if(levelValue == 100){
			Bird_type=	"Eagle_legend";
		}
		
	
		
	}
}
